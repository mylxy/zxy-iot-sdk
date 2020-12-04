package com.scin.sdk.bean.cabinet;

import com.scin.sdk.utils.StatusUtil;
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

    private Integer boardId;//CCB编号Id

    private Integer cabinetTempWarnOne ;//柜子一级温度告警
    private Integer cabinetSmokeWarnOne;//柜子一级烟感告警
    private Integer cabinetFloodWarnOne;//柜子一级水位告警
    private Integer cabinetHumWarnOne  ;//柜子一级湿度告警

    private Integer cabinetTempWarnTwo ;//柜子二级温度告警
    private Integer cabinetHumWarnTwo  ;//柜子二级湿度告警

    public Integer cabinetWarnStatusOne() {
        return StatusUtil.haveOneStatus(cabinetTempWarnOne, cabinetSmokeWarnOne, cabinetFloodWarnOne, cabinetHumWarnOne);
    }

    public Integer cabinetWarnStatusTwo() {
        return StatusUtil.haveOneStatus(cabinetTempWarnTwo, cabinetHumWarnTwo);
    }
}
