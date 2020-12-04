package com.scin.sdk.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <p>
 * </p>
 *
 * @author seven
 * @since 2020-12-03
 */
public class StatusUtil {

    /**
     * 包含状态1直接返回1
     *
     * @param statuses
     * @return
     */
    public static Integer haveOneStatus(Integer ...statuses) {
        if (ArrayUtils.isEmpty(statuses)) {
            return 0;
        }
        for (Integer status : statuses) {
            if (status != null && status == 1) {
                return 1;
            }
        }
        return 0;
    }

}
