package com.simpletech.wifisurfing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.wifisurfing.model.Login;
import com.simpletech.wifisurfing.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表login的mapper接口
 * @author 树朾
 * @date 2015-12-09 18:44:02 中国标准时间
 */
public interface LoginMapper extends MybatisMultiDao<Login>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO login ( id , idshop , idwifi , appid , openid , wxshopid , auth_type , mac_device , time_from_last , is_new_user , end_brand , time_local , create_time , update_time ) VALUES ( #{id} , #{idshop} , #{idwifi} , #{appid} , #{openid} , #{wxshopid} , #{authType} , #{macDevice} , #{timeFromLast} , #{isNewUser} , #{endBrand} , #{timeLocal} , #{createTime} , #{updateTime} )")
	int insert(Login model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM login WHERE id=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE login SET id=#{id} , idshop=#{idshop} , idwifi=#{idwifi} , appid=#{appid} , openid=#{openid} , wxshopid=#{wxshopid} , auth_type=#{authType} , mac_device=#{macDevice} , time_from_last=#{timeFromLast} , is_new_user=#{isNewUser} , end_brand=#{endBrand} , time_local=#{timeLocal} , create_time=#{createTime} , update_time=#{updateTime} WHERE id=#{id} ")
	int update(Login model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM login")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login WHERE id=#{id}")
	Login findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login ${order}")
	List<Login> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login ${order} LIMIT ${start},${limit}")
	List<Login> findByPage(@Param("order") String order, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM login ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM login WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName, @Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM login ${where}")
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
	@Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login ${where} ${order}")
	List<Login> findWhere(@Param("order") String order, @Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login ${where} ${order} LIMIT ${start},${limit}")
	List<Login> findWhereByPage(@Param("order") String order, @Param("where") String where, @Param("limit") int limit, @Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , idshop , idwifi , appid , openid , wxshopid , auth_type authType , mac_device macDevice , time_from_last timeFromLast , is_new_user isNewUser , end_brand endBrand , time_local timeLocal , create_time createTime , update_time updateTime FROM login WHERE ${propertyName}=#{value} ${order}")
	List<Login> findByPropertyName(@Param("order") String order, @Param("propertyName") String propertyName, @Param("value") Object value);
}