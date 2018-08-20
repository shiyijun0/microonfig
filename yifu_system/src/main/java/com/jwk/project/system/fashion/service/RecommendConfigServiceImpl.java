package com.jwk.project.system.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.dao.RecommendConfigDao;
import com.jwk.project.system.fashion.dao.RecommendDao;
import com.jwk.project.system.fashion.domain.SysRecommendConfig;



/**
 * 设计师推荐配置业务层处理
 * 
 * @author system
 */
@Service("recommendConfigService")
public class RecommendConfigServiceImpl implements RecommendConfigService
{

    @Autowired
    private RecommendConfigDao recommendConfigDao;

    /**
     * 根据条件分页查询设计师推荐配置数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐配置数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return recommendConfigDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有设计师推荐配置
     * 
     * @return 
     */
    @Override
    public List<SysRecommendConfig> selectSysRecommendConfigAll()
    {
        return recommendConfigDao.selectSysRecommendConfigAll();
    }

    /**
     * 通过iD查询设计师推荐配置
     * 
     * @param id 设计师推荐配置ID
     * @return 设计师推荐配置对象信息
     */
    @Override
    public SysRecommendConfig selectSysRecommendConfigById(Long id)
    {
        return recommendConfigDao.selectSysRecommendConfigById(id);
    }

    
    /**
     * 通过设计师推荐配置ID删除设计师推荐配置
     * 
     * @param id 设计师推荐配置iD
     * @return 结果
     */
    @Override
    public int deleteSysRecommendConfigById(Long id)
    {
        return recommendConfigDao.deleteSysRecommendConfigById(id);
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
        return recommendConfigDao.batchDeleteSysRecommendConfig(ids);
    }

    /**
     * 保存设计师推荐配置信息
     * 
     * @param SysRecommendConfig
     * @return 结果
     */
    @Override
    public int saveSysRecommendConfig(SysRecommendConfig sysRecommendConfig)
    {
    
        if (StringUtils.isNotNull(sysRecommendConfig.getId()))
        {
           
          return  recommendConfigDao.updateSysRecommendConfig(sysRecommendConfig);
        }else{
        	return recommendConfigDao.insertSysRecommendConfig(sysRecommendConfig);
        }
        
    }

   

}
