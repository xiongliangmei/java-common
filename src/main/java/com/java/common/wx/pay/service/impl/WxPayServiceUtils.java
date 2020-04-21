package com.java.common.wx.pay.service.impl;

import com.java.common.wx.pay.common.MyWxConfig;
import com.java.common.wx.pay.common.WXPay;
import com.java.common.wx.pay.common.WXPayUtil;
import com.java.common.wx.pay.model.WxOrderRequestModel;
import com.java.common.wx.pay.model.WxPayRequestModel;
import com.java.common.wx.pay.model.WxRefundOrderRequestModel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/4/20.
 */
@Slf4j
public class WxPayServiceUtils {
    MyWxConfig myWxConfig = new MyWxConfig(); // 全局配置

    /***
     * 微信 JSAPI 支付
     * @param wxPayRequestModel
     * @return
     */
    public Map<String, String> unifyPayOrder(WxPayRequestModel wxPayRequestModel){
        if (wxPayRequestModel == null ){
            return null;
        }
        WXPay wxPay;
        try {
            wxPay = new WXPay(myWxConfig,wxPayRequestModel.getNotifyUrl(),true, true);
            Map<String, String> map = new HashMap<String, String>();
            map.put("body",wxPayRequestModel.getBody());
            map.put("out_trade_no",wxPayRequestModel.getOrderNo());
            map.put("spbill_create_ip",wxPayRequestModel.getSpbillCreateIp());
            map.put("trade_type",wxPayRequestModel.getTradeType());
            if (wxPayRequestModel.getTradeType().equals("JSAPI")) {
                map.put("openid", wxPayRequestModel.getOpenId());
                map.put("appid", "");
            } else {
                map.put("appid", "wx9ceb4c8f15a54c77");
            }
            Map<String, String> stringMap = wxPay.unifiedOrder(map);
            System.out.println("微信端返回的参数" + stringMap.toString());
            if (stringMap.get("return_code").equals("SUCCESS") && stringMap.get("result_code").equals("SUCCESS")) {
                System.out.println("-----------微信下单成功-----------");
                Map<String, String> webdata = wxPay.againOrder(stringMap);// 进行再次签名
                System.out.println("再次签名的数据" + webdata);
                return webdata;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * APP支付
     */
    public Map<String, String> appUnifyPayOrder(WxPayRequestModel wxPayRequestModel) {
        if (wxPayRequestModel == null) {
            System.out.println("传入的参数为空");
            return null;
        }
        WXPay wxPay;
        try {
            wxPay = new WXPay(myWxConfig, wxPayRequestModel.getNotifyUrl(), true, false);
            Map<String, String> map = new HashMap<String, String>();
            map.put("body", wxPayRequestModel.getBody());
            map.put("out_trade_no", wxPayRequestModel.getOrderNo());
            map.put("total_fee", WXPayUtil.yuan2Fen(wxPayRequestModel.getTotalFree()));
            map.put("spbill_create_ip", wxPayRequestModel.getSpbillCreateIp());
            map.put("trade_type", wxPayRequestModel.getTradeType());
            if (wxPayRequestModel.getTradeType().equals("JSAPI")) {
                map.put("openid", wxPayRequestModel.getOpenId());
                map.put("appid", "appid");
            } else {
                map.put("appid", "wx9ceb4c8f15a54c77");
            }
            System.out.println("请求参数：" + map.toString());
            Map<String, String> stringMap = wxPay.unifiedOrder(map);
            System.out.println("微信端返回的参数" + stringMap.toString());
            if (stringMap.get("return_code").equals("SUCCESS") && stringMap.get("result_code").equals("SUCCESS")) {
                System.out.println("-----------微信下单成功-----------");
                Map<String, String> appUnifyPay = new HashMap<String, String>();
                appUnifyPay.put("prepayid", stringMap.get("prepay_id"));
                appUnifyPay.put("appid", map.get("appid"));
                Map<String, String> webdata = wxPay.appAgainAuthorization(appUnifyPay);// 进行再次签名
                System.out.println("再次签名的数据" + webdata);
                return webdata;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 统一查询当前订单的支付情况
     *
     * @param wxFindRequestModel
     * @return
     */
    public boolean queryOrderInfo(WxOrderRequestModel wxFindRequestModel) {
        WXPay wxPay = null;
        try {
            wxPay = new WXPay(myWxConfig);
            Map<String, String> map = new HashMap<String, String>();
            map.put("out_trade_no", wxFindRequestModel.getOrderNo());
            map.put("appid", "appid");
            Map<String, String> queryMap = wxPay.orderQuery(map);
            System.out.println("微信端返回的参数" + queryMap.toString());
            if (queryMap.get("return_code").equals("SUCCESS") && queryMap.get("result_code").equals("SUCCESS")
                    && queryMap.get("trade_state").equals("SUCCESS")) {
                System.out.println("-----------微信查询订单成功-----------");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 关闭订单
     */
    public boolean closeOrder(WxOrderRequestModel wxFindRequestModel) {
        WXPay wxPay = null;
        try {
            wxPay = new WXPay(myWxConfig);
            Map<String, String> map = new HashMap<String, String>();
            map.put("out_trade_no", wxFindRequestModel.getOrderNo());
            map.put("appid", "appid");
            Map<String, String> closeMap = wxPay.closeOrder(map);
            System.out.println("微信端返回的参数" + closeMap.toString());
            if (closeMap.get("return_code").equals("SUCCESS") && closeMap.get("result_code").equals("SUCCESS")) {
                System.out.println("-----------微信关闭订单成功-----------");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 订单退款
     */
    public boolean refundOrder(WxRefundOrderRequestModel wxRefundOrderRequestModel) {
        WXPay wxPay = null;
        try {
            wxPay = new WXPay(myWxConfig, wxRefundOrderRequestModel.getNotifyUrl());
            Map<String, String> map = new HashMap<String, String>();
            map.put("out_trade_no", wxRefundOrderRequestModel.getOrderNo());
            map.put("out_refund_no", wxRefundOrderRequestModel.getFundOrderNo());
            map.put("total_fee", WXPayUtil.yuan2Fen(wxRefundOrderRequestModel.getTotalFee()));
            map.put("refund_fee", WXPayUtil.yuan2Fen(wxRefundOrderRequestModel.getRefundFee()));
            map.put("appid", "appid");
            Map<String, String> refundMap = wxPay.refund(map);
            System.out.println("微信端返回的参数" + refundMap.toString());
            if (refundMap.get("return_code").equals("SUCCESS") && refundMap.get("result_code").equals("SUCCESS")) {
                System.out.println("-----------微信退款成功-----------");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 退款订单查询
     */
    public Object queryRefundOrder(WxOrderRequestModel wxOrderRequestModel) {
        WXPay wxPay = null;
        try {
            wxPay = new WXPay(myWxConfig);
            Map<String, String> map = new HashMap<String, String>();
            map.put("out_trade_no", wxOrderRequestModel.getOrderNo());
            map.put("appid","appid");
            Map<String, String> queryRefundMap = wxPay.refundQuery(map);
            System.out.println("微信端返回的参数" + queryRefundMap.toString());
            if (queryRefundMap.get("return_code").equals("SUCCESS") && queryRefundMap.get("result_code").equals("SUCCESS")) {
                System.out.println("-----------微信退款查询成功-----------");
                return queryRefundMap;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
