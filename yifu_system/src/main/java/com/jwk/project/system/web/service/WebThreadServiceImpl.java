package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebThreadDao;
import com.jwk.project.system.web.domain.SysWebThread;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webThreadService")
public class WebThreadServiceImpl implements WebThreadService
{

    @Autowired
    private WebThreadDao webThreadDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webThreadDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<SysWebThread> selectwebThreadAll()
    {
        return webThreadDao.selectwebThreadAll();
    }

    /**
     * 查询所有边线
     *
     * @return
     */
    @Override
    public List<SysWebThread> selectwebThreadByThread(SysWebThread webThread)
    {
        return webThreadDao.selectwebThreadByThread(webThread);
    }

    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebThread selectwebThreadById(Long id)
    {
        return webThreadDao.selectwebThreadById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deletewebThreadById(Long id)
    {
        return webThreadDao.deletewebThreadById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeletewebThread(Long[] ids)
    {
        return webThreadDao.batchDeletewebThread(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param webThread
     * @return 结果
     */
    @Override
    public int savewebThread(SysWebThread webThread)
    {
    	Integer id = webThread.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webThreadDao.updatewebThread(webThread);
        }else{
        	return webThreadDao.insertwebThread(webThread);
        }
        
    }

   

}
