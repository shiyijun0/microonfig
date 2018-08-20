package com.jwk.project.system.fashion.service;
import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommend;
import com.jwk.project.system.fashion.domain.SysRecommendBg;
/**
 * 设计师推荐业务层
 * 
 * @author system
 */
public interface RecommendBgService
{


	 /**
     * 查询设计师推荐问题列表
     * 
     * @return 设计师推荐问题列表
     */
    public List<SysRecommendBg> selectSysRecommendBgAll();

    /**
     * 通过设计师推荐ID查询设计师推荐信息
     * 
     * @param id 设计师推荐ID
     * @return 设计师推荐对象信息
     */
    public SysRecommendBg selectSysRecommendBgById(Long id);
    
    /**
     * 通过设计师推荐ID查询设计师推荐信息
     * 
     * @param id 设计师推荐ID
     * @return 设计师推荐对象信息
     */
    public List<SysRecommendBg> selectSysRecommendBgByrecommendId(Long recommendId);

   

}
