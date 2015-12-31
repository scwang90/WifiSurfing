package com.simpletech.wifisurfing.model.entity;

/**
 * 设备品牌
 * Created by ChenQi on 2015/11/3 13:20.
 */
public class DeviceBrandRankValue {

    private String name;        //排行名称
    private int uv;             //独立设备数
    private int vt;             //到访频次（独立设备在规定时间段内访问的次数）
    private float ruv;          //设备品牌占比
    private float rvt;          //访问频次占比


    public DeviceBrandRankValue(){}

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

    public float getRuv() {
        return ruv;
    }

    public void setRuv(float ruv) {
        this.ruv = ruv;
    }

    public float getRvt() {
        return rvt;
    }

    public void setRvt(float rvt) {
        this.rvt = rvt;
    }

}
