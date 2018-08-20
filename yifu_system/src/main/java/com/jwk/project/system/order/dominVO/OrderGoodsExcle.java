package com.jwk.project.system.order.dominVO;

import lombok.Data;

@Data
public class OrderGoodsExcle {
/* "公司名称","款式图片前","款式图片后","款式图片后","订单编号","商品编号","商品名称","尺码","数量","布料","1区（右腿前面上方）-1","2区（右腿前面下方）-2"
        		,"3区(左腿前面上方)-1","4区( 左腿前面下方 )-1","5区( 右腿后面上方 )-2","6区( 右腿后面下方 )-1","7区(左腿后面上方)-1","8区(左腿后面下方)-1","9区(右口袋)","10区(左口袋)","11区（工字扣"
        		,"皮牌(暂无)","订单时间","运输方式","客户账号","收货人","手机","收货地址","备注" */
private String companyName;
private String picBefore;
private String picAfter;
private String orderId;
private String goodsId;
private String goodsName;
private String size;
private String num;
private String buliao;
private String fq_a;
private String fq_b;
private String fq_c;
private String fq_d;
private String fq_e;
private String fq_f;
private String fq_g;
private String fq_h;
private String fq_i;
private String fq_j;
private String fq_k;
private String card;
private String orderTime;
private String transport;
private String account;
private String contact;
private String mobile;
private String address;
private String remark;
public OrderGoodsExcle(String companyName, String picBefore, String picAfter,
		String orderId, String goodsId, String goodsName, String size,
		String num,String buliao, String fq_a, String fq_b, String fq_c, String fq_d,
		String fq_e, String fq_f, String fq_g, String fq_h, String fq_i,
		String fq_j, String fq_k, String card, String orderTime,
		String transport, String account, String contact, String mobile,
		String address, String remark) {
	super();
	this.companyName = companyName;
	this.picBefore = picBefore;
	this.picAfter = picAfter;
	this.orderId = orderId;
	this.goodsId = goodsId;
	this.goodsName = goodsName;
	this.size = size;
	this.num = num;
	this.buliao = buliao;
	this.fq_a = fq_a;
	this.fq_b = fq_b;
	this.fq_c = fq_c;
	this.fq_d = fq_d;
	this.fq_e = fq_e;
	this.fq_f = fq_f;
	this.fq_g = fq_g;
	this.fq_h = fq_h;
	this.fq_i = fq_i;
	this.fq_j = fq_j;
	this.fq_k = fq_k;
	this.card = card;
	this.orderTime = orderTime;
	this.transport = transport;
	this.account = account;
	this.contact = contact;
	this.mobile = mobile;
	this.address = address;
	this.remark = remark;
}


}
