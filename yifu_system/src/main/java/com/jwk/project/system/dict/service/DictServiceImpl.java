package com.jwk.project.system.dict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.constant.UserConstants;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.security.ShiroUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.dict.dao.IDictDao;
import com.jwk.project.system.dict.domain.DictData;
import com.jwk.project.system.dict.domain.DictType;

/**
 * 字典 业务层处理
 * 
 * @author system
 */
@Service("dictService")
public class DictServiceImpl implements IDictService
{
    @Autowired
    private IDictDao dictDao;

    /**
     * 根据条件分页查询字典类型
     * 
     * @param pageUtilEntity 分页对象
     * @return 字典类型集合信息
     */
    @Override
    public TableDataInfo pageInfoQueryDictType(PageUtilEntity pageUtilEntity)
    {
        return dictDao.pageInfoQueryDictType(pageUtilEntity);
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
        return dictDao.pageInfoQueryDictData(pageUtilEntity);
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
        return dictDao.selectDictTypeById(dictId);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public DictData selectDictDataById(Long dictCode)
    {
        return dictDao.selectDictDataById(dictCode);
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
        return dictDao.batchDeleteDictType(ids);
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
        return dictDao.batchDeleteDictData(ids);
    }

    /**
     * 保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int saveDictType(DictType dictType)
    {
        Long dictId = dictType.getDictId();
        if (StringUtils.isNotNull(dictId))
        {
            dictType.setUpdateBy(ShiroUtils.getLoginName());
            return dictDao.updateDictType(dictType);
        }
        else
        {
            dictType.setCreateBy(ShiroUtils.getLoginName());
            return dictDao.insertDictType(dictType);
        }
    }

    /**
     * 保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int saveDictData(DictData dictData)
    {
        Long dictCode = dictData.getDictCode();
        if (StringUtils.isNotNull(dictCode))
        {
            dictData.setUpdateBy(ShiroUtils.getLoginName());
            return dictDao.updateDictData(dictData);
        }
        else
        {
            dictData.setCreateBy(ShiroUtils.getLoginName());
            return dictDao.insertDictData(dictData);
        }
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(DictType dict)
    {
        if (dict.getDictId() == null)
        {
            dict.setDictId(-1L);
        }
        Long dictId = dict.getDictId();
        DictType dictType = dictDao.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId() != dictId)
        {
            return UserConstants.NAME_NOT_UNIQUE;
        }
        return UserConstants.NAME_UNIQUE;
    }
}
