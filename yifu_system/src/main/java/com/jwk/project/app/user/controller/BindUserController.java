package com.jwk.project.app.user.controller;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.app.user.domain.AppUser;
import com.jwk.project.app.user.service.IAppUserService;

/**
 * 微信登录
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/system/binduser")
public class BindUserController extends BaseController {

	@Autowired
	private IAppUserService appUserService;

	/**
	 * 绑定
	 * 
	 * @throws ClientAbortException
	 */
	@PostMapping("/save")
	@ResponseBody
	public JSON save(AppUser user) throws ClientAbortException {
		if (StringUtils.isEmpty(user.getOpenId()))
			throw new ClientAbortException("openId为空");

		AppUser user2 = appUserService.selectUserByMobile(user.getMobile());
		if (user2 != null) {
			if (StringUtils.isNotEmpty(user2.getOpenId()))
				throw new ClientAbortException("该手机号已经绑定微信");
		}
		if (appUserService.saveUser(user) > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}
}
