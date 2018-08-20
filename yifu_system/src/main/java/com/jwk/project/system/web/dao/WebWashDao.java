package com.jwk.project.system.web.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebWash;



/**
 * 破洞表 数据层
 * 
 * @author system
 */
public interface WebWashDao
{

    /**
     * 根据条件分页查询破洞数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 破洞数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询破洞列表
     * 
     * @return 破洞列表
     */
    public List<SysWebWash> selectWebWashAll();

    /**
     * 通过破洞ID查询破洞信息
     * 
     * @param id 破洞ID
     * @return 破洞对象信息
     */
    public SysWebWash selectWebWashById(Long id);
    

    /**
     * 通过破洞ID删除破洞
     * 
     * @param id 破洞ID
     * @return 结果
     */
    public int deleteWebWashById(Long id);

    /**
     * 批量删除破洞信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebWash(Long[] ids);


    /**
     * 修改破洞信息
     * 
     * @param WebWash 破洞信息
     * @return 结果
     */
    public int updateWebWash(SysWebWash webWash);

    /**
     * 新增破洞信息
     * 
     * @param WebWash 破洞信息
     * @return 结果
     */
    public int insertWebWash(SysWebWash webWash);
    
    

}
