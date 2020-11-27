package com.scin.sdk;

import com.alibaba.fastjson.JSONObject;
import com.scin.sdk.bean.base.Message;
import com.scin.sdk.bean.battery.BmsData;
import com.scin.sdk.bean.cabinet.RefreshData;
import com.scin.sdk.bean.cabinet.RunStatusData;
import com.scin.sdk.bean.cabinet.WarnData;
import com.scin.sdk.bean.share.GpsData;
import com.scin.sdk.bean.share.StatusData;
import com.scin.sdk.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
@Slf4j
public class TestClient {

    // 原始数据订阅
    @Test
    public void test () {
        String appKey = "WsZeJ9ZBcIN";
        String secret = "L9tJIBktNrwCA1RC";
        ConsumerClient client = new ConsumerClient(appKey, secret);

        Set<String> topics  = new HashSet<>();
//        topics.add("rsp.status.WsZeJ9ZBcIN");
//        topics.add("parse.report.WsZeJ9ZBcIN");
//        topics.add("rsp.gps.WsZeJ9ZBcIN");
        topics.add("rsp.warn.WsZeJ9ZBcIN");
        client.subscribe(topics, 1, messages -> {
            messages.forEach(message -> {
                System.out.println("----------------------------");
                //品类
                System.out.println(message.getIdentity());
                //时间戳
                System.out.println(message.getTimestamp());
                //信息负载
                System.out.println(message.getPayload());
                //设备型号
                System.out.println(message.getProductKey());
                //硬件编号
                System.out.println(message.getDeviceNumber());

                if (message.is(BmsData.class)) {
                    BmsData d = message.getData();
                    System.out.println(d);
                }
            });
        });
    }

    @Test
    public void testParse() {
        String appKey = "RLuNYVI1p4I";
        String secret = "pbd1dcmGtlvqOfgW";
        ConsumerClient client = new ConsumerClient(appKey, secret);
        client.setProperties("client.id", "hello-");

        Set<String> topics = new HashSet<>();
//        topics.add("parse.gps." + appKey);
//        //BMS信息中已包含告警，所以不需要另外再订阅
        topics.add("rsp.report." + appKey);
//        topics.add("rsp.status." + appKey);

        try {
            client.subscribe(topics, 1, messages -> {
                for (Message message : messages) {
                    System.out.println("playload:" + message.getPayload());
                }
            });
        } catch(BusinessException e) {
            log.error(e.getMessage(), e);
            log.warn("数据订阅：SDK抛出异常结束, code={}, err={}, local={}", e.getCode(), e.getMessage(), e.getLocal());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testCabinet() {
        String appKey = "RLuNYVI1p4I";
        String secret = "pbd1dcmGtlvqOfgW";
        ConsumerClient client = new ConsumerClient(appKey, secret);
        client.setProperties("client.id", "cabinet");

        Set<String> topics = new HashSet<>();
        topics.add("rsp.status." + appKey);
        topics.add("parse.gps." + appKey);
        topics.add("parse.report." + appKey);
        topics.add("parse.warn." + appKey);

        try {
            client.subscribe(topics, 1, messages -> {
                for (Message message : messages) {
                    if (message.is(StatusData.class)) {
                        StatusData d = message.getData();
                        log.info("状态数据={}", JSONObject.toJSONString(d));
                        continue;
                    }
                    if (message.is(GpsData.class)) {
                        GpsData d = message.getData();
                        log.info("定位数据={}", JSONObject.toJSONString(d));
                        continue;
                    }
                    if (message.is(RefreshData.class)) {
                        RefreshData d = message.getData();
                        log.info("刷新数据={}", JSONObject.toJSONString(d));
                        continue;
                    }
                    if (message.is(RunStatusData.class)) {
                        RunStatusData d = message.getData();
                        log.info("运行状态数据={}", JSONObject.toJSONString(d));
                        continue;
                    }
                    if (message.is(WarnData.class)) {
                        WarnData d = message.getData();
                        log.info("告警数据={}", JSONObject.toJSONString(d));
                        continue;
                    }
                    log.info("未识别数据={}", JSONObject.toJSONString(message));
                }
            });
        } catch(BusinessException e) {
            log.error(e.getMessage(), e);
            log.warn("数据订阅：SDK抛出异常结束, code={}, err={}, local={}", e.getCode(), e.getMessage(), e.getLocal());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }






}
