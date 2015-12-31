package com.simpletech.wifisurfing.model.entity;

/**
 * 登录认证类型排行
 * Created by ChenQi on 2015/11/3 13:20.
 */
public class LoginAuthTypeTrendValue extends TrendValue {

    private int total;           //总数

    private int free;            //免认证数
    private int onekey;          //一键认证数
    private int weixin;          //微信认证数

    private float rfree;            //免认证数 占比
    private float ronekey;          //一键认证数 占比
    private float rweixin;          //微信认证数 占比


    public LoginAuthTypeTrendValue() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getOnekey() {
        return onekey;
    }

    public void setOnekey(int onekey) {
        this.onekey = onekey;
    }

    public int getWeixin() {
        return weixin;
    }

    public void setWeixin(int weixin) {
        this.weixin = weixin;
    }

    public float getRfree() {
        return rfree;
    }

    public void setRfree(float rfree) {
        this.rfree = rfree;
    }

    public float getRonekey() {
        return ronekey;
    }

    public void setRonekey(float ronekey) {
        this.ronekey = ronekey;
    }

    public float getRweixin() {
        return rweixin;
    }

    public void setRweixin(float rweixin) {
        this.rweixin = rweixin;
    }

}
