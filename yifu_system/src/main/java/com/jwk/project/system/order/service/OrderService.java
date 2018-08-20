package com.jwk.project.system.order.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysOrder;
/**
 * 订单业务层
 * 
 * @author system
 */
public interface OrderService
{

    /**
     * 根据条件分页查询数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 查询所有订单
     * 
     * @return 权限列表
     */
    public List<SysOrder> selectOrderAll();

    /**
     * 通过ID查询订单
     * 
     * @param roleId 订单ID
     * @return 订单对象信息
     */
    public SysOrder selectOrderById(Long id);

    /**
     * 通过订单ID删除订单信息
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
     * 保存订单信息
     * 
     * @param Order 订单信息
     * @return 结果
     */
    public int saveOrder(SysOrder order);

    public int updateOrderByPayStatus(SysOrder order);
    
    public List<SysOrder> selectOrderByOrder(SysOrder order);

}
