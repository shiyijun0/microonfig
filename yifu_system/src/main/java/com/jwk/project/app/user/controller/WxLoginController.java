package com.jwk.project.app.user.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.wx.WxUtils;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.project.app.user.domain.AppUser;
import com.jwk.project.app.user.service.IAppUserService;
import com.jwk.project.app.user.service.IWxLoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信登录
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class WxLoginController extends BaseController {
	@Autowired
	private IAppUserService appUserService;
	@Autowired
	private IWxLoginService wxLoginService;

	// 微信公众号授权登录
	@RequestMapping(value = "/gzhlogin")
	public String h5tologin2(Model model) throws Exception {
		String backurl = URLEncoder.encode(WxUtils.GZH_CALLBACK_URL, "utf-8");
		String url = WxUtils.GZH_AUTHORIZATION_URL + "?appid=" + WxUtils.GZH_APP_ID + "&redirect_uri=" + backurl
				+ "&response_type=code&scope=" + WxUtils.GZH_SCOPE + "&state=" + WxUtils.GZH_APP_ID + "#wechat_redirect";
		model.addAttribute("wxurl", url);
		return "wxlogin";
	}

	// pc端 微信扫码登录
	@RequestMapping(value = "/tologin")
	public String tologin(Model model) throws Exception {
		String backurl = URLEncoder.encode(WxUtils.callback_url, "utf-8");
		String url = WxUtils.WX_AUTHORIZATION_URL + "?appid=" + WxUtils.WX_APP_ID + "&redirect_uri=" + backurl
				+ "&response_type=code&scope=" + WxUtils.SCOPE + "&state=" + WxUtils.state + "#wechat_redirect";
		model.addAttribute("wxurl", url);
		return "wxlogin";
	}

	// pc端 授权回调
	@GetMapping("/logincallback")
	public String logincallback(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String state = request.getParameter("state");
		String code = request.getParameter("code");
		log.info("code====" + code);
		String return_url = "";
		String openId = null;
		AppUser user = null;
		if (StringUtils.isNotEmpty(code)) {
			if ("a145x7wae5x47f65a2d8we5".equals(state)) {// 用户登录
				String result = wxLoginService.getAccessToken(code, WxUtils.WX_APP_ID, WxUtils.WX_APP_KEY);
				JSONObject json = JSONObject.parseObject(result);
				String access_token = json.getString("access_token");
				openId = json.getString("openId");
				log.info("openId====" + openId);
				if (StringUtils.isEmpty(access_token))
					throw new ClientAbortException("access_token为空");

				if (StringUtils.isEmpty(openId))
					throw new ClientAbortException("openId为空");

				// 保存 access_token 到 cookie，两小时过期
				Cookie accessTokencookie = new Cookie("accessToken", access_token);
				accessTokencookie.setMaxAge(60 * 2);
				response.addCookie(accessTokencookie);

				Cookie openIdCookie = new Cookie("openId", openId);
				openIdCookie.setMaxAge(60 * 2);
				response.addCookie(openIdCookie);
				// 根据openId判断用户是否已经登陆过
				user = appUserService.selectUserByOpenId(openId);
				if (user == null) {// 未绑定，去绑定手机号
					// 获取授权的微信用户信息
					user = new AppUser();
					JSONObject res = wxLoginService.getUserInfo(access_token, openId);
					System.out.println("res=========" + res);
					System.out.println("=================授权登录成功===============");
					String nickname = res.getString("nickName");
					String openid = res.getString("openid");
					int sex = res.getInteger("sex");
					String headimgurl = res.getString("headimgurl");
					user.setNickname(nickname);
					user.setOpenId(openid);
					user.setSex(sex);
					user.setPortraiturl(headimgurl);

					return_url = "binduser";
				} else {// 已经绑定，直接登录
					return_url = "test";
				}
			}
		}

		model.addAttribute("appUser", user);
		return return_url;
	}
	// 微信公众号 授权回调
	@GetMapping("/gzh/logincallback")
	public String gzhcallback(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String state = request.getParameter("state");
		String code = request.getParameter("code");
		log.info("code====" + code);
		String return_url = "";
		String openId = null;
		AppUser user = null;
		if (StringUtils.isNotEmpty(code)) {
			if (WxUtils.GZH_APP_ID.equals(state)) {// 用户登录
				String result = wxLoginService.getAccessToken(code, WxUtils.GZH_APP_ID, WxUtils.GZH_APP_KEY);
				System.out.println("result==========="+result);
				JSONObject json = JSONObject.parseObject(result);
				String access_token = json.getString("access_token");
				openId = json.getString("openId");
				log.info("openId====" + openId);
				if (StringUtils.isEmpty(access_token))
					throw new ClientAbortException("access_token为空");
				
				if (StringUtils.isEmpty(openId))
					throw new ClientAbortException("openId为空");
				
				// 保存 access_token 到 cookie，两小时过期
				Cookie accessTokencookie = new Cookie("accessToken", access_token);
				accessTokencookie.setMaxAge(60 * 2);
				response.addCookie(accessTokencookie);
				
				Cookie openIdCookie = new Cookie("openId", openId);
				openIdCookie.setMaxAge(60 * 2);
				response.addCookie(openIdCookie);
				// 根据openId判断用户是否已经登陆过
				user = appUserService.selectUserByOpenId(openId);
				if (user == null) {// 未绑定，去绑定手机号
					// 获取授权的微信用户信息
					user = new AppUser();
					JSONObject res = wxLoginService.getUserInfo(access_token, openId);
					System.out.println("res=========" + res);
					System.out.println("=================授权登录成功===============");
					String nickname = res.getString("nickName");
					String openid = res.getString("openid");
					int sex = res.getInteger("sex");
					String headimgurl = res.getString("headimgurl");
					user.setNickname(nickname);
					user.setOpenId(openid);
					user.setSex(sex);
					user.setPortraiturl(headimgurl);
					
					return_url = "binduser";
				} else {// 已经绑定，直接登录
					return_url = "test";
				}
			}
		}
		
		model.addAttribute("appUser", user);
		return return_url;
	}

}
