package com.jwk.project.app.user.dao;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.app.user.domain.AppUser;

@Repository("appUserDao")
public class AppUserDaoImpl extends DynamicObjectBaseDao implements IAppUserDao {

	/**
	 * 根据openid查询用户是否存在
	 */
	@Override
	public AppUser selectUserByOpenId(String openId) {
		return this.findForObject("AppUserMapper.selectUserByOpenId", openId);
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public int updateUser(AppUser user) {
		return this.update("AppUserMapper.updateUser", user);
	}

	/**
	 * 新增用户信息
	 */
	@Override
	public int insertUser(AppUser user) {
		return this.save("AppUserMapper.insertUser", user);
	}

	/**
	 * 根据手机号查询用户
	 */
	@Override
	public AppUser selectUserByMobile(String mobile) {
		return this.findForObject("AppUserMapper.selectUserByMobile", mobile);
	}

	@Override
	public AppUser selectUserById(Long userId) {
		return this.findForObject("AppUserMapper.selectUserById", userId);
	}

}
