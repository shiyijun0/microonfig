package com.jwk.project.system.preselljeans.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;

/**
 * 预售款 业务层
 * 
 * @author system
 */
public interface IPresellJeansService {
	/**
     * 根据条件分页查询预售款
     * 
     * @param pageUtilEntity 分页对象
     * @return 预售款集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询预售款列表
     * 
     * @return 预售款列表
     */
    public List<SysPresellJeans> selectAll();

    /**
     * 查询所有上架的预售款列表
     * 
     * @return 预售款列表
     */
    public List<SysPresellJeans> selectAllPut();
    
    /**
     * 通过预售款ID查询信息
     * 
     * @param id 预售款ID
     * @return 预售款信息
     */
    public SysPresellJeans selectById(Long id);
    

    /**
     * 通过预售款ID删除信息
     * 
     * @param id 预售款ID
     * @return 结果
     */
    public int deleteById(Long id);

    /**
     * 批量删除预售款信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeletePresellJeans(Long[] ids);

    /**
     * 保存预售款信息
     * 
     * @param sysPresellJeans 预售款信息
     * @return 结果
     */
    public int save(SysPresellJeans sysPresellJeans);
}
