package com.chinadaas.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinadaas.util.SessionUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 页面参数上下文通用拦截器<br>
 * date: 2015年3月31日 上午9:37:14<br>
 * @author 开发者真实姓名[Andy]
 */
public class RequestContextFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		String base = request.getContextPath();
		
		request.setAttribute("base", base);
		request.setAttribute("user", SessionUtil.loginUser(request));
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		
	}

}

