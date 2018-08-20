package com.jwk.project.system.front.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.front.domain.SysWebBanner;


/**
 * 业务层
 * 
 * @author system
 */
public interface WebBannerService
{

    /**
     * 根据条件分页查询数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询所有
     * 
     * @return 权限列表
     */
    public List<SysWebBanner> selectWebBannerAll();

    /**
     * 通过ID查询
     * 
     * @param roleId ID
     * @return 对象信息
     */
    public SysWebBanner selectWebBannerById(Long id);

    /**
     * 通过ID删除信息
     * 
     * @param id ID
     * @return 结果
     */
    public int deleteWebBannerById(Long id);

    /**
     * 批量删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebBanner(Long[] ids);

    /**
     * 保存信息
     * 
     * @param WebBanner 信息
     * @return 结果
     */
    public int saveWebBanner(SysWebBanner WebBanner);

}
