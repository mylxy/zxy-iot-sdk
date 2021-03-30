package com.scin.sdk.bean.share;

import com.scin.sdk.bean.base.WarnBase;
import com.scin.sdk.bean.base.WarnComb;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 电池告警数据
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class BmsWarn extends WarnBase {

    private Integer overCharging;               //Bit_2：充电过流
    private Integer overVoltage;                //Bit_3：电芯过压
    private Integer shortCircuit;               //Bit_5：短路
    private Integer overShortCircuit;           //Bit_6：放电过流标志
    private Integer lowerVoltage;               //Bit_7：电芯欠压标志
    private Integer cellOpen;                   //Bit_8：电芯侦测线开路
    private Integer temperatureOpen;            //Bit_9：温感侦测线开路
    private Integer cellTemperatureHigher;      //Bit_10：电芯温度过高
    private Integer cellTemperatureLower;       //Bit_11：电芯温度过低
    private Integer bmsTemperatureHigher;       //Bit_12：BMS温度过高
    private Integer humidityHigher;             //Bit_16：湿度过高

    private Integer overLimit;                  //Bit_19:充电电流严重超限报警
    private Integer highTemperatureMosProtect;  //Bit_20:MOS高温保护
    private Integer chargingMosFault;           //Bit_21:充电MOS故障
    private Integer releaseMosFault;            //Bit_22:放电MOS故障
    private Integer sensorLapse;                //Bit_23:电流传感器失效
    private Integer aeeFault;                   //Bit_24:AFE故障
    private Integer lowerTemperatureProtect;    //Bit_25:环境低温保护
    private Integer innerFault;                 //Bit_26:内部通信异常

    @Override
    public WarnComb warnInstance() {
        WarnComb warn = new WarnComb();
        warn.add(overCharging, "充电过流");
        warn.add(overVoltage, "电芯过压");
        warn.add(shortCircuit, "短路");
        warn.add(overShortCircuit, "放电过流标志");
        warn.add(lowerVoltage, "电芯欠压标志");
        warn.add(cellOpen, "电芯侦测线开路");
        warn.add(temperatureOpen, "温感侦测线开路");
        warn.add(cellTemperatureHigher, "电芯温度过高");
        warn.add(cellTemperatureLower, "电芯温度过低");
        warn.add(bmsTemperatureHigher, "BMS温度过高");
        warn.add(humidityHigher, "湿度过高");
        warn.add(overLimit, "充电电流严重超限报警");
        warn.add(highTemperatureMosProtect, "MOS高温保护");
        warn.add(chargingMosFault, "充电MOS故障");
        warn.add(releaseMosFault, "放电MOS故障");
        warn.add(sensorLapse, "电流传感器失效");
        warn.add(aeeFault, "AFE故障");
        warn.add(lowerTemperatureProtect, "环境低温保护");
        warn.add(innerFault, "内部通信异常");
        return warn;
    }
}
