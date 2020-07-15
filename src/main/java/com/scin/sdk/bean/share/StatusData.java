package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *     设备状态数据：上线/离线
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class StatusData implements Serializable {

    private Integer status; //1上线，0离线

}
