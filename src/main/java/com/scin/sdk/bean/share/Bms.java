package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * BMS数据信息
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class Bms implements Serializable {

    // BMS基础数据
    private String type;               //'9' 48V电压平台, 'a' 表示60V以上电压平台
    private Integer cellBunchNum;       //电芯串数
    private Integer cellSensorNum; //电芯温度传感器数量
    private String softVersion;         //BMS软件版本
    private String hardwareVersion;     //BMS硬件版本
    private Integer protocols;          //软件协议版本

    // BMS告警数据
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

    // BMS状态数据
    private Integer charging;              //Bit_0：充电
    private Integer fullyCharged;          //Bit_1：充饱
    private Integer discharging;           //Bit_4：放电
    private Integer leaseStatus;           //Bit_13：启用租赁
    private Integer disableCharging;       //Bit_14：禁止充电（来自工控或云端）
    private Integer disableDischarging;    //Bit_15：禁止放电（来自工控或云端）
    private Integer chargingTubeStatus;    //Bit_17：当前充电管状态：0：关，1：开
    private Integer dischargingTubeStatus; //Bit_18：当前放电管状态：0：关，1：开

    // BMS其它数据
    private BigDecimal currentElectricity;    //当前电流
    private Integer temperature1;             //BMS温度1
    private Integer temperature2;             //BMS温度2
    private List<BigDecimal> cellVoltages;    //电芯电压, 根据电芯串数知道有多少电芯电压
    private List<Integer> cellTemperatures;   //电芯温度
    private Integer cycleCount;               //循环次数
    private BigDecimal leftElectricQuantity;  //剩余电池电量,单位:0.1AH
    private BigDecimal totalElectricQuantity; //总电池电量,单位:0.1AH
    private Integer soh;                      //电池健康状态 只有03版本包含
    private List<Integer> humiditys;                 //湿度
    private List<Integer> waterStages;               //水位

    public Integer realStatus() {
        if (this.charging != null && this.charging == 1) {
            return 1;
        }
        if (this.discharging != null && this.discharging == 1) {
            return 2;
        }
        return 0;
    }

    public Integer faultStatus() {
        if (this.overCharging != null && this.overCharging != 0) {
            return 1;
        }
        if (this.overVoltage != null && this.overVoltage != 0) {
            return 1;
        }
        if (this.shortCircuit != null && this.shortCircuit != 0) {
            return 1;
        }
        if (this.overShortCircuit != null && this.overShortCircuit != 0) {
            return 1;
        }
        if (this.lowerVoltage != null && this.lowerVoltage != 0) {
            return 1;
        }
        if (this.cellOpen != null && this.cellOpen != 0) {
            return 1;
        }
        if (this.temperatureOpen != null && this.temperatureOpen != 0) {
            return 1;
        }
        if (this.cellTemperatureHigher != null && this.cellTemperatureHigher != 0) {
            return 1;
        }
        if (this.cellTemperatureLower != null && this.cellTemperatureLower != 0) {
            return 1;
        }
        if (this.bmsTemperatureHigher != null && this.bmsTemperatureHigher != 0) {
            return 1;
        }
        if (this.humidityHigher != null && this.humidityHigher != 0) {
            return 1;
        }
        return 0;
    }

}
