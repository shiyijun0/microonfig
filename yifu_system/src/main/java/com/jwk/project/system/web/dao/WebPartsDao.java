package com.jwk.project.system.web.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebParts;



/**
 * 零件图片表 数据层
 * 
 * @author system
 */
public interface WebPartsDao
{

    /**
     * 根据条件分页查询零件图片数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 零件图片数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询零件图片列表
     * 
     * @return 零件图片列表
     */
    public List<SysWebParts> selectWebPartsAll(SysWebParts webParts);

    /**
     * 通过零件图片ID查询零件图片信息
     * 
     * @param id 零件图片ID
     * @return 零件图片对象信息
     */
    public SysWebParts selectWebPartsById(Long id);
    

    /**
     * 通过零件图片ID删除零件图片
     * 
     * @param id 零件图片ID
     * @return 结果
     */
    public int deleteWebPartsById(Long id);

    /**
     * 批量删除零件图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebParts(Long[] ids);


    /**
     * 修改零件图片信息
     * 
     * @param WebParts 零件图片信息
     * @return 结果
     */
    public int updateWebParts(SysWebParts WebParts);

    /**
     * 新增零件图片信息
     * 
     * @param WebParts 零件图片信息
     * @return 结果
     */
    public int insertWebParts(SysWebParts WebParts);
    
    

}
