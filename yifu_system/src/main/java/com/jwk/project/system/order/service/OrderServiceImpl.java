package com.jwk.project.system.order.service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.dao.OrderDao;
import com.jwk.project.system.order.domain.SysOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务层处理
 *
 * @author system
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据条件分页查询订单数据
     *
     * @param pageUtilEntity 分页对象
     * @return 订单数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return orderDao.pageInfoQuery(pageUtilEntity);
    }


    /**
     * 查询所有订单
     *
     * @return
     */
    @Override
    public List<SysOrder> selectOrderAll() {
        return orderDao.selectOrderAll();
    }

    /**
     * 通过iD查询订单
     *
     * @param id 订单ID
     * @return 订单对象信息
     */
    @Override
    public SysOrder selectOrderById(Long id) {
        return orderDao.selectOrderById(id);
    }

    /**
     * 通过订单ID删除订单
     *
     * @param id 订单iD
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id) {
        return orderDao.deleteOrderById(id);
    }

    /**
     * 批量删除订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteOrder(Long[] ids) {
        return orderDao.batchDeleteOrder(ids);
    }

    /**
     * 保存订单信息
     *
     * @param order
     * @return 结果
     */
    @Override
    public int saveOrder(SysOrder order) {
        Long id = order.getId();
        if (StringUtils.isNotNull(id)) {
            return orderDao.updateOrder(order);
        } else {
            return orderDao.insertOrder(order);
        }

    }

    @Override
    public int updateOrderByPayStatus(SysOrder order) {
        return orderDao.updateOrderByPayStatus(order);
    }


	@Override
	public List<SysOrder> selectOrderByOrder(SysOrder order) {
		return orderDao.selectOrderByOrder(order);
	}


}
