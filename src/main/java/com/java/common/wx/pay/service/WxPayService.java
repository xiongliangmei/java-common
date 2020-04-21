package com.java.common.wx.pay.service;

import com.java.common.wx.pay.model.WxPayRequestModel;

/**
 * Created by Administrator on 2020/4/20.
 */
public interface WxPayService {

    /***
     * 统一下单
     * @param wxPayRequestModel
     * @return
     */
    Object unifyPayOrder(WxPayRequestModel wxPayRequestModel);
}
