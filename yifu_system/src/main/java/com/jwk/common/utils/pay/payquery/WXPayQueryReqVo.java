package com.jwk.common.utils.pay.payquery;

import com.jwk.common.utils.pay.MapUtil;
import com.jwk.common.utils.pay.RandomStringGenerator;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.pay.WXSign;

import java.io.Serializable;

/**
 * 微信支付查询请求参数
 */
public class WXPayQueryReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appid = "";// 公众账号ID
	private String mch_id = "";// 商户号
	private String transaction_id = "";// 微信订单号
	private String out_trade_no = "";// 商户订单号
	private String nonce_str = "";// 随机字符串
	private String sign = "";// 签名

	/**
	 * 仅传入商户订单号:推荐使用此构造函数
	 *
	 * @param out_trade_no
	 *            商户订单号
	 */
	public WXPayQueryReqVo(String out_trade_no) {
		this("", out_trade_no);
	}

	/**
	 * 二选一，两个都填以微信订单号为主
	 *
	 * @param transaction_id
	 *            微信订单号
	 * @param out_trade_no
	 *            商户订单号
	 */
	public WXPayQueryReqVo(String transaction_id, String out_trade_no) {
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;

		this.appid = WXPayConfig.APP_ID;
		this.mch_id = WXPayConfig.MCH_ID;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXPayQueryReqVo.class, this));
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
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
