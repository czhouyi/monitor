package com.chinadaas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinadaas.domain.dao.IUserDao;
import com.chinadaas.domain.entity.User;
import com.chinadaas.util.SessionUtil;
import com.chinadaas.util.StringUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 用户登录验证控制类<br>
 * date: 2015年3月19日 下午2:39:07<br>
 * @author 开发者真实姓名[Andy]
 */
@Controller
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private Validator userValidator;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserValidator(Validator userValidator) {
		this.userValidator = userValidator;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) throws Exception {
		request.setAttribute("r", request.getParameter("r"));
		return "auth/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, User user, Errors errors) throws Exception {
		userValidator.validate(user, errors);
		String redirect = request.getParameter("r");
		if (errors.hasErrors()) {
			request.setAttribute("r", redirect);
			return "auth/login";
		}
		User u = userDao.getByEmail(user.getEmail());
		SessionUtil.login(request, u);
		if (StringUtil.isEmpty(redirect)) {
			return "redirect:/";
		} else {
			return "redirect:" + redirect;
		}
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile() throws Exception {
		return "auth/profile";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		SessionUtil.logout(request);
		return "redirect:/";
	}

}

