package com.jwk.project.system.registeruser.domain;

import lombok.Data;

/**
 * 注册用户  sys_register_user
 * @author 陈志辉
 */

@Data
public class RegisterUser {
    /**用户id */
    private Long id;
    /** 手机号码 */
    private String mobile;
    /** 用户昵称 */
    private String nickname;
    /** 密码 */
    private String password;
    /**头像地址*/
    private String portraiturl;
    /**验证码*/
    private String code;
    /** token */
    private String token;
    /** 1 男 ,2女 */
    private int sex;
    /** 微信号 */
    private String wechat;
    /** 微博 */
    private String weibo;
    /** qq */
    private String qq;
    /** 1 正常，2 禁用，-1 删除 */
    private int status;
    /** 更新时间     */
    private String updateTime;
    /** 创建时间     */
    private String ctime;
    /** 生日     */
    private String birthday;
    /** 用户类型： 1普通用户 ，2设计师  */
    private int type;
    /**积分      */
    private int integral;
//临时字段
    private String kuaidi;

}
