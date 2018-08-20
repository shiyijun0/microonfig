package com.jwk.project.system.goods.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansAction;
import com.jwk.project.system.goods.domain.JeansSize;
/**
 * 商品业务层
 * 
 * @author system
 */
public interface GoodsService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 商品数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有商品
     * 
     * @return 权限列表
     */
    public List<Jeans> selectJeansAll();

    /**
     * 通过ID查询商品
     * 
     * @param roleId 商品ID
     * @return 商品对象信息
     */
    public Jeans selectJeansById(Long id);

    /**
     * 通过商品ID删除商品信息
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
     * 保存商品信息
     * 
     * @param jeans 商品信息
     * @return 结果
     */
    public int saveJeans(Jeans jeans);
    
    /**
     * 显示商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    public JeansAction selectJeansActionBynzId(Long id);
    
    public int batchDeleteJeansSizeById(Long[] ids);
    
    public int deleteJeansActionBynzId(Long id);
    
    public int batchDeleteJeansAction(Long[] ids);
    
    public int deleteJeansSizeById(Long id);
    
    public List<JeansSize> selectJeansSizeBynzId(Long nzId);

}
