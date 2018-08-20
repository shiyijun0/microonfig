package com.jwk.project.system.web.dao;

import java.util.List;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebDesigner;



/**
 * 成品表 数据层
 * 
 * @author system
 */
public interface WebDesignerDao
{

    /**
     * 根据条件分页查询成品数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 成品数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询成品列表
     * 
     * @return 成品列表
     */
    public List<SysWebDesigner> selectWebDesignerAll();

    /**
     * 通过成品ID查询成品信息
     * 
     * @param id 成品ID
     * @return 成品对象信息
     */
    public SysWebDesigner selectWebDesignerById(Long id);
    

    /**
     * 通过成品ID删除成品
     * 
     * @param id 成品ID
     * @return 结果
     */
    public int deleteWebDesignerById(Long id);

    /**
     * 批量删除成品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebDesigner(Long[] ids);


    /**
     * 修改成品信息
     * 
     * @param WebDesigner 成品信息
     * @return 结果
     */
    public int updateWebDesigner(SysWebDesigner webDesigner);

    /**
     * 新增成品信息
     * 
     * @param WebDesigner 成品信息
     * @return 结果
     */
    public int insertWebDesigner(SysWebDesigner webDesigner);
    
    

}
