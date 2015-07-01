package com.chinadaas.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chinadaas.domain.entity.User;
import com.chinadaas.util.SessionUtil;
import com.chinadaas.util.StringUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 用户验证拦截器<br>
 * date: 2015年4月7日 上午11:42:45<br>
 * @author 开发者真实姓名[Andy]
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object obj, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object obj, ModelAndView mav) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Exception {
		User loginUser = SessionUtil.loginUser(req);
		
		if (loginUser == null) {
			String base = StringUtil.getString(req.getAttribute("base"));
			resp.sendRedirect(base + "/auth/login?r=" + req.getServletPath());
			return false;
		}
		
		return true;
	}

}

