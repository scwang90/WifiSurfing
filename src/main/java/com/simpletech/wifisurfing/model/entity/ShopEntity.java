package com.simpletech.wifisurfing.model.entity;

import com.simpletech.wifisurfing.model.Shop;

import java.util.Date;

/**
 * 店铺辅助实体
 * Created by Administrator on 2015/11/19.
 */
public class ShopEntity extends Shop{

    Shop shop;

    public ShopEntity(Shop shop) {
        this.shop = shop;
    }

    public void setCreateTime(Date createTime) {
        shop.setCreateTime(createTime);
    }

    public String getShopID() {
        return shop.getShopID();
    }

    public void setShopID(String shopID) {
        shop.setShopID(shopID);
    }

    public String getShopName() {
        return shop.getShopName();
    }

    public void setShopName(String shopName) {
        shop.setShopName(shopName);
    }

    public Integer getConfigWifiVisitExpired() {
        Integer value = shop.getConfigWifiVisitExpired();
        value = (value==null||value.equals(0))?60:value;
        return value;
    }

    public void setConfigWifiVisitExpired(Integer configVisitExpired) {
        shop.setConfigWifiVisitExpired(configVisitExpired);
    }

    public Integer getConfigWifiUserExpired() {
        Integer value = shop.getConfigWifiUserExpired();
        value = (value==null||value.equals(0))?365:value;
        return value;
    }

    public void setConfigWifiUserExpired(Integer configUserExpired) {
        shop.setConfigWifiUserExpired(configUserExpired);
    }

    public String getConfigWifiApiVisitCounts() {
        return shop.getConfigWifiApiVisitCounts();
    }

    public void setConfigWifiApiVisitCounts(String configApiVisitCounts) {
        shop.setConfigWifiApiVisitCounts(configApiVisitCounts);
    }

    public String getConfigWifiApiVisitDuration() {
        return shop.getConfigWifiApiVisitDuration();
    }

    public void setConfigWifiApiVisitDuration(String configApiVisitDuration) {
        shop.setConfigWifiApiVisitDuration(configApiVisitDuration);
    }

    public Double getConfigWifiApiVisitDurationDeep() {
        Double value = shop.getConfigWifiApiVisitDurationDeep();
        value = (value==null||value.equals(0d))?40:value;
        return value;
    }

    public void setConfigWifiApiVisitDurationDeep(Double configApiVisitDurationDeep) {
        shop.setConfigWifiApiVisitDurationDeep(configApiVisitDurationDeep);
    }

    public Double getConfigWifiApiVisitDurationJump() {
        Double value = shop.getConfigWifiApiVisitDurationJump();
        value = (value==null||value.equals(0d))?5:value;
        return value;
    }

    public void setConfigWifiApiVisitDurationJump(Double configApiVisitDurationJump) {
        shop.setConfigWifiApiVisitDurationJump(configApiVisitDurationJump);
    }

    public String getConfigWifiApiVisitPeriod() {
        return shop.getConfigWifiApiVisitPeriod();
    }

    public void setConfigWifiApiVisitPeriod(String configApiVisitPeriod) {
        shop.setConfigWifiApiVisitPeriod(configApiVisitPeriod);
    }

    public String getConfigWifiApiLiveness() {
        return shop.getConfigWifiApiLiveness();
    }

    public void setConfigWifiApiLiveness(String configApiLiveness) {
        shop.setConfigWifiApiLiveness(configApiLiveness);
    }

    public Date getCreateTime() {
        return shop.getCreateTime();
    }
}
