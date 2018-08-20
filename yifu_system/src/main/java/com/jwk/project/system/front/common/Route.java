package com.jwk.project.system.front.common;

public class Route {

	public static final String PATH = "/api";
	// 用户登录
	public static final String LOGIN = "/login";
	
	// 用户模块
	public class Uesr {

		public static final String PATH = "/users";
		

	}

	
	public static String addString(String id) {
		StringBuffer sb = new StringBuffer();
		
			sb.append(id + ",");
		
		return sb.toString();
	}

}