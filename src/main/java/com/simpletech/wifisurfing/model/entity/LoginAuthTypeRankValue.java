package com.simpletech.wifisurfing.model.entity;

import com.simpletech.wifisurfing.model.constant.AuthType;

/**
 * 登录认证类型排行
 * Created by ChenQi on 2015/11/3 13:20.
 */
public class LoginAuthTypeRankValue {

    private String name;        //排行名称
    private int type;           //类型枚举
    private int uv;             //独立设备数
    private int vt;             //到访频次（独立设备在规定时间段内访问的次数）

    public LoginAuthTypeRankValue(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (AuthType.values().length > type) {
            name = AuthType.values()[type].remark;
        } else {
            name = "";
        }
        this.type = type;
    }
}
