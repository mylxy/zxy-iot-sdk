package com.scin.sdk.bean.share;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *     经纬度简单信息
 * </p>
 *
 * @author seven
 * @since 2020-06-23
 */
@Data
public class GpsSimpleData implements Serializable {
    private BigDecimal lng;
    private BigDecimal lat;
}
