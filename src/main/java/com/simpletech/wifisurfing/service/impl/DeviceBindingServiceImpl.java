package com.simpletech.wifisurfing.service.impl;

import com.simpletech.wifisurfing.dao.DeviceBindingDao;
import com.simpletech.wifisurfing.model.DeviceBinding;
import com.simpletech.wifisurfing.model.base.ModelBase;
import com.simpletech.wifisurfing.service.DeviceBindingService;
import com.simpletech.wifisurfing.service.base.BaseServiceImpl;
import com.simpletech.wifisurfing.util.Page;
import com.simpletech.wifisurfing.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表device_binding的Service接实现
 * @author 树朾
 * @date 2015-11-20 15:47:21 中国标准时间
 */
@Service
public class DeviceBindingServiceImpl extends BaseServiceImpl<DeviceBinding> implements DeviceBindingService{

	@Autowired
	DeviceBindingDao dao;
	
	@Override
	public int insert(DeviceBinding model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(DeviceBinding model) {
		DeviceBinding old = findById(getModelID(model));
		if (old == null) {
			throw new ServiceException("请求更新记录不存在或已经被删除！");
		}
		model = checkNullField(old, model);
		return dao.update(model);
	}

	@Override
	public int delete(Object id) {
		return dao.delete(id);
	}

	@Override
	public DeviceBinding findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<DeviceBinding> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<DeviceBinding> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public DeviceBinding findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<DeviceBinding> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<DeviceBinding> list = dao.findByPage(limit, start);
		
		return new Page<DeviceBinding>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
