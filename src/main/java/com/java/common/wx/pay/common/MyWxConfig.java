package com.java.common.wx.pay.common;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by tom on 2019/6/18.
 */
public class MyWxConfig extends WXPayConfig {

    static String appId = "";
    static String key = "";
    static String mchid = "";

    @Override
    protected String getAppID() {
        return appId;
    }

    @Override
    protected String getMchID() {
        return mchid;
    }

    @Override
    String getKey() {
        return key;
    }

    @Override
    protected InputStream getCertStream() {
        ClassPathResource classPathResource = new ClassPathResource(File.separator + "apiclient_cert.p12");
        boolean system = System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0;
        String certPath;
        if (system) {
            certPath = classPathResource.getAbsolutePath();
        } else {
            if (appId.equals("wx4d76524a82bdca08")) {
                certPath = "/mnt/test_apps/services/order-service/config/apiclient_cert.p12";// 测试环境
                // certPath = " C:\\Users\\Administrator\\Desktop\\apiclient_cert.p12";
            } else {
                certPath = "/mnt/pub_apps/services/order-service/config/apiclient_cert.p12"; //正式环境
            }
        }
        File file = new File(certPath);
        InputStream certStream = null;
        try {
            certStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return certStream;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }
}
