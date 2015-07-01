package com.chinadaas.domain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.chinadaas.domain.entity.Customer;

/**
 * projectName: chinadaas-data<br>
 * desc: 客户数据访问接口<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public interface ICustomerDao extends IDao<Customer> {
	
	/**
	 * desc: 获取所有客户列表<br>
	 * date: 2015年3月29日 下午4:32:57<br>
	 * @author 开发者真实姓名[Andy]
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCustomer() throws DataAccessException;
	
}

