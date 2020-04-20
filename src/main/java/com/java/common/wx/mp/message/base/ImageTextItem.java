package com.java.common.wx.mp.message.base;

import lombok.Data;

/**
 * @desc 公众号图文消息内容
 * @author xl
 * @date 2020-04-17 10:17
 */
@Data
public class ImageTextItem {
    /***
     * 图文消息标题
     */
    private String Title;
    /**
     * 图文消息描述
     */
    private String Description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String PicUrl;
    /**
     * 点击图文消息跳转链接
     */
    private String Url;

    public ImageTextItem(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

    @Override
    public String toString() {
        return "ImageTextItem{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
