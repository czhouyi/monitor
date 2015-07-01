package com.chinadaas.domain.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chinadaas.domain.entity.ServerStatus;
import com.chinadaas.domain.entity.ServerType;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务监控状态orm<br>
 * date: 2015年3月20日 下午3:00:52<br>
 * @author 开发者真实姓名[Andy]
 */
public class ServerStatusRowMapper implements RowMapper<ServerStatus>{

	@Override
	public ServerStatus mapRow(ResultSet rs, int rn) throws SQLException {
		ServerStatus ms = new ServerStatus();
		ms.setId(rs.getInt("id"));
		ms.setType(ServerType.valueOf(rs.getString("type")));
		ms.setName(rs.getString("name"));
		ms.setStatus(rs.getString("status"));
		ms.setMdate(rs.getTimestamp("mtime"));
		ms.setInfo(rs.getString("info"));
		return ms;
	}

}

