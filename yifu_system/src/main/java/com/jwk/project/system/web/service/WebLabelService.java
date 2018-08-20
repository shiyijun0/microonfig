package com.jwk.project.system.web.service;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebLabel;

import java.util.List;

/**
 * 标签业务层
 * 
 * @author system
 */
public interface WebLabelService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 标签数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有标签
     * 
     * @return 权限列表
     */
    public List<SysWebLabel> selectSysWebLabelAll();

    /**
     * 通过ID查询标签
     * 
     * @param roleId 标签ID
     * @return 标签对象信息
     */
    public SysWebLabel selectSysWebLabelById(Long id);

    /**
     * 通过标签ID删除标签信息
     * 
     * @param id 标签ID
     * @return 结果
     */
    public int deleteSysWebLabelById(Long id);

    /**
     * 批量删除标签信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysWebLabel(Long[] ids);

    /**
     * 保存标签信息
     * 
     * @param SysWebLabel 标签信息
     * @return 结果
     */
    public int saveSysWebLabel(SysWebLabel SysWebLabel);

}
