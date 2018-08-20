package com.jwk.project.system.web.dao;

import java.util.List;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebJeans;



/**
 * 衣服表 数据层
 * 
 * @author system
 */
public interface WebJeansDao
{

    /**
     * 根据条件分页查询衣服数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 衣服数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询衣服列表
     * 
     * @return 衣服列表
     */
    public List<SysWebJeans> selectWebJeansAll();

    /**
     * 通过衣服ID查询衣服信息
     * 
     * @param id 衣服ID
     * @return 衣服对象信息
     */
    public SysWebJeans selectWebJeansById(Long id);
    

    /**
     * 通过衣服ID删除衣服
     * 
     * @param id 衣服ID
     * @return 结果
     */
    public int deleteWebJeansById(Long id);

    /**
     * 批量删除衣服信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebJeans(Long[] ids);


    /**
     * 修改衣服信息
     * 
     * @param WebJeans 衣服信息
     * @return 结果
     */
    public int updateWebJeans(SysWebJeans webJeans);

    /**
     * 新增衣服信息
     * 
     * @param WebJeans 衣服信息
     * @return 结果
     */
    public int insertWebJeans(SysWebJeans webJeans);
    
    

}
