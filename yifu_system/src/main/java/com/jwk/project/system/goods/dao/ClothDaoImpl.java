package com.jwk.project.system.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysBl;
;


/**
 * 布料 数据层处理
 * 
 * @author system
 */
@Repository("clothDao")
public class ClothDaoImpl extends DynamicObjectBaseDao implements ClothDao
{

	 /**
     * 根据条件分页查询布料数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 布料数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemClothMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据布料ID查询布料
     * 
     * @param id 布料ID
     * @return 布料列表
     */
    @Override
    public List<SysBl> selectSysBlAll()
    {
        List<SysBl> SysBlList = null;
        try
        {
            SysBlList = this.findForList("SystemClothMapper.selectSysBlAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysBlList;
    }

    /**
     * 通过布料ID查询布料
     * 
     * @param id 布料ID
     * @return 布料对象信息
     */
    @Override
    public SysBl selectSysBlById(Long id)
    {
        return this.findForObject("SystemClothMapper.selectSysBlById", id);
    }

    /**
     * 通过布料ID删除布料
     * 
     * @param id 布料ID
     * @return 结果
     */
    @Override
    public int deleteSysBlById(Long id)
    {
        return this.delete("SystemClothMapper.deleteSysBlById", id);
    }

    /**
     * 批量删除布料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysBl(Long[] ids)
    {
        return this.delete("SystemClothMapper.batchDeleteSysBl", ids);
    }

   
    /**
     * 保存布料信息
     * 
     * @param  布料信息
     * @return 结果
     */
    @Override
    public int updateSysBl(SysBl sysBl)
    {
        return this.update("SystemClothMapper.updateSysBl", sysBl);
    }

    /**
     * 新增布料信息
     * 
     * @param  布料信息
     * @return 结果
     */
    @Override
    public int insertSysBl(SysBl sysBl)
    {
        return this.update("SystemClothMapper.insertSysBl", sysBl);
    }

   
   
}
