package com.jwk.project.app.pay.service;

public interface IAliPayService {
	/**
	 * 支付宝订单支付
	 * @return
	 */
	public String orderPay(String orderId,String userId, String addrId, int num, String money, String jeansId);
	
	/**
	 * 支付宝订单支付
	 * @return
	 */
	public String wapPay(String userId, String addrId, int num, String money, String jeansId);
}
