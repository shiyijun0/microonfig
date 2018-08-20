package com.jwk.common.utils.pay.refund;

import java.io.Serializable;

/**
 * 微信支付退款请求响应参数
 */
public class WXPayRefundResVo implements Serializable {

	private static final long serialVersionUID = 1L;
	// 协议层
	private String return_code = "";
	private String return_msg = "";

	// 协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
	private String result_code = "";
	private String err_code = "";
	private String err_code_des = "";
	private String appid = "";
	private String mch_id = "";
	private String device_info = "";// 设备号
	private String nonce_str = "";
	private String sign = "";

	private String transaction_id = "";// 微信支付订单号
	private String out_trade_no = "";// 商户订单号
	private String out_refund_no = "";// 商户退款单号
	private String refund_id = "";// 微信退款单号
	private String refund_channel = "";// 退款渠道
	private int refund_fee = 0;// 退款金额
	private String total_fee = "";// 总金额
	private String fee_type = "";// 货币种类
	private int cash_fee = 0;// 现金支付金额
	private int cash_refund_fee = 0;// 现金退款金额
	private int coupon_refund_fee = 0;// 代金券或立减优惠退款金额
	private int coupon_refund_count = 0;// 代金券或立减优惠使用数量
	private int coupon_refund_id = 0;// 代金券或立减优惠ID

	/**
	 * 判断是否成功
	 *
	 * @return
	 */
	public boolean isSuccess() {
		if (getReturn_code().equalsIgnoreCase("SUCCESS") && getResult_code().equalsIgnoreCase("SUCCESS")) {
			return true;
		}

		return false;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
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

	public String getRefund_channel() {
		return refund_channel;
	}

	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}

	public int getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(int cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}

	public int getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(int coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}

	public int getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public void setCoupon_refund_count(int coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}

	public int getCoupon_refund_id() {
		return coupon_refund_id;
	}

	public void setCoupon_refund_id(int coupon_refund_id) {
		this.coupon_refund_id = coupon_refund_id;
	}
}
