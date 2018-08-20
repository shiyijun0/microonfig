package com.jwk.project.system.user.domain;

import com.jwk.project.system.dept.domain.Dept;
import lombok.Data;

/**
 * 用户对象 sys_user
 * 
 * @author system
 */
@Data
public class User
{
    /** 用户ID */
    private Long userId;
    /** 部门ID */
    private Long deptId;
    /** 登录名 */
    private String loginName;
    /** 用户名称 */
    private String userName;
    /** 用户邮箱 */
    private String email;
    /** 手机号码 */
    private String phonenumber;
    /** 密码 */
    private String password;
    /** 盐加密 */
    private String salt;
    /** 类型:Y默认用户,N非默认用户 */
    private String userType;
    /** 帐号状态:0正常,1禁用 */
    private int status;
    /** 拒绝登录描述 */
    private String refuseDes;
    /** 创建者 */
    private String createBy;
    /** 创建时间 */
    private String createTime;
    /** 更新者 */
    private String updateBy;
    /** 更新时间 */
    private String updateTime;
    /** 部门对象 */
    private Dept dept;
    /** 角色组 */
    private Long[] roleIds;

}
