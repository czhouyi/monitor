package com.chinadaas.domain.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.entity.User;

/**
 * projectName: chinadaas-data<br>
 * desc: 用户数据访问接口<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface IUserDao extends IDao<User> {
	
	/**
	 * desc: 根据email获取用户对象<br>
	 * date: 2015年4月7日 下午5:31:45<br>
	 * @author 开发者真实姓名[Andy]
	 * @param email
	 * @return
	 */
	public User getByEmail(String email) throws DataAccessException;
	
}

