package com.jwk.project.system.order.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysOrderGoods;
/**
 * 订单货物业务层
 * 
 * @author system
 */
public interface OrderGoodsService
{

    /**
     * 根据条件分页查询数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单货物数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询所有订单货物
     * 
     * @return 权限列表
     */
    public List<SysOrderGoods> selectOrderGoodsAll();

    /**
     * 通过ID查询订单货物
     * 
     * @param roleId 订单货物ID
     * @return 订单货物对象信息
     */
    public SysOrderGoods selectOrderGoodsById(Long id);

    /**
     * 通过订单货物ID删除订单货物信息
     * 
     * @param id 订单货物ID
     * @return 结果
     */
    public int deleteOrderGoodsById(Long id);

    /**
     * 批量删除订单货物信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteOrderGoods(Long[] ids);

    /**
     * 保存订单货物信息
     * 
     * @param Order 订单货物信息
     * @return 结果
     */
    public int saveOrderGoods(SysOrderGoods orderGoods);
    
    public int deleteOrderGoodsByorderId(Long orderId);
    
    /**
     * 通过订单ID查询订单货物
     * 
     * @param id 订单ID
     * @return 订单货物对象信息
     */
   
    public SysOrderGoods selectOrderGoodsByOrderId(Long orderId);

}
