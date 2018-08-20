package com.jwk.common.utils.message;

import com.jwk.common.utils.StrUtils;

/**
 * 国际短信平台接口
 * 
 * @author Administrator
 *
 */
public class GuoJiShortUtils {

	public static String sendCode(String mobile, String validate_code) throws Exception {

		String ip = "210.51.190.232";
		int port = 8085;
		HttpClientUtil util = new HttpClientUtil(ip, port, "/MT3.ashx");

		String user = "JWKJeans";// 用户名
		String pwd = "JWK201609";// 密码：
		String ServiceID = "SEND";// 固定，不需要改变

		String dest = "86" + mobile;// 你的目的号码【收短信的电话号码】 TODO 需要加上归属地如86，才能识别运营商如China
		String sender = "";// 你的原号码,可空【大部分国家原号码带不过去，只有少数国家支持透传，所有一般为空】

		String msg = "您的短信验证码是：" + validate_code + ",如非本人操作，请忽略。【JWK Jeans定制牛仔裤】";// 你的短信内容

		// 短信内容 HEX 编码，8 为 UTF-16BE HEX 编码， dataCoding = 8 ,支持所有国家的语言，建议直接使用 8
		String hex = WebNetEncode.encodeHexStr(8, msg);
		hex = hex.trim() + "&codec=8";

		// 短信唯一ID, 用户在短信提交时返回给用户的一个数字串msgid, string
		String msgid = util.sendGetMessage(user, pwd, ServiceID, dest, sender, hex);
		System.out.println("validate_code = " + validate_code);
		System.out.println("msgid = " + msgid);
		String result = "success";
		if (!StrUtils.isNumeric(msgid))
			result = "error";
		return result;
	}

	public static void main(String[] args) throws Exception {

		  String mobile = "15159873277";
		  String validate_code = StrUtils.getRandomNumber(4);
		  String result = sendCode(mobile, validate_code);
		System.out.println(result);
	}
}