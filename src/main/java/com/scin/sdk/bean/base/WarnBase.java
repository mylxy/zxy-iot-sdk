package com.scin.sdk.bean.base;

import com.scin.sdk.enums.SystemStatusEnum;
import com.scin.sdk.exception.BusinessException;

import java.io.Serializable;

/**
 * <p>
 *     告警基类
 * </p>
 *
 * @author seven
 * @since 2021-01-28
 */
public class WarnBase implements Serializable {

    private WarnComb warn;

    /**
     * 初始化异常信息, 在这个实现类里添加异常，即调用add(warnStatus, warnDesc)方法
     */
    public WarnComb warnInstance(){
        return null;
    }

    /**
     * 异常发生
     *
     * @return true表示发生 false表示未发生
     */
    public boolean warnStatus() {
        if (warn == null) {
            warn = warnInstance();
        }
        if (warn == null) {
            throw BusinessException.of(SystemStatusEnum.WARN_INSTANCE_IMPL);
        }
        return warn.warnStatus();
    }

    /**
     * 告警描述信息 如果发生多个异常用"，"号隔开
     */
    public String warnDescriptions() {
        if (warn == null) {
            warn = warnInstance();
        }
        if (warn == null) {
            throw BusinessException.of(SystemStatusEnum.WARN_INSTANCE_IMPL);
        }
        return warn.warnDescriptions();
    }

}
