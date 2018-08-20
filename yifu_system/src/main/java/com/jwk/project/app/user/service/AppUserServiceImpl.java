package com.jwk.project.app.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.project.app.user.dao.IAppUserDao;
import com.jwk.project.app.user.domain.AppUser;

@Service("appUserService")
public class AppUserServiceImpl implements IAppUserService {

	@Autowired
	private IAppUserDao appUserDao;

	@Override
	public AppUser selectUserByOpenId(String openId) {
		return appUserDao.selectUserByOpenId(openId);
	}

	 /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int saveUser(AppUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()))
        {
            return appUserDao.updateUser(user);
        }
        else
        {
            return appUserDao.insertUser(user);
        }
    }


	@Override
	public AppUser selectUserByMobile(String mobile) {
		return appUserDao.selectUserByMobile(mobile);
	}

	@Override
	public AppUser selectUserById(Long userId) {
		return appUserDao.selectUserById(userId);
	}

}
