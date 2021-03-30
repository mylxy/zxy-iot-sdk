package com.scin.sdk.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2021-03-05
 */
@Slf4j
public class KafkaConsumerStore {

    private final static Map<String, KafkaConsumerThread> STORE = new ConcurrentHashMap<>(1024);

    public static boolean exist(String key) {
        return STORE.containsKey(key);
    }

    /**
     * 缓存消费线程并启动
     *
     * @param key
     * @param thread
     */
    public static void cacheAndStart(String key, KafkaConsumerThread thread) {
        STORE.put(key, thread);
        thread.start();
        log.info("KafkaConsumerThread的消费者数量={}", STORE.size());
    }
    /**
     * 删除缓存并中断线程的执行
     *
     * @param key
     */
    public static void removeAndWakeup(String key) {
        KafkaConsumerThread thread = STORE.get(key);
        if (thread != null) {
            thread.wakeup();
            STORE.remove(key);
        }
        log.info("KafkaConsumerThread的消费者数量={}", STORE.size());
    }

    /**
     * 缓存的消费线程数据
     *
     * @return
     */
    public static int size() {
        return STORE.size();
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        STORE.remove(key);
        log.info("KafkaConsumerThread的消费者数量={}", STORE.size());
    }

    /**
     * 清除过期的失效的应用数据订阅
     *
     * @param keys 如果缓存的key 在keys未找到，清除
     */
    public static void clearExpiredKey(List<String> keys) {
        log.info("清除过期的失效的应用数据订阅开始==>");
        int i = 0;
        Iterator<Map.Entry<String, KafkaConsumerThread>> itr = STORE.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<String, KafkaConsumerThread> entry = itr.next();
            boolean expired = true;
            for (String key : keys) {
                if (key.equals(entry.getKey())) {
                    expired = false;
                    break;
                }
            }
            if (expired) {
                entry.getValue().wakeup();
                itr.remove();
                i++;
                log.info("清除过期的失效的应用数据==> key={}", entry.getKey());
            }
        }
        log.info("清除过期的失效的应用数据订阅结束==> 数量={}", i);
    }

}
