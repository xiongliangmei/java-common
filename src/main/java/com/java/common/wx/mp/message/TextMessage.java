package com.java.common.wx.mp.message;

import com.java.common.wx.mp.message.base.BaseMessage;

/**
 * @desc 文本消息(公众号)
 * @author xl
 * @date 2020-04-20 9:58
 */
public class TextMessage extends BaseMessage{
    /***
     * 回复的消息内容
     */
    private String Content;

    /***
     *  文本消息构造器
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param content
     */
    public TextMessage(String toUserName, String fromUserName, Long createTime, String msgType, String content) {
        super(toUserName, fromUserName, createTime, msgType);
        Content = content;
    }

    public TextMessage(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "Content='" + Content + '\'' +
                '}';
    }
}
