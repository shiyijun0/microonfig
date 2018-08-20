package com.jwk.project.system.fashion.service;
import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommendConfig;
/**
 * 设计师推荐配置业务层
 * 
 * @author system
 */
public interface RecommendConfigService
{

    /**
     * 根据条件分页查询数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐配置数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有设计师推荐配置
     * 
     * @return 权限列表
     */
    public List<SysRecommendConfig> selectSysRecommendConfigAll();

    /**
     * 通过ID查询设计师推荐配置
     * 
     * @param roleId 设计师推荐配置ID
     * @return 设计师推荐配置对象信息
     */
    public SysRecommendConfig selectSysRecommendConfigById(Long id);

    /**
     * 通过设计师推荐配置ID删除设计师推荐配置信息
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
     * 保存设计师推荐配置信息
     * 
     * @param SysRecommendConfig 设计师推荐配置信息
     * @return 结果
     */
    public int saveSysRecommendConfig(SysRecommendConfig sysRecommendConfig);

   
}
