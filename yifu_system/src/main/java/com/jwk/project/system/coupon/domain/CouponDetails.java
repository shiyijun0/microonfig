package com.jwk.project.system.coupon.domain;

import lombok.Data;

/**
 * 优惠券详情
 */
@Data
public class CouponDetails {

    /*优惠券详情id*/
    private Long detailsId;
    /*优惠码*/
    private String code;
    /*状态*/
    private int state;
    /*创建时间*/
    private String ctime;
    /*截止时间*/
    private String etime;
    /*关联父表id*/
    private Long cid;

}
