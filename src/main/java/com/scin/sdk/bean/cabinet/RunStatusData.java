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
    //0x01人脸识别中
    //0x02换电状态
    //0x03升级中
    //0x04初始化中
    private Integer runStatus;
}
