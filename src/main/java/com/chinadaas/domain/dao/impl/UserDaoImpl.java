package com.chinadaas.domain.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinadaas.domain.dao.BaseDao;
import com.chinadaas.domain.dao.IUserDao;
import com.chinadaas.domain.dao.rm.UserRowMapper;
import com.chinadaas.domain.entity.User;

/**
 * projectName: chinadaas-data<br>
 * desc: 用户数据访问实现<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {
	
	@Override
	public User getByEmail(String email) {
		String sql = " SELECT U.ID, U.USER_NAME USERNAME, U.EMAIL, "
				+ " U.MOBILE, C.ID CUSTOMERID, UK.PIN PASSWORD, C.FULL_NAME CUSTOMERNAME "
				+" FROM T_USER U "
				+" LEFT JOIN T_UKEY UK ON UK.ID_USER_USE=U.ID "
				+" LEFT JOIN T_CUSTOMER C ON C.ID=U.ID_CUSTOMER "
				+" WHERE U.EMAIL = ? ";
		List<User> users = find(sql, new Object[]{email}, new UserRowMapper());
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	
}

