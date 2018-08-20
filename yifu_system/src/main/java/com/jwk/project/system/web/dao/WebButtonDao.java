package com.jwk.project.system.web.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebButton;



/**
 * 纽扣表 数据层
 * 
 * @author system
 */
public interface WebButtonDao
{

    /**
     * 根据条件分页查询纽扣数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 纽扣数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询纽扣列表
     * 
     * @return 纽扣列表
     */
    public List<SysWebButton> selectWebButtonAll();

    /**
     * 通过纽扣ID查询纽扣信息
     * 
     * @param id 纽扣ID
     * @return 纽扣对象信息
     */
    public SysWebButton selectWebButtonById(Long id);
    

    /**
     * 通过纽扣ID删除纽扣
     * 
     * @param id 纽扣ID
     * @return 结果
     */
    public int deleteWebButtonById(Long id);

    /**
     * 批量删除纽扣信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebButton(Long[] ids);


    /**
     * 修改纽扣信息
     * 
     * @param WebButton 纽扣信息
     * @return 结果
     */
    public int updateWebButton(SysWebButton webButton);

    /**
     * 新增纽扣信息
     * 
     * @param WebButton 纽扣信息
     * @return 结果
     */
    public int insertWebButton(SysWebButton webButton);

    public List<SysWebButton> selectwebColorByButton(SysWebButton webButton);

}
