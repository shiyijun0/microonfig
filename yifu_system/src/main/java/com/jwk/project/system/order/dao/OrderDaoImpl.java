package com.jwk.project.system.order.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 订单 数据层处理
 *
 * @author system
 */
@Repository("orderDao")
public class OrderDaoImpl extends DynamicObjectBaseDao implements OrderDao {

    /**
     * 根据条件分页查询订单数据
     *
     * @param pageUtilEntity 分页对象
     * @return 订单数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity) {

        return this.findForList("SystemOrderMapper.pageInfoQuery", pageUtilEntity);
    }


    /**
     * 根据订单ID查询订单
     *
     * @param id 订单ID
     * @return 订单列表
     */
    @Override
    public List<SysOrder> selectOrderAll() {
        List<SysOrder> orderList = null;
        try {
            orderList = this.findForList("SystemOrderMapper.selectOrderAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    /**
     * 根据订单ID查询订单
     *
     * @param id 订单ID
     * @return 订单列表
     */
    @Override
    public List<SysOrder> selectOrderByOrder(SysOrder order) {
        List<SysOrder> orderList = null;
        try {
            orderList = this.findForList("SystemOrderMapper.selectOrderByOrder", order);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    /**
     * 通过订单ID查询订单
     *
     * @param id 订单ID
     * @return 订单对象信息
     */
    @Override
    public SysOrder selectOrderById(Long id) {
        return this.findForObject("SystemOrderMapper.selectOrderById", id);
    }


    /**
     * 通过订单ID删除订单
     *
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id) {
        return this.delete("SystemOrderMapper.deleteOrderById", id);
    }

    /**
     * 批量删除订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteOrder(Long[] ids) {
        return this.delete("SystemOrderMapper.batchDeleteOrder", ids);
    }


    /**
     * 保存订单信息
     *
     * @param role 订单信息
     * @return 结果
     */
    @Override
    public int updateOrder(SysOrder order) {
        return this.update("SystemOrderMapper.updateOrder", order);
    }

    /**
     * 保存订单信息
     *
     * @param role 订单信息
     * @return 结果
     */
    @Override
    public int updateOrderByPayStatus(SysOrder order) {
        return this.update("SystemOrderMapper.updateOrderByPayStatus", order);
    }

    /**
     * 新增订单信息
     *
     * @param role 订单信息
     * @return 结果
     */
    @Override
    public int insertOrder(SysOrder order) {
        return this.update("SystemOrderMapper.insertOrder", order);
    }


}
