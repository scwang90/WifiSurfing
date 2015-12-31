package com.simpletech.wifisurfing.model.entity;

/**
 * 认证（次数，用户，新老）-趋势
 * Created by Administrator on 2015/11/3.
 */
public class LoginAuthTrendValue extends TrendValue {

    private int vt;           //认证次数
    private int uv;           //用户量（mac排重）
    private int nuv;          //新用户（mac排重）
    private int ouv;          //老用户（mac排重）
    private float rnuv;       //新用户占比
    private float rouv;       //老用户占比

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getNuv() {
        return nuv;
    }

    public void setNuv(int nuv) {
        this.nuv = nuv;
    }

    public int getOuv() {
        return ouv;
    }

    public void setOuv(int ouv) {
        this.ouv = ouv;
    }

    public float getRnuv() {
        return rnuv;
    }

    public void setRnuv(float rnuv) {
        this.rnuv = rnuv;
    }

    public float getRouv() {
        return rouv;
    }

    public void setRouv(float rouv) {
        this.rouv = rouv;
    }
}
