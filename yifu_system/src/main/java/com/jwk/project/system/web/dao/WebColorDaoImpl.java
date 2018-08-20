package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebColor;

/**
 * 颜色 数据层处理
 * 
 * @author system
 */
@Repository("webColorDao")
public class WebColorDaoImpl extends DynamicObjectBaseDao implements WebColorDao
{

	 /**
     * 根据条件分页查询颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 颜色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebColorMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据颜色ID查询颜色
     * 
     * @param id 颜色ID
     * @return 颜色列表
     */
    @Override
    public List<SysWebColor> selectwebColorAll()
    {
        List<SysWebColor> webColorList = null;
        try
        {
            webColorList = this.findForList("SystemWebColorMapper.selectWebColorAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return webColorList;
    }


    /**
     * 根据颜色ID查询颜色
     *
     * @param id 颜色ID
     * @return 颜色列表
     */
    @Override
    public List<SysWebColor> selectwebColorByColor(SysWebColor webColor)
    {
        List<SysWebColor> webColorList = null;
        try
        {
            webColorList = this.findForList("SystemWebColorMapper.selectwebColorByColor",webColor);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return webColorList;
    }

    /**
     * 通过颜色ID查询颜色
     * 
     * @param id 颜色ID
     * @return 颜色对象信息
     */
    @Override
    public SysWebColor selectwebColorById(Long id)
    {
        return this.findForObject("SystemWebColorMapper.selectWebColorById", id);
    }
    
   

    /**
     * 通过颜色ID删除颜色
     * 
     * @param id 颜色ID
     * @return 结果
     */
    @Override
    public int deletewebColorById(Long id)
    {
        return this.delete("SystemWebColorMapper.deleteWebColorById", id);
    }

    /**
     * 批量删除颜色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeletewebColor(Long[] ids)
    {
        return this.delete("SystemWebColorMapper.batchDeleteWebColor", ids);
    }

   
    /**
     * 保存颜色信息
     * 
     * @param role 颜色信息
     * @return 结果
     */
    @Override
    public int updatewebColor(SysWebColor webColor)
    {
        return this.update("SystemWebColorMapper.updateWebColor", webColor);
    }

    /**
     * 新增颜色信息
     * 
     * @param role 颜色信息
     * @return 结果
     */
    @Override
    public int insertwebColor(SysWebColor webColor)
    {
        return this.update("SystemWebColorMapper.insertWebColor", webColor);
    }

   
}
