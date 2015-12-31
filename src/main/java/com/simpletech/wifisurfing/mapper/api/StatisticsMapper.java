package com.simpletech.wifisurfing.mapper.api;

import com.simpletech.wifisurfing.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 统计Mapper接口
 * Created by 树朾 on 2015/9/29.
 */
public interface StatisticsMapper {

    /**
     * 店铺-到访频次-分布
     * 统计店铺到访频次在min，max之间的条数
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM (SELECT COUNT(id) num FROM login WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY mac_device ) AS t WHERE t.num>=#{min} AND t.num<=#{max} ")
    int visitFrequencyMap(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-到访周期-分布
     * 统计店铺到访周期在min，max之间的条数
     *
     * @param idshop      网站ID
     * @param start       开始时间
     * @param end         结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(*)" +
            "FROM (SELECT AVG(time_from_last) period\n" +
            "   FROM visit\n" +
            "   WHERE idshop=#{idshop} \n" +
            "   AND time_from_last > 0\n" +
            "   AND (create_time BETWEEN #{start} AND #{end}) \n" +
            "   GROUP BY mac_device) AS t\n" +
            "WHERE t.period>=#{min} AND t.period<=#{max} \n")
    int visitPeriodMap(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);


    /**
     *
     * 店铺-驻店时长-分布
     * 统计店铺停留时间在min，max之间的数量
     *
     * @param idshop 网站ID
     * @param min    最小持续时间
     * @param max    最大持续时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT COUNT(id) FROM visit WHERE idshop=#{idshop} AND time_duration>#{min} AND time_duration<=#{max} AND (create_time BETWEEN #{start} AND #{end}) ")
    int visitDurationMap(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-驻店时长-时段
     *
     * @param idshop 网站ID
     * @param deep   深访标准
     * @param jump   跳出标准
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  *,\n" +
            "  amount_deep / amount_total rate_deep,\n" +
            "  amount_jump / amount_total rate_jump\n" +
            "FROM (SELECT\n" +
            "        AVG(time_duration)                  dur_entry,\n" +
            "        (SELECT AVG(time_duration)\n" +
            "         FROM visit\n" +
            "         WHERE time_duration >= #{deep})    dur_deep,\n" +
            "        COUNT(id)                           amount_total,\n" +
            "        SUM(time_duration >= #{deep})       amount_deep,\n" +
            "        SUM(time_duration <= #{jump})       amount_jump\n" +
            "      FROM visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end}) ) AS t")
    DurationSpanValue visitDurationSpan(@Param("idshop") String idshop, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-驻店时长-趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H')  date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendHour(@Param("idshop") String idshop, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d')    date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendDay(@Param("idshop") String idshop, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u')     date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendWeek(@Param("idshop") String idshop, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  date,dur_avg, deep, jump,\n" +
            "  deep / entry_count       rdeep,\n" +
            "  jump / entry_count       rjump\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m')      date,\n" +
            "        COUNT(id)                             entry_count,\n" +
            "        AVG(time_duration)                    dur_avg,\n" +
            "        SUM(time_duration >= #{deep})         deep,\n" +
            "        SUM(time_duration <= #{jump})         jump\n" +
            "      FROM visit\n" +
            "      WHERE idshop=#{idshop}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end}) " +
            "      GROUP BY date) AS t")
    List<DurationTrendValue> visitDurationTrendMonth(@Param("idshop") String idshop, @Param("deep") int deep, @Param("jump") int jump, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-认证（次数、用户、新老）-趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 统计数据
     */
    @Select("SELECT\n" +
            "  date,vt,uv,nuv,uv - nuv ouv,\n" +
            "  nuv / uv rnuv,\n" +
            "  (uv - nuv) / uv rouv\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m%d%H')           date,\n" +
            "    COUNT(id)                                      vt,\n" +
            "    COUNT(DISTINCT mac_device)                     uv,\n" +
            "    SUM(is_new_user = 1)                           nuv\n" +
            "  FROM login\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<LoginAuthTrendValue> loginAuthTrendHour(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  date,vt,uv,nuv,uv - nuv ouv,\n" +
            "  nuv / uv rnuv,\n" +
            "  (uv - nuv) / uv rouv\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m%d')           date,\n" +
            "    COUNT(id)                                    vt,\n" +
            "    COUNT(DISTINCT mac_device)                   uv,\n" +
            "    SUM(is_new_user = 1)                         nuv\n" +
            "  FROM login\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<LoginAuthTrendValue> loginAuthTrendDay(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  date,vt,uv,nuv,uv - nuv ouv,\n" +
            "  nuv / uv rnuv,\n" +
            "  (uv - nuv) / uv rouv\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y-%u')            date,\n" +
            "    COUNT(id)                                    vt,\n" +
            "    COUNT(DISTINCT mac_device)                   uv,\n" +
            "    SUM(is_new_user = 1)                         nuv\n" +
            "  FROM login\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<LoginAuthTrendValue> loginAuthTrendWeek(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  date,vt,uv,nuv,uv - nuv ouv,\n" +
            "  nuv / uv rnuv,\n" +
            "  (uv - nuv) / uv rouv\n" +
            "FROM (\n" +
            "  SELECT\n" +
            "    DATE_FORMAT(create_time, '%y%m')             date,\n" +
            "    COUNT(id)                                    vt,\n" +
            "    COUNT(DISTINCT mac_device)                   uv,\n" +
            "    SUM(is_new_user = 1)                         nuv\n" +
            "  FROM login\n" +
            "  WHERE idshop=#{idshop}\n" +
            "    AND (create_time BETWEEN #{start} AND #{end}) " +
            "    GROUP BY date) AS t")
    List<LoginAuthTrendValue> loginAuthTrendMonth(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-设备品牌-排行
     *
     * @param idshop 网站ID
     * @param type   排序规则
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 设备品牌排行
     */
    @Select("SELECT end_brand name, COUNT(id) vt,COUNT(DISTINCT mac_device) uv FROM login WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY name ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<DeviceBrandRankValue> deviceBrandRank(@Param("idshop") String idshop, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 店铺-登录验证类型-排行
     *
     * @param idshop 网站ID
     * @param type   排序规则
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页限制
     * @param skip   分页起始
     * @return 设备品牌排行
     */
    @Select("SELECT auth_type type, COUNT(id) vt,COUNT(DISTINCT mac_device) uv FROM login WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end}) GROUP BY auth_type ORDER BY ${type} DESC LIMIT ${skip},${limit}")
    List<LoginAuthTypeRankValue> loginAuthTypeRank(@Param("idshop") String idshop, @Param("type") String type, @Param("start") Date start, @Param("end") Date end, @Param("limit") int limit, @Param("skip") int skip);

    /**
     * 店铺-登录验证类型-趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 设备品牌排行
     */
    @Select("SELECT\n" +
            "  *,\n" +
            "  free / total   rfree,\n" +
            "  onekey / total ronekey,\n" +
            "  weixin / total rweixin\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H') date,\n" +
            "        COUNT(id)                          total,\n" +
            "        SUM(auth_type = 0)                 free,\n" +
            "        SUM(auth_type = 1)                 onekey,\n" +
            "        SUM(auth_type = 2)                 weixin\n" +
            "      FROM login\n" +
            "      WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<LoginAuthTypeTrendValue> loginAuthTypeTrendHour(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  *,\n" +
            "  free / total   rfree,\n" +
            "  onekey / total ronekey,\n" +
            "  weixin / total rweixin\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d') date,\n" +
            "        COUNT(id)                          total,\n" +
            "        SUM(auth_type = 0)                 free,\n" +
            "        SUM(auth_type = 1)                 onekey,\n" +
            "        SUM(auth_type = 2)                 weixin\n" +
            "      FROM login\n" +
            "      WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<LoginAuthTypeTrendValue> loginAuthTypeTrendDay(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  *,\n" +
            "  free / total   rfree,\n" +
            "  onekey / total ronekey,\n" +
            "  weixin / total rweixin\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u') date,\n" +
            "        COUNT(id)                          total,\n" +
            "        SUM(auth_type = 0)                 free,\n" +
            "        SUM(auth_type = 1)                 onekey,\n" +
            "        SUM(auth_type = 2)                 weixin\n" +
            "      FROM login\n" +
            "      WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<LoginAuthTypeTrendValue> loginAuthTypeTrendWeek(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  *,\n" +
            "  free / total   rfree,\n" +
            "  onekey / total ronekey,\n" +
            "  weixin / total rweixin\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m') date,\n" +
            "        COUNT(id)                          total,\n" +
            "        SUM(auth_type = 0)                 free,\n" +
            "        SUM(auth_type = 1)                 onekey,\n" +
            "        SUM(auth_type = 2)                 weixin\n" +
            "      FROM login\n" +
            "      WHERE idshop=#{idshop} AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<LoginAuthTypeTrendValue> loginAuthTypeTrendMonth(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);


    /**
     * 店铺-新老用户-时段
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 新老用户
     */
    /*@Select("SELECT\n" +
            "  is_new_user isNewUser,\n" +
            "  vt,uv,\n" +
            "  stay,\n" +
            "  total / num period\n" +
            "FROM (SELECT\n" +
            "        is_new_user,\n" +
            "        COUNT(id)                                  vt,\n" +
            "        COUNT(DISTINCT mac_device)                 uv,\n" +
            "        AVG(time_duration)                         stay,\n" +
            "        SUM(time_from_last > 0)                    num,\n" +
            "        SUM((time_from_last > 0) * time_from_last) total\n" +
            "      FROM visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "      GROUP BY is_new_user) AS t")
    List<UserTypeSpanValue> */

    @Select("SELECT\n" +
            "  uv,nuv,ovt,\n" +
            "  uv - nuv        ouv,\n" +
            "  vt - ovt        nvt,\n" +
            "  nuv / uv        rnuv,\n" +
            "  ovt / vt        rovt,\n" +
            "  (uv - nuv) / uv rouv,\n" +
            "  (vt - ovt) / vt rnvt,\n" +
            "  tnd / cnd       nstay,\n" +
            "  tod / cod       ostay,\n" +
            "  tnp / cnp       nperiod,\n" +
            "  top / cop       operiod\n" +
            "FROM (SELECT\n" +
            "        COUNT(id)                  vt,\n" +
            "        COUNT(DISTINCT mac_device) uv,\n" +
            "        SUM(is_new_user)           nuv,\n" +
            "        SUM(is_new_user=0)         ovt\n" +
            "      FROM visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "        AND (create_time BETWEEN #{start} AND #{end})\n" +
            "     ) AS t1,\n" +
            "  (SELECT\n" +
            "     SUM(time_duration * (is_new_user = 1))        tnd,\n" +
            "     SUM((time_duration > 0) * (is_new_user = 1))  cnd,\n" +
            "     SUM(time_duration * (is_new_user = 0))        tod,\n" +
            "     SUM((time_duration > 0) * (is_new_user = 0))  cod,\n" +
            "     SUM(time_from_last * (is_new_user = 1))       tnp,\n" +
            "     SUM((time_from_last > 0) * (is_new_user = 1)) cnp,\n" +
            "     SUM(time_from_last * (is_new_user = 0))       top,\n" +
            "     SUM((time_from_last > 0) * (is_new_user = 0)) cop\n" +
            "   FROM visit\n" +
            "   WHERE idshop = #{idshop}\n" +
            "     AND (create_time BETWEEN #{start} AND #{end})\n" +
            "  ) AS t2")
    Map<String,Object> userTypeSpan(@Param("idshop") String idshop ,@Param("start") Date start, @Param("end") Date end);
    /**
     * 店铺-新老用户-趋势
     *
     * @param idshop 网站ID
     * @param start  开始时间
     * @param end    结束时间
     * @return 数量
     */
    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d%H')   date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendHour(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m%d')     date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendDay(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y-%u')      date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendWeek(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  *,\n" +
            "  uv-nv ov,\n" +
            "  nv/uv rnv,\n" +
            "  (uv-nv)/uv rov\n" +
            "FROM (SELECT\n" +
            "        DATE_FORMAT(create_time, '%y%m')       date,\n" +
            "        COUNT(DISTINCT mac_device)             uv,\n" +
            "        SUM(is_new_user)                       nv\n" +
            "      FROM visit\n" +
            "      WHERE idshop = #{idshop}\n" +
            "            AND (create_time BETWEEN #{start} AND #{end})\n" +
            "      GROUP BY date) AS t")
    List<UserTypeTrendValue> userTypeTrendMonth(@Param("idshop") String idshop, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-顾客活跃度-分布
     * @param idshop 网站ID
     * @param min    最小周期时间
     * @param max    最大周期时间
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})")
    UserLivenessTrendValue userLivenessMap(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

    /**
     * 店铺-顾客活跃度-趋势
     * @param idshop 网站ID
     * @param min    最小周期时间
     * @param max    最大周期时间
     * @param start  开始时间
     * @param end    结束时间
     */
    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y%m%d%H')     date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendHour(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y%m%d')       date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendDay(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y-%u')     date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendWeek(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);
    @Select("SELECT\n" +
            "  DATE_FORMAT(create_time, '%y%m')     date,\n" +
            "  COUNT(id)                                vt,\n" +
            "  COUNT(DISTINCT mac_device)               uv\n" +
            "FROM visit\n" +
            "WHERE idshop = #{idshop}\n" +
            "  AND(time_from_last>=#{min} AND time_from_last<=#{max})\n" +
            "  AND (create_time BETWEEN #{start} AND  #{end})\n" +
            "GROUP BY date")
    List<UserLivenessTrendValue> userLivenessTrendMonth(@Param("idshop") String idshop, @Param("min") int min, @Param("max") int max, @Param("start") Date start, @Param("end") Date end);

}
