package com.jwk.project.system.goods.service;

import java.util.List;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysFq;
/**
 * 分区业务层
 * 
 * @author system
 */
public interface PartitionService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 分区数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有分区
     * 
     * @return 权限列表
     */
    public List<SysFq> selectSysFqAll();

    /**
     * 通过ID查询分区
     * 
     * @param roleId 分区ID
     * @return 分区对象信息
     */
    public SysFq selectSysFqById(Long id);

    /**
     * 通过分区ID删除分区信息
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
     * 保存分区信息
     * 
     * @param SysFq 分区信息
     * @return 结果
     */
    public int saveSysFq(SysFq sysFq);

}
