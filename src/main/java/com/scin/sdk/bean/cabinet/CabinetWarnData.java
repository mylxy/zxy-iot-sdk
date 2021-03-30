package com.scin.sdk.bean.cabinet;

import com.scin.sdk.bean.base.WarnComb;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     柜子告警信息
 * </p>
 *
 * @author seven
 * @since 2020-12-03
 */
@Data
public class CabinetWarnData implements Serializable {

    private Integer boardId;//主控板编号Id

    private Integer cabinetTempWarnOne ;//柜子一级温度告警
    private Integer cabinetSmokeWarnOne;//柜子一级烟感告警
    private Integer cabinetFloodWarnOne;//柜子一级水位告警
    private Integer cabinetHumWarnOne  ;//柜子一级湿度告警

    private Integer cabinetTempWarnTwo ;//柜子二级温度告警
    private Integer cabinetHumWarnTwo  ;//柜子二级湿度告警

    /**
     * 柜子一级告警
     *
     * @return
     */
    public WarnComb oneFaultInstance() {
        WarnComb warn = new WarnComb();
        warn.add(cabinetTempWarnOne, "一级温度告警");
        warn.add(cabinetSmokeWarnOne, "一级烟感告警");
        warn.add(cabinetFloodWarnOne, "一级水位告警");
        warn.add(cabinetHumWarnOne, "一级湿度告警");
        return warn;
    }

    /**
     * 柜子二级故障
     *
     * @return
     */
    public WarnComb twoFaultInstance() {
        WarnComb warn = new WarnComb();
        warn.add(cabinetTempWarnTwo, "二级温度告警");
        warn.add(cabinetHumWarnTwo, "二级湿度告警");
        return warn;
    }
}
