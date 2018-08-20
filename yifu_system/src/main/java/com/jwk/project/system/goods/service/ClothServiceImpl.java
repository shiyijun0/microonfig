package com.jwk.project.system.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.dao.ClothDao;
import com.jwk.project.system.goods.domain.SysBl;


/**
 * 角色 业务层处理
 * 
 * @author system
 */
@Service("clothService")
public class ClothServiceImpl implements ClothService
{

    @Autowired
    private ClothDao clothDao;

    /**
     * 根据条件分页查询布料数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 布料数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return clothDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有布料
     * 
     * @return 
     */
    @Override
    public List<SysBl> selectSysBlAll()
    {
        return clothDao.selectSysBlAll();
    }

    /**
     * 通过iD查询布料
     * 
     * @param id 布料ID
     * @return 布料对象信息
     */
    @Override
    public SysBl selectSysBlById(Long id)
    {
        return clothDao.selectSysBlById(id);
    }

    /**
     * 通过布料ID删除布料
     * 
     * @param id 布料iD
     * @return 结果
     */
    @Override
    public int deleteSysBlById(Long id)
    {
        return clothDao.deleteSysBlById(id);
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
        return clothDao.batchDeleteSysBl(ids);
    }

    /**
     * 保存布料信息
     * 
     * @param SysBl
     * @return 结果
     */
    @Override
    public int saveSysBl(SysBl sysBl)
    {
    	int id = sysBl.getId();
        if (StringUtils.isNotNull(sysBl) && id>0)
        {
           
          return  clothDao.updateSysBl(sysBl);
        }else{
        	return clothDao.insertSysBl(sysBl);
        }
        
    }

   

}
