package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebWashDao;
import com.jwk.project.system.web.domain.SysWebWash;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webWashService")
public class WebWashServiceImpl implements WebWashService
{

    @Autowired
    private WebWashDao webWashDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webWashDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<SysWebWash> selectWebWashAll()
    {
        return webWashDao.selectWebWashAll();
    }

    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebWash selectWebWashById(Long id)
    {
        return webWashDao.selectWebWashById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deleteWebWashById(Long id)
    {
        return webWashDao.deleteWebWashById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebWash(Long[] ids)
    {
        return webWashDao.batchDeleteWebWash(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param WebWash
     * @return 结果
     */
    @Override
    public int saveWebWash(SysWebWash webWash)
    {
    	Integer id = webWash.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webWashDao.updateWebWash(webWash);
        }else{
        	return webWashDao.insertWebWash(webWash);
        }
        
    }

   

}
