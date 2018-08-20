package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebWash;

/**
 * 破洞 数据层处理
 * 
 * @author system
 */
@Repository("webWashDao")
public class WebWashDaoImpl extends DynamicObjectBaseDao implements WebWashDao
{

	 /**
     * 根据条件分页查询破洞数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 破洞数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemWebWashMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据破洞ID查询破洞
     * 
     * @param id 破洞ID
     * @return 破洞列表
     */
    @Override
    public List<SysWebWash> selectWebWashAll()
    {
        List<SysWebWash> WebWashList = null;
        try
        {
            WebWashList = this.findForList("SystemWebWashMapper.selectWebWashAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return WebWashList;
    }

    /**
     * 通过破洞ID查询破洞
     * 
     * @param id 破洞ID
     * @return 破洞对象信息
     */
    @Override
    public SysWebWash selectWebWashById(Long id)
    {
        return this.findForObject("SystemWebWashMapper.selectWebWashById", id);
    }
    
   

    /**
     * 通过破洞ID删除破洞
     * 
     * @param id 破洞ID
     * @return 结果
     */
    @Override
    public int deleteWebWashById(Long id)
    {
        return this.delete("SystemWebWashMapper.deleteWebWashById", id);
    }

    /**
     * 批量删除破洞信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebWash(Long[] ids)
    {
        return this.delete("SystemWebWashMapper.batchDeleteWebWash", ids);
    }

   
    /**
     * 保存破洞信息
     * 
     * @param role 破洞信息
     * @return 结果
     */
    @Override
    public int updateWebWash(SysWebWash webWash)
    {
        return this.update("SystemWebWashMapper.updateWebWash", webWash);
    }

    /**
     * 新增破洞信息
     * 
     * @param role 破洞信息
     * @return 结果
     */
    @Override
    public int insertWebWash(SysWebWash webWash)
    {
        return this.update("SystemWebWashMapper.insertWebWash", webWash);
    }

   
}
