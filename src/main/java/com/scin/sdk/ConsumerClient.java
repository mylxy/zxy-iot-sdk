package com.scin.sdk;

import com.alibaba.fastjson.JSONObject;
import com.scin.sdk.api.App;
import com.scin.sdk.api.Authority;
import com.scin.sdk.bean.base.Message;
import com.scin.sdk.enums.SystemStatusEnum;
import com.scin.sdk.exception.BusinessException;
import com.scin.sdk.utils.HttpUtil;
import lombok.Getter;
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
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;

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
     * 数据订阅端
     */
    @Getter
    private KafkaConsumer<String, byte[]> consumer;

    /**
     * 鉴权地址
     */
    private String url = "https://api-iot-company.hzzxxd.com/company/application/subAuth";

    public ConsumerClient(String key, String secret) {
        this.authority = HttpUtil.sign(url, new App(key, secret));
        this.kafkaConfig = initKafkaConfig();
    }

    public ConsumerClient(String url, String key, String secret) {
        this.authority = HttpUtil.sign(url, new App(key, secret));
        this.kafkaConfig = initKafkaConfig();
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
     * 数据订阅
     *
     * @param topic
     * @param action
     */
    public void subscribe(String topic, int groupNum, Consumer<List<Message>> action) {
        subscribe(Collections.singleton(topic), groupNum, action);
    }
    public void subscribe(Set<String> topics, int groupNum, Consumer<List<Message>> action) {
        listen(topics, groupNum, action);
    }

    /**
     * 消费监听
     *
     * @param topics
     * @param groupNum
     * @param action
     * @param <T>
     */
    private <T> void listen(Set<String> topics, int groupNum, Consumer<List<Message>> action) {
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
        try {
            logger.info("开始建立数据订阅, threadId={}", Thread.currentThread().getId());
            consumer = new KafkaConsumer<>(kafkaConfig);
            consumer.subscribe(topics);
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    continue;
                }
                List<Message> messages = new ArrayList<>(16);
                for (ConsumerRecord<String, byte[]> record : records) {
                    Message m = parse(record);
                    if (m != null) {
                        messages.add(m);
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
        } catch (WakeupException e) {
            logger.info("订阅中断: Wakeup主动取消订阅, threadId={}", Thread.currentThread().getId());
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
     * @return
     */
    private Message parse(ConsumerRecord<String,byte[]> record) {
        try {
            return JSONObject.parseObject(record.value(), Message.class);
        } catch (Exception e) {
            logger.error("数据序列化失败:" + e.getMessage(), e);
            return null;
        }
    }
}
