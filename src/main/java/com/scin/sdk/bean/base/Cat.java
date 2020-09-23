package com.scin.sdk.bean.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *     // 原始数据
 *  type Cat struct {
 * 	ClientID  string `json:"-"`
 * 	Identity  string `json:"identity"`
 * 	Timestamp int64  `json:"timestamp"`
 * 	Payload   string `json:"payload"`
 * }
 *
 * // 解析数据
 * type Tiger struct {
 * 	ClientID  	string 		  `json:"-"`
 * 	Identity     string       `json:"identity"`     //品类标识
 * 	ProductKey   string       `json:"productKey"`   //型号key
 * 	DeviceNumber string       `json:"deviceNumber"` //设备编号
 * 	Timestamp    int64        `json:"timestamp"`    //时间戳
 * 	CmdKey    	 byte         `json:"cmdKey"`    	//数据标识
 * 	Data         interface{}  `json:"data"`         //解析数据
 * }
 * </p>
 *
 * @author seven
 * @since 2020-05-09
 */
@Data
@Accessors(chain = true)
public class Cat extends Message {

    /**
     * 数据负载
     */
    private String payload;
}
