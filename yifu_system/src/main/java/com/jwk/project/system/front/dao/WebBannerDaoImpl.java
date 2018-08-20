package com.jwk.project.system.front.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.front.domain.SysWebBanner;


/**
 * 颜色 数据层处理
 * 
 * @author system
 */
@Repository("WebBannerDao")
public class WebBannerDaoImpl extends DynamicObjectBaseDao implements WebBannerDao
{

	 /**
     * 根据条件分页查询颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 颜色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebBannerMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据颜色ID查询颜色
     * 
     * @param id 颜色ID
     * @return 颜色列表
     */
    @Override
    public List<SysWebBanner> selectWebBannerAll()
    {
        List<SysWebBanner> WebBannerList = null;
        try
        {
            WebBannerList = this.findForList("SystemWebBannerMapper.selectWebBannerAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return WebBannerList;
    }

    /**
     * 通过颜色ID查询颜色
     * 
     * @param id 颜色ID
     * @return 颜色对象信息
     */
    @Override
    public SysWebBanner selectWebBannerById(Long id)
    {
        return this.findForObject("SystemWebBannerMapper.selectWebBannerById", id);
    }
    
   

    /**
     * 通过颜色ID删除颜色
     * 
     * @param id 颜色ID
     * @return 结果
     */
    @Override
    public int deleteWebBannerById(Long id)
    {
        return this.delete("SystemWebBannerMapper.deleteWebBannerById", id);
    }

    /**
     * 批量删除颜色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebBanner(Long[] ids)
    {
        return this.delete("SystemWebBannerMapper.batchDeleteWebBanner", ids);
    }

   
    /**
     * 保存颜色信息
     * 
     * @param role 颜色信息
     * @return 结果
     */
    @Override
    public int updateWebBanner(SysWebBanner WebBanner)
    {
        return this.update("SystemWebBannerMapper.updateWebBanner", WebBanner);
    }

    /**
     * 新增颜色信息
     * 
     * @param role 颜色信息
     * @return 结果
     */
    @Override
    public int insertWebBanner(SysWebBanner WebBanner)
    {
        return this.update("SystemWebBannerMapper.insertWebBanner", WebBanner);
    }

   
}
