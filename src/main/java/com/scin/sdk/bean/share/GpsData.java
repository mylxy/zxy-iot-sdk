package com.scin.sdk.bean.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *     定位数据
 * </p>
 *
 * @author seven
 * @since 2020-05-12
 */
@Data
@Accessors(chain = true)
public class GpsData implements Serializable {

    private Integer gsmSignal;  //基站信号强度
    private Integer type;       //定位类型
    private Integer length;     //定位信息长度
    private Gps gps;            //GPS信息
    private String  gsmContext; //基站信息内容

}
