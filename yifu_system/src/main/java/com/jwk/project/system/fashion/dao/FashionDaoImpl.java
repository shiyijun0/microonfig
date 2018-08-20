package com.jwk.project.system.fashion.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysFashion;


/**
 * 潮裤社区 数据层处理
 * 
 * @author system
 */
@Repository("fashionDao")
public class FashionDaoImpl extends DynamicObjectBaseDao implements FashionDao
{

	 /**
     * 根据条件分页查询潮裤社区数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 潮裤社区数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemFashionMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据潮裤社区ID查询潮裤社区
     * 
     * @param id 潮裤社区ID
     * @return 潮裤社区列表
     */
    @Override
    public List<SysFashion> selectSysFashionAll()
    {
        List<SysFashion> SysFashionList = null;
        try
        {
            SysFashionList = this.findForList("SystemFashionMapper.selectSysFashionAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysFashionList;
    }

    /**
     * 通过潮裤社区ID查询潮裤社区
     * 
     * @param id 潮裤社区ID
     * @return 潮裤社区对象信息
     */
    @Override
    public SysFashion selectSysFashionById(Long id)
    {
        return this.findForObject("SystemFashionMapper.selectSysFashionById", id);
    }

    /**
     * 通过潮裤社区ID删除潮裤社区
     * 
     * @param id 潮裤社区ID
     * @return 结果
     */
    @Override
    public int deleteSysFashionById(Long id)
    {
        return this.delete("SystemFashionMapper.deleteSysFashionById", id);
    }

    /**
     * 批量删除潮裤社区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysFashion(Long[] ids)
    {
        return this.delete("SystemFashionMapper.batchDeleteSysFashion", ids);
    }

   
    /**
     * 保存潮裤社区信息
     * 
     * @param  潮裤社区信息
     * @return 结果
     */
    @Override
    public int updateSysFashion(SysFashion sysFashion)
    {
        return this.update("SystemFashionMapper.updateSysFashion", sysFashion);
    }

    /**
     * 新增潮裤社区信息
     * 
     * @param  潮裤社区信息
     * @return 结果
     */
    @Override
    public int insertSysFashion(SysFashion sysFashion)
    {
        return this.update("SystemFashionMapper.insertSysFashion", sysFashion);
    }

   
   
}
