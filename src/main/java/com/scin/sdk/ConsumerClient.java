package com.scin.sdk;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.scin.sdk.api.AppUser;
import com.scin.sdk.api.Authority;
import com.scin.sdk.bean.base.Cat;
import com.scin.sdk.bean.base.Constant;
import com.scin.sdk.bean.base.Tiger;
import com.scin.sdk.enums.SystemStatusEnum;
import com.scin.sdk.exception.BusinessException;
import com.scin.sdk.utils.HttpUtil;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.errors.GroupAuthorizationException;
import org.apache.kafka.common.errors.SaslAuthenticationException;
import org.apache.kafka.common.errors.TopicAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-08
 */
public class ConsumerClient {

    private final Logger logger = LoggerFactory.getLogger(ConsumerClient.class);

    /**
     * kafka的连接信息
     */
    @Setter
    private Properties kafkaConfig;

    /**
     * kafka的权限信息
     */
    private Authority authority;

    /**
     * 鉴权地址
     */
    private String url = "https://api-iot-company.hzzxxd.com/company/application/subAuth";

    /**
     * 数据标识与封装的解析类对应关系
     */
    public Map<Integer, Class> parseClassMap;

    public ConsumerClient(String key, String secret) {
        this.authority = HttpUtil.sign(url, new AppUser(key, secret));
        this.parseClassMap = initParseClassMap();
        this.kafkaConfig = initKafkaConfig();
    }

    public ConsumerClient(String url, String key, String secret) {
        this.authority = HttpUtil.sign(url, new AppUser(key, secret));
        this.parseClassMap = initParseClassMap();
        this.kafkaConfig = initKafkaConfig();
    }

    private Map<Integer, Class> initParseClassMap() {
        switch (authority.getIdentity()){
            case "battery":
                return Constant.BATTERY_DATA_CLASS;
            case "vehicle":
                return Constant.VEHICLE_DATA_CLASS;
            case "cabinet":
                return Constant.CABINET_DATA_CLASS;
            default:
                throw BusinessException.of(SystemStatusEnum.IDENTITY_NOT_SUPPORT);
        }
    }

    /**
     * 重新设置kafka属性信息
     *
     * @param key
     * @param value
     */
    public void setProperties(String key, String value) {
        this.kafkaConfig.setProperty(key, value);
    }

