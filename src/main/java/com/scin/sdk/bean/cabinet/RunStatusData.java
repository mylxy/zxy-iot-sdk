package com.scin.sdk.bean.cabinet;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     柜子的运行状态数据
 * </p>
 *
 * @author seven
 * @since 2020-11-26
 */
@Data
public class RunStatusData implements Serializable {
    //0x00闲置状态
    //0x01换电状态
    //0x02升级中
    //0x03人工介入
    //0x04柜子禁用
    //0x05告警状态
    private Integer runStatus;
}
