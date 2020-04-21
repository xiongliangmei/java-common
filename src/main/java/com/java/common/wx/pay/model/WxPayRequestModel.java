package com.java.common.wx.pay.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/4/20.
 */
@Data
public class WxPayRequestModel {
    /***
     * 订单编码
     */
    private String orderNo;
    /***
     * 商品描述
     */
    private String body;
    /***
     * 客户端Ip
     */
    private BigDecimal totalFree;
    /***
     * 客户端IP
     */
    private String spbillCreateIp	;
    /***
     * 异步通知
     */
    private String notifyUrl;
    private String openId;
    private String tradeType;
    private Integer orderType;
}