    /**
     * kafka配置信息初始化
     *
     * @return
     */
    private Properties initKafkaConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, authority.getServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 12);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""+authority.getUsername()+"\" password=\""+authority.getPassword()+"\";");
        return props;
    }

    /**
     * 解析原始数据
     *
     * @param topic
     * @param action
     */
    public void raw(String topic, int groupNum, Consumer<List<Cat>> action) {
        raw(Collections.singleton(topic), groupNum, action);
    }
    public void raw(Set<String> topics, int groupNum, Consumer<List<Cat>> action) {
        Type type = new TypeReference<Cat>(){}.getType();
        listen(topics, groupNum, type, action);
    }
    /**
     * 订阅解析数据，自己订阅数据类型
     *
     * @param topic 主题
     * @param groupNum 消费组
     * @param action 客户消费动作
     * @param clazz 返回的数据类型
     * @param <T>
     */
    public <T> void ripe(Class<T> clazz, String topic, int groupNum, Consumer<List<Tiger>> action) {
        ripe(clazz, Collections.singleton(topic), groupNum, action);
    }
    public void ripe(String topic, int groupNum, Consumer<List<Tiger>> action) {
        ripe(Collections.singleton(topic), groupNum, action);
    }
    public void ripe(Set<String> topics, int groupNum, Consumer<List<Tiger>> action) {
        ripe(null, topics, groupNum, action);
    }
    public <T> void ripe(Class<T> clazz, Set<String> topics, int groupNum, Consumer<List<Tiger>> action) {
        Consumer<List<Tiger>> messages = findConsumerOfCmdKey(clazz, action);
        Type type = new TypeReference<Tiger>(){}.getType();
        listen(topics, groupNum, type, messages);
    }

    /**
     * 消费信息
     *
     * @param clazz
     * @param action
     * @return
     */
    private Consumer<List<Tiger>> findConsumerOfCmdKey(Class clazz, Consumer<List<Tiger>> action) {
        Integer cmdKey = findCmdKey(clazz);

        if (cmdKey == null) {
            return tigers -> {
                List<Tiger> messages = tigers.stream().filter(t -> parseClassMap.containsKey(t.getCmdKey())).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(messages)) {
                    messages.forEach(m -> m.setDataClazz(parseClassMap.get(m.getCmdKey())));
                    action.accept(tigers);
                }
            };
        }

        return tigers -> {
            List<Tiger> messages = tigers.stream()
                    .filter(t -> t.getCmdKey().equals(cmdKey))
                    .filter(t -> parseClassMap.containsKey(t.getCmdKey())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(messages)) {
                messages.forEach(m -> m.setDataClazz(clazz));
                action.accept(tigers);
            }
        };
    }

    /**
     * 找出clazz对应的数据标识，解析类与数据标识是一一对应的
     * @param clazz
     * @return
     */
    private Integer findCmdKey(Class clazz) {
        Integer cmdKey = null;
        if (clazz == null) {
            return cmdKey;
        }
        for (Map.Entry<Integer, Class> entry : parseClassMap.entrySet()) {
            if (entry.getValue() == clazz) {
                cmdKey = entry.getKey();
                break;
            }
        }
        if (cmdKey == null) {
            throw BusinessException.of(SystemStatusEnum.PARSE_CLASS_SUPPORT);
        }
        return cmdKey;
    }

    /**
     * 消费监听
     *
     * @param topics
     * @param groupNum
     * @param type
     * @param action
     * @param <T>
     */
    private <T> void listen(Set<String> topics, int groupNum,  Type type, Consumer<List<T>> action) {
        // 检验topic是否为空
        if (topics == null || topics.isEmpty()) {
            throw BusinessException.of(SystemStatusEnum.SUBSCRIBE_TOPIC_EMPTY);
        }
        // 检验topic是否在已授权的topic范围内
        topics.stream().forEach(topic -> {
            if (!authority.getTopics().contains(topic)) {
                throw BusinessException.of(SystemStatusEnum.APP_TOPIC_NOT_AUTHORIZED);
            }
        });
        // 检验group是否在已授权的组范围内
        if (groupNum < 1 && groupNum > authority.getGroupNum()) {
            throw BusinessException.of(SystemStatusEnum.APP_GROUP_NOT_AUTHORIZED);
        }
        // 数据消费
        kafkaConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "app." + authority.getUsername()+ "." + groupNum);
        KafkaConsumer<String, byte[]> consumer = null;
        try {
            consumer = new KafkaConsumer<>(kafkaConfig);
            consumer.subscribe(topics);
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    continue;
                }
                List<T> messages = new ArrayList<>(16);
                for (ConsumerRecord<String, byte[]> record : records) {
                    T t = parse(record, type);
                    if (t != null) {
                        messages.add(t);
                    }
                }
                if (CollectionUtils.isNotEmpty(messages)) {
                    action.accept(messages);
                }
            }
        } catch (SaslAuthenticationException e) {
            throw BusinessException.of(SystemStatusEnum.KAFKA_USER_NOT_AUTHORIZED);
        } catch (TopicAuthorizationException e) {
            throw BusinessException.of(SystemStatusEnum.KAFKA_TOPIC_NOT_AUTHORIZED);
        } catch (GroupAuthorizationException e) {
            throw BusinessException.of(SystemStatusEnum.KAFKA_GROUP_NOT_AUTHORIZED);
        } catch (Exception e) {
            logger.error("订阅异常:" + e.getMessage(), e);
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }

    /**
     * 数据解析
     *
     * @param record 数据
     * @param type 解析数据类型
     * @param <T> Cat、Tiger
     * @return
     */
    private <T> T parse(ConsumerRecord<String,byte[]> record, Type type) {
        try {
            T t = JSONObject.parseObject(record.value(), type);
            return t;
        } catch (Exception e) {
            logger.error("数据序列化失败:" + e.getMessage(), e);
            return null;
        }
    }
}
