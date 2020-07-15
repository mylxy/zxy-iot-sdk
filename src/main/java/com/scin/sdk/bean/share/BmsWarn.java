package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *    电池告警数据
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class BmsWarn implements Serializable {

    private Integer overCharging;          //Bit_2：充电过流
    private Integer overVoltage;           //Bit_3：电芯过压
    private Integer shortCircuit;          //Bit_5：短路
    private Integer overShortCircuit;      //Bit_6：放电过流标志
    private Integer lowerVoltage;          //Bit_7：电芯欠压标志
    private Integer cellOpen;              //Bit_8：电芯侦测线开路
    private Integer temperatureOpen;       //Bit_9：温感侦测线开路
    private Integer cellTemperatureHigher; //Bit_10：电芯温度过高
    private Integer cellTemperatureLower;  //Bit_11：电芯温度过低
    private Integer bmsTemperatureHigher;  //Bit_12：BMS温度过高
    private Integer humidityHigher;        //Bit_16：湿度过高

}
