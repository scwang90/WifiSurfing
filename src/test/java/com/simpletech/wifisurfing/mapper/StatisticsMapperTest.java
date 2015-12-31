package com.simpletech.wifisurfing.mapper;

import com.simpletech.wifisurfing.aspect.LoggingAspect;
import com.simpletech.wifisurfing.mapper.api.StatisticsMapper;
import com.simpletech.wifisurfing.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 统计map测试类
 * Created by Administrator on 2015/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsMapperTest {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsMapper mapper;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void loginAuthTrendDay() throws Exception {
        Object result = mapper.loginAuthTrendDay("2", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitDurationTrendHour() throws Exception {
        Object result = mapper.visitDurationTrendHour("", 1000, 100, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitDurationSpan() throws Exception {
        Object result = mapper.visitDurationSpan("", 1000, 100, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitDurationMap() throws Exception {
        Object result = mapper.visitDurationMap("", 1000, 100, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitPeriodMap() throws Exception {
        Object result = mapper.visitPeriodMap("", 1000, 10, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void visitFrequencyMap() throws Exception {
        Object result = mapper.visitFrequencyMap("", 1000, 10, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void deviceBrandRank() throws Exception {
        Object result = mapper.deviceBrandRank("123", "uv", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void loginAuthTypeRank() throws Exception {
        Object result = mapper.loginAuthTypeRank("", "uv", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void loginAuthTypeTrend() throws Exception {
        Object result = mapper.loginAuthTypeTrendDay("", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void customerLivenessTrendHour() throws Exception {
        Object result = mapper.userLivenessTrendHour("123",0,100000000, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

}