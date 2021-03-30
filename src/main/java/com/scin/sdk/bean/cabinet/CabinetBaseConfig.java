package com.scin.sdk.bean.cabinet;

import lombok.Data;


/**
 * <p>
 *     柜子基础配置信息(0x15)
 * </p>
 *
 * @author seven
 * @since 2021-03-23
 */
@Data
public class CabinetBaseConfig {


    private Integer boardId;//主控板编号Id
    private Integer iccid;//柜子iccid
    private Integer maxPower;//最大充电功率
    private Integer logStatus;//实时日志状态(0代表关闭上传，1代表开启上传)
    private Integer openLightTime;//顶灯打开时间(s)
    private Integer closeLightTime;//顶灯关闭时间(s)
    private Integer canSwapPower;//可换电电量(%)
    private Integer reserveSaveTime;//预约保留时间(min)
    private Integer refreshUploadTime;//刷新数据上报时间(min)
    private Integer activityTimeOutTime;//页面超时时间(s)
    private Integer protectIntervalTime;//守护进程检测间隔时间(s)

}
