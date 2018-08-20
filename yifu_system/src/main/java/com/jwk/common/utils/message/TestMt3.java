/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jwk.common.utils.message;

/**
 *
 * @author Administrator
 */
public class TestMt3 {

	public static void test() {

		String ip = "210.51.190.232";
		int port = 8086;
		HttpClientUtil util = new HttpClientUtil(ip, port, "/mt/MT3.ashx");

		// 用户名
		String user = "";

		// 密码：
		String pwd = "";

		String ServiceID = "SEND";

		// 目的号码
		String dest = "8613701387330";

		// 原号码
		String sender = "8613701234567";

		// 短信内容
		String msg = "大家好 test 123 。。";

		// UTF-16BE
		String hex = WebNetEncode.encodeHexStr(8, msg);
		hex = hex.trim() + "&codec=8";
		// "ISO-8859-1"
		// String hex = WebNetEncode.encodeHexStr(3, msg);
		// hex = hex.trim() + "&codec=3";

		// "GBK"
		// String hex = WebNetEncode.encodeHexStr(15, msg);
		// hex = hex.trim() + "&codec=15";

		// "ASCII"
		// String hex = WebNetEncode.encodeHexStr(0, msg);
		// hex = hex.trim() + "&codec=0";

		// 4e3e9996534353e496c45173575062e54e791cc957f57ce5c4581ea71364e4b62405438592957304e4b70756c14
		// 4E3E9996534353E496C45173575062E54E0791CC957F57CE5C4581EA71364E4B62405438592957304E4B70756C14
		// String mms = "举首千古雄关坐拥万里长城居自然之所吸天地之灵气";
		// System.out.println(WebNetEncode.encodeHexStr(8, mms));

		// System.out.println("msgid = " + util.sendGetMessage( user, pwd, ServiceID,
		// dest, sender, hex));

		System.out.println("POST MT3");
		System.out.println("msgid = " + util.sendPostMessage(user, pwd, ServiceID, dest, sender, hex));
	}
}
