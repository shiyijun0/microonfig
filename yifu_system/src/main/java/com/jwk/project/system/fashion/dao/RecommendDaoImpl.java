package com.jwk.project.system.fashion.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommend;



/**
 * 设计师推荐 数据层处理
 * 
 * @author system
 */
@Repository("recommendDao")
public class RecommendDaoImpl extends DynamicObjectBaseDao implements RecommendDao
{

	 /**
     * 根据条件分页查询设计师推荐数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemRecommendMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据设计师推荐ID查询设计师推荐
     * 
     * @param id 设计师推荐ID
     * @return 设计师推荐列表
     */
    @Override
    public List<SysRecommend> selectSysRecommendAll()
    {
        List<SysRecommend> SysRecommendList = null;
        try
        {
            SysRecommendList = this.findForList("SystemRecommendMapper.selectSysRecommendAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SysRecommendList;
    }

    /**
     * 通过设计师推荐ID查询设计师推荐
     * 
     * @param id 设计师推荐ID
     * @return 设计师推荐对象信息
     */
    @Override
    public SysRecommend selectSysRecommendById(Long id)
    {
        return this.findForObject("SystemRecommendMapper.selectSysRecommendById", id);
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
        return this.findForObject("SystemRecommendMapper.selectSysRecommendByGoodsName", goodsName);
    }
    

    /**
     * 通过设计师推荐ID删除设计师推荐
     * 
     * @param id 设计师推荐ID
     * @return 结果
     */
    @Override
    public int deleteSysRecommendById(Long id)
    {
        return this.delete("SystemRecommendMapper.deleteSysRecommendById", id);
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
        return this.delete("SystemRecommendMapper.batchDeleteSysRecommend", ids);
    }

   
    /**
     * 保存设计师推荐信息
     * 
     * @param  设计师推荐信息
     * @return 结果
     */
    @Override
    public int updateSysRecommend(SysRecommend SysRecommend)
    {
        return this.update("SystemRecommendMapper.updateSysRecommend", SysRecommend);
    }

    /**
     * 新增设计师推荐信息
     * 
     * @param  设计师推荐信息
     * @return 结果
     */
    @Override
    public int insertSysRecommend(SysRecommend SysRecommend)
    {
        return this.update("SystemRecommendMapper.insertSysRecommend", SysRecommend);
    }

   
   
}
