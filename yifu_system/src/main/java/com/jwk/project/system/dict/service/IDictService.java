package com.jwk.project.system.dict.service;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.dict.domain.DictData;
import com.jwk.project.system.dict.domain.DictType;

/**
 * 字典 业务层
 * 
 * @author system
 */
public interface IDictService
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
     * 保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int saveDictType(DictType dictType);
    
    /**
     * 保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int saveDictData(DictData dictData);
    
    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public String checkDictTypeUnique(DictType dictType);
}
