package com.scin.sdk.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *     权限请求
 * </p>
 *
 * @author seven
 * @since 2020-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class App implements Serializable {

    private String key;
    private String secret;

}
