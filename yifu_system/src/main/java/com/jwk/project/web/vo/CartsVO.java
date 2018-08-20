package com.jwk.project.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车
 * 
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsVO {
	/** 购物车id */
	private Long cartId;
	/** 用户id */
	private Long userId;
	/** 牛仔id */
	private Long jeansId;
	/** 商品名称 */
	private String jeansName;
	/** 商品价格 */
	private String price;
	/** 颜色 */
	private String color;
	/** 尺码 */
	private String size;
	/** 尺码id */
	private Long sizeId;
	/** 颜色id */
	private Long colorId;
	/** 数量 */
	private int num;
	/** 商品图片 */
	private String images;
	/** 是否选中 */
	private boolean flag;
	/** 是否删除 */
	private int delFlag;
	/** 创建时间 */
	private String createTime;
}
