package com.scin.sdk.bean.share;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 完整的经纬度信息，也包括省市区编码及详细地址
 * </p>
 *
 * @author seven
 * @since 2020-06-23
 */
@Data
public class GpsComplexData implements Serializable {
    private BigDecimal lng;
    private BigDecimal lat;
    private Integer type;
    private Integer gsmSignal;
    private Integer gnssNumber;
    private Integer Altitude;
    private Integer Speed;
    private Integer Course;
    private String provinceCode;
    private String cityCode;
    private String zoneCode;
    private String address;
}
