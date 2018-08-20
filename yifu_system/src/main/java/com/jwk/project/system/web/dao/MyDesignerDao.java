package com.jwk.project.system.web.dao;

import java.util.List;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.MyDesigner;



/**
 * 设计师表 数据层
 * 
 * @author system
 */
public interface MyDesignerDao
{

    /**
     * 根据条件分页查询设计师数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询设计师列表
     * 
     * @return 设计师列表
     */
    public List<MyDesigner> selectMyDesignerAll();

    /**
     * 通过设计师ID查询设计师信息
     * 
     * @param id 设计师ID
     * @return 设计师对象信息
     */
    public MyDesigner selectMyDesignerById(Long id);
    

    /**
     * 通过设计师ID删除设计师
     * 
     * @param id 设计师ID
     * @return 结果
     */
    public int deleteMyDesignerById(Long id);

    /**
     * 批量删除设计师信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteMyDesigner(Long[] ids);


    /**
     * 修改设计师信息
     * 
     * @param myDesigner 设计师信息
     * @return 结果
     */
    public int updateMyDesigner(MyDesigner myDesigner);

    /**
     * 新增设计师信息
     * 
     * @param myDesigner 设计师信息
     * @return 结果
     */
    public int insertMyDesigner(MyDesigner myDesigner);


    public List<MyDesigner> selectMyDesignerByMyDesigner(MyDesigner myDesigner);
}
