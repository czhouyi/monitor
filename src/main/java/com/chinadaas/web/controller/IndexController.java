package com.chinadaas.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * projectName: chinadaas-data<br>
 * desc: 首页控制类<br>
 * date: 2015年3月23日 下午5:53:12<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() throws Exception {
		return "index";
	}
		
}
