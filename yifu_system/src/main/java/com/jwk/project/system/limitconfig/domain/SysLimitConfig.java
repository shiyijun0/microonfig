package com.jwk.project.system.limitconfig.domain;

import lombok.Data;

/**
 * 预售款限时限量限价
 * 
 * @author Administrator
 *
 */
@Data
public class SysLimitConfig {
	/** id */
	private Long id;
	/** 预售款id */
	private Long presellId;
	/** 限量 */
	private int limited;
	/** 价格 */
	private String price;
	/** 是否启用 0禁用 1启用 */
	private int status;
	/** 限时开始时间 */
	private String startTime;
	/** 限时结束时间 */
	private String endTime;
	/** 创建时间 */
	private String createTime;
	/** 修改时间 */
	private String updateTime;
	/** 剩余库存量 */
	private int resultNum;

}