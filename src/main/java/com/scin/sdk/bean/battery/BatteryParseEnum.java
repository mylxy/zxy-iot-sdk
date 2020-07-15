package com.scin.sdk.bean.battery;

import com.scin.sdk.bean.share.GpsData;
import com.scin.sdk.bean.share.StatusData;
import lombok.Getter;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-06-01
 */
public enum BatteryParseEnum {

    STATUS_DATA(0x00, StatusData.class),
    BMS_DATA(0x12, BmsData.class),
    SENSOR_SHOCK_DATA(0x14, SensorShockData.class),
    BMS_WARN_DATA(0x61, BmsWarnData.class),
    GPS_DATA(0x1F, GpsData.class),
    ;

    @Getter
    private Integer cmdKey;

    @Getter
    private Class<?> clazz;

    BatteryParseEnum(Integer cmdKey, Class<?> clazz) {
        this.cmdKey = cmdKey;
        this.clazz = clazz;
    }
}
