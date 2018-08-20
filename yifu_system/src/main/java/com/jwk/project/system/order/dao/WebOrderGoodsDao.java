package com.jwk.project.system.order.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysWebOrderGoods;



/**
 * 订单货物表 数据层
 * 
 * @author system
 */
public interface WebOrderGoodsDao
{

    /**
     * 根据条件分页查询订单货物数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单货物数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询订单货物列表
     * 
     * @return 订单货物列表
     */
    public List<SysWebOrderGoods> selectWebOrderGoodsAll();

    /**
     * 通过订单货物ID查询订单货物信息
     * 
     * @param id 订单货物ID
     * @return 订单货物对象信息
     */
    public SysWebOrderGoods selectWebOrderGoodsById(Long id);
    
   
    /**
     * 通过订单ID查询订单货物
     * 
     * @param id 订单ID
     * @return 订单货物对象信息
     */
   
    public List<SysWebOrderGoods> selectWebOrderGoodsByOrderId(String orderId);
    

    /**
     * 通过订单货物ID删除订单货物
     * 
     * @param id 订单货物ID
     * @return 结果
     */
    public int deleteWebOrderGoodsById(Long id);

    /**
     * 批量删除订单货物信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebOrderGoods(Long[] ids);

   

    /**
     * 修改订单货物信息
     * 
     * @param Order 订单货物信息
     * @return 结果
     */
    public int updateWebOrderGoods(SysWebOrderGoods order);

    /**
     * 新增订单货物信息
     * 
     * @param Order 订单货物信息
     * @return 结果
     */
    public int insertWebOrderGoods(SysWebOrderGoods order);
    
    public int deleteWebOrderGoodsByOrderId(Long orderId);
    
    

}
