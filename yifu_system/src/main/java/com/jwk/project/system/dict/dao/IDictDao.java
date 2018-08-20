package com.jwk.project.system.dict.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.dict.domain.DictData;
import com.jwk.project.system.dict.domain.DictType;

/**
 * 字典表 数据层
 * 
 * @author system
 */
public interface IDictDao
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param pageUtilEntity 分页对象
     * @return 字典类型集合信息
     */
    public TableDataInfo pageInfoQueryDictType(PageUtilEntity pageUtilEntity);

    /**
     * 根据条件分页查询字典数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 字典数据集合信息
     */
    public TableDataInfo pageInfoQueryDictData(PageUtilEntity pageUtilEntity);

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public DictType selectDictTypeById(Long dictId);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public DictData selectDictDataById(Long dictCode);

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int batchDeleteDictType(Long[] ids);

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int batchDeleteDictData(Long[] ids);

    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(DictType dictType);

    /**
     * 修改字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updateDictType(DictType dictType);

    /**
     * 新增字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(DictData dictData);

    /**
     * 修改字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(DictData dictData);
    
    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public DictType checkDictTypeUnique(String dictType);
}
