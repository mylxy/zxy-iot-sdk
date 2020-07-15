package com.scin.sdk.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     数据类型，只有两种 透传数据，解析数据
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
public enum DataEnum {

    TRANSPARENT("rsp", "透传数据"),
    PARSE("parse", "解析数据"),
    ;

    @Setter
    @Getter
    private String mark;

    @Setter
    @Getter
    private String description;

    DataEnum(String mark, String description) {
        this.mark = description;
        this.description = description;
    }

}
