package com.jwk.project.system.limitconfig.dao;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;

/**
 * 预售款限量 控制层
 * 
 * @author Administrator
 *
 */
@Repository("limitConfigDao")
public class LimitConfigDaoImpl extends DynamicObjectBaseDao implements ILimitConfigDao {

	/**
	 * 根据限量id查询限量信息
	 */
	@Override
	public SysLimitConfig selectLimitConfigById(Long id) {
		return this.findForObject("SystemLimitConfigMapper.selectLimitConfigById", id);
	}

	/**
	 * 根据预售款ID查询限量
	 */
	@Override
	public SysLimitConfig selectByPresellId(Long presellId) {
		return this.findForObject("SystemLimitConfigMapper.selectByPresellId", presellId);
	}

	/**
	 * 修改限量
	 */
	@Override
	public int updateLimitConfig(SysLimitConfig sysLimitConfig) {
		return this.update("SystemLimitConfigMapper.updateLimitConfig", sysLimitConfig);
	}

	/**
	 * 新增限量
	 */
	@Override
	public int insertLimitConfig(SysLimitConfig sysLimitConfig) {
		return this.update("SystemLimitConfigMapper.insertLimitConfig", sysLimitConfig);
	}

	/**
	 * 删除限量
	 */
	@Override
	public int deleteLimitConfigById(Long id) {
		return this.delete("SystemLimitConfigMapper.deleteLimitConfigById", id);
	}

	/**
	 * 通过预售款ID查询可用的限量信息
	 */
	@Override
	public SysLimitConfig selectUseByPresellId(Long presellId) {
		return this.findForObject("SystemLimitConfigMapper.selectUseByPresellId", presellId);
	}

}
