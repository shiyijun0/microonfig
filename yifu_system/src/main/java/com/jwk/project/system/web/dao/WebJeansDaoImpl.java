package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebJeans;

/**
 * 衣服 数据层处理
 * 
 * @author system
 */
@Repository("webJeansDao")
public class WebJeansDaoImpl extends DynamicObjectBaseDao implements WebJeansDao
{

	 /**
     * 根据条件分页查询衣服数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 衣服数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebJeansMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据衣服ID查询衣服
     * 
     * @param id 衣服ID
     * @return 衣服列表
     */
    @Override
    public List<SysWebJeans> selectWebJeansAll()
    {
        List<SysWebJeans> WebJeansList = null;
        try
        {
            WebJeansList = this.findForList("SystemWebJeansMapper.selectWebJeansAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return WebJeansList;
    }

    /**
     * 通过衣服ID查询衣服
     * 
     * @param id 衣服ID
     * @return 衣服对象信息
     */
    @Override
    public SysWebJeans selectWebJeansById(Long id)
    {
        return this.findForObject("SystemWebJeansMapper.selectWebJeansById", id);
    }
    
   

    /**
     * 通过衣服ID删除衣服
     * 
     * @param id 衣服ID
     * @return 结果
     */
    @Override
    public int deleteWebJeansById(Long id)
    {
        return this.delete("SystemWebJeansMapper.deleteWebJeansById", id);
    }

    /**
     * 批量删除衣服信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebJeans(Long[] ids)
    {
        return this.delete("SystemWebJeansMapper.batchDeleteWebJeans", ids);
    }

   
    /**
     * 保存衣服信息
     * 
     * @param role 衣服信息
     * @return 结果
     */
    @Override
    public int updateWebJeans(SysWebJeans webJeans)
    {
        return this.update("SystemWebJeansMapper.updateWebJeans", webJeans);
    }

    /**
     * 新增衣服信息
     * 
     * @param role 衣服信息
     * @return 结果
     */
    @Override
    public int insertWebJeans(SysWebJeans webJeans)
    {
        return this.update("SystemWebJeansMapper.insertWebJeans", webJeans);
    }

   
}
