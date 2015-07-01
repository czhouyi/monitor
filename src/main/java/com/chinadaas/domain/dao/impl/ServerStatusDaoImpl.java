package com.chinadaas.domain.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinadaas.domain.dao.BaseDao;
import com.chinadaas.domain.dao.IServerStatusDao;
import com.chinadaas.domain.dao.rm.ServerStatusRowMapper;
import com.chinadaas.domain.entity.ServerStatus;
import com.chinadaas.util.StringUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务监控状态数据访问实现<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
@Repository
public class ServerStatusDaoImpl extends BaseDao<ServerStatus> implements IServerStatusDao {

	@Override
	public List<ServerStatus> getLatestStatus(String type) {
		String sql = " SELECT META.ID, META.TYPE, META.NAME, ML.STATUS, ML.MTIME, ML.INFO "
				+ " FROM (SELECT META_ID, MAX(MTIME) MTIME FROM T_MONITOR_LOG GROUP BY META_ID) LATEST "
				+ " LEFT JOIN T_MONITOR_LOG ML ON ML.META_ID=LATEST.META_ID AND ML.MTIME=LATEST.MTIME "
				+ " LEFT JOIN T_META_SERVER META ON META.ID=LATEST.META_ID "
				+ " WHERE META.ISMONITOR=1 ";
		if(StringUtil.isEmpty(type)) {
			return find(sql, new Object[] {}, new ServerStatusRowMapper());
		} else {
			sql += " AND META.TYPE=? ";
			return find(sql, new Object[] {type}, new ServerStatusRowMapper());
		}
	}
	
	@Override
	public List<ServerStatus> getServerStatus(int id) {
		String sql = " SELECT MTIME, STATUS FROM T_MONITOR_LOG WHERE META_ID=? ";
		return find(sql, new Object[] {id}, new ServerStatusRowMapper());
	}

}
