package com.scin.sdk.bean.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.scin.sdk.bean.share.StatusData;
import com.scin.sdk.enums.SystemStatusEnum;
import com.scin.sdk.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static com.scin.sdk.bean.base.Constant.*;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class Message implements Serializable {

    /**
     * 品类标识
     */
    private String identity;

    /**
     * 型号key
     */
    private String productKey;

    /**
     * 硬件编号
     */
    private String deviceNumber;

    /**
     * 硬件名称
     */
    private String deviceName;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 数据标识
     */
    private Integer cmdKey;

    /**
     * 是否解析过 0原始数据 1解析数据 2加工数据
     */
    private Integer Parsed;

    /**
     * 数据负载
     */
    private String payload;

    /**
     * 数据负载对应着的解析类
     */
    @JSONField(serialize=false , deserialize = false)
    private Class clazz;

    /**
     * 解析类型是否一致
     *
     * @param clazz
     * @return
     */
    public boolean is(Class clazz) {
        return clazz == cmdKeyOfClass();
    }

    /**
     * 取得负载的解析数据类型, 废弃getData, 优化解决序列化相关的问题
     *
     * @param <T>
     * @return
     */
    public <T> T data() {
        Class<T> clazz = cmdKeyOfClass();
        if (clazz == null) {
            throw BusinessException.of(SystemStatusEnum.CMDKEYOFCLASS_NO_CONFIG);
        }
        boolean isStatusData = (
                clazz == StatusData.class
                && StringUtils.equalsAny(payload, "0", "1")
        );
        if (isStatusData) {
            return (T)new StatusData().setStatus(Integer.valueOf(payload));
        }
        return JSONObject.parseObject(payload, clazz);
    }

    /**
     * 取得品类的数据标识对应着的解析类
     */
    public <T> Class<T> cmdKeyOfClass() {
        if (clazz == null) {
            switch (identity) {
                case BATTERY: {
                    clazz = Constant.BATTERY_DATA_CLASS.get(cmdKey);
                    break;
                }
                case CABINET: {
                    clazz =  Constant.CABINET_DATA_CLASS.get(cmdKey);
                    break;
                }
                case VEHICLE: {
                    clazz =  Constant.VEHICLE_DATA_CLASS.get(cmdKey);
                    break;
                }
                default: {
                    clazz =  null;
                }
            }
        }
        return clazz;
    }

    /**
     * 设备的唯一标识
     *
     * @return
     */
    public String clientId() {
        return String.format("%s;%s;%s", deviceNumber, productKey, identity);
    }
}
