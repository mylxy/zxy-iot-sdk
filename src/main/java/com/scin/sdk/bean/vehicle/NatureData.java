package com.scin.sdk.bean.vehicle;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     整车固定信息
 * </p>
 *
 * @author seven
 * @since 2021-01-27
 */
@Data
public class NatureData implements Serializable {
    private String imei                       ;// string `zson:"IMEI" json:"imei"`                                    //车辆 imei
    private String vendorId                   ;// string `zson:"厂商" json:"vendorId"`                                  //中控厂商(4 字节，zxxd)
    private String iccid                      ;// string `zson:"ICCID" json:"iccid"`                                   //Iccid(20 字节
    private String imsi                       ;// string `zson:"IMSI" json:"imsi"`                                        //Imsi(15 字节)
    private String vehicleId                  ;// string `zson:"车辆ID" json:"vehicleId"`                               //车辆 id(6 字节)(与电池关联做校验用)
    private String motorControlId             ;// string `zson:"电机控制器ID" json:"motorControlId"`                       //电机控制器id
    private Integer motorControlSoftVersion    ;// uint8  `zson:"电机控制器软件版本" json:"motorControlSoftVersion"`     //电机控制器软件版本
    private Integer motorControlHardwareVersion;// uint8  `zson:"电机控制器硬件版本" json:"motorControlHardwareVersion"` //电机控制器硬件版本
    private Integer motorRatedPower            ;// uint16 `zson:"电机额定功率" json:"motorRatedPower"`                   //电机额定功率
    private Integer motorPerimeter             ;// uint8  `zson:"电机轮毂周长" json:"motorPerimeter"`                    //电机轮毂周长
    private Integer motorPairs                 ;// uint8  `zson:"电机电机极对数" json:"motorPairs"`                       //电机电机极对数
    private Integer motorMove                  ;// uint8  `zson:"电机相移量" json:"motorMove"`                          //电机相移量
    private String meterId                    ;// string `zson:"仪表盘ID" json:"meterId"`                                //仪表盘id
    private Integer meterSoftVersion           ;// uint8  `zson:"仪表盘软件版本" json:"meterSoftVersion"`             //仪表盘软件版本
    private Integer meterHardwareVersion       ;// uint8  `zson:"仪表盘硬件版本" json:"meterHardwareVersion"`         //仪表盘硬件版本
    private Integer battery1SoftVersion        ;// uint8  `zson:"电池1软件版本" json:"battery1SoftVersion"`      //电池1软件版本（1字节）
    private Integer battery1HardwareVersion    ;// uint8  `zson:"电池1硬件版本" json:"battery1HardwareVersion"` //电池1硬件版本（1字节）
    private Integer battery1Capacity           ;// uint16 `zson:"电池1最大容量(A/h)" json:"battery1Capacity"`   //电池1最大容量（2字节，单位0.1A/h）
    private Integer battery2SoftVersion        ;// uint8  `zson:"电池2软件版本" json:"battery2SoftVersion"`     //电池2软件版本（1字节）
    private Integer battery2HardwareVersion    ;// uint8  `zson:"电池2硬件版本" json:"battery2HardwareVersion"` //电池2硬件版本（1字节）
    private Integer battery2Capacity           ;// uint16 `zson:"电池2最大容量(A/h)" json:"battery2Capacity"`   //电池2最大容量（2字节，单位0.1A/h）
    private String doublePlateId              ;// string `zson:"双电并联板ID" json:"doublePlateId"`                //双电并联板id（6字节）
    private Integer doublePlateSoftVersion     ;// uint8  `zson:"双电并联板软件版本" json:"doublePlateSoftVersion"`     //双电并联板软件版本（1字节）
    private Integer doublePlateHardwareVersion ;// uint8  `zson:"双电并联板硬件版本" json:"doublePlateHardwareVersion"` //双电并联板硬件版本（1字节）
    private Integer model                      ;// uint8  `zson:"车辆型号" json:"model"`                 //车辆型号（1字节）
    private Integer version                    ;// uint8  `zson:"车架版本" json:"version"`               //车架版本（1字节）
    private Integer ratedPower                 ;// uint16 `zson:"额定功率(w)" json:"ratedPower"`         //额定功率（2字节，单位w）
    private Integer ratedCurrent               ;// uint16 `zson:"车辆额定电流(A)" json:"ratedCurrent"`   //车辆额定电流（2字节，单位0.1A）
    private Integer ratedVoltage               ;// uint16 `zson:"车辆额定电压(V)" json:"ratedVoltage"`   //车辆额定电压（2字节，单位0.1v）
    private Integer maxRunSpeed                ;// uint16 `zson:"车辆最高时速(km/h)" json:"maxRunSpeed"` //车辆最高时速（2字节，单位0.1km/h）
    private Integer type                       ;// uint8  `zson:"单双电模式" json:"type"`                //单双电模式（1字节0x01单电，0x02双电）
    private Integer unit                       ;// uint8  `zson:"单位方式" json:"unit"`                 //单位方式（1字节，0x01表示km，其他hph）
    private Integer maxRollSpeed               ;// uint16 `zson:"最大转速(r/min)" json:"maxRollSpeed"`  //最大转速（2字节，单位r/min）
    private Integer kmConsume                  ;// uint16 `zson:"100km耗电量(Ah)" json:"kmConsume"`     //100km耗电量（2字节，单位0.1Ah）
    private String bleName                    ;// string `zson:"蓝牙名称" json:"bleName"`              //蓝牙名称
}
