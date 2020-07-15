package com.scin.sdk.enums;

import java.io.Serializable;


/**
 * <p>
 *     状态枚举接口
 * </p>
 *
 * @author seven
 * @since 2019-08-08
 */
public interface StatusEnum extends Serializable {

    /**
     * 返回请求状态
     *
     * @return 状态编码
     */
    int getCode();


    /**
     * 返回请求状态英文描述
     *
     * @return 信息描述
     */
    String getMessage();


    /**
     * 返回请求状态中文描述
     * @return
     */
    String getDescription();
}
