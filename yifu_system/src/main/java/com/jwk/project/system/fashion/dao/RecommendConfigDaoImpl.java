package com.jwk.project.system.fashion.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommendConfig;



/**
 * 设计师推荐配置 数据层处理
 * 
 * @author system
 */
@Repository("recommendConfigDao")
public class RecommendConfigDaoImpl extends DynamicObjectBaseDao implements RecommendConfigDao
{

	 /**
     * 根据条件分页查询设计师推荐配置数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐配置数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemRecommendConfigMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据设计师推荐配置ID查询设计师推荐配置
     * 
     * @param id 设计师推荐配置ID
     * @return 设计师推荐配置列表
     */
    @Override
    public List<SysRecommendConfig> selectSysRecommendConfigAll()
    {
        List<SysRecommendConfig> SysRecommendConfigList = null;
        try
        {
            SysRecommendConfigList = this.findForList("SystemRecommendConfigMapper.selectSysRecommendConfigAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysRecommendConfigList;
    }

    /**
     * 通过设计师推荐配置ID查询设计师推荐配置
     * 
     * @param id 设计师推荐配置ID
     * @return 设计师推荐配置对象信息
     */
    @Override
    public SysRecommendConfig selectSysRecommendConfigById(Long id)
    {
        return this.findForObject("SystemRecommendConfigMapper.selectSysRecommendConfigById", id);
    }
    
    

    /**
     * 通过设计师推荐配置ID删除设计师推荐配置
     * 
     * @param id 设计师推荐配置ID
     * @return 结果
     */
    @Override
    public int deleteSysRecommendConfigById(Long id)
    {
        return this.delete("SystemRecommendConfigMapper.deleteSysRecommendConfigById", id);
    }

    /**
     * 批量删除设计师推荐配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysRecommendConfig(Long[] ids)
    {
        return this.delete("SystemRecommendConfigMapper.batchDeleteSysRecommendConfig", ids);
    }

   
    /**
     * 保存设计师推荐配置信息
     * 
     * @param  设计师推荐配置信息
     * @return 结果
     */
    @Override
    public int updateSysRecommendConfig(SysRecommendConfig sysRecommendConfig)
    {
        return this.update("SystemRecommendConfigMapper.updateSysRecommendConfig", sysRecommendConfig);
    }

    /**
     * 新增设计师推荐配置信息
     * 
     * @param  设计师推荐配置信息
     * @return 结果
     */
    @Override
    public int insertSysRecommendConfig(SysRecommendConfig sysRecommendConfig)
    {
        return this.update("SystemRecommendConfigMapper.insertSysRecommendConfig", sysRecommendConfig);
    }

   
   
}
