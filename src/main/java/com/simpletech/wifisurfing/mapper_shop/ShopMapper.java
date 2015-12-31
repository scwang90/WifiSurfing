package com.simpletech.wifisurfing.mapper_shop;

import com.simpletech.wifisurfing.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;
import com.simpletech.wifisurfing.model.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 数据库表shop的mapper接口
 * @author 树朾
 * @date 2015-11-25 17:33:49 中国标准时间
 */
public interface ShopMapper extends MybatisMultiDao<Shop>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO shop ( shopID , shopName , config_wifi_visit_expired , config_wifi_user_expired , config_wifi_api_visit_counts , config_wifi_api_visit_duration , config_wifi_api_visit_duration_deep , config_wifi_api_visit_duration_jump , config_wifi_api_visit_period , config_wifi_api_liveness , createTime ) VALUES ( #{shopID} , #{shopName} , #{configWifiVisitExpired} , #{configWifiUserExpired} , #{configWifiApiVisitCounts} , #{configWifiApiVisitDuration} , #{configWifiApiVisitDurationDeep} , #{configWifiApiVisitDurationJump} , #{configWifiApiVisitPeriod} , #{configWifiApiLiveness} , #{createTime} )")
	int insert(Shop model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM shop WHERE shopID=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE shop SET shopID=#{shopID} , shopName=#{shopName} , config_wifi_visit_expired=#{configWifiVisitExpired} , config_wifi_user_expired=#{configWifiUserExpired} , config_wifi_api_visit_counts=#{configWifiApiVisitCounts} , config_wifi_api_visit_duration=#{configWifiApiVisitDuration} , config_wifi_api_visit_duration_deep=#{configWifiApiVisitDurationDeep} , config_wifi_api_visit_duration_jump=#{configWifiApiVisitDurationJump} , config_wifi_api_visit_period=#{configWifiApiVisitPeriod} , config_wifi_api_liveness=#{configWifiApiLiveness} , createTime=#{createTime} WHERE shopID=#{shopID} ")
	int update(Shop model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM shop")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT shopID , shopName , config_wifi_visit_expired configWifiVisitExpired , config_wifi_user_expired configWifiUserExpired , config_wifi_api_visit_counts configWifiApiVisitCounts , config_wifi_api_visit_duration configWifiApiVisitDuration , config_wifi_api_visit_duration_deep configWifiApiVisitDurationDeep , config_wifi_api_visit_duration_jump configWifiApiVisitDurationJump , config_wifi_api_visit_period configWifiApiVisitPeriod , config_wifi_api_liveness configWifiApiLiveness , createTime FROM shop WHERE shopID=#{id}")
	Shop findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT shopID , shopName , config_wifi_visit_expired configWifiVisitExpired , config_wifi_user_expired configWifiUserExpired , config_wifi_api_visit_counts configWifiApiVisitCounts , config_wifi_api_visit_duration configWifiApiVisitDuration , config_wifi_api_visit_duration_deep configWifiApiVisitDurationDeep , config_wifi_api_visit_duration_jump configWifiApiVisitDurationJump , config_wifi_api_visit_period configWifiApiVisitPeriod , config_wifi_api_liveness configWifiApiLiveness , createTime FROM shop ${order}")
	List<Shop> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT shopID , shopName , config_wifi_visit_expired configWifiVisitExpired , config_wifi_user_expired configWifiUserExpired , config_wifi_api_visit_counts configWifiApiVisitCounts , config_wifi_api_visit_duration configWifiApiVisitDuration , config_wifi_api_visit_duration_deep configWifiApiVisitDurationDeep , config_wifi_api_visit_duration_jump configWifiApiVisitDurationJump , config_wifi_api_visit_period configWifiApiVisitPeriod , config_wifi_api_liveness configWifiApiLiveness , createTime FROM shop ${order} LIMIT ${start},${limit}")
	List<Shop> findByPage(@Param("order") String order, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM shop ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM shop WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM shop ${where}")
	int countWhere(@Param("where") String where);
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value);
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT shopID , shopName , config_wifi_visit_expired configWifiVisitExpired , config_wifi_user_expired configWifiUserExpired , config_wifi_api_visit_counts configWifiApiVisitCounts , config_wifi_api_visit_duration configWifiApiVisitDuration , config_wifi_api_visit_duration_deep configWifiApiVisitDurationDeep , config_wifi_api_visit_duration_jump configWifiApiVisitDurationJump , config_wifi_api_visit_period configWifiApiVisitPeriod , config_wifi_api_liveness configWifiApiLiveness , createTime FROM shop ${where} ${order}")
	List<Shop> findWhere(@Param("order") String order, @Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT shopID , shopName , config_wifi_visit_expired configWifiVisitExpired , config_wifi_user_expired configWifiUserExpired , config_wifi_api_visit_counts configWifiApiVisitCounts , config_wifi_api_visit_duration configWifiApiVisitDuration , config_wifi_api_visit_duration_deep configWifiApiVisitDurationDeep , config_wifi_api_visit_duration_jump configWifiApiVisitDurationJump , config_wifi_api_visit_period configWifiApiVisitPeriod , config_wifi_api_liveness configWifiApiLiveness , createTime FROM shop ${where} ${order} LIMIT ${start},${limit}")
	List<Shop> findWhereByPage(@Param("order") String order, @Param("where") String where, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT shopID , shopName , config_wifi_visit_expired configWifiVisitExpired , config_wifi_user_expired configWifiUserExpired , config_wifi_api_visit_counts configWifiApiVisitCounts , config_wifi_api_visit_duration configWifiApiVisitDuration , config_wifi_api_visit_duration_deep configWifiApiVisitDurationDeep , config_wifi_api_visit_duration_jump configWifiApiVisitDurationJump , config_wifi_api_visit_period configWifiApiVisitPeriod , config_wifi_api_liveness configWifiApiLiveness , createTime FROM shop WHERE ${propertyName}=#{value} ${order}")
	List<Shop> findByPropertyName(@Param("order") String order, @Param("propertyName") String propertyName, @Param("value") Object value);
}