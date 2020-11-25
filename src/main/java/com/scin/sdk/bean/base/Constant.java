package com.scin.sdk.bean.base;

import com.scin.sdk.bean.battery.BmsData;
import com.scin.sdk.bean.battery.BmsWarnData;
import com.scin.sdk.bean.battery.SensorShockData;
import com.scin.sdk.bean.share.GpsData;
import com.scin.sdk.bean.share.StatusData;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     数据标识与解析类型的对应关系
 * </p>
 *
 * @author seven
 * @since 2020-05-29
 */
public class Constant {

    /**
     * 品类标识
     */
    public final static String BATTERY = "battery";
    public final static String VEHICLE = "vehicle";
    public final static String CABINET = "cabinet";

    /**
     * 电池数据解析MAP
     */
    public final static Map<Integer, Class> BATTERY_DATA_CLASS = new HashMap<>();

    /**
     * 车辆中控数据解析MAP
     */
    public final static Map<Integer, Class> VEHICLE_DATA_CLASS = new HashMap<>();

    /**
     * 换电柜中控数据解析MAP
     */
    public final static Map<Integer, Class> CABINET_DATA_CLASS = new HashMap<>();

    static {

        // 电池数据标识与解析类的对应关系
        BATTERY_DATA_CLASS.put(0x00, StatusData.class);
        BATTERY_DATA_CLASS.put(0x1F, GpsData.class);
        BATTERY_DATA_CLASS.put(0x12, BmsData.class);
        BATTERY_DATA_CLASS.put(0x14, SensorShockData.class);
        BATTERY_DATA_CLASS.put(0x61, BmsWarnData.class);

        // TODO 车辆中控数据标识与解析类的对应关系
        // TODO 换电柜数据标识与解析类的对应关系
    }


}
