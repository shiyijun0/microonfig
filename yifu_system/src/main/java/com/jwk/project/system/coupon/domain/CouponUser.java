package com.jwk.project.system.coupon.domain;

import lombok.Data;

/*
* 优惠券绑定用户
* */

@Data
public class CouponUser {

    /*主键id*/
    private Long id;

    /*关联用户id*/
    private Long uid;

    /*优惠券详情id*/
    private Long cid;

    /*1:绑定用户未使用 2：已使用*/
    private int state;

    /*优惠券级别*/
    private String rank;

    /*开始时间*/
    private String ctime;

    /*结束时间*/
    private String endtime;

}
