package com.scin.sdk.utils;

import com.alibaba.fastjson.JSONObject;
import com.scin.sdk.api.Authority;
import com.scin.sdk.api.RestResponse;
import com.scin.sdk.enums.SystemStatusEnum;
import com.scin.sdk.exception.BusinessException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 最爱吃小鱼
 */
public class HttpUtil {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 发起连接请求，post json内容
     *
     * @param url
     * @param params
     * @return
     */
    public static Authority sign(String url, Object params) {
        log.info("连接URL={}", url);
        log.info("============================================");
        try {
            String content = JSONObject.toJSONString(params);
            RequestBody requestBody = RequestBody.create(JSON, content);

            Request.Builder requestBuilder = new Request.Builder()
                    .url(url)
                    .post(requestBody);

            Request request = requestBuilder.build();

            Response response = CLIENT.newCall(request).execute();
            log.info("请求JSON:{}", content);
            log.info("返回CODE:{}", response.code());
            log.info("============================================");
            if (response.code() != 200) {
                throw BusinessException.of(SystemStatusEnum.APP_USER_NOT_AUTHORIZED);
            }
            RestResponse r = JSONObject.parseObject(response.body().bytes(), RestResponse.class);
            if (r.getCode() != 1) {
                throw BusinessException.of(r.getCode(), r.getMessage(), r.getLocal());
            }
            return r.getData();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
