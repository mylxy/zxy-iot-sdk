package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *     GPS定位数据
 * </p>
 *
 * @author seven
 * @since 2020-05-20
 */
@Data
@Accessors(chain = true)
public class Gps implements Serializable {

    private Integer success;       //0:未定位,1:定位成功
    private Integer latType;       //0:北纬,1:南纬
    private Integer lngType;       //0:东经,1:西经
    private Integer gpsStatus;     //0:未使用GPS定位,1:使用GPS定位
    private Integer bdsStatus;     //0:未使用北斗定位,1:使用北斗定位
    private Integer glonassStatus; //0:未使用GLONASS定位,1:使用GLONASS定位
    private Integer galileoStatus; //0:未使用Galileo定位,1:使用Galileo定位
    private Integer gnssType;      //1:使用GPS定位,2:使用北斗定位,3:使用GLONASS定位,4:使用Galileo定位
    private Integer gnssNumber;    //定位卫星数量
    private BigDecimal lat;        //纬度
    private BigDecimal lng;        //经度
    private Integer altitude;      //海拔高度 单位m
    private Integer speed;         //速度（2字节，单位km/h）
    private Integer course;        //航向

}
