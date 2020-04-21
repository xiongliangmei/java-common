package com.java.common.wx.pay.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/4/21.
 */
@Data
public class WxRefundOrderRequestModel {
    private String orderNo;// 订单编码
    private String fundOrderNo;// 退款编码
    private BigDecimal totalFee;// 订单金额
    private BigDecimal refundFee;// 退款金额
    private String notifyUrl;// 回调地址
}
