package com.jwk.project.app.user.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.wx.WxUtils;

import lombok.extern.slf4j.Slf4j;

@Service("wxLoginService")
@Slf4j
public class WxLoginServiceImpl implements IWxLoginService {

	/**
	 * 获取微信access_token
	 */
	@Override
	public String getAccessToken(String code, String appid, String secret) {
		String url = String.format(getAccessTokenURL(code, appid, secret));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
		// String resp = getRestTemplate().getForObject(uri, String.class);
		RestTemplate restTemplate = new RestTemplate();
		String resp = restTemplate.getForObject(uri, String.class);
		log.error("getAccessToken resp = " + resp);
		if (resp.contains("openid")) {
			JSONObject jsonObject = JSONObject.parseObject(resp);
			String access_token = jsonObject.getString("access_token");
			String openId = jsonObject.getString("openid");

			JSONObject res = new JSONObject();
			res.put("access_token", access_token);
			res.put("openId", openId);
			res.put("refresh_token", jsonObject.getString("refresh_token"));

			return res.toJSONString();
		} else {
			try {
				throw new Exception("获取token失败，msg = " + resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "获取token失败";
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public JSONObject getUserInfo(String accessToken, String openId) {
		String url = String.format(getUserInfoURL(accessToken, openId));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();

		RestTemplate restTemplate = new RestTemplate();
		String resp = restTemplate.getForObject(uri, String.class);
		String json = "";
		try {
			json = new String(resp.getBytes("ISO-8859-1"), "UTF-8");// 防止中文乱码
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		JSONObject result = new JSONObject();
		if (json.contains("errcode")) {
			try {
				throw new Exception("获取用户信息错误，msg = " + json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JSONObject.parseObject(json);
		} else {
			JSONObject data = JSONObject.parseObject(json);

			result.put("id", data.getString("unionid"));
			result.put("nickName", data.getString("nickname"));
			result.put("headimgurl", data.getString("headimgurl"));
			String sex = data.getString("sex");
			result.put("openid", data.getString("openid"));
			result.put("sex", Integer.parseInt(sex));//0:男 2：女
			result.put("city", data.getString("city"));
			result.put("province", data.getString("province"));
			result.put("country", data.getString("country"));
			return result;
		}
	}

	/**
	 * 重新获取access_token
	 */
	@Override
	public String refreshToken(String refresh_token) {
		String url = String.format(getRefreshTokenURL(refresh_token));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
		// ResponseEntity<JSONObject> resp = getRestTemplate().getForEntity(uri,
		// JSONObject.class);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JSONObject> resp = restTemplate.getForEntity(uri, JSONObject.class);
		JSONObject jsonObject = resp.getBody();
		String access_token = jsonObject.getString("access_token");
		return access_token;
	}

	/**
	 * 获取accessToken的url
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getAccessTokenURL(String code, String appid, String secret) {
		// 获取授权 access_token
		StringBuilder sb = new StringBuilder();
		sb.append(WxUtils.WX_AUTH_LOGIN_URL);
		sb.append("?appid=").append(appid);
		sb.append("&secret=").append(secret);
		sb.append("&code=").append(code);
		sb.append("&grant_type=authorization_code");
		return sb.toString();
	}

	/**
	 * 获取用户信息的url
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public String getUserInfoURL(String accessToken, String openId) {
		// "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
		StringBuilder sb = new StringBuilder();
		sb.append(WxUtils.WX_USERINFO_URL);
		sb.append("?access_token=").append(accessToken);
		sb.append("&openid=").append(openId);
		sb.append("&lang=zh_CN");
		return sb.toString();
	}

	/**
	 * 重新获取token
	 * 
	 * @param refresh_token
	 * @return
	 */
	public String getRefreshTokenURL(String refresh_token) {
		// "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
		StringBuilder sb = new StringBuilder();
		sb.append(WxUtils.WX_REFRESH_TOKEN_URL);
		sb.append("?appid=").append(WxUtils.WX_APP_ID);
		sb.append("&grant_type=refresh_token");
		sb.append("&refresh_token=").append(refresh_token);
		return sb.toString();
	}
}
