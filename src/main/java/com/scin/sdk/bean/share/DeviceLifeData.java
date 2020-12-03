package com.scin.sdk.bean.share;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     设备生命周期事件
 * </p>
 *
 * @author seven
 * @since 2020-12-02
 */
@Data
public class DeviceLifeData implements Serializable {

    //生命周期类型 1表示新增 0表示更新 -1表示删除
    private Integer type;

    //所属公司
    private Long companyId;

    //型号名称
    private String modelName;
}

