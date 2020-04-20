package com.java.common.wx.mp.message.base;

import lombok.Data;

/**
 * @desc 回复消息基本类 父类（公众号）
 * @author xl
 * @date 2020-04-20 9:50
 */
@Data
public class BaseMessage {
    /***
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /***
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;
    /***
     * 消息类型，文本为text|image|voice|title|music|news
     */
    private String MsgType;

    /***
     * 回复消息基本类 构造器
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     */
    public BaseMessage(String toUserName, String fromUserName, Long createTime, String msgType) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
    }

    public BaseMessage() {
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }
}
