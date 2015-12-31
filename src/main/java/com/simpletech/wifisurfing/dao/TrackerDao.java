package com.simpletech.wifisurfing.dao;

import com.simpletech.wifisurfing.model.Shop;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerDao {


    /**
     * 根据店铺ID获取 店铺
     * @param shopid 店铺ID
     * @return 店铺或者null
     */
    Shop findShopById(String shopid);
}
