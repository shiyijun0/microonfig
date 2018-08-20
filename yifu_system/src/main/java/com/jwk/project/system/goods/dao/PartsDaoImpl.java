package com.jwk.project.system.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysPj;



/**
 * 配件 数据层处理
 * 
 * @author system
 */
@Repository("partsDao")
public class PartsDaoImpl extends DynamicObjectBaseDao implements PartsDao
{

	 /**
     * 根据条件分页查询配件数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 配件数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemPartsMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据配件ID查询配件
     * 
     * @param id 配件ID
     * @return 配件列表
     */
    @Override
    public List<SysPj> selectSysPjAll()
    {
        List<SysPj> sysPjList = null;
        try
        {
            sysPjList = this.findForList("SystemPartsMapper.selectSysPjAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sysPjList;
    }

    /**
     * 通过配件ID查询配件
     * 
     * @param id 配件ID
     * @return 配件对象信息
     */
    @Override
    public SysPj selectSysPjById(Long id)
    {
        return this.findForObject("SystemPartsMapper.selectSysPjById", id);
    }

    /**
     * 通过配件ID删除配件
     * 
     * @param id 配件ID
     * @return 结果
     */
    @Override
    public int deleteSysPjById(Long id)
    {
        return this.delete("SystemPartsMapper.deleteSysPjById", id);
    }

    /**
     * 批量删除配件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysPj(Long[] ids)
    {
        return this.delete("SystemPartsMapper.batchDeleteSysPj", ids);
    }

   
    /**
     * 保存配件信息
     * 
     * @param  配件信息
     * @return 结果
     */
    @Override
    public int updateSysPj(SysPj sysPj)
    {
        return this.update("SystemPartsMapper.updateSysPj", sysPj);
    }

    /**
     * 新增配件信息
     * 
     * @param  配件信息
     * @return 结果
     */
    @Override
    public int insertSysPj(SysPj sysPj)
    {
        return this.update("SystemPartsMapper.insertSysPj", sysPj);
    }

   
   
}
