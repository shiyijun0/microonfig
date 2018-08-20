package com.jwk.common.utils.pay;

public enum BillTypeEnum {
	/**
	 * 返回当日所有订单信息，默认值
	 */
	ALL,
	/**
	 * 返回当日成功支付的订单
	 */
	SUCCESS,
	/**
	 * 返回当日退款订单
	 */
	REFUND,
	/**
	 * 已撤销的订单
	 */
	REVOKED;
}
