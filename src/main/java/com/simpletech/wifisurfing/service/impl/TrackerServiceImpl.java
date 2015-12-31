package com.simpletech.wifisurfing.service.impl;

import com.simpletech.wifisurfing.dao.TrackerDao;
import com.simpletech.wifisurfing.mac.MacBrandMemory;
import com.simpletech.wifisurfing.mapper.api.TrackerMapper;
import com.simpletech.wifisurfing.mapper_log.WifiLogMapper;
import com.simpletech.wifisurfing.model.Login;
import com.simpletech.wifisurfing.model.Shop;
import com.simpletech.wifisurfing.model.Visit;
import com.simpletech.wifisurfing.model.WifiLog;
import com.simpletech.wifisurfing.service.TrackerService;
import com.simpletech.wifisurfing.util.ServiceException;
import com.simpletech.wifisurfing.util.SynchronizedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@Service
public class TrackerServiceImpl implements TrackerService {


    @Autowired
    WifiLogMapper wifiLogMapper;

    @Autowired
    TrackerDao dao;

    @Autowired
    TrackerMapper mapper;

    SynchronizedLock<String> visitLock = new SynchronizedLock<>(1000);

    @Override
    public boolean wifilogin(Login login) {
        Date now = new Date();
        Shop shop = dao.findShopById(login.getIdshop());
        if (shop != null) {
            Integer configUserExpired = shop.getConfigWifiUserExpired();
            if (configUserExpired == null || configUserExpired.equals(0)) configUserExpired = 365;

            Login last = mapper.findLastLoginByMacAndShop(login.getIdshop(), login.getMacDevice());
            login.setUpdateTime(now);
            login.setCreateTime(now);
            login.setId(UUID.randomUUID().toString());
            login.setEndBrand(MacBrandMemory.parserBrandMac(login.getMacDevice()));
            login.setIsNewUser((last == null || last.getCreateTime().getTime() < now.getTime() - configUserExpired * 24 * 60 * 60 * 1000l));
            login.setTimeFromLast((int) ((now.getTime() - ((last != null) ? last.getCreateTime().getTime() : now.getTime())) / 1000l));//除以1000换算成秒
            mapper.insertLogin(login);
            return true;
        }
        throw new ServiceException("shopid 无效");
    }

    @Override
    public boolean wifiauth(Date time, String mac, String shopid, String wifiid) {
        Date now = new Date();
        Shop shop = dao.findShopById(shopid);
        if (shop != null) {
            Integer configUserExpired = shop.getConfigWifiUserExpired();
            Integer configVisitExpired = shop.getConfigWifiVisitExpired();
            if (configUserExpired == null || configUserExpired.equals(0)) configUserExpired = 365;
            if (configVisitExpired == null || configVisitExpired.equals(0)) configVisitExpired = 60;

            synchronized (visitLock.getLock(shopid + mac)) {
                Visit last = mapper.findLastVisitByMacAndShop(shopid, mac);
                WifiLog log = new WifiLog();
                if (last == null || last.getTimeLeave().getTime() < now.getTime() - configVisitExpired * 60 * 1000l) {
                    Visit visit = new Visit();
                    visit.setMacDevice(mac);
                    visit.setIdwifi(wifiid);
                    visit.setIdshop(shopid);
                    visit.fillNullID();
                    visit.setCreateTime(now);
                    visit.setUpdateTime(now);
                    visit.setTimeEntry(now);
                    visit.setTimeLeave(now);
                    visit.setTimeDuration(0);
                    visit.setCountLogs(1);
                    visit.setEndBrand(MacBrandMemory.parserBrandMac(mac));
                    visit.setTimeFromLast((int) ((now.getTime() - ((last != null) ? last.getCreateTime().getTime() : now.getTime())) / 1000l));//除以1000换算成秒
                    visit.setIsNewUser((last == null || last.getCreateTime().getTime() < now.getTime() - configUserExpired * 24 * 60 * 60 * 1000l));
                    log.setIdvisit(visit.getId());
                    mapper.insertVisit(visit);
                } else {
                    last.setUpdateTime(now);
                    last.setTimeLeave(now);
                    last.setCountLogs(1 + last.getCountLogs() + 1);
                    last.setTimeDuration((int) (now.getTime() - last.getTimeEntry().getTime()) / 1000);
                    log.setIdvisit(last.getId());
                    mapper.updateVisitByLog(last);
                }
                log.setUpdateTime(now);
                log.setCreateTime(now);
                log.setTimeLocal(time);
                log.setId(UUID.randomUUID().toString());
                log.setMacDevice(mac);
                log.setIdshop(shopid);
                log.setIdwifi(wifiid);
                wifiLogMapper.insert(log);
                return true;
            }
        }
        throw new ServiceException("shopid 无效");
    }
}
