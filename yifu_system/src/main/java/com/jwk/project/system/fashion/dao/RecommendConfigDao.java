package com.jwk.project.system.fashion.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommendConfig;

/**
 * 设计师推荐配置表 数据层
 * 
 * @author system
 */
public interface RecommendConfigDao
{

    /**
     * 根据条件分页查询设计师推荐配置数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐配置数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询设计师推荐配置列表
     * 
     * @return 设计师推荐配置列表
     */
    public List<SysRecommendConfig> selectSysRecommendConfigAll();

    /**
     * 通过设计师推荐配置ID查询设计师推荐配置信息
     * 
     * @param id 设计师推荐配置ID
     * @return 设计师推荐配置对象信息
     */
    public SysRecommendConfig selectSysRecommendConfigById(Long id);

    /**
     * 通过设计师推荐配置ID删除设计师推荐配置
     * 
     * @param id 设计师推荐配置ID
     * @return 结果
     */
    public int deleteSysRecommendConfigById(Long id);

    /**
     * 批量删除设计师推荐配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysRecommendConfig(Long[] ids);

   

    /**
     * 修改设计师推荐配置信息
     * 
     * @param SysRecommendConfig 设计师推荐配置信息
     * @return 结果
     */
    public int updateSysRecommendConfig(SysRecommendConfig sysRecommendConfig);

    /**
     * 新增设计师推荐配置信息
     * 
     * @param SysRecommendConfig 设计师推荐配置信息
     * @return 结果
     */
    public int insertSysRecommendConfig(SysRecommendConfig sysRecommendConfig);
    
    

}
