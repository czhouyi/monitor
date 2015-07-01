package com.chinadaas.domain.entity.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chinadaas.domain.dao.IUserDao;
import com.chinadaas.domain.entity.User;
import com.chinadaas.util.CryptUtils;

/**
 * projectName: chinadaas-data<br>
 * desc: 用户登录表单验证<br>
 * date: 2015年4月1日 下午12:53:00<br>
 * @author 开发者真实姓名[Andy]
 */
public class UserValidator implements Validator {
	
	@Autowired
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "email.required", "Email is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password.required", "Password is required");
		
		User user = (User) obj;
		User u = userDao.getByEmail(user.getEmail());
		if (u == null) {
			e.rejectValue("email", "email.notexist", "Email is not exist");
		} else {
			String encodePwd = CryptUtils.encode(user.getPassword());
			if (!encodePwd.equals(u.getPassword())) {
				e.reject("auth.failed", "Auth failed");
			}
		}
	}

}

