package com.jwk.project.system.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.front.dao.WebBannerDao;
import com.jwk.project.system.front.domain.SysWebBanner;




/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webBannerService")
public class WebBannerServiceImpl implements WebBannerService
{

    @Autowired
    private WebBannerDao webBannerDao;

    /**
     * 根据条件分页查询颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 颜色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webBannerDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有颜色
     * 
     * @return 
     */
    @Override
    public List<SysWebBanner> selectWebBannerAll()
    {
        return webBannerDao.selectWebBannerAll();
    }

    /**
     * 通过iD查询颜色
     * 
     * @param id 颜色ID
     * @return 颜色对象信息
     */
    @Override
    public SysWebBanner selectWebBannerById(Long id)
    {
        return webBannerDao.selectWebBannerById(id);
    }

    /**
     * 通过颜色ID删除颜色
     * 
     * @param id 颜色iD
     * @return 结果
     */
    @Override
    public int deleteWebBannerById(Long id)
    {
        return webBannerDao.deleteWebBannerById(id);
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
        return webBannerDao.batchDeleteWebBanner(ids);
    }

    /**
     * 保存颜色信息
     * 
     * @param WebBanner
     * @return 结果
     */
    @Override
    public int saveWebBanner(SysWebBanner WebBanner)
    {
    	Integer id = WebBanner.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webBannerDao.updateWebBanner(WebBanner);
        }else{
        	return webBannerDao.insertWebBanner(WebBanner);
        }
        
    }

   

}
