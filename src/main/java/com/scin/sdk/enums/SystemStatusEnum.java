package com.scin.sdk.enums;

import lombok.Getter;

/**
 * <p>
 *     系统状态枚举
 * </p>
 *
 * @author seven
 * @since 2019-08-08
 */
@Getter
public enum SystemStatusEnum implements StatusEnum {

    SUBSCRIBE_TOPIC_EMPTY(1001, "订阅的主题Topic为空"),
    APP_USER_NOT_AUTHORIZED(1002, "应用账号无权限"),
    APP_TOPIC_NOT_AUTHORIZED(1003, "主题Topic在此应用下无权限"),
    APP_GROUP_NOT_AUTHORIZED(1004, "消费组Group在此应用下无权限"),
    KAFKA_USER_NOT_AUTHORIZED(1005, "Kafka账号无权限, 请联系智享云"),
    KAFKA_TOPIC_NOT_AUTHORIZED(1006, "Kafka主题无权限, 请联系智享云"),
    KAFKA_GROUP_NOT_AUTHORIZED(1007, "Kafka消费组无权限, 请联系智享云"),
    IDENTITY_NOT_SUPPORT(1008, "此品类SDK暂不支持, 请升级SDK"),
    PARSE_CLASS_SUPPORT(1009, "此解析封装类暂不支持"),
    IO_EXCEPTION(1010, "HTTP请求IOException"),
    CMDKEYOFCLASS_NO_CONFIG(1011, "cmdKey对的数据反序列化未配置"),
    ;

    SystemStatusEnum(int code, String description) {
        this.code = code;
        this.message = name();
        this.description = description;
    }

    /**
     * 响应编码
     */
    private int code;

    /**
     * 英文描述
     */
    private String message;

    /**
     * 错误描述
     */
    private String description;
}
