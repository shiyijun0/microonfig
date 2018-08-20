package com.jwk.project.system.limitconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.system.limitconfig.dao.ILimitConfigDao;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;

/**
 * 预售款限量 业务层处理
 * 
 * @author system
 */
@Service("limitConfigService")
public class LimitConfigServiceImpl implements ILimitConfigService {

	@Autowired
	ILimitConfigDao limitConfigDao;

	/**
	 * 根据限量id查询限量信息
	 */
	@Override
	public SysLimitConfig selectLimitConfigById(Long id) {
		return limitConfigDao.selectLimitConfigById(id);
	}

	/**
	 * 根据预售款id查询限量信息
	 */
	@Override
	public SysLimitConfig selectByPresellId(Long presellId) {
		return limitConfigDao.selectByPresellId(presellId);
	}

	/**
	 * 保存限量信息
	 */
	@Override
	public int saveLimitConfig(SysLimitConfig sysLimitConfig) {
		Long id = sysLimitConfig.getId();
		if (StringUtils.isNotNull(id)) {
			return limitConfigDao.updateLimitConfig(sysLimitConfig);
		} else {
			sysLimitConfig.setId(StrUtils.generateInstanceID());
			return limitConfigDao.insertLimitConfig(sysLimitConfig);
		}
	}

	/**
	 * 删除限量信息
	 */
	@Override
	public int deleteLimitConfigById(Long id) {
		return limitConfigDao.deleteLimitConfigById(id);
	}

	/**
	 * 通过预售款ID查询可用的限量信息
	 */
	@Override
	public SysLimitConfig selectUseByPresellId(Long presellId) {
		return limitConfigDao.selectUseByPresellId(presellId);
	}

}
