package com.chinadaas.domain.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chinadaas.domain.entity.User;

/**
 * projectName: chinadaas-data<br>
 * desc: 用户orm<br>
 * date: 2015年3月20日 下午3:00:52<br>
 * @author 开发者真实姓名[Andy]
 */
public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rn) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setMobile(rs.getString("mobile"));
		user.setCustomerId(rs.getString("customerid"));
		user.setCustomerName(rs.getString("customername"));
		return user;
	}

}

