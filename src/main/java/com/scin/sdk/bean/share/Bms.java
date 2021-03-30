package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class Bms extends BmsWarn {

    // BMS基础数据
    private String type;               //'9' 48V电压平台, 'a' 表示60V以上电压平台
    private Integer cellBunchNum;       //电芯串数
    private Integer cellSensorNum; //电芯温度传感器数量
    private String softVersion;         //BMS软件版本
    private String hardwareVersion;     //BMS硬件版本
    private Integer protocols;          //软件协议版本

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
    private Integer envTemperature;           //05版本环境温度
    private List<BigDecimal> cellVoltages;    //电芯电压, 根据电芯串数知道有多少电芯电压
    private List<Integer> cellTemperatures;   //电芯温度
    private Integer maxHighTemperature;       //05版本电芯最高温度
    private Integer maxLowerTemperature;      //05版本电芯最低温度
    private Integer cycleCount;               //循环次数
    private BigDecimal leftElectricQuantity;  //剩余电池电量,单位:0.1AH
    private BigDecimal totalElectricQuantity; //总电池电量,单位:0.1AH
    private Integer soc;                      //剩余电量 只有05版本包含 单位:%
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
}
