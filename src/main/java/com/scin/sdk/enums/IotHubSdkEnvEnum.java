package com.scin.sdk.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     智享云数据订阅连接环境
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
public enum IotHubSdkEnvEnum {

    dev("https://dev-api-iot-company.hzzxxd.com/company/application/subAuth", "研发环境"),
    test("https://test-api-iot-company.hzzxxd.com/company/application/subAuth", "测试环境"),
    prod("https://api-iot-company.hzzxxd.com/company/application/subAuth", "生产环境"),
    ;

    @Setter
    @Getter
    private String url;

    @Setter
    @Getter
    private String description;

    IotHubSdkEnvEnum(String url, String description) {
        this.url = url;
        this.description = description;
    }

}
