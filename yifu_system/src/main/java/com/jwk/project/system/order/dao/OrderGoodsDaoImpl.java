package com.jwk.project.system.order.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysOrderGoods;



/**
 * 订单货物 数据层处理
 * 
 * @author system
 */
@Repository("orderGoodsDao")
public class OrderGoodsDaoImpl extends DynamicObjectBaseDao implements OrderGoodsDao
{

	 /**
     * 根据条件分页查询订单货物数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单货物数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemOrderGoodsMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据订单货物ID查询订单货物
     * 
     * @param id 订单货物ID
     * @return 订单货物列表
     */
    @Override
    public List<SysOrderGoods> selectOrderGoodsAll()
    {
        List<SysOrderGoods> orderList = null;
        try
        {
            orderList = this.findForList("SystemOrderGoodsMapper.selectOrderGoodsAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return orderList;
    }

    /**
     * 通过订单货物ID查询订单货物
     * 
     * @param id 订单货物ID
     * @return 订单货物对象信息
     */
    @Override
    public SysOrderGoods selectOrderGoodsById(Long id)
    {
        return this.findForObject("SystemOrderGoodsMapper.selectOrderGoodsById", id);
    }
    
   
    /**
     * 通过订单ID查询订单货物
     * 
     * @param id 订单ID
     * @return 订单货物对象信息
     */
    @Override
    public SysOrderGoods selectOrderGoodsByOrderId(Long orderId)
    {
        return this.findForObject("SystemOrderGoodsMapper.selectOrderGoodsByOrderId", orderId);
    }

    /**
     * 通过订单货物ID删除订单货物
     * 
     * @param id 订单货物ID
     * @return 结果
     */
    @Override
    public int deleteOrderGoodsById(Long id)
    {
        return this.delete("SystemOrderGoodsMapper.deleteOrderGoodsById", id);
    }
    
    /**
     * 通过订单货物ID删除订单货物
     * 
     * @param id 订单货物ID
     * @return 结果
     */
    @Override
    public int deleteOrderGoodsByorderId(Long orderId)
    {
        return this.delete("SystemOrderGoodsMapper.deleteOrderGoodsByorderId", orderId);
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
        return this.delete("SystemOrderGoodsMapper.batchDeleteOrderGoods", ids);
    }

   
    /**
     * 保存订单货物信息
     * 
     * @param role 订单货物信息
     * @return 结果
     */
    @Override
    public int updateOrderGoods(SysOrderGoods order)
    {
        return this.update("SystemOrderGoodsMapper.updateOrderGoods", order);
    }

    /**
     * 新增订单货物信息
     * 
     * @param role 订单货物信息
     * @return 结果
     */
    @Override
    public int insertOrderGoods(SysOrderGoods order)
    {
        return this.update("SystemOrderGoodsMapper.insertOrderGoods", order);
    }

   
}
