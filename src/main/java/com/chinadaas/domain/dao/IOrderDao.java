package com.chinadaas.domain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.entity.Order;

/**
 * projectName: chinadaas-data<br>
 * desc: 订单时序数据访问接口<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface IOrderDao extends IDao<Order>{
	
	/**
	 * desc: 获取当前按分钟统计的订单数量<br>
	 * date: 2015年3月25日 上午10:52:20<br>
	 * @author 开发者真实姓名[Andy]
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, List<Object>> getCurrent() throws DataAccessException;
	
	/**
	 * desc: 获取前一分钟统计的订单数量<br>
	 * date: 2015年3月25日 上午10:52:20<br>
	 * @author 开发者真实姓名[Andy]
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Object> getIncrease() throws DataAccessException;
	
	/**
	 * desc: 平均订单处理时间<br>
	 * date: 2015年3月30日 上午11:00:50<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Double> delayData(String from, String to) throws DataAccessException;
	
	/**
	 * desc: 订单被查询对象地域分布<br>
	 * date: 2015年3月30日 上午10:51:46<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Object> distributionData(String from, String to) throws DataAccessException;
	
	/**
	 * desc: 订单历史时序数据 - 按月统计<br>
	 * date: 2015年3月29日 下午4:32:57<br>
	 * @author 开发者真实姓名[Andy]
	 * @param cid
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Double> tsStaticDataByMonth(String from, String to, String cid, String orderFrom, String product) throws DataAccessException;
	
	/**
	 * desc: 订单历史时序数据 - 按天统计<br>
	 * date: 2015年3月29日 下午4:32:57<br>
	 * @author 开发者真实姓名[Andy]
	 * @param cid
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Double> tsStaticDataByDay(String from, String to, String cid, String orderFrom, String product) throws DataAccessException;
	
	/**
	 * desc: 订单历史时序数据 - 按小时统计<br>
	 * date: 2015年3月29日 下午4:32:57<br>
	 * @author 开发者真实姓名[Andy]
	 * @param cid
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Double> tsStaticDataByHour(String from, String to, String cid, String orderFrom, String product) throws DataAccessException;
	
	/**
	 * desc: 主要客户订单数量<br>
	 * date: 2015年3月30日 上午10:03:16<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> mainCustomerData(String from, String to) throws DataAccessException;
	
	/**
	 * desc: 订单数量按天统计数据<br>
	 * date: 2015年5月11日 下午3:59:14<br>
	 * @author 开发者真实姓名[Andy]
	 * @param cid
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public Map<String, Double> calendarData(String cid, String from, String to) throws DataAccessException;
	
	/**
	 * desc: 订单数量按产品统计数据<br>
	 * date: 2015年5月15日 上午9:17:29<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> productCntData(String from, String to) throws DataAccessException;
	
	/**
	 * desc: 订单数量按来源统计数据<br>
	 * date: 2015年5月15日 上午9:17:29<br>
	 * @author 开发者真实姓名[Andy]
	 * @param from
	 * @param to
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> fromCntData(String from, String to) throws DataAccessException;
	
}

