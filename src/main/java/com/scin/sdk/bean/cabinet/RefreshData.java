package com.scin.sdk.bean.cabinet;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 柜子上报的刷新数据
 * </p>
 *
 * @author seven
 * @since 2020-11-25
 */
@Data
public class RefreshData implements Serializable {
    private Integer boardId;//CCB编号Id
    private BigDecimal reading;//电表读数
    private BigDecimal current;//电表电流读数
    private BigDecimal voltage;//电表电压读数
    private BigDecimal temp;//柜子温度
    private BigDecimal hum;//柜子湿度
    private Integer flood;//水位状态
    private Integer smoke;//烟感状态
    private Integer networkType;//网络类型 0x00移动网络，0x01wifi网络
    private Integer networkLevel;//信号强度（1字节，0-5）
    private Integer boxNum;//格口数量
    private List<BoxItem> boxes;//格口数据
}
