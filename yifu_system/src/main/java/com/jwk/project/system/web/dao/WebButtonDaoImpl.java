package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebButton;

/**
 * 纽扣 数据层处理
 * 
 * @author system
 */
@Repository("webButtonDao")
public class WebButtonDaoImpl extends DynamicObjectBaseDao implements WebButtonDao
{

	 /**
     * 根据条件分页查询纽扣数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 纽扣数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebButtonMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据纽扣ID查询纽扣
     * 
     * @param id 纽扣ID
     * @return 纽扣列表
     */
    @Override
    public List<SysWebButton> selectWebButtonAll()
    {
        List<SysWebButton> WebButtonList = null;
        try
        {
            WebButtonList = this.findForList("SystemWebButtonMapper.selectWebButtonAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return WebButtonList;
    }

    /**
     * 根据纽扣ID查询纽扣
     *
     * @param id 纽扣ID
     * @return 纽扣列表
     */
    @Override
    public List<SysWebButton> selectwebColorByButton(SysWebButton webButton)
    {
        List<SysWebButton> WebButtonList = null;
        try
        {
            WebButtonList = this.findForList("SystemWebButtonMapper.selectwebColorByButton",webButton);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return WebButtonList;
    }


    /**
     * 通过纽扣ID查询纽扣
     * 
     * @param id 纽扣ID
     * @return 纽扣对象信息
     */
    @Override
    public SysWebButton selectWebButtonById(Long id)
    {
        return this.findForObject("SystemWebButtonMapper.selectWebButtonById", id);
    }
    
   

    /**
     * 通过纽扣ID删除纽扣
     * 
     * @param id 纽扣ID
     * @return 结果
     */
    @Override
    public int deleteWebButtonById(Long id)
    {
        return this.delete("SystemWebButtonMapper.deleteWebButtonById", id);
    }

    /**
     * 批量删除纽扣信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebButton(Long[] ids)
    {
        return this.delete("SystemWebButtonMapper.batchDeleteWebButton", ids);
    }

   
    /**
     * 保存纽扣信息
     * 
     * @param role 纽扣信息
     * @return 结果
     */
    @Override
    public int updateWebButton(SysWebButton webButton)
    {
        return this.update("SystemWebButtonMapper.updateWebButton", webButton);
    }

    /**
     * 新增纽扣信息
     * 
     * @param role 纽扣信息
     * @return 结果
     */
    @Override
    public int insertWebButton(SysWebButton webButton)
    {
        return this.update("SystemWebButtonMapper.insertWebButton", webButton);
    }

   
}
