package com.jwk.project.system.web.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebSizes;

/**
 * 尺寸业务层
 * 
 * @author system
 */
public interface WebSizesService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 尺寸数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有尺寸
     * 
     * @return 权限列表
     */
    public List<SysWebSizes> selectSysWebSizesAll();

    /**
     * 通过ID查询尺寸
     * 
     * @param roleId 尺寸ID
     * @return 尺寸对象信息
     */
    public SysWebSizes selectSysWebSizesById(Long id);

    /**
     * 通过尺寸ID删除尺寸信息
     * 
     * @param id 尺寸ID
     * @return 结果
     */
    public int deleteSysWebSizesById(Long id);

    /**
     * 批量删除尺寸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysWebSizes(Long[] ids);

    /**
     * 保存尺寸信息
     * 
     * @param SysWebSizes 尺寸信息
     * @return 结果
     */
    public int saveSysWebSizes(SysWebSizes sysWebSizes);

}
