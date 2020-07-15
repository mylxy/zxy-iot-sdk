package com.scin.sdk.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *     Rest请求返回结果
 * </p>
 *
 * @author seven
 * @since 2019-08-08
 */
@Data
@Accessors(chain = true)
public class RestResponse implements Serializable {

    /**
     * 状态编码
     */
    int code;

    /**
     * 状态描述
     */
    String message;

    /**
     * 错误信息国际化，目前暂时只有中文
     */
    private String local;

    /**
     * 参数检验消息
     */
    List<ValidateResult> validates;

    /**
     * 正确的返回结果
     */
    private Authority data;

}
