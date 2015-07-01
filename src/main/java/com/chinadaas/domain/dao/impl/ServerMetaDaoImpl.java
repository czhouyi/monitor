package com.chinadaas.domain.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinadaas.domain.dao.BaseDao;
import com.chinadaas.domain.dao.IServerMetaDao;
import com.chinadaas.domain.dao.rm.ServerMetaRowMapper;
import com.chinadaas.domain.entity.ServerMeta;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务配置数据访问实现<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public class ServerMetaDaoImpl extends BaseDao<ServerMeta> implements IServerMetaDao {
	
	@Override
	public List<ServerMeta> getActiveList() {
		return find("SELECT * FROM T_META_SERVER WHERE ISMONITOR=1", new Object[]{}, new ServerMetaRowMapper());
	}
	
	@Override
	public ServerMeta getServerMeta(int id) {
		List<ServerMeta> sms = find("SELECT * FROM T_META_SERVER WHERE ID=?", new Object[]{id}, new ServerMetaRowMapper());
		if (sms != null && sms.size() > 0) {
			return sms.get(0);
		}
		return null;
	}
	
	@Override
	public List<ServerMeta> getAllList() {
		return find("SELECT * FROM T_META_SERVER", new Object[]{}, new ServerMetaRowMapper());
	}
	
	@Override
	public void updateServerMeta(ServerMeta sm) {
		String sql = " UPDATE T_META_SERVER SET NAME=?, TYPE=?, URL=?, USERNAME=?, PASSWORD=?, ISMONITOR=? WHERE ID=? ";
		update(sql, new Object[]{sm.getName(), sm.getType().name(), sm.getUrl(), 
				sm.getUsername(), sm.getPassword(), sm.isMonitor(), sm.getId()});
	}
	
	@Override
	public void saveServerMeta(ServerMeta sm) {
		String sql = " INSERT INTO T_META_SERVER VALUES (SEQ_META_SERVER.NEXTVAL, ?, ?, ?, ?, ?, ?) ";
		update(sql, new Object[]{sm.getName(), sm.getType().name(), sm.getUrl(),
				sm.getUsername(), sm.getPassword(), sm.isMonitor()});
	}
	
	@Override
	public void saveOrUpdateServerMeta(ServerMeta sm) {
		boolean existedObj = exist("SELECT ID FROM T_META_SERVER WHERE ID=?", new Object[]{sm.getId()});
		if (existedObj) {
			updateServerMeta(sm);
		} else {
			saveServerMeta(sm);
		}
	}
	
	@Override
	public boolean duplicateUrl(ServerMeta sm) {
		if(sm.getId() <= 0) {
			if (exist("SELECT * FROM T_META_SERVER WHERE URL=?", new Object[]{sm.getUrl()})) {
				return true;
			}
		} else {
			if (exist("SELECT * FROM T_META_SERVER WHERE URL=? AND ID<>?", new Object[]{sm.getUrl(), sm.getId()})) {
				return true;
			}
		}
		return false;
	}
}

