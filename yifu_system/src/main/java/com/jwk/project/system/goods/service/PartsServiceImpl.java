package com.jwk.project.system.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.dao.PartsDao;
import com.jwk.project.system.goods.domain.SysPj;


/**
 * 角色 业务层处理
 * 
 * @author system
 */
@Service("partsService")
public class PartsServiceImpl implements PartsService
{

    @Autowired
    private PartsDao partsDao;

    /**
     * 根据条件分页查询配件数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 配件数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return partsDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有配件
     * 
     * @return 
     */
    @Override
    public List<SysPj> selectSysPjAll()
    {
        return partsDao.selectSysPjAll();
    }

    /**
     * 通过iD查询配件
     * 
     * @param id 配件ID
     * @return 配件对象信息
     */
    @Override
    public SysPj selectSysPjById(Long id)
    {
        return partsDao.selectSysPjById(id);
    }

    /**
     * 通过配件ID删除配件
     * 
     * @param id 配件iD
     * @return 结果
     */
    @Override
    public int deleteSysPjById(Long id)
    {
        return partsDao.deleteSysPjById(id);
    }

    /**
     * 批量删除配件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysPj(Long[] ids)
    {
        return partsDao.batchDeleteSysPj(ids);
    }

    /**
     * 保存配件信息
     * 
     * @param SysPj
     * @return 结果
     */
    @Override
    public int saveSysPj(SysPj sysPj)
    {
        
    	 int id = sysPj.getId();
    	    if (StringUtils.isNotNull(id) && id>0)
    	    {
    	       
    	      return  partsDao.updateSysPj(sysPj);
    	    }else{
    	    	return  partsDao.insertSysPj(sysPj);
    	    }
    }

   
   

}
