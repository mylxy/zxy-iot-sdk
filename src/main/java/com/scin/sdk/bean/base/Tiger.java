package com.scin.sdk.bean.base;

import com.alibaba.fastjson.JSONObject;
import com.scin.sdk.ConsumerClient;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class Tiger extends Message {

    private final Logger logger = LoggerFactory.getLogger(Tiger.class);

    /**
     * 数据标识
     */
    private Integer cmdKey;

    /**
     * 数据解析后的封装类型
     */
    private Class dataClazz;

    /**
     * 数据
     */
    private JSONObject data;

    /**
     * 取得data
     *
     * @param <T>
     * @return
     */
    public <T> T getData() {
        return (T)data.toJavaObject(dataClazz);
    }

    public <T> T getData(Class clazz) {
        try {
            return (T)data.toJavaObject(clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 解析类型是否一致
     *
     * @param clazz
     * @return
     */
    public boolean is(Class clazz) {
        return clazz == dataClazz;
    }
}
