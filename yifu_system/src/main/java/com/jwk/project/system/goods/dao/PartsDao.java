package com.jwk.project.system.goods.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysPj;



/**
 * 配件表 数据层
 * 
 * @author system
 */
public interface PartsDao
{

    /**
     * 根据条件分页查询配件数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 配件数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询配件列表
     * 
     * @return 配件列表
     */
    public List<SysPj> selectSysPjAll();

    /**
     * 通过配件ID查询配件信息
     * 
     * @param id 配件ID
     * @return 配件对象信息
     */
    public SysPj selectSysPjById(Long id);

    /**
     * 通过配件ID删除配件
     * 
     * @param id 配件ID
     * @return 结果
     */
    public int deleteSysPjById(Long id);

    /**
     * 批量删除配件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysPj(Long[] ids);

   

    /**
     * 修改配件信息
     * 
     * @param SysPj 配件信息
     * @return 结果
     */
    public int updateSysPj(SysPj SysPj);

    /**
     * 新增配件信息
     * 
     * @param SysPj 配件信息
     * @return 结果
     */
    public int insertSysPj(SysPj sysPj);
    
   

}
