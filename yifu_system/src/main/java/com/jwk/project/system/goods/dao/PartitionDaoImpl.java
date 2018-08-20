package com.jwk.project.system.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysFq;


/**
 * 分区 数据层处理
 * 
 * @author system
 */
@Repository("partitionDao")
public class PartitionDaoImpl extends DynamicObjectBaseDao implements PartitionDao
{

	 /**
     * 根据条件分页查询分区数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 分区数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemPartitionMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据分区ID查询分区
     * 
     * @param id 分区ID
     * @return 分区列表
     */
    @Override
    public List<SysFq> selectSysFqAll()
    {
        List<SysFq> SysFqList = null;
        try
        {
            SysFqList = this.findForList("SystemPartitionMapper.selectSysFqAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysFqList;
    }

    /**
     * 通过分区ID查询分区
     * 
     * @param id 分区ID
     * @return 分区对象信息
     */
    @Override
    public SysFq selectSysFqById(Long id)
    {
        return this.findForObject("SystemPartitionMapper.selectSysFqById", id);
    }

    /**
     * 通过分区ID删除分区
     * 
     * @param id 分区ID
     * @return 结果
     */
    @Override
    public int deleteSysFqById(Long id)
    {
        return this.delete("SystemPartitionMapper.deleteSysFqById", id);
    }

    /**
     * 批量删除分区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysFq(Long[] ids)
    {
        return this.delete("SystemPartitionMapper.batchDeleteSysFq", ids);
    }

   
    /**
     * 保存分区信息
     * 
     * @param role 分区信息
     * @return 结果
     */
    @Override
    public int updateSysFq(SysFq sysFq)
    {
        return this.update("SystemPartitionMapper.updateSysFq", sysFq);
    }

    /**
     * 新增分区信息
     * 
     * @param role 分区信息
     * @return 结果
     */
    @Override
    public int insertSysFq(SysFq sysFq)
    {
        return this.update("SystemPartitionMapper.insertSysFq", sysFq);
    }

   
   
}
