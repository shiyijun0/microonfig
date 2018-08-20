package com.jwk.project.app.user.service;

import com.alibaba.fastjson.JSONObject;

public interface IWxLoginService {

	/**
	 * 获取微信accessToken
	 * @param code
	 * @return
	 */
	public String getAccessToken(String code,String appid,String secret);

	/**
	 * 获取微信用户信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws Exception 
	 */
	public JSONObject getUserInfo(String accessToken, String openId);

	/**
	 * 重新获取微信accessToken
	 * @param refresh_token
	 * @return
	 */
	public String refreshToken(String refresh_token);

}
