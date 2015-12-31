package com.simpletech.wifisurfing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifisurfing.dao.base.BaseDaoImpl;
import com.simpletech.wifisurfing.dao.DeviceBindingDao;
import com.simpletech.wifisurfing.model.DeviceBinding;

/**
 * 数据库表device_binding的Dao实现
 * @author 树朾
 * @date 2015-11-20 15:47:21 中国标准时间
 */
@Repository
public class DeviceBindingDaoImpl extends BaseDaoImpl<DeviceBinding> implements DeviceBindingDao{

	@Override
	public int insert(DeviceBinding t) {
		return super.insert(t);
	}

	@Override
	public int update(DeviceBinding t) {
		return super.update(t);
	}

	@Override
	public int delete(Object id) {
		return super.delete(id);
	}

	@Override
	public int countAll() {
		return super.countAll();
	}

	@Override
	public DeviceBinding findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<DeviceBinding> findAll() {
		return super.findAll();
	}

	@Override
	public List<DeviceBinding> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

