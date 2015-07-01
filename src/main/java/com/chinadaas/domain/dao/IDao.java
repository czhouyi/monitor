package com.chinadaas.domain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * projectName: chinadaas-data<br>
 * desc: 通用数据访问接口<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface IDao<T> {

	/**
	 * desc: 查找指定对象列表<br>
	 * date: 2015年3月31日 下午1:49:09<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public List<T> find(String sql, Object[] params, RowMapper<T> rowMapper) throws DataAccessException;
	
	/**
	 * desc: 查找自定义对象列表<br>
	 * date: 2015年3月31日 下午1:49:09<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public List<?> findObj(String sql, Object[] params, RowMapper<?> rowMapper) throws DataAccessException;
	
	/**
	 * desc: 查找字典列表<br>
	 * date: 2015年3月31日 下午1:49:09<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findMap(String sql, Object[] params) throws DataAccessException;
	
	/**
	 * desc: 执行数据库更新语句<br>
	 * date: 2015年3月31日 下午1:50:21<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object[] params) throws DataAccessException;
	
	/**
	 * desc: 获取sql查询结果记录数量<br>
	 * date: 2015年4月1日 下午2:11:35<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @param params
	 * @return
	 */
	public int count(String sql, Object[] params) throws DataAccessException;
	
	/**
	 * desc: 获取sql查询结果是否有记录<br>
	 * date: 2015年4月1日 下午2:12:33<br>
	 * @author 开发者真实姓名[Andy]
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean exist(String sql, Object[] params) throws DataAccessException;
}

