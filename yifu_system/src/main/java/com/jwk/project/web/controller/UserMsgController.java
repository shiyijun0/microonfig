package com.jwk.project.web.controller;

import com.jwk.common.utils.JacksonUtils;
import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.service.RedisService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;
import static com.jwk.common.utils.message.YunPianUtils.sendCode;

@Controller
@RequestMapping("/custom")
public class UserMsgController {
	private String prefix = "custom";

	@Autowired
	private IRegisterUserService registerUserService;

	@Autowired
	private RedisService redisService;

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	@GetMapping("/user")
	public String user(Model model) {
		RegisterUser registerUser = (RegisterUser) ServletUtils.getHttpServletRequest().getSession()
				.getAttribute("user");
		if (registerUser != null) {
			registerUser = registerUserService.selectRegisterUserById(registerUser.getId());
			model.addAttribute("registerUser", registerUser);
			return prefix + "/user";
		}
		return "redirect:/custom/index";
	}

	/*
	 * 查询登陆用户的个人信息
	 */
	@RequestMapping("/user2")
	@ResponseBody
	public String selectUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			map.put("user", null);
		} else {
			user = registerUserService.selectRegisterUserById(user.getId());
			map.put("user", user);
		}
		return JacksonUtils.toJsonString(map);
	}

	/*
	 * 编辑保存用户的个人信息
	 */
	@PostMapping("/usersave")
	@ResponseBody
	public String usersave(RegisterUser registerUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (registerUserService.saveRegisterUser(registerUser) > 0) {
			RegisterUser user = registerUserService.selectRegisterUserById(registerUser.getId());
			ServletUtils.getHttpServletRequest().getSession().setAttribute("user", user);
			map.put("user", user);
			return JacksonUtils.toJsonString(map);
		}
		map.put("user", null);
		return JacksonUtils.toJsonString(map);
	}

	/*
	 * 编辑保存用户的图片
	 */
	@PostMapping("/userphoto")
	@ResponseBody
	public String userphoto(RegisterUser registerUser,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (registerUserService.saveRegisterUserPhoto(registerUser, file) > 0) {
			RegisterUser user = registerUserService.selectRegisterUserById(registerUser.getId());
			ServletUtils.getHttpServletRequest().getSession().setAttribute("user", user);
			map.put("user", user);
		}
		return JacksonUtils.toJsonString(map);
	}

	/**
	 * 用户登陆认证
	 * 
	 * @param registerUser
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public String save(RegisterUser registerUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (registerUserService.saveRegisterUser(registerUser) > 0) {
			RegisterUser user = registerUserService.selectRegisterUserByMobile(registerUser.getMobile());
			ServletUtils.getHttpServletRequest().getSession().setAttribute("user", user);
			map.put("user", user);
			map.put("code", 0);
			return JacksonUtils.toJsonString(map);
		}
		map.put("code", 1);
		return JacksonUtils.toJsonString(map);
	}

	/**
	 * 校验手机号
	 */
	@PostMapping("/checkMobileUnique")
	@ResponseBody
	public String checkMobileUnique(RegisterUser registerUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (registerUserService.checkMobileUnique(registerUser.getMobile()).equals("1")) {
			RegisterUser user = registerUserService.selectRegisterUserByMobile(registerUser.getMobile());
			map.put("code", 0);
			map.put("user", user);
			return JacksonUtils.toJsonString(map);
		}
		map.put("code", 1);
		return JacksonUtils.toJsonString(map);
	}

	/**
	 * 校验密码是否正确
	 */
	@PostMapping("/checkPassword")
	@ResponseBody
	public String checkPassword(RegisterUser registerUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (registerUserService.checkPassword(registerUser).equals("1")) {
			RegisterUser user = registerUserService.selectRegisterUserByMobile(registerUser.getMobile());
			ServletUtils.getHttpServletRequest().getSession().setAttribute("user", user);
			map.put("code", 0);
			map.put("user", user);
			return JacksonUtils.toJsonString(map);
		}
		map.put("code", 1);
		return JacksonUtils.toJsonString(map);
	}

	/**
	 * 发送短信验证码
	 */
	@PostMapping("/sendcode")
	@ResponseBody
	public JSON sendcode(String mobile) throws Exception {
		if (redisService.exists("code:" + mobile)) {
			return JSON.error();
		}
		String validate_code = StrUtils.getRandomNumber(4);
		String result = sendCode(mobile, validate_code);
		if (result.equals("success")) {
			redisService.setEx("code:" + mobile, validate_code, 59);
			return JSON.ok();
		}

		return JSON.error();
	}

	/**
	 * 验证短信验证码
	 */
	@PostMapping("/Iscode")
	@ResponseBody
	public String Iscode(RegisterUser registerUser) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		RegisterUser user = registerUserService.selectRegisterUserByMobile(registerUser.getMobile());
		/* 用户存在 */
		if (user != null) {
			if (registerUser.getCode().equals(redisService.get("code:" + registerUser.getMobile()))) {
				redisService.delete("code:" + registerUser.getMobile());
				ServletUtils.getHttpServletRequest().getSession().setAttribute("user", user);
				map.put("code", 0);
				map.put("user", user);
				return JacksonUtils.toJsonString(map);
			}
			map.put("code", 2);// 验证码错误
			return JacksonUtils.toJsonString(map);
		} // 用户不存在
		else {
			if (registerUser.getCode().equals(redisService.get("code:" + registerUser.getMobile()))) {
				redisService.delete("code:" + registerUser.getMobile());
				map.put("code", 1);//
				return JacksonUtils.toJsonString(map);
			}
			map.put("code", 2);// 验证码错误
			return JacksonUtils.toJsonString(map);
		}
	}

	/* 短信验证码剩余时间 */
	@PostMapping("/codetime")
	@ResponseBody
	public Long codetime(String mobile) {
		Long time = redisService.ttl("code:" + mobile);
		System.out.println("剩余时间" + time);
		if (time != null && time < 1) {
			return 0L;
		}
		return time;
	}

	/*
	 * 用户退出登陆
	 *
	 */
	@GetMapping("/logout")
	public String logout() {
		ServletUtils.getHttpServletRequest().getSession().removeAttribute("user");// 清除用户 session 中的所有信息
		return "redirect:/custom/index";
	}

	/**
	 * 判断用户是否已经登录
	 * 
	 * @return
	 */
	@RequestMapping("/islogin")
	@ResponseBody
	public String islogin(String reqLeftUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {// 未登录
			map.put("msg", "未登录");
			return JacksonUtils.toJsonString(map);
		} else {
			if (StringUtils.isNotEmpty(reqLeftUrl)) {
				String existUrl = (String) ServletUtils.getHttpServletRequest().getSession().getAttribute("reqLeftUrl");
				if (StringUtils.isEmpty(existUrl)) {
					ServletUtils.getHttpServletRequest().getSession().setAttribute("reqLeftUrl", reqLeftUrl);
				} else {
					if (!reqLeftUrl.equals(existUrl)) {
						ServletUtils.getHttpServletRequest().getSession().setAttribute("reqLeftUrl", reqLeftUrl);
					}
				}
			}
			user = registerUserService.selectRegisterUserById(user.getId());
			map.put("user", user);
		}
		return JacksonUtils.toJsonString(map);
	}


	/**
	 * 用户密码重置
	 *
	 * @return
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public JSON resetPassword(RegisterUser registerUser) {
		if(registerUserService.saveRegisterUserPassword(registerUser)>0){
			return JSON.ok();
		}
        return JSON.error();
	}



}
