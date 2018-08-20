package com.jwk.project.system.web.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebLabel;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 标签 数据层处理
 * 
 * @author system
 */
@Repository("webLabelDao")
public class WebLabelDaoImpl extends DynamicObjectBaseDao implements WebLabelDao
{

	 /**
     * 根据条件分页查询标签数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 标签数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebLabelMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据标签ID查询标签
     * 
     * @param  标签
     * @return 标签列表
     */
    @Override
    public List<SysWebLabel> selectSysWebLabelAll()
    {
        List<SysWebLabel> SysWebLabelList = null;
        try
        {
            SysWebLabelList = this.findForList("SystemWebLabelMapper.selectSysWebLabelAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysWebLabelList;
    }

    /**
     * 通过标签ID查询标签
     * 
     * @param id 标签ID
     * @return 标签对象信息
     */
    @Override
    public SysWebLabel selectSysWebLabelById(Long id)
    {
        return this.findForObject("SystemWebLabelMapper.selectSysWebLabelById", id);
    }

    /**
     * 通过标签ID删除标签
     * 
     * @param id 标签ID
     * @return 结果
     */
    @Override
    public int deleteSysWebLabelById(Long id)
    {
        return this.delete("SystemWebLabelMapper.deleteSysWebLabelById", id);
    }

    /**
     * 批量删除标签信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysWebLabel(Long[] ids)
    {
        return this.delete("SystemWebLabelMapper.batchDeleteSysWebLabel", ids);
    }

   
    /**
     * 保存标签信息
     * 
     * @param 标签信息
     * @return 结果
     */
    @Override
    public int updateSysWebLabel(SysWebLabel sysWebLabel)
    {
        return this.update("SystemWebLabelMapper.updateSysWebLabel", sysWebLabel);
    }

    /**
     * 新增标签信息
     * 
     * @param role 标签信息
     * @return 结果
     */
    @Override
    public int insertSysWebLabel(SysWebLabel sysWebLabel)
    {
        return this.update("SystemWebLabelMapper.insertSysWebLabel", sysWebLabel);
    }

   
   
}
