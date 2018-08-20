package com.jwk.project.system.web.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebLabel;

import java.util.List;


/**
 * 标签表 数据层
 * 
 * @author system
 */
public interface WebLabelDao
{

    /**
     * 根据条件分页查询标签数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 标签数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询标签列表
     * 
     * @return 标签列表
     */
    public List<SysWebLabel> selectSysWebLabelAll();

    /**
     * 通过标签ID查询标签信息
     * 
     * @param id 标签ID
     * @return 标签对象信息
     */
    public SysWebLabel selectSysWebLabelById(Long id);

    /**
     * 通过标签ID删除标签
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
     * 修改标签信息
     * 
     * @param sysWebLabel 标签信息
     * @return 结果
     */
    public int updateSysWebLabel(SysWebLabel sysWebLabel);

    /**
     * 新增标签信息
     * 
     * @param sysWebLabel 标签信息
     * @return 结果
     */
    public int insertSysWebLabel(SysWebLabel sysWebLabel);
    
   

}
