package com.scin.sdk.bean.share;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     电池信号强度信息
 * </p>
 *
 * @author seven
 * @since 2020-09-25
 */
@Data
public class SignalData implements Serializable {
    private Integer gsmSignal;
    private Integer gnssNumber;

}
