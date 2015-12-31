package com.simpletech.wifisurfing.controller;

import com.simpletech.wifisurfing.model.Login;
import com.simpletech.wifisurfing.service.TrackerService;
import com.simpletech.wifisurfing.util.JacksonUtil;
import com.simpletech.wifisurfing.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Wifi 探针 接收API
 * Created by Administrator on 2015/10/30.
 */
@RestController
@RequestMapping("v1/tracker")
public class TrackerController {

    @Autowired
    TrackerService service;

    private String mustArg(HashMap body, String name, String remark) {
        Object value = body.get(name);
        if (value == null) {
            throw new ServiceException("缺少参数：" + name + "：" + remark);
        }
        return value.toString();
    }

    /**
     * 接收探针日志数据-登录
     *
     * @param body JSON 数据 {time:1442416546,type:1,mac:"14:f6:5a:90:a6:63",shopid:"B068B6FFB1C4",wifiid:"B068B6FFB1C4"}
     */
    @RequestMapping("login")
    public Object wifilogin(@RequestBody HashMap body) throws Exception {
        Date time = new Date(Long.parseLong(mustArg(body, "time", "时间戳")));
        String mac = mustArg(body, "mac", "网卡地址");
        String shopid = mustArg(body, "shopid", "店铺ID");
        String wifiid = mustArg(body, "wifiid", "网关ID");
        Integer type = Integer.parseInt(mustArg(body, "type", "登录类型"));

        Login login = new Login();
        login.setMacDevice(mac);
        login.setIdshop(shopid);
        login.setIdwifi(wifiid);
        login.setAuthType(type);
        login.setTimeLocal(time);

        login.setOpenid("" + body.get("openID"));
        login.setAppid("" + body.get("appID"));
        login.setWxshopid("" + body.get("weChatShopID"));

        return service.wifilogin(login);
    }

    /**
     * 接收探针日志数据-持续
     *
     * @param body JSON 数据 {timestamp:1442416546,mac:"14:f6:5a:90:a6:63",shopID:"B068B6FFB1C4",gw_id:"B068B6FFB1C4"}
     */
    @RequestMapping("auth")
    public Object wifiauth(@RequestBody HashMap body) throws Exception {
        Date time = new Date(Long.parseLong(mustArg(body, "timestamp", "时间戳")));
        String mac = mustArg(body, "mac", "网卡地址");
        String shopid = mustArg(body, "shopID", "店铺ID");
        String wifiid = mustArg(body, "gw_id", "网关ID");
        return service.wifiauth(time, mac, shopid, wifiid);
    }

    /**
     * 接收探针日志数据
     *
     * @param request JSON 数据 {data:[{time:"2015-08-13 14:53:23",rssi:-78,mac:"14:f6:5a:90:a6:63",id:"B068B6FFB1C4"},...]}
     */
    @RequestMapping("{path}/compat")
    public Object compat(@PathVariable String path, HttpServletRequest request) throws Exception {
        String body = "", tmp;
        BufferedReader reader = request.getReader();
        while (null != (tmp = reader.readLine())) {
            body = body + tmp;
        }
        if (!body.startsWith("{") && body.indexOf("{") > 0) {
            body = body.substring(body.indexOf("{"));
        }
        if (body.indexOf("[]") > 0) {
            return false;
        }
        if ("login".equals(path))
            return wifilogin(JacksonUtil.toObject(body, LinkedHashMap.class));
        else if ("auth".equals(path))
            return wifiauth(JacksonUtil.toObject(body, LinkedHashMap.class));
        else
            return false;
    }
}
