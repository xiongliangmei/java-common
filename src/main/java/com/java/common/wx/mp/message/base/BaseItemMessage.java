package com.java.common.wx.mp.message.base;

import lombok.Data;

/**
 * @desc 图文消息内容父类（公众号）
 * @author xl
 * @date 2020-04-20 10:10
 */
@Data
public class BaseItemMessage {
    /***
     * 图文消息内容
     */
    private Object item;

    public BaseItemMessage(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "BaseItemMessage{" +
                "item=" + item +
                '}';
    }
}
