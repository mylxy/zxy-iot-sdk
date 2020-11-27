package com.scin.sdk.bean.cabinet;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *    告警数据
 * </p>
 *
 * @author seven
 * @since 2020-11-26
 */
@Data
public class WarnData implements Serializable {
    private Integer type;//告警类型(0CCB,1BCB)
    private Integer boardId;//CCB编号Id
    private Integer boxId;//格口编号Id
    private Integer warnLowTemp;//低温告警
    private Integer warnHighTemp;//高温告警
    private Integer warnLowVoltage;//低电压告警
    private Integer warnHighVoltage;//高电压告警
    private Integer warnOverCurrent;//过流告警
    private Integer warnSmoke;//烟感触发告警`
    private Integer warnFlood;//水位触发告警
    private Integer warnFireExtinguishers;//灭火器触发告警
    private Integer warnHotAerosol;//热气溶胶触发告警
    private Integer warnCharger;//充电器过温告警
    private Integer warnBatteryStatus;//电池告警
    private String batteryId;//电池ID
    private String bmsStatus;//bms状态信息, 二进制32位字符串，bit大小从左到右

    /**
     * 告警异常
     *
     * @return
     */
    public Integer warnStatus() {
        if (this.boardId != null && this.boardId == 1) {
            return 1;
        }
        if (this.boxId != null && this.boxId == 1) {
            return 1;
        }
        if (this.warnLowTemp != null && this.warnLowTemp == 1) {
            return 1;
        }
        if (this.warnHighTemp != null && this.warnHighTemp == 1) {
            return 1;
        }
        if (this.warnLowVoltage != null && this.warnLowVoltage == 1) {
            return 1;
        }
        if (this.warnHighVoltage != null && this.warnHighVoltage == 1) {
            return 1;
        }
        if (this.warnOverCurrent != null && this.warnOverCurrent == 1) {
            return 1;
        }
        if (this.warnSmoke != null && this.warnSmoke == 1) {
            return 1;
        }
        if (this.warnFlood != null && this.warnFlood == 1) {
            return 1;
        }
        if (this.warnFireExtinguishers != null && this.warnFireExtinguishers == 1) {
            return 1;
        }
        if (this.warnHotAerosol != null && this.warnHotAerosol == 1) {
            return 1;
        }
        if (this.type != null && this.type == 0x01) {
            if (this.warnCharger != null && this.warnCharger == 1) {
                return 1;
            }
            if (this.warnBatteryStatus != null && this.warnBatteryStatus == 1) {
                return 1;
            }
        }
        return 0;
    }

}
