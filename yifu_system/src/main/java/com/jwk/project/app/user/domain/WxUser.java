package com.jwk.project.app.user.domain;

import lombok.Data;

@Data
public class WxUser {

	private String openid;
	// 昵称
	private String nickname;
	// 头像
	private String headimgurl;
	// 1男 2女 0未知
	private int sex;
	// 市
	private String city;
	// 省
	private String province;
	// 国家
	private String country;
}
