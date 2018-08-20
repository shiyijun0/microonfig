package com.jwk.project.system.web.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.MyDesigner;

/**
 * 设计师 数据层处理
 * 
 * @author system
 */
@Repository("myDesignerDao")
public class MyDesignerDaoImpl extends DynamicObjectBaseDao implements MyDesignerDao
{

	 /**
     * 根据条件分页查询设计师数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemMyDesignerMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据设计师ID查询设计师
     * 
     * @param id 设计师ID
     * @return 设计师列表
     */
    @Override
    public List<MyDesigner> selectMyDesignerAll()
    {
        List<MyDesigner> MyDesignerList = null;
        try
        {
            MyDesignerList = this.findForList("SystemMyDesignerMapper.selectMyDesignerAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return MyDesignerList;
    }

    /**
     * 根据设计师ID查询设计师
     *
     * @param id 设计师ID
     * @return 设计师列表
     */
    @Override
    public List<MyDesigner> selectMyDesignerByMyDesigner(MyDesigner myDesigner)
    {
        List<MyDesigner> MyDesignerList = null;
        try
        {
            MyDesignerList = this.findForList("SystemMyDesignerMapper.selectMyDesignerByMyDesigner",myDesigner);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return MyDesignerList;
    }

    /**
     * 通过设计师ID查询设计师
     * 
     * @param id 设计师ID
     * @return 设计师对象信息
     */
    @Override
    public MyDesigner selectMyDesignerById(Long id)
    {
        return this.findForObject("SystemMyDesignerMapper.selectMyDesignerById", id);
    }
    
   

    /**
     * 通过设计师ID删除设计师
     * 
     * @param id 设计师ID
     * @return 结果
     */
    @Override
    public int deleteMyDesignerById(Long id)
    {
        return this.delete("SystemMyDesignerMapper.deleteMyDesignerById", id);
    }

    /**
     * 批量删除设计师信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteMyDesigner(Long[] ids)
    {
        return this.delete("SystemMyDesignerMapper.batchDeleteMyDesigner", ids);
    }

   
    /**
     * 保存设计师信息
     * 
     * @param role 设计师信息
     * @return 结果
     */
    @Override
    public int updateMyDesigner(MyDesigner MyDesigner)
    {
        return this.update("SystemMyDesignerMapper.updateMyDesigner", MyDesigner);
    }

    /**
     * 新增设计师信息
     * 
     * @param role 设计师信息
     * @return 结果
     */
    @Override
    public int insertMyDesigner(MyDesigner MyDesigner)
    {
        return this.update("SystemMyDesignerMapper.insertMyDesigner", MyDesigner);
    }

   
}
