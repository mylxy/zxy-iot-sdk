package com.scin.sdk.bean.cabinet;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *     上报基础配置信息(0x15)
 * </p>
 *
 * @author seven
 * @since 2021-03-23
 */
@Data
public class BaseConfigInfoData {

    private CabinetBaseConfig cabinetBaseConfig;//柜子告警配置数据
    private Integer BoxCount;                   //格口数量
    private List<BoxBaseConfig> boxBaseConfig; //格口告警配置数据

}
