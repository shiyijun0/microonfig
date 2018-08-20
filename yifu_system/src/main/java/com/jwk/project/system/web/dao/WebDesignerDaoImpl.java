package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebDesigner;

/**
 * 成品 数据层处理
 * 
 * @author system
 */
@Repository("webDesignerDao")
public class WebDesignerDaoImpl extends DynamicObjectBaseDao implements WebDesignerDao
{

	 /**
     * 根据条件分页查询成品数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 成品数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebDesignerMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据成品ID查询成品
     * 
     * @param id 成品ID
     * @return 成品列表
     */
    @Override
    public List<SysWebDesigner> selectWebDesignerAll()
    {
        List<SysWebDesigner> webDesignerList = null;
        try
        {
            webDesignerList = this.findForList("SystemWebDesignerMapper.selectWebDesignerAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return webDesignerList;
    }

    /**
     * 通过成品ID查询成品
     * 
     * @param id 成品ID
     * @return 成品对象信息
     */
    @Override
    public SysWebDesigner selectWebDesignerById(Long id)
    {
        return this.findForObject("SystemWebDesignerMapper.selectWebDesignerById", id);
    }
    
   

    /**
     * 通过成品ID删除成品
     * 
     * @param id 成品ID
     * @return 结果
     */
    @Override
    public int deleteWebDesignerById(Long id)
    {
        return this.delete("SystemWebDesignerMapper.deleteWebDesignerById", id);
    }

    /**
     * 批量删除成品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebDesigner(Long[] ids)
    {
        return this.delete("SystemWebDesignerMapper.batchDeleteWebDesigner", ids);
    }

   
    /**
     * 保存成品信息
     * 
     * @param role 成品信息
     * @return 结果
     */
    @Override
    public int updateWebDesigner(SysWebDesigner webDesigner)
    {
        return this.update("SystemWebDesignerMapper.updateWebDesigner", webDesigner);
    }

    /**
     * 新增成品信息
     * 
     * @param role 成品信息
     * @return 结果
     */
    @Override
    public int insertWebDesigner(SysWebDesigner webDesigner)
    {
        return this.update("SystemWebDesignerMapper.insertWebDesigner", webDesigner);
    }

   
}
