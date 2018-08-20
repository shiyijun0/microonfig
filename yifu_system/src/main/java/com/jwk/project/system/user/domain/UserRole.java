package com.jwk.project.system.user.domain;

import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author system
 */
@Data
public class UserRole
{
    /** 用户ID */
    private Long userId;
    /** 角色ID */
    private Long roleId;
}
