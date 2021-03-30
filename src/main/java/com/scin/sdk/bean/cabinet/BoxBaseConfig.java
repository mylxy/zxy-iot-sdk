package com.scin.sdk.bean.cabinet;

import lombok.Data;

/**
 * <p>
 *     格口基础配置信息(0x15)
 * </p>
 *
 * @author seven
 * @since 2021-03-23
 */
@Data
public class BoxBaseConfig {

    private Integer BoardId          ;//主控板编号Id
    private Integer BoxId            ;//格口编号Id
    private Integer OpenChargePower  ;//电池打开充电电量(%)
    private Integer CloseChargePower ;//电池关闭充电电量(%)

}
