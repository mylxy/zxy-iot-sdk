package com.scin.sdk.bean.base;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     异常处理辅助类
 * </p>
 *
 * @author seven
 * @since 2021-01-28
 */
@Data
public class WarnComb implements Serializable {

    //异常初始化标志
    private boolean instanced = false;

    //异常描述信息
    private List<String> descs = new ArrayList<>();

    /**
     * 添加异常信息
     *
     * @param warnStatus 告警状态
     * @param warnDesc 告警描述
     * @return
     */
    public WarnComb add(Integer warnStatus, String warnDesc) {
        if (!instanced) {
            instanced = true;
        }
        if (warnStatus == null) {
            return this;
        }
        if (warnStatus != 1) {
            return this;
        }
        descs.add(warnDesc);
        return this;
    }

    /**
     * 异常发生
     *
     * @return true表示发生 false表示未发生
     */
    public boolean warnStatus() {
        return CollectionUtils.isNotEmpty(descs);
    }

    /**
     * 告警描述信息 如果发生多个异常用"，"号隔开
     */
    public String warnDescriptions() {
        if (CollectionUtils.isEmpty(descs)) {
            return "";
        }
        return String.join(",", descs);
    }


}
