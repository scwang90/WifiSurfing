package com.simpletech.wifisurfing.service.base;

import com.simpletech.wifisurfing.dao.base.BaseDao;
import com.simpletech.wifisurfing.util.Page;

/**
 * 通用Service层接口
 * @param <T>
 * @author 树朾
 * @date 2015-11-18 09:39:08 中国标准时间
 */
public interface BaseService<T> extends BaseDao<T>{
	int delete(String id);
	T findById(String id);
	Page<T> listByPage(int pageSize, int pageNo);
}
