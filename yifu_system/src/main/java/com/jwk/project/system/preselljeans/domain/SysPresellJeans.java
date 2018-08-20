package com.jwk.project.system.preselljeans.domain;

import com.jwk.project.system.limitconfig.domain.SysLimitConfig;

import lombok.Data;

/**
 * 预售款
 * @author Administrator
 *
 */
@Data
public class SysPresellJeans {
	 /**预售款id*/
	 private Long id;
	 /**商品名称 */
	 private String name; 
	 /**商品编号 */
	 private String presellCode;
	 /**商品图片 */
	 private String images;
	 /**商品详细图 */
	 private String detailImgs;
	 /**商品详细图1 */
	 private String detailImg1;
	 /**商品详细图2 */
	 private String detailImg2;
	 /**商品详细图3 */
	 private String detailImg3;
	 /**价格 */
	 private String price;
	 /**价格名称 */
	 private String priceName;
	 /**原价 */
	 private String originalPrice;
	 /**库存 */
	 private int inventory;
	 /**尺码 */
	 private String sizesId;
	 /**颜色 */
	 private String colorsId;
	 /**排序号 */
	 private int orderNum;
	 /**是否上架 0：是 1：否 */
	 private int status;
	 /**创建时间 */
	 private String createTime;
	 /**修改时间 */
	 private String updateTime;
	 /**剩余库存量 */
	 private int resultNum;
	 
	 /**限量信息 */
	private SysLimitConfig sysLimitConfig;
	 
	 /**详细图片 */
	 private String[] deatilImgList;

	//临时字段
	private String sizesName;
	private String colorsName;
	 
}