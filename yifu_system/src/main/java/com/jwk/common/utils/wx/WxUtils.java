package com.jwk.common.utils.wx;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;

@Slf4j
public class WxUtils {

	// h5授权登录 京东例子
	//https://open.weixin.qq.com/sns/explorer_broker?appid=&redirect_uri=&response_type=code&scope=snsapi_userinfo&state=45lqf8ju&connect_redirect=1#wechat_redirect
	//public static final String WX_AUTHORIZATION_H5_URL = "https://open.weixin.qq.com/sns/explorer_broker";
	// 微信公众号授权												
	public static final String GZH_AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	//pc端扫码授权
	public static final String WX_AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/qrconnect";
	// 根据code获取access_token
	public static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	// 根据code重新获取access_token
	public static final String WX_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	// 检查access_token有效性
	public static final String WX_AUTH_URL = "https://api.weixin.qq.com/sns/auth";
	// 获取用户基本信息
	public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	// appid和appSecret 是在开放平台上申请的
	// AppId
	public static final String WX_APP_ID = "wxe394a1e8635681d4";//wxdc5ae7bd5a17c129
	// AppSecret
	public static final String WX_APP_KEY = "8566000b9dc1b94b86720602b92f3de3";
	//pc端授权回调
	public static final String callback_url = "http://jwk.bzvs.cn/wx/logincallback";
	
	public static final String state = "a145x7wae5x47f65a2d8we5";
	
	public static final String SCOPE = "snsapi_login";
	
	// appid和appSecret 是在公众号平台上申请的
	public static final String GZH_APP_ID = "wxdc5ae7bd5a17c129";// AppId
	public static final String GZH_APP_KEY = "be1341d5cc3dcb262b9b974417c2f88a";// AppSecret
	//public static final String GZH_APP_ID = "wxba3948f39b8d63da";//测试号
	//public static final String GZH_APP_KEY = "f907c43ed6023327a092536be6613857";// 测试号
	public static final String GZH_SCOPE = "snsapi_userinfo";
	//公众号授权回调
	public static final String GZH_CALLBACK_URL = "http://jwk.bzvs.cn/wx/gzh/logincallback";
	
	
	/**
	 * 用户授权登录获取code
	 * 
	 * @param state
	 * @return 用户允许授权后，将会重定向到redirect_uri的网址上，并且带上code和state参数
	 *         redirect_uri?code=CODE&state=STATE
	 *         若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数 redirect_uri?state=STATE
	 * @throws Exception
	 */
	public static String getWXLoginUrl(String state) throws Exception {
		// state 请求页面带过来的参数
		// 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
		String callback_url = "http://jwk.bzvs.cn/wx/logincallback";
		callback_url = URLEncoder.encode(callback_url, "utf-8");
		String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + WX_APP_ID + "&redirect_uri=" + callback_url
				+ "&response_type=code&scope=snsapi_login&state=" + state + "#wechat_redirect";
		return url;
	}

	/**
	 * 获取openId
	 * 
	 * @param appid
	 * @param wx_corp_secret
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String getOpenId(String code) throws Exception {
		String openid = null;

		String url = String.format(getAccessTokenURL(code));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
		RestTemplate restTemplate = new RestTemplate();
		String resp = restTemplate.getForObject(uri, String.class);
		JSONObject jsonObject = JSONObject.parseObject(resp);
		openid = jsonObject.getString("openid");
		return openid;

	}

	/**
	 * 获取access_token
	 */
	public String getAccessToken(String code) {
		String url = String.format(getAccessTokenURL(code));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();
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
	 * 获取accessToken的url
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String getAccessTokenURL(String code) {
		// 获取授权 access_token
		StringBuilder sb = new StringBuilder();
		sb.append(WxUtils.WX_AUTH_LOGIN_URL);
		sb.append("?appid=").append(WxUtils.WX_APP_ID);
		sb.append("&secret=").append(WxUtils.WX_APP_KEY);
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
	 * 获取用户信息
	 */
	public JSONObject getUserInfo(String accessToken, String openId) {
		String url = String.format(getUserInfoURL(accessToken, openId));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		URI uri = builder.build().encode().toUri();

		// String resp = getRestTemplate().getForObject(uri, String.class);
		RestTemplate restTemplate = new RestTemplate();
		String resp = restTemplate.getForObject(uri, String.class);
		log.error("getUserInfo resp = " + resp);
		JSONObject result = new JSONObject();
		if (resp.contains("errcode")) {
			try {
				throw new Exception("获取用户信息错误，msg = " + resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JSONObject.parseObject(resp);
		} else {
			JSONObject data = JSONObject.parseObject(resp);

			result.put("id", data.getString("unionid"));
			result.put("nickName", data.getString("nickname"));
			result.put("avatar", data.getString("headimgurl"));
			String sex = data.getString("sex");
			result.put("openid", data.getString("openid"));
			result.put("sex", Integer.parseInt(sex));
			result.put("city", data.getString("city"));
			result.put("province", data.getString("province"));
			result.put("country", data.getString("country"));
			return result;
		}
	}
	
	/**
	 * 重新获取access_token
	 */
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
