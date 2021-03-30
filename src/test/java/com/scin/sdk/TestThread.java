package com.scin.sdk;

import com.scin.sdk.thread.KafkaConsumerStore;
import com.scin.sdk.thread.KafkaConsumerThread;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2021-03-05
 */
public class TestThread {

    @Test
    public void test() throws InterruptedException {

        String key = "RLuNYVI1p4I";
        String secret = "pbd1dcmGtlvqOfgW";
        Set<String> topics = Collections.singleton("parse.gps." + key);
        KafkaConsumerThread thread = new KafkaConsumerThread(key, secret, topics,  3, messages->{
            System.out.println("----");
        });
        KafkaConsumerStore.cacheAndStart(key, thread);

        System.out.println("");

        Thread.sleep(10000);

        KafkaConsumerStore.removeAndWakeup(key);

        System.out.println("");
    }
}
