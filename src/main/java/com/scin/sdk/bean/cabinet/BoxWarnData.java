package com.scin.sdk.bean.cabinet;

import com.scin.sdk.bean.share.BmsWarn;
import com.scin.sdk.utils.StatusUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-12-03
 */
@Data
public class BoxWarnData implements Serializable {

    private Integer boardId;//CCB编号Id
    private Integer boxId;//格口编号Id

    private Integer boxTempWarnOne;      //格口一级温度告警
    private Integer boxHotAerosolWarnOne;//格口一级热气溶胶触发告警

    private Integer boxTempWarnTwo;      //格口二级温度告警
    private Integer boxChargerWarnTwo;   //格口二级充电器告警
    private Integer boxBatteryWarnTwo;   //格口二级电池告警
    private Integer chargerWarnLowTemp;  //充电器过温告警

    private String batteryId;//电池ID
    private BmsWarn bmsWarn;//bms状态信息, 二进制32位字符串，bit大小从左到右


    public Integer boxWarnStatusOne() {
        return StatusUtil.haveOneStatus(boxTempWarnOne, boxHotAerosolWarnOne);
    }

    public Integer boxWarnStatusTwo() {
        return StatusUtil.haveOneStatus(boxTempWarnTwo, boxChargerWarnTwo, boxBatteryWarnTwo, chargerWarnLowTemp);
    }


}
