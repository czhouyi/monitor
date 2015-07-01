package com.chinadaas.util;

import javax.servlet.http.HttpServletRequest;

import com.chinadaas.domain.entity.User;

/**
 * projectName: chinadaas-data<br>
 * desc: session工具类<br>
 * date: 2015年4月2日 上午9:13:48<br>
 * @author 开发者真实姓名[Andy]
 */
public class SessionUtil {
	
	public static final String LOGIN_USER_KEY = "LOGIN_USER";
	
	public static void login(HttpServletRequest request, User user) {
		request.getSession().setAttribute(LOGIN_USER_KEY, user);
	}
	
	public static void logout(HttpServletRequest request) {
		request.getSession().removeAttribute(LOGIN_USER_KEY);
	}
	
	public static User loginUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(LOGIN_USER_KEY);
	}

}

