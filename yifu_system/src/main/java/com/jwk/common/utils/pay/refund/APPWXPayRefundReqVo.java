package com.jwk.common.utils.pay.refund;

import com.jwk.common.utils.pay.AppWXPayConfig;
import com.jwk.common.utils.pay.AppWXSign;
import com.jwk.common.utils.pay.MapUtil;
import com.jwk.common.utils.pay.RandomStringGenerator;

import java.io.Serializable;

/**
 * 微信支付退款请求参数
 */
public class APPWXPayRefundReqVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String appid = "";
	private String mch_id = "";
	private String device_info = "";
	private String nonce_str = "";
	private String sign = "";
	private String transaction_id = "";// 微信支付订单号
	private String out_trade_no = "";// 商户订单号
	private String out_refund_no = "";// 商户退款单号
	private int total_fee = 0;// 总金额
	private int refund_fee = 0;// 退款金额
	private String refund_fee_type = "";// 货币种类
	private String op_user_id = "";// 操作员帐号, 默认为商户号

	/**
	 * 推荐使用
	 *
	 * @param out_trade_no
	 *            商户订单号
	 * @param total_fee
	 *            总金额
	 * @param refund_fee
	 *            退款金额
	 */
	public APPWXPayRefundReqVo(String out_trade_no, int total_fee, int refund_fee) {
		this("", out_trade_no, out_trade_no, total_fee, refund_fee);
	}

	/**
	 * 推荐使用
	 *
	 * @param out_trade_no
	 *            商户订单号
	 * @param out_refund_no
	 *            商户退款单号
	 * @param total_fee
	 *            总金额
	 * @param refund_fee
	 *            退款金额
	 */
	public APPWXPayRefundReqVo(String out_trade_no, String out_refund_no, int total_fee, int refund_fee) {
		this("", out_trade_no, out_refund_no, total_fee, refund_fee);
	}

	/**
	 * @param transaction_id
	 *            微信支付订单号
	 * @param out_trade_no
	 *            商户订单号
	 * @param out_refund_no
	 *            商户退款单号
	 * @param total_fee
	 *            总金额
	 * @param refund_fee
	 *            退款金额
	 */
	public APPWXPayRefundReqVo(String transaction_id, String out_trade_no, String out_refund_no, int total_fee,
			int refund_fee) {
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.total_fee = total_fee;
		this.refund_fee = refund_fee;
		this.op_user_id = AppWXPayConfig.MCH_ID;

		this.appid = AppWXPayConfig.APP_ID;
		this.mch_id = AppWXPayConfig.MCH_ID;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = AppWXSign.getSign(MapUtil.objectToMap(APPWXPayRefundReqVo.class, this));
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

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getRefund_fee_type() {
		return refund_fee_type;
	}

	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
}
