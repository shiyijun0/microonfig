package com.jwk.project.system.coupon.domain;

import lombok.Data;

/**
 * 优惠券 sys_coupon
 * @author  陈志辉
 */
@Data
public class Coupon {
    /**优惠券id */
    private Long couponId;
    /**优惠券类型级别 */
    private String rank;
    /**满这个价开始优惠 */
    private Double full;
    /**优惠力度（价格） */
    private Double rates;
    /**数量 */
    private int number;
    /**0正常， 1已使用 */
    private int status;
    /**创建时间 */
    private String ctime;
    /**失效时间 */
    private String endtime;
    /**1 普通 2 VIP 3 免单 */
    private int people;

    private CouponDetails couponDetails;

}
