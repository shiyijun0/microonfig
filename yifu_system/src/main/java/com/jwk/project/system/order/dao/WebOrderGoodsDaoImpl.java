package com.jwk.project.system.order.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.SysWebOrderGoods;



/**
 * 订单货物 数据层处理
 * 
 * @author system
 */
@Repository("webOrderGoodsDao")
public class WebOrderGoodsDaoImpl extends DynamicObjectBaseDao implements WebOrderGoodsDao
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
    	
        return this.findForList("SystemWebOrderGoodsMapper.pageInfoQuery", pageUtilEntity);
    }

    
    /**
     * 根据订单货物ID查询订单货物
     * 
     * @param id 订单货物ID
     * @return 订单货物列表
     */
    @Override
    public List<SysWebOrderGoods> selectWebOrderGoodsAll()
    {
        List<SysWebOrderGoods> orderList = null;
        try
        {
            orderList = this.findForList("SystemWebOrderGoodsMapper.selectWebOrderGoodsAll");
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
    public SysWebOrderGoods selectWebOrderGoodsById(Long id)
    {
        return this.findForObject("SystemWebOrderGoodsMapper.selectWebOrderGoodsById", id);
    }
    
   
    /**
     * 通过订单ID查询订单货物
     * 
     * @param id 订单ID
     * @return 订单货物对象信息
     */
    @Override
    public List<SysWebOrderGoods> selectWebOrderGoodsByOrderId(String orderId)
    {

        List<SysWebOrderGoods> orderList = null;
        try
        {
            orderList = findForList("SystemWebOrderGoodsMapper.selectWebOrderGoodsByOrderId", orderId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return orderList;
       // return this.findForObject("SystemWebOrderGoodsMapper.selectWebOrderGoodsByOrderId", orderId);


    }

    /**
     * 通过订单货物ID删除订单货物
     * 
     * @param id 订单货物ID
     * @return 结果
     */
    @Override
    public int deleteWebOrderGoodsById(Long id)
    {
        return this.delete("SystemWebOrderGoodsMapper.deleteWebOrderGoodsById", id);
    }
    
    /**
     * 通过订单货物orderID删除订单货物
     * 
     * @param id 订单货物ID
     * @return 结果
     */
    @Override
    public int deleteWebOrderGoodsByOrderId(Long orderId)
    {
        return this.delete("SystemWebOrderGoodsMapper.deleteWebOrderGoodsByOrderId", orderId);
    }

    /**
     * 批量删除订单货物信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebOrderGoods(Long[] ids)
    {
        return this.delete("SystemWebOrderGoodsMapper.batchDeleteWebOrderGoods", ids);
    }

   
    /**
     * 保存订单货物信息
     * 
     * @param role 订单货物信息
     * @return 结果
     */
    @Override
    public int updateWebOrderGoods(SysWebOrderGoods order)
    {
        return this.update("SystemWebOrderGoodsMapper.updateWebOrderGoods", order);
    }

    /**
     * 新增订单货物信息
     * 
     * @param role 订单货物信息
     * @return 结果
     */
    @Override
    public int insertWebOrderGoods(SysWebOrderGoods order)
    {
        return this.update("SystemWebOrderGoodsMapper.insertWebOrderGoods", order);
    }

   
}
