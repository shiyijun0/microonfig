package com.jwk.project.system.order.dao;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysOrder;



/**
 * 订单表 数据层
 * 
 * @author system
 */
public interface OrderDao
{

    /**
     * 根据条件分页查询订单数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询订单列表
     * 
     * @return 订单列表
     */
    public List<SysOrder> selectOrderAll();

    /**
     * 通过订单ID查询订单信息
     * 
     * @param id 订单ID
     * @return 订单对象信息
     */
    public SysOrder selectOrderById(Long id);
    

    /**
     * 通过订单ID删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteOrder(Long[] ids);

   

    /**
     * 修改订单信息
     * 
     * @param Order 订单信息
     * @return 结果
     */
    public int updateOrder(SysOrder order);

    /**
     * 新增订单信息
     * 
     * @param Order 订单信息
     * @return 结果
     */
    public int insertOrder(SysOrder order);
    
    public List<SysOrder> selectOrderByOrder(SysOrder order);

    public int updateOrderByPayStatus(SysOrder order);

}
