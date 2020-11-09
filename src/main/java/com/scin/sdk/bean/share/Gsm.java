package com.scin.sdk.bean.share;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-10-29
 */
@Data
public class Gsm implements Serializable {
    //纬度
    private BigDecimal lat;
    //经度
    private BigDecimal lng;
    //经度类型 E表示东经，w西经，
    private String LngType;
    //纬度类型 N表示北纬，S南纬
    private String LatType;
}
