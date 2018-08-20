package com.jwk.project.system.front.domainVO;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmOrderVO {
private String addrId;//地址id
private String jeansId;//牛仔id
private String sizes;
private String colors;
private String name;//收货人姓名
private String mobile;//收货人手机号
private String addr;//省市区

private Integer count;//数量
private Double freight;//运费
private String coupon;//优惠券
private Double redPacket;//红包
private Double cards;//礼品卡
private Double actualPayment;//实际付款
private Double price;//商品价格

private String jeansName;//素库名称
     private String id;//订单id
private String createTime;

    private String payStatus;//支付状态
private String payTime;
private String deliveryTime;//发货时间

private Map<String,Object> parts;//区域位置

    private String type;//快递公司简称

    private String typeName;//快递公司名称

    private String postid;//快递公司运单号

    private String temp;//快递公司随机码

    private String url;//快递100网址

    private JSONObject jSONObject;
    public static  final  String kuaidi="http://www.kuaidi100.com/query";

}
