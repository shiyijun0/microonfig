package com.jwk.project.system.index.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebPolicy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退换货政策 数据层处理
 *
 * @author 陈志辉
 */
@Repository("webPolicyDao")
public class WebPolicyDaoImpl extends DynamicObjectBaseDao implements IWebPolicyDao
{

    /**
     * 根据条件分页查询退换货政策数据
     *
     * @param pageUtilEntity 分页对象
     * @return 退换货政策数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemSysWebPolicyMapper.pageInfoQuery", pageUtilEntity);
    }


    /**
     * 前端查询退换货政策
     * @return
     */

    @Override
    public SysWebPolicy selectWebPolicyAll()
    {
        List<SysWebPolicy> WebPolicyList = null;
        try
        {
            WebPolicyList = this.findForList("SystemSysWebPolicyMapper.selectWebPolicyAll");
            return WebPolicyList.get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 通过退换货政策ID查询退换货政策
     *
     * @param id 退换货政策ID
     * @return 退换货政策信息
     */
    @Override
    public SysWebPolicy selectSysWebPolicyById(int id) {
        return this.findForObject("SystemSysWebPolicyMapper.selectSysWebPolicyById", id);
    }


    /**
     * 保存退换货政策信息
     *
     * @param sysWebPolicy 退换货政策信息
     * @return 结果
     */
    @Override
    public int updateSysWebPolicy(SysWebPolicy sysWebPolicy)
    {
        return this.update("SystemSysWebPolicyMapper.updateSysWebPolicy", sysWebPolicy);
    }

  


}
