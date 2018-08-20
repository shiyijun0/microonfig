package com.jwk.project.system.goods.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysBl;



/**
 * 布料表 数据层
 * 
 * @author system
 */
public interface ClothDao
{

    /**
     * 根据条件分页查询布料数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 布料数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询布料列表
     * 
     * @return 布料列表
     */
    public List<SysBl> selectSysBlAll();

    /**
     * 通过布料ID查询布料信息
     * 
     * @param id 布料ID
     * @return 布料对象信息
     */
    public SysBl selectSysBlById(Long id);

    /**
     * 通过布料ID删除布料
     * 
     * @param id 布料ID
     * @return 结果
     */
    public int deleteSysBlById(Long id);

    /**
     * 批量删除布料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysBl(Long[] ids);

   

    /**
     * 修改布料信息
     * 
     * @param SysBl 布料信息
     * @return 结果
     */
    public int updateSysBl(SysBl sysBl);

    /**
     * 新增布料信息
     * 
     * @param SysBl 布料信息
     * @return 结果
     */
    public int insertSysBl(SysBl sysBl);
    
   

}
