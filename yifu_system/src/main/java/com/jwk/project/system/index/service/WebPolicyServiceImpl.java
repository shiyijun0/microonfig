package com.jwk.project.system.index.service;



import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.dao.IWebPolicyDao;
import com.jwk.project.system.index.domain.SysWebPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 退换货政策业务层处理
 * 
 * @author 陈志辉
 */
@Service("webPolicyService")
public class WebPolicyServiceImpl implements IWebPolicyService
{

    @Autowired
    private IWebPolicyDao webPolicyDao;



    /**
     * 根据条件分页查询退换货政策数据
     *
     * @param pageUtilEntity 分页对象
     * @return 退换货政策数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webPolicyDao.pageInfoQuery(pageUtilEntity);
    }


    /**
     * 通过退换货政策ID查询退换货政策
     *
     * @param id 退换货政策ID
     * @return 退换货政策信息
     */
    @Override
    public SysWebPolicy selectSysWebPolicyById(int id)
    {
        return webPolicyDao.selectSysWebPolicyById(id);
    }


    /**
     * 前端查询退换货政策
     *
     */
    @Override
    public SysWebPolicy selectSysWebPolicyAll()
    {
        return webPolicyDao.selectWebPolicyAll();
    }


    /**
     * 保存退换货政策信息
     *
     * @param sysWebPolicy 退换货政策信息
     * @return 结果
     */
    @Override
    public int saveSysWebPolicy(SysWebPolicy sysWebPolicy)
    {
        return webPolicyDao.updateSysWebPolicy(sysWebPolicy);

    }




}
