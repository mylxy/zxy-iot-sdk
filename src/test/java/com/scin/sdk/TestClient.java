package com.scin.sdk;

import com.alibaba.fastjson.JSONObject;
import com.scin.sdk.bean.base.Cat;
import com.scin.sdk.bean.base.Tiger;
import com.scin.sdk.bean.battery.BmsData;
import com.scin.sdk.bean.battery.BmsWarnData;
import com.scin.sdk.bean.battery.SensorShockData;
import com.scin.sdk.bean.share.GpsData;
import lombok.Data;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.junit.Test;

import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
public class TestClient {

    // 原始数据订阅
    @Test
    public void test () {
        String appKey = "rsPlMMtzDLY";
        String secret = "x8oEW4SQ2i3ZY8HL";
        ConsumerClient client = new ConsumerClient("https://dev-api-iot-company.hzzxxd.com/company/application/subAuth", appKey, secret);

        client.raw("rsp.gps.rsPlMMtzDLY", 1, cats -> {
            cats.forEach(cat -> {
                System.out.println("----------------------------");
                //时间戳
                System.out.println(cat.getTimestamp());
                //信息负载
                System.out.println(cat.getPayload());
                //设备型号
                System.out.println(cat.getProductKey());
                //硬件编号
                System.out.println(cat.getDeviceNumber());
            });
        });
    }

    // 解析数据订阅, 指定需要的数据类型
    @Test
    public void testRipe() {
        String appKey = "rsPlMMtzDLY";
        String secret = "x8oEW4SQ2i3ZY8HL";
        ConsumerClient client = new ConsumerClient("https://dev-api-iot-company.hzzxxd.com/company/application/subAuth", appKey, secret);

        client.ripe(GpsData.class,"parse.gps.rsPlMMtzDLY", 1, tigers -> {
            tigers.forEach(tiger -> {
                System.out.println("----------------------------");
                //时间戳
                System.out.println(tiger.getTimestamp());
                //设备型号
                System.out.println(tiger.getProductKey());
                //硬件编号
                System.out.println(tiger.getDeviceNumber());
                //GPS定位信息
                GpsData data = tiger.getData();
                System.out.println(data.getType());
            });
        });
    }

    // 解析数据订阅，通道内多个数据类型的
    @Test
    public void testRipeMore() {
        String appKey = "rsPlMMtzDLY";
        String secret = "x8oEW4SQ2i3ZY8HL";
        ConsumerClient client = new ConsumerClient("https://dev-api-iot-company.hzzxxd.com/company/application/subAuth", appKey, secret);

        client.ripe("parse.report.rsPlMMtzDLY", 1, tigers -> {
            // TODO new thread save
            for (Tiger tiger : tigers) {
                if (tiger.is(BmsData.class)) {
                    BmsData g = tiger.getData();
                    System.out.println(g.getCellBunchNum());
                    continue;
                }
                if (tiger.is(SensorShockData.class)) {
                    SensorShockData s = tiger.getData();
                    System.out.println(s.getNum());
                    continue;
                }
            }
        });
    }

    @Test
    public void spring() {
        Cat r = new Cat();
        r.setTimestamp(System.currentTimeMillis()/1000);
        r.setPayload("622219493224324324324");
        System.out.println(JSONObject.toJSONString(r));
    }

    @Test
    public void testKafka()  {
        try {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.96.81.62:9004,47.96.81.62:9005,47.96.81.62:9006");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");

            props.put(ConsumerConfig.GROUP_ID_CONFIG, "app.WVzZsMcaosG.1");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


            props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
            props.put(SaslConfigs.SASL_JAAS_CONFIG,
                    "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"WVzZsMcaosG\" password=\"anmL8LYvXbOoYAr5\";");

            KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList("rsp.gps.WVzZsMcaosG"));
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    continue;
                }
                for (ConsumerRecord<String, byte[]> record: records) {
                    System.out.printf("offset = %d, key= %s , value = %s\n", record.offset(), record.key(), new String(record.value()));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("when calling kafka output error." + ex.getMessage());
        }
    }

    @Test
    public void testByte() {
        byte b = 0x17;
        T t = new T();
        t.setT(b);
        System.out.println(JSONObject.toJSONString(t));
        T tt = JSONObject.parseObject(JSONObject.toJSONString(t), T.class);
        System.out.println(tt.getT() == 0x17);
    }

    @Data
    static class T implements Serializable {
        private byte t;
    }

    @Test
    public void str () {
//        Tiger t = new Tiger<BmsWarnData>();
//        t.setCmdKey(0x11);
//        t.setDeviceNumber("11111");
//        t.setIdentity("battery");
//        t.setProductKey("sfsfl2ljl");
//        t.setTimestamp(System.currentTimeMillis()/1000);
//
//        BmsWarnData b = new BmsWarnData();
//        b.setCellOpen(1);
//        b.setHumidityHigher(1);
//        b.setTemperatureOpen(1);
//
//        t.setData(b);
//
//        byte[] str = JSONObject.toJSONBytes(t);
//
//        Type type = new TypeReference<Tiger>(){}.getType();
////        Type type = new TypeReference<Tiger<BmsWarnData>>(){}.getType();
//
////        Tiger<BmsWarnData> parsedA = parse(str, BmsWarnData.class);
////        Tiger<BmsWarnData> parsedA = parse(str, BmsWarnData.class);
//
//        Tiger<Object> parsedA = JSON.parseObject(str, type);
//        parsedA.setData(((JSONObject)parsedA.getData()).toJavaObject(BmsWarnData.class));
//        System.out.println(parsedA);

//        System.out.println(parsedA.getData().getCellOpen());

    }




}
