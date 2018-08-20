package com.jwk.project.system.web.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebThread;



/**
 * 边线表 数据层
 * 
 * @author system
 */
public interface WebThreadDao
{

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询边线列表
     * 
     * @return 边线列表
     */
    public List<SysWebThread> selectwebThreadAll();

    /**
     * 通过边线ID查询边线信息
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    public SysWebThread selectwebThreadById(Long id);
    

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线ID
     * @return 结果
     */
    public int deletewebThreadById(Long id);

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeletewebThread(Long[] ids);


    /**
     * 修改边线信息
     * 
     * @param webThread 边线信息
     * @return 结果
     */
    public int updatewebThread(SysWebThread webThread);

    /**
     * 新增边线信息
     * 
     * @param webThread 边线信息
     * @return 结果
     */
    public int insertwebThread(SysWebThread webThread);

    public List<SysWebThread> selectwebThreadByThread(SysWebThread webThread);

}
