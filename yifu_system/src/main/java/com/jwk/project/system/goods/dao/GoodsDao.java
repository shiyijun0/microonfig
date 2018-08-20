package com.jwk.project.system.goods.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansAction;
import com.jwk.project.system.goods.domain.JeansSize;



/**
 * 商品表 数据层
 * 
 * @author system
 */
public interface GoodsDao
{

    /**
     * 根据条件分页查询商品数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 商品数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询商品列表
     * 
     * @return 商品列表
     */
    public List<Jeans> selectJeansAll();

    /**
     * 通过商品ID查询商品信息
     * 
     * @param id 商品ID
     * @return 商品对象信息
     */
    public Jeans selectJeansById(Long id);
    
    /**
     * 通过商品ID查询商品
     * 
     * @param id 商品ID
     * @return 商品对象信息
     */
    public Jeans selectJeansByCover(int cover);

    /**
     * 通过商品ID删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteJeansById(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteJeans(Long[] ids);

   

    /**
     * 修改商品信息
     * 
     * @param Jeans 商品信息
     * @return 结果
     */
    public int updateJeans(Jeans jeans);

    /**
     * 新增商品信息
     * 
     * @param Jeans 商品信息
     * @return 结果
     */
    public int insertJeans(Jeans jeans);
    
    /**
     * 新增商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    public int insertJeansAction(JeansAction jeansActionList);
    
    /**
     * 显示商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    public JeansAction selectJeansActionBynzId(Long id);
    
    
    /**
     * 通过商品ID删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteJeansActionBynzId(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteJeansAction(Long[] ids);
    
    
    /**
     * 批量新增商品尺寸大小信息
     * 
     * @param roleMenuList 商品尺寸列表
     * @return 结果
     */
    public int batchJeansSize(List<JeansSize> jeansSizeList);
    
    /**
     * 通过商品ID删除商品尺寸
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteJeansSizeById(Long id);
    
    /**
     * 修改商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    public int updateJeansAction(JeansAction jeansAction);
    
    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteJeansSizeById(Long[] ids);
    
    /**
     * 显示商品尺寸信息
     * 
     * @param jeansActionList 商品尺寸信息列表
     * @return 结果
     */
    public List<JeansSize> selectJeansSizeBynzId(Long id);

}
