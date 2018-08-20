package com.jwk.common.utils.pay;

import java.io.Serializable;

/**
 * 请求参数对象
 */
public class WXH5PayReqVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String out_trade_no = "";// 商户订单号:必填(商户系统内部的订单号,32个字符)
	private String openid = "";// 用户标识

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
