package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebParts;

/**
 * 零件图片 数据层处理
 * 
 * @author system
 */
@Repository("webPartsDao")
public class WebPartsDaoImpl extends DynamicObjectBaseDao implements WebPartsDao
{

	 /**
     * 根据条件分页查询零件图片数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 零件图片数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebPartsMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据零件图片ID查询零件图片
     * 
     * @param id 零件图片ID
     * @return 零件图片列表
     */
    @Override
    public List<SysWebParts> selectWebPartsAll(SysWebParts webParts)
    {
        List<SysWebParts> WebPartsList = null;
        try
        {
            WebPartsList = this.findForList("SystemWebPartsMapper.selectWebPartsAll",webParts);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return WebPartsList;
    }

    /**
     * 通过零件图片ID查询零件图片
     * 
     * @param id 零件图片ID
     * @return 零件图片对象信息
     */
    @Override
    public SysWebParts selectWebPartsById(Long id)
    {
        return this.findForObject("SystemWebPartsMapper.selectWebPartsById", id);
    }
    
   

    /**
     * 通过零件图片ID删除零件图片
     * 
     * @param id 零件图片ID
     * @return 结果
     */
    @Override
    public int deleteWebPartsById(Long id)
    {
        return this.delete("SystemWebPartsMapper.deleteWebPartsById", id);
    }

    /**
     * 批量删除零件图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebParts(Long[] ids)
    {
        return this.delete("SystemWebPartsMapper.batchDeleteWebParts", ids);
    }

   
    /**
     * 保存零件图片信息
     * 
     * @param role 零件图片信息
     * @return 结果
     */
    @Override
    public int updateWebParts(SysWebParts WebParts)
    {
        return this.update("SystemWebPartsMapper.updateWebParts", WebParts);
    }

    /**
     * 新增零件图片信息
     * 
     * @param role 零件图片信息
     * @return 结果
     */
    @Override
    public int insertWebParts(SysWebParts WebParts)
    {
        return this.update("SystemWebPartsMapper.insertWebParts", WebParts);
    }

   
}
