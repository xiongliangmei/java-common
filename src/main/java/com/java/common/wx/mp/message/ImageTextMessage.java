package com.java.common.wx.mp.message;

import com.java.common.wx.mp.message.base.BaseItemMessage;
import com.java.common.wx.mp.message.base.BaseMessage;
import lombok.Data;

/**
 * @desc 图文消息类 （公众号）
 * @author xl
 * @date 2020-04-20 10:12
 */
@Data
public class ImageTextMessage extends BaseMessage{
    /***
     *  图文消息个数,当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     */
    private int ArticleCount;
    /**
     * 图文消息基础类
     */
    private BaseItemMessage Articles;

    /***
     *  图文消息类 构造器
     * @param toUserName
     * @param fromUserName
     * @param createTime
     * @param msgType
     * @param articleCount
     * @param articles
     */
    public ImageTextMessage(String toUserName, String fromUserName, Long createTime, String msgType, int articleCount, BaseItemMessage articles) {
        super(toUserName, fromUserName, createTime, msgType);
        ArticleCount = articleCount;
        Articles = articles;
    }

    public ImageTextMessage(int articleCount, BaseItemMessage articles) {
        ArticleCount = articleCount;
        Articles = articles;
    }

    public ImageTextMessage(int articleCount) {
        ArticleCount = articleCount;
    }

    @Override
    public String toString() {
        return "ImageTextMessage{" +
                "ArticleCount=" + ArticleCount +
                ", Articles=" + Articles +
                '}';
    }
}
