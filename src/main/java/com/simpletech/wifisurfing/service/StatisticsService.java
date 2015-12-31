package com.simpletech.wifisurfing.service;


import com.simpletech.wifisurfing.model.constant.Period;
import com.simpletech.wifisurfing.model.constant.RankingType;
import com.simpletech.wifisurfing.model.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 统计API Service
 * Created by 树朾 on 2015/9/25.
 */
public interface StatisticsService {

    /**
     * 店铺-到访频次-分布
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end);

    /**
     * 店铺-驻店时长-分布
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationMapValue> visitDurationMap(String idshop, Date start, Date end);

    /**
     * 统计店铺的到访周期
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<PeriodMapValue> visitPeriodMap(String idshop, Date start, Date end);

    /**
     * 店铺-驻店时长-时段
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    DurationSpanValue visitDurationSpan(String idshop, Date start, Date end);

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    List<DurationTrendValue> visitDurationTrend(String idshop, Period period, Date start, Date end);

    /**
     * 店铺-入店人次-趋势
     *
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间 ("yyyyMMddHHmmss")
     * @param end    结束时间 ("yyyyMMddHHmmss")
     * @return 统计数据
     */
    List<LoginAuthTrendValue> loginAuthTrend(String idshop, Period period, Date start, Date end);


    /**
     * 店铺-设备品牌-排行
     *
     * @param idshop   网站ID
     * @param ranktype 排序类型 按 vt|uv
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行数据
     */
    List<DeviceBrandRankValue> deviceBrandRanking(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip);
    List<DeviceBrandRankValue> deviceBrandRank(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip);

    /**
     * 店铺-新老用户-时段
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    List<UserTypeSpanValue> userTypeSpan(String idshop, Date start, Date end);

    /**
     * 店铺-新老用户-趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户趋势
     */
    List<UserTypeTrendValue> userTypeTrend(String idshop, Period period, Date start, Date end);

    /**
     * 店铺-活跃度-分布
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 活跃度分布
     */
    List<UserLivenessMapValue> userLivenessMap(String idshop, Date start, Date end);

    /**
     * 店铺-活跃度-趋势
     *
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 活跃度趋势
     */
    List<UserLivenessTrendMapValue> userLivenessTrend(String idshop, Period period, Date start, Date end);


    /**
     * 店铺-登录验证类型-排行
     *
     * @param idshop   网站ID
     * @param ranktype 排序规则
     * @param start    开始时间
     * @param end      结束时间
     * @param limit    分页限制
     * @param skip     分页起始
     * @return 排行
     */
    List<LoginAuthTypeRankValue> loginAuthTypeRank(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip);
    /**
     * 店铺-登录验证类型-趋势
     *
     * @param idshop 网站ID
     * @param period 时段周期 [时|日|周|月]
     * @param start  开始时间
     * @param end    结束时间
     * @return 设备品牌排行
     */
    List<LoginAuthTypeTrendValue> loginAuthTypeTrend(String idshop, Period period, Date start, Date end);
}
