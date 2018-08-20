package com.jwk.project.system.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.dao.PartitionDao;
import com.jwk.project.system.goods.domain.SysFq;


/**
 * 分区业务层处理
 * 
 * @author system
 */
@Service("partitionService")
public class PartitionServiceImpl implements PartitionService
{

    @Autowired
    private PartitionDao partitionDao;

    /**
     * 根据条件分页查询分区数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 分区数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return partitionDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有分区
     * 
     * @return 
     */
    @Override
    public List<SysFq> selectSysFqAll()
    {
        return partitionDao.selectSysFqAll();
    }

    /**
     * 通过iD查询分区
     * 
     * @param id 分区ID
     * @return 分区对象信息
     */
    @Override
    public SysFq selectSysFqById(Long id)
    {
        return partitionDao.selectSysFqById(id);
    }

    /**
     * 通过分区ID删除分区
     * 
     * @param id 分区iD
     * @return 结果
     */
    @Override
    public int deleteSysFqById(Long id)
    {
        return partitionDao.deleteSysFqById(id);
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
        return partitionDao.batchDeleteSysFq(ids);
    }

    /**
     * 保存分区信息
     * 
     * @param SysFq
     * @return 结果
     */
    @Override
    public int saveSysFq(SysFq sysFq)
    {
    	 int id = sysFq.getId();
        if (StringUtils.isNotNull(id) && id>0)
        {
           
          return  partitionDao.updateSysFq(sysFq);
        }else{
        	return  partitionDao.insertSysFq(sysFq);
        }
        
    }

   

}
