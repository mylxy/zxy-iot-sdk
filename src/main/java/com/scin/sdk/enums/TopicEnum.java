package com.scin.sdk.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     TOPIC类型，只有两种 透传数据，解析数据
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
public enum TopicEnum {

    REPORT("report", "上报基本数据"),
    GPS("gps", "定位数据"),
    WARN("warn", "告警数据"),
    STATUS("status", "状态数据"),
    ;

    @Setter
    @Getter
    private String mark;

    @Setter
    @Getter
    private String description;

    TopicEnum(String mark, String description) {
        this.mark = description;
        this.description = description;
    }

}
