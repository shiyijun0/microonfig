package com.jwk.project.app.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.message.GuoJiShortUtils;
import com.jwk.common.utils.message.ValidateCodeSession;
import com.jwk.framework.web.controller.BaseController;

/**
 * 用户注册
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/app/reguser")
public class RegisterController extends BaseController {

	// 发送短信验证码
	@RequestMapping("/sendValidateCode")
	public ValidateCodeSession sendValidateCode(String mobile, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mobile2 = StrUtils.parsePhone(mobile);
		if (StrUtils.isEmpty(mobile2))
			throw new ClientAbortException("手机号为空！");
		if (StrUtils.isMobile(mobile))
			throw new ClientAbortException("手机号格式不正确！");
		String validate_code = StrUtils.getRandomNumber(6);
		String result = GuoJiShortUtils.sendCode(mobile2, validate_code);
		ValidateCodeSession validateCodeSession2 = null;
		if ("success".equals(result)) {
			validateCodeSession2 = new ValidateCodeSession(validate_code);
		} else {
			throw new ClientAbortException("验证短信未发送成功！");
		}
		return validateCodeSession2;
	}

	// 注册用户
	@RequestMapping("/register")
	public void register(String mobile, String validate_code, String password, Object obj) throws Exception {
		if (StrUtils.isEmpty(validate_code))
			throw new ClientAbortException("验证码不正！");
		if (StrUtils.isEmpty(mobile))
			throw new ClientAbortException("手机号为空！");
		if (StrUtils.isMobile(mobile))
			throw new ClientAbortException("手机号格式不正确！");
		if (StrUtils.isEmpty(password))
			throw new ClientAbortException("密码为空！");
		if (obj == null) {
			throw new ClientAbortException("验证码不正确.");
		}
		ValidateCodeSession validateCodeSession = (ValidateCodeSession) obj;
		if (System.currentTimeMillis() - validateCodeSession.getCreate_time() > 1000 * 60 * 15) {
			throw new ClientAbortException("验证码已失效！");
		}
		if (!validateCodeSession.getValidate_code().equals(validate_code)) {
			throw new ClientAbortException("验证码不正确！");
		}
	}

}
