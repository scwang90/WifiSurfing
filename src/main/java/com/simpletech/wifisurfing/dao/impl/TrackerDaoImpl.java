package com.simpletech.wifisurfing.dao.impl;

import com.simpletech.wifisurfing.dao.TrackerDao;
import com.simpletech.wifisurfing.mapper.api.TrackerMapper;
import com.simpletech.wifisurfing.mapper_shop.ShopMapper;
import com.simpletech.wifisurfing.model.Shop;
import com.simpletech.wifisurfing.util.LruTimeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@Repository
public class TrackerDaoImpl implements TrackerDao {

    @Autowired
    TrackerMapper mapper;

    @Autowired
    ShopMapper shopMapper;

    LruTimeCache<String, Shop> timeCacheShop = new LruTimeCache<>(100, 60 * 1000);

    @Override
    public Shop findShopById(String shopid) {
        Shop shop = timeCacheShop.get(shopid);
        if (shop != null) return shop;
        shop = shopMapper.findById(shopid);
        if (shop != null)
            timeCacheShop.put(shopid, shop);
        return shop;
    }
}
