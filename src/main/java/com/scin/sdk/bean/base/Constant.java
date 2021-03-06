package com.scin.sdk.bean.base;

import com.scin.sdk.bean.battery.BmsData;
import com.scin.sdk.bean.battery.BmsWarnData;
import com.scin.sdk.bean.battery.ModelIOData;
import com.scin.sdk.bean.battery.SensorShockData;
import com.scin.sdk.bean.cabinet.*;
import com.scin.sdk.bean.share.*;
import com.scin.sdk.bean.vehicle.AccStatusData;
import com.scin.sdk.bean.vehicle.ControlFaultData;
import com.scin.sdk.bean.vehicle.NatureData;

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
        BATTERY_DATA_CLASS.put(0x00, StatusData.class);//内部编码标识, 状态数据
        BATTERY_DATA_CLASS.put(0x01, SignalData.class);//内部编码标识, 信号强度(不对外开放)
        BATTERY_DATA_CLASS.put(0x02, GpsSimpleData.class);//内部编码标识, 定位基本信息数据标识，只包含经纬度(不对外开放)
        BATTERY_DATA_CLASS.put(0x03, GpsComplexData.class);//内部编码标识, 定位完整信息数据标识，除经纬度外，还包含信号强度，省市区等信息(不对外开放)
        BATTERY_DATA_CLASS.put(0x04, DeviceLifeData.class);//内部编码标识, 设备生命周期数据，增加/修改/删除(不对外开放)

        BATTERY_DATA_CLASS.put(0x1F, GpsData.class);//电池MQTT协议标识
        BATTERY_DATA_CLASS.put(0x12, BmsData.class);//电池MQTT协议标识
        BATTERY_DATA_CLASS.put(0x14, SensorShockData.class);//电池MQTT协议标识
        BATTERY_DATA_CLASS.put(0x17, ModelIOData.class);//电池MQTT协议标识
        BATTERY_DATA_CLASS.put(0x61, BmsWarnData.class);//电池MQTT协议标识

        //换电柜数据标识与解析类的对应关系
        CABINET_DATA_CLASS.put(0x00, StatusData.class);//内部编码标识, 状态数据
        CABINET_DATA_CLASS.put(0x01, SignalData.class);//内部编码标识, 信号强度(不对外开放)
        CABINET_DATA_CLASS.put(0x02, GpsSimpleData.class);//内部编码标识, 定位基本信息数据标识，只包含经纬度(不对外开放)
        CABINET_DATA_CLASS.put(0x03, GpsComplexData.class);//内部编码标识, 定位完整信息数据标识，除经纬度外，还包含信号强度，省市区等信息(不对外开放)
        CABINET_DATA_CLASS.put(0x04, DeviceLifeData.class);//内部编码标识, 设备生命周期数据，增加/修改/删除(不对外开放)

        CABINET_DATA_CLASS.put(0x1F, GpsData.class);//换电柜MQTT协议标识
        CABINET_DATA_CLASS.put(0x13, RefreshData.class);//换电柜MQTT协议标识
        CABINET_DATA_CLASS.put(0x14, RunStatusData.class);//换电柜MQTT协议标识
        CABINET_DATA_CLASS.put(0x15, BaseConfigInfoData.class);//上报基础配置信息(0x15)
        CABINET_DATA_CLASS.put(0x61, CabinetWarnData.class);//换电柜MQTT协议标识
        CABINET_DATA_CLASS.put(0x62, BoxWarnData.class);//换电柜MQTT协议标识

        //车辆中控数据标识与解析类的对应关系
        VEHICLE_DATA_CLASS.put(0x00, StatusData.class);//内部编码标识, 状态数据
        VEHICLE_DATA_CLASS.put(0x01, SignalData.class);//内部编码标识, 信号强度(不对外开放)
        VEHICLE_DATA_CLASS.put(0x02, GpsSimpleData.class);//内部编码标识, 定位基本信息数据标识，只包含经纬度(不对外开放)
        VEHICLE_DATA_CLASS.put(0x03, GpsComplexData.class);//内部编码标识, 定位完整信息数据标识，除经纬度外，还包含信号强度，省市区等信息(不对外开放)
        VEHICLE_DATA_CLASS.put(0x04, DeviceLifeData.class);//内部编码标识, 设备生命周期数据，增加/修改/删除(不对外开放)

        VEHICLE_DATA_CLASS.put(0x1F, GpsData.class);//车辆MQTT协议标识
        VEHICLE_DATA_CLASS.put(0x12, NatureData.class);//车辆MQTT协议标识
        VEHICLE_DATA_CLASS.put(0x14, AccStatusData.class);//车辆MQTT协议标识 acc状态
        VEHICLE_DATA_CLASS.put(0x21, ControlFaultData.class);//车辆MQTT协议标识 中控故障
    }


}
