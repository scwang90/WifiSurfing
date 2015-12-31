package com.simpletech.wifisurfing.model.constant;

/**
 * 认证类型
 * Created by 树朾 on 2015/9/25.
 */
public enum AuthType {
    FREE("免认证"),
    ONE_KEY("一键认证"),
    WE_CHAT("微信认证"),
    ;

    public final String remark;

    AuthType(String remark) {
        this.remark = remark;
    }
}
