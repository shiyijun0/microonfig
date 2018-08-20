package com.jwk.project.system.limitconfig.service;

import com.jwk.project.system.limitconfig.domain.SysLimitConfig;

/**
 * 预售款限量 业务层
 * 
 * @author system
 */
public interface ILimitConfigService {
	 /**
     * 通过限量ID查询信息
     * 
     * @param id 限量ID
     * @return 预售款信息
     */
    public SysLimitConfig selectLimitConfigById(Long id);
    
    /**
     * 通过预售款ID查询信息
     * 
     * @param id 预售款ID
     * @return 结果
     */
    public SysLimitConfig selectByPresellId(Long presellId);

    /**
     * 通过预售款ID查询可用的限量信息
     * 
     * @param id 预售款ID
     * @return 结果
     */
    public SysLimitConfig selectUseByPresellId(Long presellId);
    
    /**
     * 保存限量信息
     * 
     * @param sysLimitConfig 限量信息
     * @return 结果
     */
    public int saveLimitConfig(SysLimitConfig sysLimitConfig);
    
    /**
     * 通过限量ID删除信息
     * 
     * @param id 限量ID
     * @return 结果
     */
    public int deleteLimitConfigById(Long id);
}
