package com.scin.sdk.bean.battery;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *     模块IO状态(z20硬件版)
 * </p>
 *
 * @author seven
 * @since 2020-09-25
 */
@Data
public class ModelIOData implements Serializable {
    private Integer charging;//Bit_0：充电状态
    private Integer discharging;//Bit_1：放电状态
    private BigDecimal voltage;//电池总电压
    private BigDecimal quantity; //电量百分比
}
