package com.scin.sdk.bean.battery;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-05-29
 */
@Data
@Accessors(chain = true)
public class SensorShockItem {
    private String value; //震动数据 6个字节 转成16进制字符串存储
    private Long timestamp; //时间戳
}
