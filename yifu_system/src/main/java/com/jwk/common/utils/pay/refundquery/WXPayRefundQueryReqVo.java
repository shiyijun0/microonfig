package com.jwk.common.utils.pay.refundquery;

import com.jwk.common.utils.pay.MapUtil;
import com.jwk.common.utils.pay.RandomStringGenerator;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.pay.WXSign;

import java.io.Serializable;

/**
 * 微信退款查询请求参数
 */
public class WXPayRefundQueryReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appid = "";// 公众账号ID:必填
	private String mch_id = "";// 商户号:必填
	private String device_info = "";// 设备号
	private String nonce_str = "";// 随机字符串:必填(随机字符串，不长于32位)
	private String sign = "";// 签名:必填

	private String transaction_id = "";// 微信订单号
	private String out_trade_no = "";// 商户订单号
	private String out_refund_no = "";// 商户退款单号
	private String refund_id = "";// 微信退款单号

	/**
	 * 推荐使用此构造函数
	 *
	 * @param out_trade_no
	 *            商户订单号
	 */
	public WXPayRefundQueryReqVo(String out_trade_no) {
		this("", out_trade_no, "", "");
	}

	/**
	 * 四选一
	 *
	 * @param transaction_id
	 *            微信订单号
	 * @param out_trade_no
	 *            商户订单号
	 * @param out_refund_no
	 *            商户退款单号
	 * @param refund_id
	 *            微信退款单号
	 */
	public WXPayRefundQueryReqVo(String transaction_id, String out_trade_no, String out_refund_no, String refund_id) {
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.refund_id = refund_id;

		this.appid = WXPayConfig.APP_ID;
		this.mch_id = WXPayConfig.MCH_ID;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXPayRefundQueryReqVo.class, this));
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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
}
