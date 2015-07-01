package com.chinadaas.domain.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.entity.ServerMeta;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务配置数据访问接口<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface IServerMetaDao extends IDao<ServerMeta> {
	
	/**
	 * desc: 获取监控中列表<br>
	 * date: 2015年4月1日 下午2:15:35<br>
	 * @author 开发者真实姓名[Andy]
	 * @return
	 */
	public List<ServerMeta> getActiveList() throws DataAccessException;
	
	/**
	 * desc: 根据主键获取对象<br>
	 * date: 2015年4月1日 下午2:16:02<br>
	 * @author 开发者真实姓名[Andy]
	 * @param id
	 * @return
	 */
	public ServerMeta getServerMeta(int id) throws DataAccessException;
	
	/**
	 * desc: 获取所有对象列表<br>
	 * date: 2015年4月1日 下午2:16:55<br>
	 * @author 开发者真实姓名[Andy]
	 * @return
	 */
	public List<ServerMeta> getAllList() throws DataAccessException;
	
	/**
	 * desc: 更新对象<br>
	 * date: 2015年4月1日 下午2:17:16<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sm
	 */
	public void updateServerMeta(ServerMeta sm) throws DataAccessException;
	
	/**
	 * desc: 新增对象<br>
	 * date: 2015年4月1日 下午2:17:19<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sm
	 */
	public void saveServerMeta(ServerMeta sm) throws DataAccessException;
	
	/**
	 * desc: 新增或更新对象<br>
	 * date: 2015年4月1日 下午2:17:22<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sm
	 */
	public void saveOrUpdateServerMeta(ServerMeta sm) throws DataAccessException;
	
	/**
	 * desc: url是否重复判断<br>
	 * date: 2015年4月7日 下午5:14:55<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sm
	 * @return
	 */
	public boolean duplicateUrl(ServerMeta sm) throws DataAccessException;
	
}

