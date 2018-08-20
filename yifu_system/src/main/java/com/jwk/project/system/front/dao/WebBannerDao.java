package com.jwk.project.system.front.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.front.domain.SysWebBanner;




/**
 * 颜色表 数据层
 * 
 * @author system
 */
public interface WebBannerDao
{

    /**
     * 根据条件分页查询颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 颜色数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询颜色列表
     * 
     * @return 颜色列表
     */
    public List<SysWebBanner> selectWebBannerAll();

    /**
     * 通过颜色ID查询颜色信息
     * 
     * @param id 颜色ID
     * @return 颜色对象信息
     */
    public SysWebBanner selectWebBannerById(Long id);
    

    /**
     * 通过颜色ID删除颜色
     * 
     * @param id 颜色ID
     * @return 结果
     */
    public int deleteWebBannerById(Long id);

    /**
     * 批量删除颜色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebBanner(Long[] ids);


    /**
     * 修改颜色信息
     * 
     * @param WebBanner 颜色信息
     * @return 结果
     */
    public int updateWebBanner(SysWebBanner WebBanner);

    /**
     * 新增颜色信息
     * 
     * @param WebBanner 颜色信息
     * @return 结果
     */
    public int insertWebBanner(SysWebBanner WebBanner);
    
    

}
