package com.scin.sdk.bean.battery;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *     原始振动数数
 * </p>
 *
 * @author seven
 * @since 2020-05-29
 */
@Data
@Accessors(chain = true)
public class SensorShockData {
    private Integer num; //数组数量
    List<SensorShockItem> items; //每组震动数据+时间戳
}
