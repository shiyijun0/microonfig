package com.jwk.project.system.dict.dao;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.dict.domain.DictData;
import com.jwk.project.system.dict.domain.DictType;

/**
 * 字典数据层处理
 * 
 * @author system
 */
@Repository("dictDao")
public class DictDaoImpl extends DynamicObjectBaseDao implements IDictDao
{

    /**
     * 根据条件分页查询字典类型
     * 
     * @param pageUtilEntity 分页对象
     * @return 字典类型集合信息
     */
    @Override
    public TableDataInfo pageInfoQueryDictType(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemDictMapper.pageInfoQueryDictType", pageUtilEntity);
    }

    /**
     * 根据条件分页查询字典数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 字典数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQueryDictData(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemDictMapper.pageInfoQueryDictData", pageUtilEntity);
    }

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public DictType selectDictTypeById(Long dictId)
    {
        return this.findForObject("SystemDictMapper.selectDictTypeById", dictId);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public DictData selectDictDataById(Long dictId)
    {
        return this.findForObject("SystemDictMapper.selectDictDataById", dictId);
    }

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int batchDeleteDictType(Long[] ids)
    {
        return this.batchDelete("SystemDictMapper.batchDeleteDictType", ids);
    }

    /**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int batchDeleteDictData(Long[] ids)
    {
        return this.batchDelete("SystemDictMapper.batchDeleteDictData", ids);
    }

    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(DictType dictType)
    {
        return this.save("SystemDictMapper.insertDictType", dictType);
    }

    /**
     * 修改字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(DictType dictType)
    {
        return this.update("SystemDictMapper.updateDictType", dictType);
    }

    /**
     * 新增字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(DictData dictData)
    {
        return this.save("SystemDictMapper.insertDictData", dictData);
    }

    /**
     * 修改字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(DictData dictData)
    {
        return this.update("SystemDictMapper.updateDictData", dictData);
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public DictType checkDictTypeUnique(String dictType)
    {
        return this.findForObject("SystemDictMapper.checkDictTypeUnique", dictType);
    }

}
