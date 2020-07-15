package com.scin.sdk.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *     参数检验结果
 * </p>
 *
 * @author seven
 * @since 2019-08-09
 */
@Data
@Accessors(chain = true)
public class ValidateResult implements Serializable {

    /**
     * 参数名称
     */
    private String param;

    /**
     * 错误信息
     */
    private String error;
}
