package com.jwk.common.utils.pay;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * app 微信支付配置信息
 */
public class AppWXPayConfig {

	// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	public static String KEY = "";

	// 微信分配的公众号ID（开通公众号之后可以获取到）
	public static String APP_ID = "";

	// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static String MCH_ID = "";

	// 机器IP
	public static String IP = "";

	// 微信支付回调入口
	public static String PAY_CALLBACK_URL = "";

	// 微信证书秘钥地址
	public static String CERT_FILE_PATH = "";

	// 统一下单URL
	public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	// 扫支付查询URL
	public static final String PAY_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

	// 支付关闭URL
	public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";

	// 退款URL
	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	// 退款查询URL
	public static final String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

	// 对账单下载
	public static final String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";

	static {

		// 初始化必要参数
		if (StringUtils.isBlank(APP_ID)) {
			APP_ID = "";
		}

		if (StringUtils.isBlank(MCH_ID)) {
			MCH_ID = "";
		}

		if (StringUtils.isBlank(KEY)) {
			KEY = "";
		}
		if (StringUtils.isBlank(PAY_CALLBACK_URL)) {
			PAY_CALLBACK_URL = "";
		}

		if (StringUtils.isBlank(CERT_FILE_PATH)) {
			CERT_FILE_PATH = "";
		}

		if (StringUtils.isBlank(IP)) {
			try {
				IP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				IP = "192.168.1.1";
				// throw new RuntimeException("获取本机IP失败");
			}
		}
	}

}
