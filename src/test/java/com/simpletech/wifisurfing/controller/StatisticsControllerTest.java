package com.simpletech.wifisurfing.controller;

import com.simpletech.wifisurfing.aspect.LoggingAspect;
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
 * StatisticsControllerTest
 * Created by Administrator on 2015/12/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsControllerTest {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsController controller;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void testVisitFrequencyMap() throws Exception {

    }

    @Test
    public void testVisitDurationSpan() throws Exception {

    }

    @Test
    public void testVisitDurationTrend() throws Exception {

    }

    @Test
    public void testVisitDurationMap() throws Exception {

    }

    @Test
    public void testVisitPeriodMap() throws Exception {

    }

    @Test
    public void testDeviceBrandRanking() throws Exception {

    }

    @Test
    public void testDeviceBrandRank() throws Exception {
        Object result = controller.deviceBrandRank("3", RankingType.vt, 10, 1, null, null, monthf.parse("2015-12-0"), monthf.parse("2015-12-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testUserTypeSpan() throws Exception {

    }

    @Test
    public void testUserTypeTrend() throws Exception {

    }

    @Test
    public void testUserLivenessMap() throws Exception {

    }

    @Test
    public void testUserLivenessTrend() throws Exception {

    }

    @Test
    public void testLoginAuthTrend() throws Exception {

    }

    @Test
    public void testLoginAuthTypeRank() throws Exception {

    }

    @Test
    public void testLoginAuthTypeTrend() throws Exception {

    }
}