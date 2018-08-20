package com.jwk.project.system.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansAction;
import com.jwk.project.system.goods.domain.JeansSize;
import com.jwk.project.system.role.domain.RoleMenu;


/**
 * 商品 数据层处理
 * 
 * @author system
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends DynamicObjectBaseDao implements GoodsDao
{

	 /**
     * 根据条件分页查询商品数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 商品数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemGoodsMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据商品ID查询商品
     * 
     * @param id 商品ID
     * @return 商品列表
     */
    @Override
    public List<Jeans> selectJeansAll()
    {
        List<Jeans> jeansList = null;
        try
        {
            jeansList = this.findForList("SystemGoodsMapper.selectJeansAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jeansList;
    }

    /**
     * 通过商品ID查询商品
     * 
     * @param id 商品ID
     * @return 商品对象信息
     */
    @Override
    public Jeans selectJeansById(Long id)
    {
        return this.findForObject("SystemGoodsMapper.selectJeansById", id);
    }
    
    /**
     * 通过商品ID查询商品
     * 
     * @param id 商品ID
     * @return 商品对象信息
     */
    @Override
    public Jeans selectJeansByCover(int cover)
    {
        return this.findForObject("SystemGoodsMapper.selectJeansByCover", cover);
    }

    /**
     * 通过商品ID删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteJeansById(Long id)
    {
        return this.delete("SystemGoodsMapper.deleteJeansById", id);
    }

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteJeans(Long[] ids)
    {
        return this.delete("SystemGoodsMapper.batchDeleteJeans", ids);
    }

   
    /**
     * 保存商品信息
     * 
     * @param role 商品信息
     * @return 结果
     */
    @Override
    public int updateJeans(Jeans jeans)
    {
        return this.update("SystemGoodsMapper.updateJeans", jeans);
    }

    /**
     * 新增商品信息
     * 
     * @param role 商品信息
     * @return 结果
     */
    @Override
    public int insertJeans(Jeans jeans)
    {
        return this.update("SystemGoodsMapper.insertJeans", jeans);
    }

    /**
     * 新增商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    @Override
    public int insertJeansAction(JeansAction jeansAction)
    {
        return this.update("SystemGoodsActionMapper.insertJeansAction", jeansAction);
    }
    
    /**
     * 修改商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    @Override
    public int updateJeansAction(JeansAction jeansAction)
    {
        return this.update("SystemGoodsActionMapper.updateJeansAction", jeansAction);
    }
    
    /**
     * 通过商品ID删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteJeansActionBynzId(Long id)
    {
        return this.delete("SystemGoodsActionMapper.deleteJeansActionBynzId", id);
    }

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteJeansAction(Long[] ids)
    {
        return this.delete("SystemGoodsActionMapper.batchDeleteJeansAction", ids);
    }
    
   
    /**
     * 显示商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    @Override
    public JeansAction selectJeansActionBynzId(Long id)
    {
    	 return this.findForObject("SystemGoodsActionMapper.selectJeansActionBynzId", id);
    			 
    }
    
    
    
    /**
     * 批量新增商品尺寸大小信息
     * 
     * @param roleMenuList 商品尺寸列表
     * @return 结果
     */
    @Override
    public int batchJeansSize(List<JeansSize> jeansSizeList)
    {
        return this.batchSave("SystemGoodsSizeMapper.batchJeansSize", jeansSizeList);
    }
    
    /**
     * 通过商品ID删除商品尺寸
     * 
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteJeansSizeById(Long id)
    {
        return this.delete("SystemGoodsSizeMapper.deleteJeansSizeByjeansId", id);
    }
    
    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteJeansSizeById(Long[] ids)
    {
        return this.delete("SystemGoodsSizeMapper.batchDeleteJeansSizeByjeansId", ids);
    }
    
    /**
     * 显示商品尺寸信息
     * 
     * @param jeansActionList 商品尺寸信息列表
     * @return 结果
     */
    @Override
    public List<JeansSize> selectJeansSizeBynzId(Long id)
    {
    	
    	 List<JeansSize> jeansSizeList = null;
    	 try {
    		 jeansSizeList= this.findForList("SystemGoodsSizeMapper.selectJeansSizeBynzId", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jeansSizeList;		 
    }
}
