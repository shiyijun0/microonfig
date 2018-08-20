package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebThread;

/**
 * 边线 数据层处理
 * 
 * @author system
 */
@Repository("webThreadDao")
public class WebThreadDaoImpl extends DynamicObjectBaseDao implements WebThreadDao
{

	 /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebThreadMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据边线ID查询边线
     * 
     * @param id 边线ID
     * @return 边线列表
     */
    @Override
    public List<SysWebThread> selectwebThreadAll()
    {
        List<SysWebThread> webThreadList = null;
        try
        {
            webThreadList = this.findForList("SystemWebThreadMapper.selectWebThreadAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return webThreadList;
    }

    /**
     * 根据边线ID查询边线
     *
     * @param id 边线ID
     * @return 边线列表
     */
    @Override
    public List<SysWebThread> selectwebThreadByThread(SysWebThread webThread)
    {
        List<SysWebThread> webThreadList = null;
        try
        {
            webThreadList = this.findForList("SystemWebThreadMapper.selectwebThreadByThread",webThread);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return webThreadList;
    }

    /**
     * 通过边线ID查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebThread selectwebThreadById(Long id)
    {
        return this.findForObject("SystemWebThreadMapper.selectWebThreadById", id);
    }
    
   

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线ID
     * @return 结果
     */
    @Override
    public int deletewebThreadById(Long id)
    {
        return this.delete("SystemWebThreadMapper.deleteWebThreadById", id);
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
        return this.delete("SystemWebThreadMapper.batchDeleteWebThread", ids);
    }

   
    /**
     * 保存边线信息
     * 
     * @param role 边线信息
     * @return 结果
     */
    @Override
    public int updatewebThread(SysWebThread webThread)
    {
        return this.update("SystemWebThreadMapper.updateWebThread", webThread);
    }

    /**
     * 新增边线信息
     * 
     * @param role 边线信息
     * @return 结果
     */
    @Override
    public int insertwebThread(SysWebThread webThread)
    {
        return this.update("SystemWebThreadMapper.insertWebThread", webThread);
    }

   
}
