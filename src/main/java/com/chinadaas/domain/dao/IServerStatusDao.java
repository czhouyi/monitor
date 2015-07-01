package com.chinadaas.domain.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.entity.ServerStatus;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务监控状态数据访问接口<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface IServerStatusDao extends IDao<ServerStatus> {

	/**
	 * desc: 获取某种类型所有监控中服务的最新状态<br>
	 * date: 2015年4月7日 下午5:29:33<br>
	 * @author 开发者真实姓名[Andy]
	 * @param type
	 * @return
	 */
	public List<ServerStatus> getLatestStatus(String type) throws DataAccessException;
	
	/**
	 * desc: 获取服务的时序数据<br>
	 * date: 2015年4月7日 下午5:30:20<br>
	 * @author 开发者真实姓名[Andy]
	 * @param id
	 * @return
	 */
	public List<ServerStatus> getServerStatus(int id) throws DataAccessException;

}
