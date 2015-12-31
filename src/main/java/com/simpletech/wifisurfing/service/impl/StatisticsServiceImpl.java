package com.simpletech.wifisurfing.service.impl;

import com.simpletech.wifisurfing.dao.ShopDao;
import com.simpletech.wifisurfing.mac.MacBrandMemory;
import com.simpletech.wifisurfing.mapper.api.StatisticsMapper;
import com.simpletech.wifisurfing.model.Shop;
import com.simpletech.wifisurfing.model.constant.Period;
import com.simpletech.wifisurfing.model.constant.RankingType;
import com.simpletech.wifisurfing.model.entity.*;
import com.simpletech.wifisurfing.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 统计API Service 实现
 * Created by 树朾 on 2015/9/25.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper mapper;

    @Autowired
    ShopDao shopDao;

    @Override
    public List<FrequencyMapValue> visitFrequencyMap(String idshop, Date start, Date end) {
        List<FrequencyMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String _map = "" + shop.getConfigWifiApiVisitCounts();
            _map = _map.matches("(\\d+,)+\\d+") ? _map : "1,2,5";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            int lastValue = 1, total = 0;
            for (String map : maps) {
                FrequencyMapValue value = new FrequencyMapValue(lastValue, Integer.valueOf(map), "次");
                value.setNum(mapper.visitFrequencyMap(idshop, lastValue, Integer.parseInt(map), start, end));

                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getNum() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                total += value.getNum();
                lastValue = Integer.parseInt(map) + 1;
            }
            for (FrequencyMapValue value : list) {
                value.setRate(1f * value.getNum() / total);
            }
        }
        return list;
    }


    @Override
    public List<DurationMapValue> visitDurationMap(String idshop, Date start, Date end) {
        List<DurationMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String _map = "" + shop.getConfigWifiApiVisitDuration();
            _map = _map.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? _map : "5,30,60,120";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            int total = 0;
            float lastValue = 0;
            for (String map : maps) {
                DurationMapValue value = new DurationMapValue(lastValue, Float.parseFloat(map), "分钟");
                value.setNum(mapper.visitDurationMap(idshop, (int) (lastValue * 60), (int) (Float.parseFloat(map) * 60), start, end));

                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getNum() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                total += value.getNum();
                lastValue = Float.parseFloat(map);
            }
            for (DurationMapValue value : list) {
                value.setRate(1f * value.getNum() / total);
            }
        }
        return list;
    }

    @Override
    public List<PeriodMapValue> visitPeriodMap(String idshop, Date start, Date end) {
        List<PeriodMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String _map = "" + shop.getConfigWifiApiVisitPeriod();
            _map = _map.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? _map : "1,2,4,7,14";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            int total = 0;
            float lastValue = 0;
            for (String map : maps) {
                PeriodMapValue value = new PeriodMapValue(lastValue, Float.parseFloat(map), "天");
                value.setNum(mapper.visitPeriodMap(idshop, (int) (lastValue * 24 * 60 * 60), (int) (Float.parseFloat(map) * 24 * 60 * 60), start, end));

                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getNum() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                total += value.getNum();
                lastValue = Float.parseFloat(map);
            }
            for (PeriodMapValue value : list) {
                value.setRate(1f * value.getNum() / total);
            }
        }
        return list;
    }

    @Override
    public DurationSpanValue visitDurationSpan(String idshop, Date start, Date end) {
        DurationSpanValue value = new DurationSpanValue();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int deep = (int) (shop.getConfigWifiApiVisitDurationDeep() * 60);
            int jump = (int) (shop.getConfigWifiApiVisitDurationJump() * 60);
            return mapper.visitDurationSpan(idshop, deep, jump, start, end);
        }
        return value;
    }

    @Override
    public List<DurationTrendValue> visitDurationTrend(String idshop, Period period, Date start, Date end) {
        List<DurationTrendValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            int deep = (int) (shop.getConfigWifiApiVisitDurationDeep() * 60);
            int jump = (int) (shop.getConfigWifiApiVisitDurationJump() * 60);
            switch (period) {
                case hour:
                    list = mapper.visitDurationTrendHour(idshop, deep, jump, start, end);
                    break;
                case day:
                    list = mapper.visitDurationTrendDay(idshop, deep, jump, start, end);
                    break;
                case week:
                    list = mapper.visitDurationTrendWeek(idshop, deep, jump, start, end);
                    break;
                case month:
                    list = mapper.visitDurationTrendMonth(idshop, deep, jump, start, end);
                    break;
            }
        }
        return list;
    }

    @Override
    public List<LoginAuthTrendValue> loginAuthTrend(String idshop, Period period, Date start, Date end) {
        List<LoginAuthTrendValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = mapper.loginAuthTrendHour(idshop, start, end);
                break;
            case day:
                list = mapper.loginAuthTrendDay(idshop, start, end);
                break;
            case week:
                list = mapper.loginAuthTrendWeek(idshop, start, end);
                break;
            case month:
                list = mapper.loginAuthTrendMonth(idshop, start, end);
                break;
        }
        return list;
    }

    @Override
    public List<DeviceBrandRankValue> deviceBrandRanking(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) {
        long tuv = 0, tvt = 0;
        List<DeviceBrandRankValue> list = mapper.deviceBrandRank(idshop, ranktype.name(), start, end, limit, skip);
        for (DeviceBrandRankValue value : list) {
            tuv += value.getUv();
            tvt += value.getVt();
            value.setName(MacBrandMemory.parser(value.getName()));
        }
        for (DeviceBrandRankValue value : list) {
            value.setRuv(1f * value.getUv() / tuv);
            value.setRvt(1f * value.getVt() / tvt);
        }
        return list;
    }

    @Override
    public List<DeviceBrandRankValue> deviceBrandRank(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) {
        int olimit = limit;
        limit = limit + limit / 2;

        List<DeviceBrandRankValue> list = new ArrayList<>(), tmps;
        do {
            tmps = mapper.deviceBrandRank(idshop, ranktype.name(), start, end, limit, skip);
            for (int i = 0; i < tmps.size() && list.size() < olimit - 1; i++) {
                DeviceBrandRankValue value = tmps.get(i);
                String brand = MacBrandMemory.parserNull(value.getName());
                if (brand != null) {
                    value.setName(brand);
                    list.add(value);
                }
            }
            skip += tmps.size();
        } while (list.size() < olimit - 1 && tmps.size() == limit);

        if (list.size() > 0) {
            DeviceBrandRankValue top = tmps.get(0);
            DeviceBrandRankValue total = new DeviceBrandRankValue();
            total.setVt(Float.valueOf(1f * top.getVt() / top.getRvt()).intValue());
            total.setUv(Float.valueOf(1f * top.getUv() / top.getRuv()).intValue());
            DeviceBrandRankValue other = new DeviceBrandRankValue();
            for (DeviceBrandRankValue value : list) {
                other.setVt(other.getVt() + value.getVt());
                other.setUv(other.getUv() + value.getUv());
            }
            other.setVt(total.getVt() - other.getVt());
            other.setUv(total.getUv() - other.getUv());
            other.setRvt(1f * other.getVt() / total.getVt());
            other.setRuv(1f * other.getUv() / total.getUv());

            if (other.getVt() > 0 || other.getUv() > 0) {
                other.setName("其他");
                list.add(other);
            }
        }
        return list;
    }

    @Override
    public List<UserTypeSpanValue> userTypeSpan(String idshop, Date start, Date end) {
//        List<UserTypeSpanValue> list = mapper.userTypeSpan(idshop, start, end);
//        int tvt = 0,tuv = 0;
//        for (UserTypeSpanValue value : list) {
//            tvt += value.getVt();
//            tuv += value.getUv();
//        }
//        for (UserTypeSpanValue value : list) {
//            value.setRvt(1f * value.getVt() / tvt);
//            value.setRuv(1f * value.getUv() / tuv);
//        }
//        return list;
        List<UserTypeSpanValue> list = new ArrayList<>();
        Map<String, Object> map = mapper.userTypeSpan(idshop, start, end);
        UserTypeSpanValue value1 = new UserTypeSpanValue();
        value1.setIsNewUser(true);
        try {
            value1.setUv(Integer.valueOf(map.get("nuv").toString()));
            value1.setVt(Integer.valueOf(map.get("nvt").toString()));
            value1.setRuv(Double.valueOf(map.get("rnuv").toString()).floatValue());
            value1.setRvt(Double.valueOf(map.get("rnvt").toString()).floatValue());
            value1.setStay(Double.valueOf(map.get("nstay").toString()).intValue());
            try {
                value1.setPeriod(Double.valueOf(map.get("nperiod").toString()).intValue());
            } catch (NullPointerException e) {
                value1.setPeriod(0);
            }
        } catch (NullPointerException e) {
            value1.setUv(0);
            value1.setVt(0);
            value1.setRuv(0);
            value1.setRvt(0);
            value1.setStay(0);
            value1.setPeriod(0);
        }
        UserTypeSpanValue value2 = new UserTypeSpanValue();
        value2.setIsNewUser(false);
        try {
            value2.setUv(Integer.valueOf(map.get("ouv").toString()));
            value2.setVt(Integer.valueOf(map.get("ovt").toString()));
            value2.setRuv(Double.valueOf(map.get("rouv").toString()).floatValue());
            value2.setRvt(Double.valueOf(map.get("rovt").toString()).floatValue());
            value2.setStay(Double.valueOf(map.get("ostay").toString()).intValue());
            value2.setPeriod(Double.valueOf(map.get("operiod").toString()).intValue());
        } catch (NullPointerException e) {
            value2.setUv(0);
            value2.setVt(0);
            value2.setRuv(0);
            value2.setRvt(0);
            value2.setStay(0);
            value2.setPeriod(0);
        }
        list.add(value1);
        list.add(value2);
        return list;
    }

    @Override
    public List<UserTypeTrendValue> userTypeTrend(String idshop, Period period, Date start, Date end) {
        List<UserTypeTrendValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = mapper.userTypeTrendHour(idshop, start, end);
                break;
            case day:
                list = mapper.userTypeTrendDay(idshop, start, end);
                break;
            case week:
                list = mapper.userTypeTrendWeek(idshop, start, end);
                break;
            case month:
                list = mapper.userTypeTrendMonth(idshop, start, end);
                break;
        }
        return list;
    }

    @Override
    public List<UserLivenessMapValue> userLivenessMap(String idshop, Date start, Date end) {
        List<UserLivenessMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String _map = "" + shop.getConfigWifiApiLiveness();
            _map = _map.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? _map : "1,7,15,30";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            float lastValue = 0;
            for (String map : maps) {
                UserLivenessMapValue value = new UserLivenessMapValue(lastValue, Float.parseFloat(map), "天");
                int min = (int) (lastValue * 24 * 60 * 60);
                int max = (int) (Float.parseFloat(map) * 24 * 60 * 60);
                UserLivenessTrendValue trendValue = mapper.userLivenessMap(idshop, min, max, start, end);
                value.setUv(trendValue.getUv());
                value.setVt(trendValue.getVt());
                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getUv() > 0 || value.getVt() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                lastValue = Float.parseFloat(map);
            }
            String[] remarks = {"高活跃度顾客", "中活跃度顾客", "低活跃度顾客", "沉睡顾客", "深度沉睡顾客"};
            for (int i = 0; i < list.size(); i++) {
                if (i < remarks.length) {
                    list.get(i).setRemark(remarks[i]);
                } else {
                    list.get(i).setRemark(remarks[remarks.length - 1]);
                }
            }
        }
        return list;
    }

    @Override
    public List<UserLivenessTrendMapValue> userLivenessTrend(String idshop, Period period, Date start, Date end) {
        List<UserLivenessTrendMapValue> list = new ArrayList<>();
        Shop shop = shopDao.findById(idshop);
        if (shop != null) {
            shop = new ShopEntity(shop);
            String _map = "" + shop.getConfigWifiApiLiveness();
            _map = _map.matches("(\\d[\\d\\.]*,)+\\d[\\d\\.]*") ? _map : "1,7,15,30";
            _map = _map + "," + Integer.MAX_VALUE;
            String[] maps = _map.split(",");
            float lastValue = 0;
            for (String map : maps) {
                UserLivenessTrendMapValue value = new UserLivenessTrendMapValue(lastValue, Float.parseFloat(map), "天");
                int min = (int) (lastValue * 24 * 60 * 60);
                int max = (int) (Float.parseFloat(map) * 24 * 60 * 60);
                switch (period) {
                    case hour:
                        value.setTrend(mapper.userLivenessTrendHour(idshop, min, max, start, end));
                        break;
                    case day:
                        value.setTrend(mapper.userLivenessTrendDay(idshop, min, max, start, end));
                        break;
                    case week:
                        value.setTrend(mapper.userLivenessTrendWeek(idshop, min, max, start, end));
                        break;
                    case month:
                        value.setTrend(mapper.userLivenessTrendMonth(idshop, min, max, start, end));
                        break;
                }
                if (map.equals(String.valueOf(Integer.MAX_VALUE))) {
                    if (value.getTrend() != null && value.getTrend().size() > 0) {
                        list.add(value);
                    }
                } else {
                    list.add(value);
                }
                lastValue = Float.parseFloat(map);
            }
            String[] remarks = {"高活跃度顾客", "中活跃度顾客", "低活跃度顾客", "沉睡顾客", "深度沉睡顾客"};
            for (int i = 0; i < list.size(); i++) {
                if (i < remarks.length) {
                    list.get(i).setRemark(remarks[i]);
                } else {
                    list.get(i).setRemark(remarks[remarks.length - 1]);
                }
            }
        }
        return list;
    }

    @Override
    public List<LoginAuthTypeRankValue> loginAuthTypeRank(String idshop, RankingType ranktype, Date start, Date end, int limit, int skip) {
        return mapper.loginAuthTypeRank(idshop, ranktype.name(), start, end, limit, skip);
    }

    @Override
    public List<LoginAuthTypeTrendValue> loginAuthTypeTrend(String idshop, Period period, Date start, Date end) {
        List<LoginAuthTypeTrendValue> list = new ArrayList<>();
        switch (period) {
            case hour:
                list = mapper.loginAuthTypeTrendHour(idshop, start, end);
                break;
            case day:
                list = mapper.loginAuthTypeTrendDay(idshop, start, end);
                break;
            case week:
                list = mapper.loginAuthTypeTrendWeek(idshop, start, end);
                break;
            case month:
                list = mapper.loginAuthTypeTrendMonth(idshop, start, end);
                break;
        }
        return list;
    }
}
