package com.jwk.project.system.fashion.service;
import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysFashion;
/**
 * 潮裤社区业务层
 * 
 * @author system
 */
public interface FashionService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 潮裤社区数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有潮裤社区
     * 
     * @return 权限列表
     */
    public List<SysFashion> selectSysFashionAll();

    /**
     * 通过ID查询潮裤社区
     * 
     * @param roleId 潮裤社区ID
     * @return 潮裤社区对象信息
     */
    public SysFashion selectSysFashionById(Long id);

    /**
     * 通过潮裤社区ID删除潮裤社区信息
     * 
     * @param id 潮裤社区ID
     * @return 结果
     */
    public int deleteSysFashionById(Long id);

    /**
     * 批量删除潮裤社区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysFashion(Long[] ids);

    /**
     * 保存潮裤社区信息
     * 
     * @param SysFashion 潮裤社区信息
     * @return 结果
     */
    public int saveSysFashion(SysFashion sysFashion);

}
