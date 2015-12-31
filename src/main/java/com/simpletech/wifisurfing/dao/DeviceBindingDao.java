package com.simpletech.wifisurfing.dao;

import java.util.List;

import com.simpletech.wifisurfing.dao.base.BaseDao;
import com.simpletech.wifisurfing.model.DeviceBinding;

/**
 * 数据库表device_binding的Dao接口
 * @author 树朾
 * @date 2015-11-20 15:47:21 中国标准时间
 */
public interface DeviceBindingDao extends BaseDao<DeviceBinding>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变行数
	 */
	int insert(DeviceBinding model);
	/**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 改变行数
	 */
	int delete(Object id);
	/**
	 * 更新一条数据
	 * @param model 需要更新数据
	 * @return 改变行数
	 */
	int update(DeviceBinding model);
	/**
	 * 统计全部出数据
	 * @return 全部数据量
	 */
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return 数据对象 or null
	 */
	DeviceBinding findById(Object id);
	/**
	 * 获取全部数据
	 * @return 全部所有数据
	 */
	List<DeviceBinding> findAll();
	/**
	 * 分页查询数据
	 * @param limit 分页最大值
	 * @param start 开始编号
	 * @return 分页列表数据
	 */
	List<DeviceBinding> findByPage(int limit,int start);
	

}
