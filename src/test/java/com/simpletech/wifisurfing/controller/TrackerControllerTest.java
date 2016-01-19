package com.simpletech.wifisurfing.controller;

import com.simpletech.wifisurfing.util.ServiceException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * TrackerControllerTest
 * Created by root on 16-1-19.
 */

public class TrackerControllerTest {

    private String compateArg(HashMap body, String remark, String... names) {
        Set<Map.Entry> set = body.entrySet();
        for (String name : names) {
            for (Map.Entry entity : set) {
                if (name.equalsIgnoreCase(entity.getKey().toString())) {
                    return entity.getValue().toString();
                }
            }
        }
        throw new ServiceException("缺少参数：" + names[0] + "：" + remark);
    }

    @Test
    public void testCompateArg() {
        HashMap body = new HashMap();
        body.put("name","scwang");
        body.put("argmE","Arg");
        System.out.println(compateArg(body,"名称","name"));
        System.out.println(compateArg(body,"参数","argme"));
        System.out.println(compateArg(body,"多媒","are","name"));
        System.out.println(compateArg(body,"多媒2","argmess"));
    }

}