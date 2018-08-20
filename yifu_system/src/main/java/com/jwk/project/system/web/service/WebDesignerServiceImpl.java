package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebDesignerDao;
import com.jwk.project.system.web.domain.SysWebDesigner;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webDesignerService")
public class WebDesignerServiceImpl implements WebDesignerService
{

    @Autowired
    private WebDesignerDao webDesignerDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webDesignerDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<SysWebDesigner> selectWebDesignerAll()
    {
        return webDesignerDao.selectWebDesignerAll();
    }

    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebDesigner selectWebDesignerById(Long id)
    {
        return webDesignerDao.selectWebDesignerById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deleteWebDesignerById(Long id)
    {
        return webDesignerDao.deleteWebDesignerById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebDesigner(Long[] ids)
    {
        return webDesignerDao.batchDeleteWebDesigner(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param WebDesigner
     * @return 结果
     */
    @Override
    public int saveWebDesigner(SysWebDesigner WebDesigner)
    {
    	Integer id = WebDesigner.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webDesignerDao.updateWebDesigner(WebDesigner);
        }else{
        	return webDesignerDao.insertWebDesigner(WebDesigner);
        }
        
    }

   

}
