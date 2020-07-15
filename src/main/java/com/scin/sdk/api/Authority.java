package com.scin.sdk.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * <p>
 *     消费者的kafka连接配置信息
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
@Data
@Accessors(chain = true)
public class Authority {

    /**
     * 服务器地址
     */
    private String servers;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 品类标识
     */
    private String identity = "battery";

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 消费组个数, groupName = app.{appKey}.1
     */
    private Integer groupNum;

    /**
     * 可订阅消费主题, 有ACL权限
     */
    private Set<String> topics;

}
