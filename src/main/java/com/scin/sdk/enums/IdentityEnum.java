package com.scin.sdk.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     设备类型
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
public enum IdentityEnum {

    CABINET("cabinet", "换电柜"),
    VEHICLE("vehicle", "车辆中控"),
    BATTERY("battery", "电池"),
    ;

    @Setter
    @Getter
    private String mark;

    @Setter
    @Getter
    private String description;

    IdentityEnum(String mark, String description) {
        this.mark = description;
        this.description = description;
    }

}
