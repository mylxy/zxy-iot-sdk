package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 电池状态数据
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class BmsStatus implements Serializable {

    // BMS状态数据
    private Integer charging;              //Bit_0：充电
    private Integer fullyCharged;          //Bit_1：充饱
    private Integer discharging;           //Bit_4：放电
    private Integer leaseStatus;           //Bit_13：启用租赁
    private Integer disableCharging;       //Bit_14：禁止充电（来自工控或云端）
    private Integer disableDischarging;    //Bit_15：禁止放电（来自工控或云端）
    private Integer chargingTubeStatus;    //Bit_17：当前充电管状态：0：关，1：开
    private Integer dischargingTubeStatus; //Bit_18：当前放电管状态：0：关，1：开

}
