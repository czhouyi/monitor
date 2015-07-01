package com.chinadaas.domain.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chinadaas.domain.entity.ServerMeta;
import com.chinadaas.domain.entity.ServerType;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务配置orm<br>
 * date: 2015年3月20日 下午3:00:52<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
public class ServerMetaRowMapper implements RowMapper<ServerMeta> {

	@Override
	public ServerMeta mapRow(ResultSet rs, int rn) throws SQLException {
		ServerMeta sm = new ServerMeta();
		sm.setId(rs.getInt("id"));
		sm.setName(rs.getString("name"));
		sm.setType(ServerType.valueOf(rs.getString("type")));
		sm.setUrl(rs.getString("url"));
		sm.setUsername(rs.getString("username"));
		sm.setPassword(rs.getString("password"));
		sm.setMonitor(rs.getBoolean("ismonitor"));
		return sm;
	}

}
