package com.simpletech.wifisurfing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.wifisurfing.dao.base.BaseDaoImpl;
import com.simpletech.wifisurfing.dao.LoginDao;
import com.simpletech.wifisurfing.model.Login;

/**
 * 数据库表t_login的Dao实现
 * @author 树朾
 * @date 2015-11-20 15:47:21 中国标准时间
 */
@Repository
public class LoginDaoImpl extends BaseDaoImpl<Login> implements LoginDao{

	@Override
	public int insert(Login t) {
		return super.insert(t);
	}

	@Override
	public int update(Login t) {
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
	public Login findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Login> findAll() {
		return super.findAll();
	}

	@Override
	public List<Login> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

