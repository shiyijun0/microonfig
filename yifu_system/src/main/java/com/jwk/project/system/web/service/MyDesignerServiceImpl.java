package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.MyDesignerDao;
import com.jwk.project.system.web.domain.MyDesigner;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("myDesignerService")
public class MyDesignerServiceImpl implements MyDesignerService
{

    @Autowired
    private MyDesignerDao myDesignerDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return myDesignerDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<MyDesigner> selectMyDesignerAll()
    {
        return myDesignerDao.selectMyDesignerAll();
    }
    @Override
    public List<MyDesigner> selectMyDesignerByMyDesigner(MyDesigner myDesigner){
        return myDesignerDao.selectMyDesignerByMyDesigner(myDesigner);
    }
    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public MyDesigner selectMyDesignerById(Long id)
    {
        return myDesignerDao.selectMyDesignerById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deleteMyDesignerById(Long id)
    {
        return myDesignerDao.deleteMyDesignerById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteMyDesigner(Long[] ids)
    {
        return myDesignerDao.batchDeleteMyDesigner(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param MyDesigner
     * @return 结果
     */
    @Override
    public int saveMyDesigner(MyDesigner MyDesigner)
    {
    	Integer id = MyDesigner.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  myDesignerDao.updateMyDesigner(MyDesigner);
        }else{
        	return myDesignerDao.insertMyDesigner(MyDesigner);
        }
        
    }

   

}
