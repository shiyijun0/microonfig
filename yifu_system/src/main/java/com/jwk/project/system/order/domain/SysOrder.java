package com.jwk.project.system.order.domain;


import java.math.BigDecimal;
import java.util.Date;

import com.jwk.project.system.coupon.domain.Coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysOrder {
    private Long id;
    
    private String orderId;
    
    private Integer userId;

    private Integer addrId;

    private Integer couponId;

    private String remark;

    private Integer num;

    private Integer payType;

    private BigDecimal money;

    private Integer payStatus;

    private Date payTime;

    private Date createTime;
    
    private String color;
    
    private String size;
    
    private String jeansName;
    private int orderType;
    //临时字段
    private Coupon coupon;
    private String sizesId;
    private  String colorsId;
    private  String jeansId;

	public SysOrder(String orderId, Integer userId, Integer addrId,
			Integer num, BigDecimal money) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.addrId = addrId;
		this.num = num;
		this.money = money;
	}
    
    
   	
}