package com.jwk.project.system.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebOrderGoods implements Serializable {
	private Integer id;

	private String orderId;

	private Integer userId;

	private Integer status;

	private String colorsId;

	private Date ctime;

	private Date updateTime;

	private String sizesId;

	private String buttonsId;

	private String washId;

	private String threadsId;

	private String partsId;

	private String remark;

	private String wordPrice;

	private String wordColor;

	private String wordContent;

	private String wordFont;

	private Integer type;

	private BigDecimal price;

	private Long jeansId;
	private Integer orderType;//下单类型 0定制款 1预售款
	private int num;//购买数量

	// 临时字段
	private String jeansName;

}