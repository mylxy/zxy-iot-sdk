package com.scin.sdk.bean.cabinet;

import com.scin.sdk.bean.share.Bms;
import com.scin.sdk.bean.share.BmsStatus;
import com.scin.sdk.bean.share.BmsWarn;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *     格口数据
 * </p>
 *
 * @author seven
 * @since 2020-11-25
 */
@Data
@Accessors(chain = true)
public class BoxItem implements Serializable {
    private Integer boardId;//CCB编号Id
    private Integer boxId;//格口编号Id
    private Integer door;//仓门状态
    private Integer good;//到位状态
    private Integer relay;//继电器状态
    private Integer lock;//电池锁状态
    private Integer nfc;//nfc状态
    private BigDecimal boxTemp;//格口温度
    private Integer chargerStatus;//充电器状态
    private BigDecimal chargerVoltage;//充电器电压
    private BigDecimal chargerCurrent;//充电器电流
    private Integer chargerTempHave;//存在充电器温度值（0x00不存在，0x01存在
    private BigDecimal chargerTemp;//充电器温度
    private Integer queryBoxStatusError;//查询格口状态异常
    private Integer batteryLoginError;//电池登录异常
    private Integer noGoodsError;//无到位，有电池异常
    private Integer noBatteryError;//有到位，无电池异常
    private Integer readBatteryIdError;//读取电池ID异常
    private Integer readBatteryBmsError;//读取电池BMS异常
    private Integer emptyStatus;//是否包含电池
    private String batteryId;//电池ID
    private BigDecimal batteryCapacity;//电量
    private Integer batteryType;// 电池类型
    private BmsStatus bmsStatus;//bms状态信息
    private BmsWarn bmsWarn;//bms告警信息
    private Bms bms;//bms负载数据

    /**
     * 格口操作异常字段
     *
     * @return
     */
    public Integer errorStatus() {
        if (this.queryBoxStatusError != null && this.queryBoxStatusError == 1) {
            return 1;
        }
        if (this.batteryLoginError != null && this.batteryLoginError == 1) {
            return 1;
        }
        if (this.noGoodsError != null && this.noGoodsError == 1) {
            return 1;
        }
        if (this.noBatteryError != null && this.noBatteryError == 1) {
            return 1;
        }
        if (this.readBatteryIdError != null && this.readBatteryIdError == 1) {
            return 1;
        }
        if (this.readBatteryBmsError != null && this.readBatteryBmsError == 1) {
            return 1;
        }
        return 0;
    }
}
