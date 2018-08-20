package com.jwk.project.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.Md5Utils;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.app.user.domain.AppUser;
import com.jwk.project.app.user.service.IAppUserService;

/**
 * 用户登录
 * 
 * @author Administrator
 *
 */
@Controller
public class AppUserLoginController extends BaseController {

	@Autowired
	private IAppUserService appUserService;

	@PostMapping("/normalLogin")
	@ResponseBody
	public JSON ajaxLogin(String mobile, String password) {
		AppUser user = appUserService.selectUserByMobile(mobile);
		String msg ="用户名或密码错误";
		if(user ==null)
			return JSON.error(msg);
		if(!Md5Utils.hash(password).equals(user.getPassword()))
			return JSON.error("密码错误");
		return JSON.ok();
		/*UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return JSON.ok();
		} catch (AuthenticationException e) {
			String msg = "用户或密码错误";
			if (StringUtils.isNotEmpty(e.getMessage())) {
				msg = e.getMessage();
			}
			return JSON.error(msg);
		}*/
	}
}
