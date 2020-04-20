package com.java.common.wx.utils;

import com.java.common.constant.WxConstant;
import com.java.common.wx.mp.message.ImageTextMessage;
import com.java.common.wx.mp.message.TextMessage;
import com.java.common.wx.mp.message.base.BaseItemMessage;
import com.java.common.wx.mp.message.base.ImageTextItem;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * @desc 发送消息工具类
 * @author xl
 * @date 2020-04-20 10:51
 */
public class SendMessageTool {

    /***
     * 发送文本消息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String sendTextMessage(String toUserName,String fromUserName,String content){
        TextMessage textMessage = new TextMessage(toUserName,fromUserName,new Date().getTime(), WxConstant.RESP_MESSAGE_TYPE_TEXT,content);
        XStream xStream = new XStream();
        xStream.alias("xml", TextMessage.class);
        String xml = xStream.toXML(textMessage);
        return xml;
    }

    /***
     * 发送图文消息
     * @param toUserName
     * @param fromUserName
     * @param title
     * @param description
     * @param picUrl
     * @param url
     * @return
     */
    public static String sendImageTextMessage(String toUserName, String fromUserName,String title, String description, String picUrl, String url){
        ImageTextItem imageTextItem = new ImageTextItem(title,description,picUrl,url);
        BaseItemMessage baseItemMessage = new BaseItemMessage(imageTextItem);
        ImageTextMessage imageTextMessage = new ImageTextMessage(toUserName,fromUserName,new Date().getTime(),WxConstant.RESP_MESSAGE_TYPE_NEWS,1, baseItemMessage);
        //创建解析XML对象
        XStream xStream = new XStream();
        //设置别名, 默认会输出全路径
        xStream.alias("xml", ImageTextItem.class);
        //转为xml
        String xml = xStream.toXML(imageTextItem);
        return xml;
    }
}
