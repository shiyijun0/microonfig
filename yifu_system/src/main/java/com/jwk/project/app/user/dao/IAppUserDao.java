package com.jwk.project.app.user.dao;

import com.jwk.project.app.user.domain.AppUser;

public interface IAppUserDao {
	/**
	 * 根据openid查询用户
	 * @param openId
	 * @return
	 */
	public AppUser selectUserByOpenId(String openId);
	
	/**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(AppUser user);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(AppUser user);
    
    /**
     * 用户登录 根据手机号查询
     */
    public AppUser selectUserByMobile(String mobile);
    
    /**
     * 根据用户id查询
     */
    public AppUser selectUserById(Long userId);
}
