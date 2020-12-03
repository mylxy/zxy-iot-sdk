package com.scin.sdk.bean.base;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class MessageDeserializer implements Deserializer<Message> {
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Message deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        } else {
            return JSONObject.parseObject(data, Message.class);
        }
    }

    @Override
    public void close() {
    }
}
