package com.scin.sdk.bean.vehicle;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     ACC状态码
 * </p>
 *
 * @author seven
 * @since 2021-01-27
 */
@Data
public class AccStatusData implements Serializable {

    //0x01开始骑行，0x02结束骑行
    private Integer accStatus;

}
