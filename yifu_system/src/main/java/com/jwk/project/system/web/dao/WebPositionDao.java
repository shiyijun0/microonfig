package com.jwk.project.system.web.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebPosition;




/**
 * 衣服位置表 数据层
 * 
 * @author system
 */
public interface WebPositionDao
{

    /**
     * 根据条件分页查询衣服位置数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 衣服位置数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询衣服位置列表
     * 
     * @return 衣服位置列表
     */
    public List<SysWebPosition> selectSysWebPositionAll(Long type);

    /**
     * 通过衣服位置ID查询衣服位置信息
     * 
     * @param id 衣服位置ID
     * @return 衣服位置对象信息
     */
    public SysWebPosition selectSysWebPositionById(Long id);

    /**
     * 通过衣服位置ID删除衣服位置
     * 
     * @param id 衣服位置ID
     * @return 结果
     */
    public int deleteSysWebPositionById(Long id);

    /**
     * 批量删除衣服位置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysWebPosition(Long[] ids);

   

    /**
     * 修改衣服位置信息
     * 
     * @param SysWebPosition 衣服位置信息
     * @return 结果
     */
    public int updateSysWebPosition(SysWebPosition webPosition);

    /**
     * 新增衣服位置信息
     * 
     * @param SysWebPosition 衣服位置信息
     * @return 结果
     */
    public int insertSysWebPosition(SysWebPosition webPosition);
    
    public SysWebPosition selectSysWebPositionByPosition(SysWebPosition position);
}
