package com.chinadaas.domain.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chinadaas.domain.entity.Order;

/**
 * projectName: chinadaas-data<br>
 * desc: 订单时序orm<br>
 * date: 2015年3月20日 下午3:00:52<br>
 * @author 开发者真实姓名[Andy]
 */
public class OrderRowMapper implements RowMapper<Order>{
	
	private boolean withFrom;
	
	public OrderRowMapper(boolean withFrom) {
		this.withFrom = withFrom;
	}

	@Override
	public Order mapRow(ResultSet rs, int rn) throws SQLException {
		Order o = new Order();
		o.setSeries(rs.getString("series"));
		o.setValue(rs.getDouble("value"));
		if (withFrom) {
			o.setFrom(rs.getString("ORDER_FROM"));
		}
		return o;
	}

}

