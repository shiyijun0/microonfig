package com.jwk.project.app.user.service;

import com.jwk.project.app.user.domain.AppUser;

public interface IAppUserService {

	/**
	 * 根据openid查询用户
	 * 
	 * @param openId
	 * @return
	 */
	public AppUser selectUserByOpenId(String openId);

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @return 结果
	 */
	public int saveUser(AppUser user);
	
	/**
	 * 根据手机号查询用户信息
	 * @param mobile
	 * @param password
	 * @return
	 */
	public AppUser selectUserByMobile(String mobile);
	
	/**
	 * 根据用户ID查询用户信息
	 * @param userId
	 * @return
	 */
	public AppUser selectUserById(Long userId);
}
