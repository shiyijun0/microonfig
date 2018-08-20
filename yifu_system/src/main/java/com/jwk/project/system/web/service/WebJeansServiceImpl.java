package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebJeansDao;
import com.jwk.project.system.web.domain.SysWebJeans;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webJeansService")
public class WebJeansServiceImpl implements WebJeansService
{

    @Autowired
    private WebJeansDao webJeansDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webJeansDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<SysWebJeans> selectWebJeansAll()
    {
        return webJeansDao.selectWebJeansAll();
    }

    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebJeans selectWebJeansById(Long id)
    {
        return webJeansDao.selectWebJeansById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deleteWebJeansById(Long id)
    {
        return webJeansDao.deleteWebJeansById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebJeans(Long[] ids)
    {
        return webJeansDao.batchDeleteWebJeans(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param WebJeans
     * @return 结果
     */
    @Override
    public int saveWebJeans(SysWebJeans webJeans)
    {
    	Integer id = webJeans.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webJeansDao.updateWebJeans(webJeans);
        }else{
        	return webJeansDao.insertWebJeans(webJeans);
        }
        
    }

   

}
