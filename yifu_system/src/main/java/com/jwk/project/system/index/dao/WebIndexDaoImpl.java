package com.jwk.project.system.index.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebIndex;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * 首页图片 数据层处理
 * 
 * @author system
 */
@Repository("WebIndexDao")
public class WebIndexDaoImpl extends DynamicObjectBaseDao implements WebIndexDao
{

	 /**
     * 根据条件分页查询首页图片数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 首页图片数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebIndexMapper.pageInfoQuery", pageUtilEntity);
    }


    /**
     * 通过首页图片ID查询首页图片
     * 
     * @param id 首页图片ID
     * @return 首页图片对象信息
     */
    @Override
    public SysWebIndex selectWebIndexById(Long id)
    {
        return this.findForObject("SystemWebIndexMapper.selectWebIndexById", id);
    }


    /**
     * 通过首页图片ID查询首页图片
     *
     */
    @Override
    public List<SysWebIndex> selectWebIndexAll()
    {
        List<SysWebIndex> webIndexList = null;
        try
        {
            webIndexList = this.findForList("SystemWebIndexMapper.selectWebIndexAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return webIndexList;
    }

   

    /**
     * 通过首页图片ID删除首页图片
     * 
     * @param id 首页图片ID
     * @return 结果
     */
    @Override
    public int deleteWebIndexById(Long id)
    {
        return this.delete("SystemWebIndexMapper.deleteWebIndexById", id);
    }

    /**
     * 批量删除首页图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebIndex(Long[] ids)
    {
        return this.delete("SystemWebIndexMapper.batchDeleteWebIndex", ids);
    }

   
    /**
     * 保存首页图片信息
     * 
     * @param webIndex 首页图片信息
     * @return 结果
     */
    @Override
    public int updateWebIndex(SysWebIndex webIndex)
    {
        return this.update("SystemWebIndexMapper.updateWebIndex", webIndex);
    }

    /**
     * 新增首页图片信息
     * 
     * @param webIndex 首页图片信息
     * @return 结果
     */
    @Override
    public int insertWebIndex(SysWebIndex webIndex)
    {
        return this.update("SystemWebIndexMapper.insertWebIndex", webIndex);
    }

   
}
