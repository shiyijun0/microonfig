package com.jwk.project.app.pay.domain;

import lombok.Data;

/**
 * 微信支付记录
 * 
 * @author Administrator
 *
 */
@Data
public class PayRecord {

	/** 支付记录id */
	private Long id;
	/** 牛仔裤id */
	private String jeansId;
	/** 用户id */
	private Long userId;
	/** 订单id */
	private Long orderId;
	/** 支付金额 */
	private float money;
	/** 支付类型 */
	private int payType;
	/** 支付状态 */
	private int status;
	/** 支付时间 */
	private String createTime;
	/** 交易号 */
	private String tradeNo;
}
