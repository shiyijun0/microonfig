package com.jwk.project.system.goods.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysFq;



/**
 * 分区表 数据层
 * 
 * @author system
 */
public interface PartitionDao
{

    /**
     * 根据条件分页查询分区数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 分区数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询分区列表
     * 
     * @return 分区列表
     */
    public List<SysFq> selectSysFqAll();

    /**
     * 通过分区ID查询分区信息
     * 
     * @param id 分区ID
     * @return 分区对象信息
     */
    public SysFq selectSysFqById(Long id);

    /**
     * 通过分区ID删除分区
     * 
     * @param id 分区ID
     * @return 结果
     */
    public int deleteSysFqById(Long id);

    /**
     * 批量删除分区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysFq(Long[] ids);

   

    /**
     * 修改分区信息
     * 
     * @param SysFq 分区信息
     * @return 结果
     */
    public int updateSysFq(SysFq sysFq);

    /**
     * 新增分区信息
     * 
     * @param SysFq 分区信息
     * @return 结果
     */
    public int insertSysFq(SysFq sysFq);
    
   

}
