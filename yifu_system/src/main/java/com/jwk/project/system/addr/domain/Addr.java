package com.jwk.project.system.addr.domain;

import lombok.Data;

/**
 * 收货地址 sys_user_addr
 * 
 * @author system
 */
@Data
public class Addr
{
    /** 地址ID */
    private Long addrId;
    /** 用户ID */
    private Long userId;
    /** 省份ID */
    private Long provinceId;
    /** 城市ID */
    private Long cityId;
    /** 区域ID */
    private Long areaId;
    /** 收货详细地址 */
    private String addr;
    /** 备注 */
    private String remark;
    /** 联系人 */
    private String linkman;
    /** 联系电话 */
    private String mobile;
    /** 是否默认收货地址 0：否 1：是 */
    private int defaultFlag;
    /** 创建时间 */
    private String createTime;
    /** 更新时间 */
    private String updateTime;

}
