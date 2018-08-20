package com.jwk.project.system.fashion.service;
import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommend;
/**
 * 设计师推荐业务层
 * 
 * @author system
 */
public interface RecommendService
{

    /**
     * 根据条件分页查询数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 设计师推荐数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有设计师推荐
     * 
     * @return 权限列表
     */
    public List<SysRecommend> selectSysRecommendAll();

    /**
     * 通过ID查询设计师推荐
     * 
     * @param roleId 设计师推荐ID
     * @return 设计师推荐对象信息
     */
    public SysRecommend selectSysRecommendById(Long id);

    /**
     * 通过设计师推荐ID删除设计师推荐信息
     * 
     * @param id 设计师推荐ID
     * @return 结果
     */
    public int deleteSysRecommendById(Long id);

    /**
     * 批量删除设计师推荐信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysRecommend(Long[] ids);

    /**
     * 保存设计师推荐信息
     * 
     * @param SysRecommend 设计师推荐信息
     * @return 结果
     */
    public int saveSysRecommend(SysRecommend sysRecommend);

    /**
     * 通过设计师推荐goodsName查询设计师推荐
     * 
     * @param goodsName 设计师推荐goodsName
     * @return 设计师推荐对象信息
     */
    public SysRecommend selectSysRecommendByGoodsName(String goodsName);
}
