package com.jwk.project.system.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.dao.WebOrderGoodsDao;
import com.jwk.project.system.order.domain.SysWebOrderGoods;


/**
 * 角色 业务层处理
 * 
 * @author system
 */
@Service("webOrderGoodsService")
public class WebOrderGoodsServiceImpl implements WebOrderGoodsService
{

    @Autowired
    private WebOrderGoodsDao webOrderGoodsDao;

    /**
     * 根据条件分页查询订单货物数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 订单货物数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webOrderGoodsDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有订单货物
     * 
     * @return 
     */
    @Override
    public List<SysWebOrderGoods> selectWebOrderGoodsAll()
    {
        return webOrderGoodsDao.selectWebOrderGoodsAll();
    }

    /**
     * 通过iD查询订单货物
     * 
     * @param id 订单货物ID
     * @return 订单货物对象信息
     */
    @Override
    public SysWebOrderGoods selectWebOrderGoodsById(Long id)
    {
        return webOrderGoodsDao.selectWebOrderGoodsById(id);
    }

    /**
     * 通过订单货物ID删除订单货物
     * 
     * @param id 订单货物iD
     * @return 结果
     */
    @Override
    public int deleteWebOrderGoodsById(Long id)
    {
        return webOrderGoodsDao.deleteWebOrderGoodsById(id);
    }

    /**
     * 通过订单货物orderID删除订单货物
     * 
     * @param id 订单货物iD
     * @return 结果
     */
    @Override
    public int deleteWebOrderGoodsByOrderId(Long orderId)
    {
        return webOrderGoodsDao.deleteWebOrderGoodsByOrderId(orderId);
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
        return webOrderGoodsDao.batchDeleteWebOrderGoods(ids);
    }

    /**
     * 保存订单货物信息
     * 
     * @param Order
     * @return 结果
     */
    @Override
    public int saveWebOrderGoods(SysWebOrderGoods WebOrderGoods)
    {
    	Integer id = WebOrderGoods.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webOrderGoodsDao.updateWebOrderGoods(WebOrderGoods);
        }else{
        	return webOrderGoodsDao.insertWebOrderGoods(WebOrderGoods);
        }
        
    }

    /**
     * 通过订单ID查询订单货物
     * 
     * @param id 订单ID
     * @return 订单货物对象信息
     */
	@Override
	public List<SysWebOrderGoods> selectWebOrderGoodsByOrderId(String orderId) {
		return webOrderGoodsDao.selectWebOrderGoodsByOrderId(orderId);
	}
}
