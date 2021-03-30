package com.scin.sdk.bean.vehicle;

import com.scin.sdk.bean.base.WarnBase;
import com.scin.sdk.bean.base.WarnComb;
import lombok.Data;

/**
 * <p>
 *     中控信息故障
 * </p>
 *
 * @author seven
 * @since 2021-01-28
 */
@Data
public class ControlFaultData extends WarnBase {
    private Integer gsensorFault        ;//uint8 `zson:"Gsensor故障" json:"gsensorFault"`     //Gsensor故障
    private Integer gpsFault            ;//uint8 `zson:"Gps故障" json:"gpsFault"`             //Gps故障
    private Integer voiceFault          ;//uint8 `zson:"语音芯片故障" json:"voiceFault"`          //语音芯片故障
    private Integer u485Fault           ;//uint8 `zson:"485串口故障" json:"u485Fault"`          //485串口故障
    private Integer m4GFault            ;//uint8 `zson:"4G模块故障" json:"m4GFault"`            //4G模块故障
    private Integer bleFault            ;//uint8 `zson:"ble故障" json:"bleFault"`             //ble故障
    private Integer simFault            ;//uint8 `zson:"sim卡故障" json:"simFault"`            //sim卡故障
    private Integer u433Fault           ;//uint8 `zson:"433故障" json:"u433Fault"`            //433故障
    private Integer canFault            ;//uint8 `zson:"can通信故障" json:"canFault"`           //can通信故障
    private Integer accFault            ;//uint8 `zson:"acc故障" json:"accFault"`             //Acc 故障
    private Integer lockErr             ;//uint8 `zson:"锁异常" json:"lockErr"`                //锁异常
    private Integer dischargeErr        ;//uint8 `zson:"车辆放电异常" json:"dischargeErr"`        //车辆放电异常
    private Integer frontTirePressFault ;//uint8 `zson:"前胎胎压故障" json:"frontTirePressFault"` //前胎胎压故障
    private Integer backTirePressFault  ;//uint8 `zson:"后胎胎压故障" json:"backTirePressFault"`  //后胎胎压故障
    private Integer waterTip            ;//uint8 `zson:"水位淹没提醒" json:"waterTip"`            //水位淹没提醒
    private Integer vehicleAbnormal     ;//uint8 `zson:"车辆异动" json:"vehicleAbnormal"`       //车辆异动
    private Integer overSpeed           ;//uint8 `zson:"超速" json:"overSpeed"`                 //超速
    private Integer lowPowerErr         ;//uint8 `zson:"低电量异常" json:"lowPowerErr"`          //低电量异常
    private Integer powerOffWarn        ;//uint8 `zson:"断电告警" json:"powerOffWarn"`          //断电告警
    private Integer driveUpRapidStatus 	;//uint8 `zson:"急加速" json:"driveUpRapidStatus"`      //车辆行驶状态1字节，bit0急加速
    private Integer driveUpRetardStatus ;//uint8 `zson:"急减速" json:"driveUpRetardStatus"`     //bit1急减速
    private Integer driveDumpStatus 	;//uint8 `zson:"倾倒" json:"driveDumpStatus"`           //bit2倾倒
    private Integer driveUpTurnStatus   ;//uint8 `zson:"急转弯" json:"driveUpTurnStatus"`       //bit3急转弯

    @Override
    public WarnComb warnInstance() {
        WarnComb warn = new WarnComb();
        warn.add(gsensorFault, "Gsensor故障");
        warn.add(gpsFault, "Gps故障");
        warn.add(voiceFault, "语音芯片故障");
        warn.add(u485Fault, "485串口故障");
        warn.add(m4GFault, "4G模块故障");
        warn.add(bleFault, "ble故障");
        warn.add(simFault, "sim卡故障");
        warn.add(u433Fault, "433故障");
        warn.add(canFault, "can通信故障");
        warn.add(accFault, "acc故障");
        warn.add(lockErr, "锁异常");
        warn.add(dischargeErr, "车辆放电异常");
        warn.add(frontTirePressFault, "前胎胎压故障");
        warn.add(backTirePressFault, "后胎胎压故障");
        warn.add(waterTip, "水位淹没提醒");
        warn.add(vehicleAbnormal, "车辆异动");
        warn.add(overSpeed, "超速");
        warn.add(lowPowerErr, "低电量异常");
        warn.add(powerOffWarn, "断电告警");
        warn.add(driveUpRapidStatus, "急加速");
        warn.add(driveUpRetardStatus, "急减速");
        warn.add(driveDumpStatus, "车辆倾倒");
        warn.add(driveUpTurnStatus, "急转弯");
        return warn;
    }
}
