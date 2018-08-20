package com.jwk.project.system.index.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebIndex;

import java.util.List;


/**
 * 首页图片表 数据层
 * 
 * @author system
 */
public interface WebIndexDao
{

    /**
     * 根据条件分页查询首页图片数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 首页图片数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过首页图片ID查询首页图片信息
     * 
     * @param id 首页图片ID
     * @return 首页图片对象信息
     */
    public SysWebIndex selectWebIndexById(Long id);


    /**
     * 通过首页图片ID查询首页图片
     *
     */
    public List<SysWebIndex> selectWebIndexAll();



    /**
     * 通过首页图片ID删除首页图片
     * 
     * @param id 首页图片ID
     * @return 结果
     */
    public int deleteWebIndexById(Long id);

    /**
     * 批量删除首页图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebIndex(Long[] ids);


    /**
     * 修改首页图片信息
     * 
     * @param webIndex 首页图片信息
     * @return 结果
     */
    public int updateWebIndex(SysWebIndex webIndex);

    /**
     * 新增首页图片信息
     * 
     * @param webIndex 首页图片信息
     * @return 结果
     */
    public int insertWebIndex(SysWebIndex webIndex);
    
    

}
