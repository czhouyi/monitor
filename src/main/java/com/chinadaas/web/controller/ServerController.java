package com.chinadaas.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinadaas.domain.dao.IServerMetaDao;
import com.chinadaas.domain.dao.IServerStatusDao;
import com.chinadaas.domain.entity.ServerMeta;
import com.chinadaas.domain.entity.ServerStatus;
import com.chinadaas.domain.entity.ServerType;
import com.chinadaas.util.StringUtil;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务监控页面控制类<br>
 * date: 2015年3月19日 下午2:39:07<br>
 * @author 开发者真实姓名[Andy]
 */
@Controller
@RequestMapping(value="/server")
public class ServerController{

	@Autowired
	private IServerStatusDao serverStatusDao;
	
	@Autowired
	private IServerMetaDao serverMetaDao;
	
	@Autowired
	private Validator serverMetaValidator;

	public void setServerStatusDao(IServerStatusDao serverStatusDao) {
		this.serverStatusDao = serverStatusDao;
	}

	public void setServerMetaDao(IServerMetaDao serverMetaDao) {
		this.serverMetaDao = serverMetaDao;
	}

	public void setServerMetaValidator(Validator serverMetaValidator) {
		this.serverMetaValidator = serverMetaValidator;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listServerStatus(HttpServletRequest request, 
			@RequestParam(value="f", required=false) String f) throws Exception {
		List<ServerStatus> statusList = serverStatusDao.getLatestStatus(f);
		request.setAttribute("statusList", statusList);
		if(StringUtil.isEmpty(f)) {
			request.setAttribute("f", "all");
		} else {
			request.setAttribute("f", f);
		}
		return "server/server";
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String exportServerStatus(HttpServletRequest request,
			HttpServletResponse response) {
		List<ServerStatus> statusList = serverStatusDao.getLatestStatus("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setContentType("application/csv;charset=GB2312");
		response.setHeader("Content-Disposition", "attachment; filename=monitor"+sdf.format(new Date())+".csv");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write("id,type,name,mdate,status,info\n");
			for (ServerStatus ss : statusList) {
				out.write(String.format("%s,%s,%s,%s,%s,%s\n", ss.getId(), ss.getType(), 
						ss.getName(), sdf.format(ss.getMdate()), ss.getStatus(), StringUtil.getString(ss.getInfo())));
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/meta", method = RequestMethod.GET)
	public String meta(HttpServletRequest request) throws Exception {
		request.setAttribute("metas", serverMetaDao.getAllList());
		return "server/meta/list";
	}
	
	@RequestMapping(value = "/meta/{id:[0-9]+}", method = RequestMethod.GET)
	public String editMeta(HttpServletRequest request, @PathVariable("id") int id) throws Exception {
		ServerMeta meta = serverMetaDao.getServerMeta(id);
		request.setAttribute("meta", meta);
		return "server/meta/edit";
	}
	
	@RequestMapping(value = "/meta/profile", method = RequestMethod.GET)
	public String addMeta(HttpServletRequest request) throws Exception {
		ServerMeta meta = new ServerMeta();
		meta.setMonitor(true);
		meta.setType(ServerType.tomcat);
		request.setAttribute("meta", meta);
		return "server/meta/edit";
	}
	
	@RequestMapping(value = "/meta/profile", method = RequestMethod.POST)
	public String updateMeta(HttpServletRequest request, ServerMeta meta, Errors errors) throws Exception {
		serverMetaValidator.validate(meta, errors);
		if (errors.hasErrors()) {
			request.setAttribute("meta", meta);
			return "server/meta/edit";
		}
		try {
			serverMetaDao.saveOrUpdateServerMeta(meta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/server/meta";
	}

}

