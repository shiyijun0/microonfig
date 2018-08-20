package com.jwk.common.utils.message;

import java.util.Map;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信http接口的java代码调用示例
 */
@Slf4j
public class YunPianUtils {
	private static String API_KEY = "aed7c35ac35dc7e73b6ac0cf288c6958";
	// private static String API_KEY = "c60dc38bcf76d8ad1351dfc921fa273b";

	public static String sendCode(String mobile, String validate_code) throws Exception {
		String text = String.format("【JWK定制牛仔裤】您的验证码是%s。如非本人操作，请忽略本短信", validate_code);
		// 初始化clnt,使用单例方式
		YunpianClient clnt = new YunpianClient(API_KEY).init();
		// 发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, text);
		Result<SmsSingleSend> result = clnt.sms().single_send(param);
		clnt.close();
		log.info("sendCode:{}", result);
		if(result.getCode()==0) {
			return "success";
		}
		return "error";
	}

	public static void main(String[] args) throws Exception {
		//String text = "【云片网】您的验证码是123456";
		String mobile = "15980928870";
		// System.out.println(YunPianUtils.sendSms(text, mobile));

		// 发送短信API
		String r = YunPianUtils.sendCode(mobile, "456789");
		// 获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
		// 账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().*
		// 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
		// 释放clnt
		System.out.println("result**************" + r);
	}
}
