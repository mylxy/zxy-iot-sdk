package com.scin.sdk.bean.cabinet;

import com.scin.sdk.utils.StatusUtil;
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
    //主控板编号Id
    private Integer boardId;

    //柜子基础数据
    private BigDecimal reading;//电表读数
    private BigDecimal current;//电表电流读数(A)
    private BigDecimal voltage;//电表电压读数(V)
    private BigDecimal temp;//柜子温度(℃)
    private BigDecimal hum;//柜子湿度(%)
    private Integer flood;//水位状态(0代表未淹没，1代表淹没)
    private Integer smoke;//烟感状态(0代表未触发，1代表触发)
    private Integer cabinetDisable;//云端禁用状态(0代表禁用，1代表启用)

    //柜子网格数据
    private Integer networkType;//网络类型 0x00移动网络，0x01wifi网络
    private Integer networkLevel;//信号强度（1字节，0-5）

    //柜子二级告警信息
    private Integer cabinetTempWarnTwo ;//柜子二级温度告警
    private Integer cabinetHumWarnTwo  ;//柜子二级湿度告警

    private Integer boxNum;//格口数量
    private List<BoxItem> boxes;//格口数据

    public Integer cabinetWarnStatusTwo() {
        return StatusUtil.haveOneStatus(cabinetTempWarnTwo, cabinetHumWarnTwo);
    }
}
