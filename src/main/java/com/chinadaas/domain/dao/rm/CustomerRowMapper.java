package com.chinadaas.domain.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chinadaas.domain.entity.Customer;

/**
 * projectName: chinadaas-data<br>
 * desc: 客户orm<br>
 * date: 2015年3月20日 下午3:00:52<br>
 * @author 开发者真实姓名[Andy]
 */
public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rn) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getString("id"));
		customer.setName(rs.getString("name"));
		return customer;
	}

}

