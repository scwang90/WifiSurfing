package com.simpletech.wifisurfing.service;


import com.simpletech.wifisurfing.model.Login;

import java.util.Date;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
public interface TrackerService {

    /**
     * 接收探针日志数据-登录
     */
    boolean wifilogin(Login login);

    /**
     * 接收探针日志数据-持续
     */
    boolean wifiauth(Date time, String mac, String shopid, String wifiid);
}
