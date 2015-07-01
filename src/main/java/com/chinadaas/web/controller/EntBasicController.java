package com.chinadaas.web.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinadaas.domain.dao.IEntBasicDao;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年3月19日 下午2:39:07<br>
 * @author 开发者真实姓名[Andy]
 */
@Controller
public class EntBasicController {
	
	@Autowired
	private IEntBasicDao entBasicDao;

	public void setEntBasicDao(IEntBasicDao entBasicDao) {
		this.entBasicDao = entBasicDao;
	}

	@RequestMapping(value = "/entbasic/{name:\\d+}", method = RequestMethod.GET)
	public @ResponseBody String getPerson(@PathVariable("name") int name) {
		System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master");
		List<String> rs = entBasicDao.findRowkeyByName("北京中数智汇科技有限公司");
//		userDao.find("select * from t_user where id=?", new Object[]{"7282"});
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "Get EntBasic successful!");
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/entbasic/{id:\\d+}", method = RequestMethod.DELETE)
	public @ResponseBody String deletePerson(@PathVariable("id") int id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "Delete EntBasic successful!");
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/entbasic/{id:\\d+}", method = RequestMethod.PUT)
	public @ResponseBody String updatePerson(@PathVariable("id") int id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "Update EntBasic successful!");
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/entbasic", method = RequestMethod.POST)
	public @ResponseBody String addPerson(Object obj) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "Add EntBasic successful!");
		return jsonObject.toJSONString();
	}
	
}

