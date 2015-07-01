package com.chinadaas.domain.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinadaas.domain.dao.BaseDao;
import com.chinadaas.domain.dao.ICustomerDao;
import com.chinadaas.domain.entity.Customer;

/**
 * projectName: chinadaas-data<br>
 * desc: 客户数据访问实现<br>
 * date: 2015年3月19日 下午4:03:21<br>
 * @author 开发者真实姓名[Andy]
 */
@Repository
public class CustomerDaoImpl extends BaseDao<Customer> implements ICustomerDao {
	
	@Override
	public List<Map<String, Object>> getCustomer() {
		String sql = " SELECT ID, FULL_NAME NAME FROM T_CUSTOMER ORDER BY FULL_NAME ";
		
		return findMap(sql, new Object[]{});
	}
}

