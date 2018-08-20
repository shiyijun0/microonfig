package com.jwk.project.system.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebPosition;



/**
 * 衣服位置 数据层处理
 * 
 * @author system
 */
@Repository("webPositionDao")
public class WebPositionDaoImpl extends DynamicObjectBaseDao implements WebPositionDao
{

	 /**
     * 根据条件分页查询衣服位置数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 衣服位置数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebPositionMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据衣服位置ID查询衣服位置
     * 
     * @param id 衣服位置ID
     * @return 衣服位置列表
     */
    @Override
    public List<SysWebPosition> selectSysWebPositionAll(Long type)
    {
        List<SysWebPosition> SysWebPositionList = null;
        try
        {
            SysWebPositionList = this.findForList("SystemWebPositionMapper.selectWebPositionAll",type);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysWebPositionList;
    }

    /**
     * 通过衣服位置ID查询衣服位置
     * 
     * @param id 衣服位置ID
     * @return 衣服位置对象信息
     */
    @Override
    public SysWebPosition selectSysWebPositionById(Long id)
    {
        return this.findForObject("SystemWebPositionMapper.selectWebPositionById", id);
    }
    
    
    /**
     * 通过衣服位置对象查询衣服位置
     * 
     * @param id 衣服位置对象
     * @return 衣服位置对象信息
     */
    @Override
    public SysWebPosition selectSysWebPositionByPosition(SysWebPosition position)
    {
        return this.findForObject("SystemWebPositionMapper.selectWebPositionByPosition", position);
    }

    /**
     * 通过衣服位置ID删除衣服位置
     * 
     * @param id 衣服位置ID
     * @return 结果
     */
    @Override
    public int deleteSysWebPositionById(Long id)
    {
        return this.delete("SystemWebPositionMapper.deleteWebPositionById", id);
    }

    /**
     * 批量删除衣服位置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysWebPosition(Long[] ids)
    {
        return this.delete("SystemWebPositionMapper.batchDeleteWebPosition", ids);
    }

   
    /**
     * 保存衣服位置信息
     * 
     * @param role 衣服位置信息
     * @return 结果
     */
    @Override
    public int updateSysWebPosition(SysWebPosition webPosition)
    {
        return this.update("SystemWebPositionMapper.updateWebPosition", webPosition);
    }

    /**
     * 新增衣服位置信息
     * 
     * @param  衣服位置信息
     * @return 结果
     */
    @Override
    public int insertSysWebPosition(SysWebPosition webPosition)
    {
        return this.update("SystemWebPositionMapper.insertWebPosition", webPosition);
    }

   
   
}
