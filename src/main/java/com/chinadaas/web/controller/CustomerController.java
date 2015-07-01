package com.chinadaas.web.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadaas.domain.dao.ICustomerDao;

/**
 * projectName: chinadaas-data<br>
 * desc: 客户相关请求控制器<br>
 * date: 2015年3月19日 下午2:39:07<br>
 * @author 开发者真实姓名[Andy]
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerDao customerDao;

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody String all() throws Exception {
		List customers = customerDao.getCustomer();
		JSONArray json = new JSONArray();
		json.addAll(customers);
		return json.toJSONString();
	}
	
}

