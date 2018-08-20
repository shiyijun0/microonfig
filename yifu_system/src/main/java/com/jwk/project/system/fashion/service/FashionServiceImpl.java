package com.jwk.project.system.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.dao.FashionDao;
import com.jwk.project.system.fashion.domain.SysFashion;



/**
 * 潮裤社区业务层处理
 * 
 * @author system
 */
@Service("fashionService")
public class FashionServiceImpl implements FashionService
{

    @Autowired
    private FashionDao fashionDao;

    /**
     * 根据条件分页查询潮裤社区数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 潮裤社区数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return fashionDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有潮裤社区
     * 
     * @return 
     */
    @Override
    public List<SysFashion> selectSysFashionAll()
    {
        return fashionDao.selectSysFashionAll();
    }

    /**
     * 通过iD查询潮裤社区
     * 
     * @param id 潮裤社区ID
     * @return 潮裤社区对象信息
     */
    @Override
    public SysFashion selectSysFashionById(Long id)
    {
        return fashionDao.selectSysFashionById(id);
    }

    /**
     * 通过潮裤社区ID删除潮裤社区
     * 
     * @param id 潮裤社区iD
     * @return 结果
     */
    @Override
    public int deleteSysFashionById(Long id)
    {
        return fashionDao.deleteSysFashionById(id);
    }

    /**
     * 批量删除潮裤社区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysFashion(Long[] ids)
    {
        return fashionDao.batchDeleteSysFashion(ids);
    }

    /**
     * 保存潮裤社区信息
     * 
     * @param SysFashion
     * @return 结果
     */
    @Override
    public int saveSysFashion(SysFashion sysFashion)
    {
    	int id = sysFashion.getId();
        if (StringUtils.isNotNull(sysFashion) && id>0)
        {
           
          return  fashionDao.updateSysFashion(sysFashion);
        }else{
        	return fashionDao.insertSysFashion(sysFashion);
        }
        
    }

   

}
