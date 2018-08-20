package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebSizesDao;
import com.jwk.project.system.web.domain.SysWebSizes;


/**
 * 尺寸业务层处理
 * 
 * @author system
 */
@Service("webSizesService")
public class WebSizesServiceImpl implements WebSizesService
{

    @Autowired
    private WebSizesDao webSizesDao;

    /**
     * 根据条件分页查询尺寸数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 尺寸数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webSizesDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有尺寸
     * 
     * @return 
     */
    @Override
    public List<SysWebSizes> selectSysWebSizesAll()
    {
        return webSizesDao.selectSysWebSizesAll();
    }

    /**
     * 通过iD查询尺寸
     * 
     * @param id 尺寸ID
     * @return 尺寸对象信息
     */
    @Override
    public SysWebSizes selectSysWebSizesById(Long id)
    {
        return webSizesDao.selectSysWebSizesById(id);
    }

    /**
     * 通过尺寸ID删除尺寸
     * 
     * @param id 尺寸iD
     * @return 结果
     */
    @Override
    public int deleteSysWebSizesById(Long id)
    {
        return webSizesDao.deleteSysWebSizesById(id);
    }

    /**
     * 批量删除尺寸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysWebSizes(Long[] ids)
    {
        return webSizesDao.batchDeleteSysWebSizes(ids);
    }

    /**
     * 保存尺寸信息
     * 
     * @param SysWebSizes
     * @return 结果
     */
    @Override
    public int saveSysWebSizes(SysWebSizes SysWebSizes)
    {
    	 Integer id = SysWebSizes.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webSizesDao.updateSysWebSizes(SysWebSizes);
        }else{
        	return  webSizesDao.insertSysWebSizes(SysWebSizes);
        }
        
    }

   

}
