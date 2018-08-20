package com.jwk.common.utils.pay.refundquery;

import java.io.Serializable;

/**
 * 微信退款查询请求响应参数
 */
public class WXPayRefundQueryResVo implements Serializable {

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
	private String total_fee = "";// 总金额
	private String fee_type = "";// 货币种类
	private int cash_fee = 0;// 现金支付金额
	private int refund_count = 0;// 退款笔数
	private String out_refund_no_$n = "";// 商户退款单号
	private String refund_id_$n = "";// 微信退款单号
	private String refund_channel_$n = "";// 退款渠道
	private String refund_fee_$n = "";// 退款金额
	private String coupon_refund_fee_$n = "";// 代金券或立减优惠退款金额
	private String coupon_refund_count_$n = "";// 代金券或立减优惠使用数量
	private String coupon_refund_batch_id_$n_$m = "";// 代金券或立减优惠批次ID
	private String coupon_refund_id_$n_$m = "";// 代金券或立减优惠ID
	private String coupon_refund_fee_$n_$m = "";// 单个代金券或立减优惠支付金额
	/**
	 * 退款状态：<br/>
	 * SUCCESS—退款成功<br/>
	 * FAIL—退款失败<br/>
	 * PROCESSING—退款处理中<br/>
	 * NOTSURE—未确定，需要商户原退款单号重新发起<br/>
	 * CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
	 */
	private String refund_status_$n = "";

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

	public int getRefund_count() {
		return refund_count;
	}

	public void setRefund_count(int refund_count) {
		this.refund_count = refund_count;
	}

	public String getOut_refund_no_$n() {
		return out_refund_no_$n;
	}

	public void setOut_refund_no_$n(String out_refund_no_$n) {
		this.out_refund_no_$n = out_refund_no_$n;
	}

	public String getRefund_id_$n() {
		return refund_id_$n;
	}

	public void setRefund_id_$n(String refund_id_$n) {
		this.refund_id_$n = refund_id_$n;
	}

	public String getRefund_channel_$n() {
		return refund_channel_$n;
	}

	public void setRefund_channel_$n(String refund_channel_$n) {
		this.refund_channel_$n = refund_channel_$n;
	}

	public String getRefund_fee_$n() {
		return refund_fee_$n;
	}

	public void setRefund_fee_$n(String refund_fee_$n) {
		this.refund_fee_$n = refund_fee_$n;
	}

	public String getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}

	public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
		this.coupon_refund_fee_$n = coupon_refund_fee_$n;
	}

	public String getCoupon_refund_count_$n() {
		return coupon_refund_count_$n;
	}

	public void setCoupon_refund_count_$n(String coupon_refund_count_$n) {
		this.coupon_refund_count_$n = coupon_refund_count_$n;
	}

	public String getCoupon_refund_batch_id_$n_$m() {
		return coupon_refund_batch_id_$n_$m;
	}

	public void setCoupon_refund_batch_id_$n_$m(String coupon_refund_batch_id_$n_$m) {
		this.coupon_refund_batch_id_$n_$m = coupon_refund_batch_id_$n_$m;
	}

	public String getCoupon_refund_id_$n_$m() {
		return coupon_refund_id_$n_$m;
	}

	public void setCoupon_refund_id_$n_$m(String coupon_refund_id_$n_$m) {
		this.coupon_refund_id_$n_$m = coupon_refund_id_$n_$m;
	}

	public String getCoupon_refund_fee_$n_$m() {
		return coupon_refund_fee_$n_$m;
	}

	public void setCoupon_refund_fee_$n_$m(String coupon_refund_fee_$n_$m) {
		this.coupon_refund_fee_$n_$m = coupon_refund_fee_$n_$m;
	}

	public String getRefund_status_$n() {
		return refund_status_$n;
	}

	public void setRefund_status_$n(String refund_status_$n) {
		this.refund_status_$n = refund_status_$n;
	}
}
