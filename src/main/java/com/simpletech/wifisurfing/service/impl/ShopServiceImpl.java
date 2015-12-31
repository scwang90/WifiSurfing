package com.simpletech.wifisurfing.service.impl;

import com.simpletech.wifisurfing.dao.ShopDao;
import com.simpletech.wifisurfing.model.Shop;
import com.simpletech.wifisurfing.model.base.ModelBase;
import com.simpletech.wifisurfing.service.ShopService;
import com.simpletech.wifisurfing.service.base.BaseServiceImpl;
import com.simpletech.wifisurfing.util.Page;
import com.simpletech.wifisurfing.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表shop的Service接实现
 * @author 树朾
 * @date 2015-11-20 15:47:21 中国标准时间
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements ShopService{

	@Autowired
	ShopDao dao;
	
	@Override
	public int insert(Shop model){
		ModelBase.check(model);
		ModelBase.fillNullID(model);
		return dao.insert(model);
	}
	
	@Override
	public int update(Shop model) {
		Shop old = findById(getModelID(model));
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
	public Shop findById(Object id){
		return dao.findById(id);
	}

	@Override
	public List<Shop> findAll(){
		return dao.findAll();
	}

	@Override
	public int delete(String id){
		return dao.delete(id);
	}

	@Override
	public List<Shop> findByPage(int limit, int start) {
		return dao.findByPage(limit,start);
	}

	@Override
	public Shop findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Page<Shop> listByPage(int pageSize, int pageNo){
		int limit = pageSize; 
		int start = pageNo*pageSize;
		int totalRecord = dao.countAll();
		int totalPage = 1 + (totalRecord - 1) / pageSize;
		
		List<Shop> list = dao.findByPage(limit, start);
		
		return new Page<Shop>(pageNo,pageSize,totalPage,totalRecord,list){};
	}

	@Override
	public int countAll() {
		return dao.countAll();
	}
}
