package com.jwk.project.system.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.dao.OrderGoodsDao;
import com.jwk.project.system.order.domain.SysOrderGoods;


/**
 * 角色 业务层处理
 * 
 * @author system
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService
{

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    /**
     * 根据条件分页查询订单货物数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单货物数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return orderGoodsDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有订单货物
     * 
     * @return 
     */
    @Override
    public List<SysOrderGoods> selectOrderGoodsAll()
    {
        return orderGoodsDao.selectOrderGoodsAll();
    }

    /**
     * 通过iD查询订单货物
     * 
     * @param id 订单货物ID
     * @return 订单货物对象信息
     */
    @Override
    public SysOrderGoods selectOrderGoodsById(Long id)
    {
        return orderGoodsDao.selectOrderGoodsById(id);
    }

    /**
     * 通过订单货物ID删除订单货物
     * 
     * @param id 订单货物iD
     * @return 结果
     */
    @Override
    public int deleteOrderGoodsById(Long id)
    {
        return orderGoodsDao.deleteOrderGoodsById(id);
    }
    
    
    /**
     * 通过订单货物orderID删除订单货物
     * 
     * @param id 订单货物iD
     * @return 结果
     */
    @Override
    public int deleteOrderGoodsByorderId(Long orderId)
    {
        return orderGoodsDao.deleteOrderGoodsById(orderId);
    }

    /**
     * 批量删除订单货物信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteOrderGoods(Long[] ids)
    {
        return orderGoodsDao.batchDeleteOrderGoods(ids);
    }

    /**
     * 保存订单货物信息
     * 
     * @param Order
     * @return 结果
     */
    @Override
    public int saveOrderGoods(SysOrderGoods orderGoods)
    {
    	int id = orderGoods.getId().intValue();
        if (StringUtils.isNotNull(orderGoods) && id>0)
        {
           
          return  orderGoodsDao.updateOrderGoods(orderGoods);
        }else{
        	return orderGoodsDao.insertOrderGoods(orderGoods);
        }
        
    }


	@Override
	public SysOrderGoods selectOrderGoodsByOrderId(Long orderId) {
		return orderGoodsDao.selectOrderGoodsByOrderId(orderId);
	}

   

}
