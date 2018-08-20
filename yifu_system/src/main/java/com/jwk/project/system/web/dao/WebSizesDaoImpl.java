package com.jwk.project.system.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebSizes;


/**
 * 尺寸 数据层处理
 * 
 * @author system
 */
@Repository("webSizesDao")
public class WebSizesDaoImpl extends DynamicObjectBaseDao implements WebSizesDao
{

	 /**
     * 根据条件分页查询尺寸数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 尺寸数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebSizesMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据尺寸ID查询尺寸
     * 
     * @param id 尺寸ID
     * @return 尺寸列表
     */
    @Override
    public List<SysWebSizes> selectSysWebSizesAll()
    {
        List<SysWebSizes> SysWebSizesList = null;
        try
        {
            SysWebSizesList = this.findForList("SystemWebSizesMapper.selectSysWebSizesAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysWebSizesList;
    }

    /**
     * 通过尺寸ID查询尺寸
     * 
     * @param id 尺寸ID
     * @return 尺寸对象信息
     */
    @Override
    public SysWebSizes selectSysWebSizesById(Long id)
    {
        return this.findForObject("SystemWebSizesMapper.selectSysWebSizesById", id);
    }

    /**
     * 通过尺寸ID删除尺寸
     * 
     * @param id 尺寸ID
     * @return 结果
     */
    @Override
    public int deleteSysWebSizesById(Long id)
    {
        return this.delete("SystemWebSizesMapper.deleteSysWebSizesById", id);
    }

    /**
     * 批量删除尺寸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysWebSizes(Long[] ids)
    {
        return this.delete("SystemWebSizesMapper.batchDeleteSysWebSizes", ids);
    }

   
    /**
     * 保存尺寸信息
     * 
     * @param role 尺寸信息
     * @return 结果
     */
    @Override
    public int updateSysWebSizes(SysWebSizes sysWebSizes)
    {
        return this.update("SystemWebSizesMapper.updateSysWebSizes", sysWebSizes);
    }

    /**
     * 新增尺寸信息
     * 
     * @param role 尺寸信息
     * @return 结果
     */
    @Override
    public int insertSysWebSizes(SysWebSizes sysWebSizes)
    {
        return this.update("SystemWebSizesMapper.insertSysWebSizes", sysWebSizes);
    }

   
   
}
