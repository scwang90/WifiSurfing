package com.simpletech.wifisurfing.service;

import com.simpletech.wifisurfing.aspect.LoggingAspect;
import com.simpletech.wifisurfing.model.constant.Period;
import com.simpletech.wifisurfing.model.constant.RankingType;
import com.simpletech.wifisurfing.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 统计服务测试
 * Created by Administrator on 2015/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsServiceTest {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsService service;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void testVisitFrequencyMap() throws Exception {
        Object result = service.visitFrequencyMap("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitDurationMap() throws Exception {
        Object result = service.visitDurationMap("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitPeriodMap() throws Exception {
        Object result = service.visitPeriodMap("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitDurationSpan() throws Exception {
        Object result = service.visitDurationSpan("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitDurationTrend() throws Exception {
        Object result = service.visitDurationTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testLoginAuthTrend() throws Exception {
        Object result = service.loginAuthTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testDeviceBrandRank() throws Exception {
        Object result = service.deviceBrandRanking("123", RankingType.uv, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testCustomerTypeSpan() throws Exception {
        Object result = service.userTypeSpan("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));

    }

    @Test
    public void testCustomerTypeTrend() throws Exception {
        Object result = service.userTypeTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testLoginAuthTypeRank() throws Exception {
        Object result = service.loginAuthTypeRank("1", RankingType.uv, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testLoginAuthTypeTrend() throws Exception {
        Object result = service.loginAuthTypeTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void userLivenessTrend() throws Exception {
        Object result = service.userLivenessTrend("123", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void userLivenessMap() throws Exception {
        Object result = service.userLivenessMap("123", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

}