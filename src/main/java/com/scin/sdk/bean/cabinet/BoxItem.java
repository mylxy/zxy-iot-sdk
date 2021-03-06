package com.scin.sdk.bean.cabinet;

import com.scin.sdk.bean.base.WarnComb;
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
    private Integer boardId;//主控板编号Id
    private Integer boxId;//格口编号Id

    //格口基础数据(0x13)
    private Integer door;//仓门状态
    private Integer good;//到位状态
    private Integer relay;//继电器状态
    private Integer lock;//电池锁状态
    private Integer nfc;//nfc状态
    private BigDecimal boxTemp;//格口温度
    private Integer boxReserve;//是否为预约(0代表无预约，1代表已预约)
    private Integer boxDisable;//云端禁用状态(0代表禁用，1代表启用)

    //格口充电器数据(0x13)
    private Integer chargerStatus;//充电器状态
    private BigDecimal chargerVoltage;//充电器电压
    private BigDecimal chargerCurrent;//充电器电流
    private Integer chargerTempHave;//存在充电器温度值（0x00不存在，0x01存在
    private BigDecimal chargerTemp;//充电器温度

    //(格口四级故障)格口异常内容(0x13)
    private Integer queryBoxStatusError;//查询格口状态异常(0代表未发生，1代表发生)
    private Integer batteryLoginError;//电池登录异常(0代表未发生，1代表发生)
    private Integer noGoodsError;//无到位，有电池异常(0代表未发生，1代表发生)
    private Integer noBatteryError;//有到位，无电池异常(0代表未发生，1代表发生)
    private Integer readBatteryIdError;//读取电池ID异常(0代表未发生，1代表发生)
    private Integer readBatteryBmsError;//读取电池BMS异常(0代表未发生，1代表发生)
    private Integer boxDoorOpenError;//格口仓门开启中异常(0代表未发生，1代表发生)

    //(格口三级故障)
    private Integer openDoorError       ;//开仓失败(0代表未发生，1代表发生)
    private Integer userBatteryError    ;//用户本人电池被吞(0代表未发生，1代表发生)
    private Integer notUserBatteryError ;//非用户本人电池被吞(0代表未发生，1代表发生)
    private Integer backBatteryError    ;//还电成功，检测失败(0代表未发生，1代表发生)
    private Integer takeBatteryError    ;//取电成功，检测失败(0代表未发生，1代表发生)

    //格口电池数据(0x13)
    private Integer emptyStatus;//是否包含电池
    private Integer batteryType;//电池类型(0代表智享，其他未定义)
    private String batteryId;//电池ID
    private BigDecimal batteryCapacity;//电量
    private BmsStatus bmsStatus;//bms状态信息
    private BmsWarn bmsWarn;//bms告警信息
    private Bms bms;//bms负载数据

    //(格口一级故障)格口二级告警信息
    private Integer boxTempWarnTwo;      //格口二级温度告警
    private Integer boxChargerWarnTwo;   //格口二级充电器告警
    private Integer boxBatteryWarnTwo;   //格口二级电池告警
    private Integer chargerWarnLowTemp;  //充电器过温告警


    /**
     * 二级故障
     *
     * @return
     */
    public WarnComb twoFaultInstance() {
        WarnComb warn = new WarnComb();
        warn.add(boxTempWarnTwo, "格口二级温度告警");
        warn.add(boxChargerWarnTwo, "格口二级充电器告警");
        warn.add(boxBatteryWarnTwo, "格口二级电池告警");
        warn.add(chargerWarnLowTemp, "格口充电器过温告警");
        return warn;
    }

    /**
     * 三级故障
     *
     * @return
     */
    public WarnComb threeFaultInstance() {
        WarnComb warn = new WarnComb();
        warn.add(openDoorError, "开仓失败");
        warn.add(userBatteryError, "用户本人电池被吞");
        warn.add(notUserBatteryError, "非用户本人电池被吞");
        warn.add(backBatteryError, "还电成功检测失败");
        warn.add(takeBatteryError, "取电成功检测失败");
        return warn;
    }

    /**
     * 四级故障
     *
     * @return
     */
    public WarnComb fourFaultInstance() {
        WarnComb warn = new WarnComb();
        warn.add(queryBoxStatusError, "查询格口状态异常");
        warn.add(batteryLoginError, "电池登录异常");
        warn.add(noGoodsError, "无到位有电池异常");
        warn.add(noBatteryError, "有到位无电池异常");
        warn.add(readBatteryIdError, "读取电池ID异常");
        warn.add(readBatteryBmsError, "读取电池BMS异常");
        warn.add(boxDoorOpenError, "格口仓门开启中异常");
        return warn;
    }

}
