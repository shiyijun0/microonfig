package com.jwk.project.system.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.constant.EnumKey;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.dao.GoodsDao;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansAction;
import com.jwk.project.system.goods.domain.JeansSize;


/**
 * 商品      业务层处理
 * 
 * @author system
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService
{

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 根据条件分页查询商品数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 商品数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return goodsDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有商品
     * 
     * @return 
     */
    @Override
    public List<Jeans> selectJeansAll()
    {
        return goodsDao.selectJeansAll();
    }

    /**
     * 通过iD查询商品
     * 
     * @param id 商品ID
     * @return 商品对象信息
     */
    @Override
    public Jeans selectJeansById(Long id)
    {
        return goodsDao.selectJeansById(id);
    }

    /**
     * 通过商品ID删除商品
     * 
     * @param id 商品iD
     * @return 结果
     */
    @Override
    public int deleteJeansById(Long id)
    {
        return goodsDao.deleteJeansById(id);
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
        return goodsDao.batchDeleteJeans(ids);
    }

    /**
     * 批量删除商品尺寸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteJeansSizeById(Long[] ids)
    {
        return goodsDao.batchDeleteJeansSizeById(ids);
    }
    
    /**
     * 通过商品动作ID删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteJeansActionBynzId(Long id)
    {
        return goodsDao.deleteJeansActionBynzId(id);
    }

    /**
     * 批量删除商品动作信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteJeansAction(Long[] ids)
    {
        return goodsDao.batchDeleteJeansAction(ids);
    }
    /**
     * 删除商品尺寸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJeansSizeById(Long id){
    	
    	return goodsDao.deleteJeansSizeById(id);
    }
    
    /**
     * 保存商品信息
     * 
     * @param jeans
     * @return 结果
     */
    @Override
    public int saveJeans(Jeans jeans)
    {
    	 Long jeansId = jeans.getId();
        if (StringUtils.isNotNull(jeans) && StringUtils.isNull(jeansId))
        {
         jeans.setStatus(Jeans.INIT_STATUS_NO);
   		 jeans.setInit(EnumKey.ACTION.getIndex());
        	//新增商品
            int id=	goodsDao.insertJeans(jeans);
            int nzId=0;
        	 
            if(id>0){
            	//新增商品成功
            	System.out.println("**cover***"+jeans.getCover());
            Jeans jean=	goodsDao.selectJeansByCover(jeans.getCover());
            if(StringUtils.isNotNull(jean)){
            	nzId=jean.getId().intValue();
            	System.out.println("**nzId***"+nzId);
            }
            }
          //批量新增产品尺寸大小
        	 if (StringUtils.isNotNull(jeans.getJeansSize()))
             {
        		 for(JeansSize size:jeans.getJeansSize()){
        			 size.setNzId(nzId);
        		 }
        // jeans.setSizeImg(jeans.getJeansSize().get(0).getSize());  		 
        int actionId=goodsDao.batchJeansSize(jeans.getJeansSize());
        if(actionId==0){
        	try {
				throw new Exception("批量插入商品尺寸大小失败");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
             }
        	 
        	//新增商品动作
        	 if (StringUtils.isNotNull(jeans.getJeansAction()))
             {
        		 jeans.getJeansAction().setNzId(nzId);
        int actionId=goodsDao.insertJeansAction(jeans.getJeansAction());
        if(actionId==0){
        	try {
				throw new Exception("插入商品动作图片失败");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
             }
        	 
          //return  goodsDao.insertJeans(jeans);
        	 return id;
        }else{
        	//修改商品
               int id=	goodsDao.updateJeans(jeans);
               int nzId=jeansId.intValue();
             //批量修改产品尺寸大小（先删除然后在新增）
           	 if (StringUtils.isNotNull(jeans.getJeansSize()))
                {
           		 for(JeansSize size:jeans.getJeansSize()){
           			 size.setNzId(nzId);
           		 } 
           		 
           		 int deleteId=goodsDao.deleteJeansSizeById(jeansId);
           		 if(deleteId>0){
           			 int actionId=goodsDao.batchJeansSize(jeans.getJeansSize());
                     if(actionId==0){
                     	try {
             				throw new Exception("批量修改商品尺寸大小失败");
             			} catch (Exception e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			}
                     }
           		 }
          
                }
           	 
           	//修改商品动作
           	 if (StringUtils.isNotNull(jeans.getJeansAction()))
                {
           		 jeans.getJeansAction().setNzId(nzId);
           int actionId=goodsDao.updateJeansAction(jeans.getJeansAction());
           if(actionId==0){
           	try {
   				throw new Exception("插入商品动作图片失败");
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
           }
                }
        	return id;
        }
        
    }

    /**
     * 显示商品动作信息
     * 
     * @param jeansActionList 牛仔动作列表
     * @return 结果
     */
    @Override
    public JeansAction selectJeansActionBynzId(Long id){
    	 return goodsDao.selectJeansActionBynzId(id);
    }

    /**
     * 显示商品尺寸信息
     * 
     * @param jeansActionList 商品尺寸信息列表
     * @return 结果
     */
    @Override
    public List<JeansSize> selectJeansSizeBynzId(Long nzId){
    	return goodsDao.selectJeansSizeBynzId(nzId);
    }
}
