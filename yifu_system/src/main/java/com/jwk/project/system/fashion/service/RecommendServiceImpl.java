package com.jwk.project.system.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.dao.RecommendDao;
import com.jwk.project.system.fashion.domain.SysRecommend;



/**
 * 设计师推荐业务层处理
 * 
 * @author system
 */
@Service("recommendService")
public class RecommendServiceImpl implements RecommendService
{

    @Autowired
    private RecommendDao recommendDao;

    /**
     * 根据条件分页查询设计师推荐数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return recommendDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有设计师推荐
     * 
     * @return 
     */
    @Override
    public List<SysRecommend> selectSysRecommendAll()
    {
        return recommendDao.selectSysRecommendAll();
    }

    /**
     * 通过iD查询设计师推荐
     * 
     * @param id 设计师推荐ID
     * @return 设计师推荐对象信息
     */
    @Override
    public SysRecommend selectSysRecommendById(Long id)
    {
        return recommendDao.selectSysRecommendById(id);
    }

    /**
     * 通过设计师推荐goodsName查询设计师推荐
     * 
     * @param goodsName 设计师推荐goodsName
     * @return 设计师推荐对象信息
     */
    @Override
    public SysRecommend selectSysRecommendByGoodsName(String goodsName)
    {
        return recommendDao.selectSysRecommendByGoodsName(goodsName);
    }
    
    
    
    /**
     * 通过设计师推荐ID删除设计师推荐
     * 
     * @param id 设计师推荐iD
     * @return 结果
     */
    @Override
    public int deleteSysRecommendById(Long id)
    {
        return recommendDao.deleteSysRecommendById(id);
    }

    /**
     * 批量删除设计师推荐信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysRecommend(Long[] ids)
    {
        return recommendDao.batchDeleteSysRecommend(ids);
    }

    /**
     * 保存设计师推荐信息
     * 
     * @param SysRecommend
     * @return 结果
     */
    @Override
    public int saveSysRecommend(SysRecommend sysRecommend)
    {
    	int id = sysRecommend.getId();
        if (StringUtils.isNotNull(sysRecommend) && id>0)
        {
           
          return  recommendDao.updateSysRecommend(sysRecommend);
        }else{
        	return recommendDao.insertSysRecommend(sysRecommend);
        }
        
    }

   

}
