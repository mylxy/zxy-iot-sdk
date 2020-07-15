package com.scin.sdk.bean.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class Message implements Serializable {

    /**
     * 品类标识
     */
    private String identity;

    /**
     * 型号key
     */
    private String productKey;

    /**
     * 硬件编号
     */
    private String deviceNumber;

    /**
     * 硬件名称
     */
    private String deviceName;

    /**
     * 时间戳
     */
    private Long timestamp;

}
