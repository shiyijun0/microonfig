package com.jwk.common.utils.pay.payclose;

import com.jwk.common.utils.pay.MapUtil;
import com.jwk.common.utils.pay.RandomStringGenerator;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.pay.WXSign;

import java.io.Serializable;

/**
 * 微信支付关闭请求参数
 */
public class WXPayCloseReqVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String appid = "";// 公众账号ID
	private String mch_id = "";// 商户号
	private String out_trade_no = "";// 商户订单号
	private String nonce_str = "";// 随机字符串
	private String sign = "";// 签名

	/**
	 * @param out_trade_no
	 *            商户订单号
	 */
	public WXPayCloseReqVo(String out_trade_no) {
		this.out_trade_no = out_trade_no;

		this.appid = WXPayConfig.APP_ID;
		this.mch_id = WXPayConfig.MCH_ID;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXPayCloseReqVo.class, this));
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
