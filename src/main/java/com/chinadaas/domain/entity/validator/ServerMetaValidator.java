package com.chinadaas.domain.entity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chinadaas.domain.dao.IServerMetaDao;
import com.chinadaas.domain.entity.ServerMeta;

/**
 * projectName: chinadaas-data<br>
 * desc: 服务配置表单验证<br>
 * date: 2015年4月1日 下午12:53:00<br>
 * @author 开发者真实姓名[Andy]
 */
public class ServerMetaValidator implements Validator {
	
	@Autowired
	private IServerMetaDao serverMetaDao;

	public void setServerMetaDao(IServerMetaDao serverMetaDao) {
		this.serverMetaDao = serverMetaDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ServerMeta.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "name.required", "Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "type", "type.required", "Type is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "url", "url.required", "Url is required");
		
		ServerMeta sm = (ServerMeta) obj;
		switch (sm.getType()) {
		case oracle:
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "username.required", "Username is required");
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password.required", "Password is required");
			break;

		default:
			break;
		}
		
		if (serverMetaDao.duplicateUrl(sm)) {
			e.rejectValue("url", "url.duplicate", "Url is duplicate");
		}
	}

}

