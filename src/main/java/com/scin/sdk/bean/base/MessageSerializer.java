package com.scin.sdk.bean.base;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class MessageSerializer implements Serializer<Message> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Message data) {
        return JSONObject.toJSONBytes(data);
    }

    @Override
    public void close() {
    }

}
