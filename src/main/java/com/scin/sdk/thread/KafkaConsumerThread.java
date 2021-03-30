package com.scin.sdk.thread;

import com.scin.sdk.ConsumerClient;
import com.scin.sdk.bean.base.Message;
import com.scin.sdk.enums.IotHubSdkEnvEnum;
import com.scin.sdk.enums.SystemStatusEnum;
import com.scin.sdk.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>
 *     kafka消费线程
 * </p>
 *
 * @author seven
 * @since 2021-03-05
 */
@Slf4j
public class KafkaConsumerThread extends Thread {

    /**
     * kafka消费者
     */
    private ConsumerClient client;

    //应用key
    private String key;
    //应用secret
    private String secret;
    //订阅的topics
    private Set<String> topics;
    //数据订阅到操作
    private Consumer<List<Message>> action;
    //重新连接次数
    private int retryTime;

    public KafkaConsumerThread(String key, String secret, Set<String> topics, int retryTime, Consumer<List<Message>> pollAction) {
        new KafkaConsumerThread(IotHubSdkEnvEnum.prod, key, secret, topics, retryTime, pollAction);
    }

    public KafkaConsumerThread(IotHubSdkEnvEnum env, String key, String secret, Set<String> topics, int retryTime, Consumer<List<Message>> pollAction) {
        if (env == null) {
            throw BusinessException.of(SystemStatusEnum.SUBSCRIBE_ENV_NULL);
        }
        if (CollectionUtils.isEmpty(topics)) {
            throw BusinessException.of(SystemStatusEnum.SUBSCRIBE_TOPIC_EMPTY);
        }
        if (retryTime < 1 || retryTime > 5) {
            throw BusinessException.of(SystemStatusEnum.SUBSCRIBE_RETRY_ERROR);
        }
        if (pollAction == null) {
            throw BusinessException.of(SystemStatusEnum.SUBSCRIBE_ACTION_NULL);
        }
        this.key = key;
        this.secret = secret;
        this.topics = topics;
        this.action = pollAction;
        this.retryTime = retryTime;
        this.client = new ConsumerClient(env.getUrl(), key, secret);
    }

    @Override
    public void run() {
        subscribe(client, topics, action, retryTime);
    }

    private void subscribe(ConsumerClient client, Set<String> topics, Consumer<List<Message>> action, int time) {
        if (time < 0) {
            KafkaConsumerStore.remove(key);
            log.warn("数据订阅：订阅中断, 已尝试{}次订阅皆失败, 退出, key={}, secret={}", retryTime - time, key, secret);
            return;
        }
        try {
            client.subscribe(topics, 1, action);
        } catch(BusinessException e) {
            KafkaConsumerStore.remove(key);
            log.error(e.getMessage(), e);
            log.warn("数据订阅：SDK抛出异常结束, code={}, err={}, local={}, key={}, secret={}", e.getCode(), e.getMessage(), e.getLocal(), key, secret);
        } catch (Exception e) {
            time--;
            log.error(e.getMessage(), e);
            log.warn("数据订阅：订阅中断, 等待500ms, 重复尝试第{}次订阅, key={}, secret={}, err={}", retryTime - time, key, secret, e.getMessage());
            try {
                Thread.sleep(500);
                subscribe(client, topics, action, ++time);
            } catch (InterruptedException ex) {
                KafkaConsumerStore.remove(key);
                log.info("数据订阅: 线程终止执行(InterruptedException), 订阅中断, key={}, secret={}", key, secret);
            }
        }
    }


    /**
     * kafka消费端退出订阅
     */
    public void wakeup() {
        if (client != null && client.getConsumer() != null) {
            client.getConsumer().wakeup();
        }
    }
}
