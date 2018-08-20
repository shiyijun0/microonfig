package com.jwk.common.utils.pay.payquery;

import java.io.Serializable;

/**
 * 微信支付查询响应参数
 */
public class WXPayQueryResVo implements Serializable {

	private static final long serialVersionUID = 1L;
	// 协议层
	private String return_code = "";
	private String return_msg = "";

	// 协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
	private String appid = "";
	private String mch_id = "";
	private String nonce_str = "";
	private String sign = "";
	private String result_code = "";
	private String err_code = "";
	private String err_code_des = "";

	// 以下字段在trade_state 为SUCCESS 或者REFUND 的时候有返回
	private String device_info = "";// 设备号
	private String openid = "";// 用户标识
	private String is_subscribe = "";// 是否关注公众账号
	private String trade_type = "";// 交易类型
	private String trade_state = "";// 交易状态
	private String bank_type = "";// 付款银行
	private String total_fee = "";// 总金额
	private String fee_type = "";// 货币种类
	private int cash_fee = 0;// 现金支付金额
	private String cash_fee_type = "";// 现金支付货币类型
	private int coupon_fee = 0;// 代金券或立减优惠金额
	private String transaction_id = "";// 微信支付订单号
	private String out_trade_no = "";// 商户订单号
	private String attach = "";// 附加数据
	private String time_end = "";// 支付完成时间
	private String trade_state_desc = "";// 交易状态描述

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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
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

	public int getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(int coupon_fee) {
		this.coupon_fee = coupon_fee;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}
}
