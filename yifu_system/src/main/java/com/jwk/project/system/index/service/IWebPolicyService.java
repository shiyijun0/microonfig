package com.jwk.project.system.index.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebPolicy;


/**
 * 退换货政策业务层
 *
 * @author 陈志辉
 */
public interface IWebPolicyService
{


    /**
     * 根据条件分页查询退换货政策数据
     *
     * @param pageUtilEntity 分页对象
     * @return 退换货政策数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过退换货政策ID查询退换货政策
     *
     * @param id 退换货政策ID
     * @return 退换货政策信息
     */

    public SysWebPolicy selectSysWebPolicyById(int id);


    /**
     * 前端查询退换货政策
     *
     */
    public SysWebPolicy selectSysWebPolicyAll();


    /**
     * 保存退换货政策信息
     *
     * @param sysWebPolicy 退换货政策信息
     * @return 结果
     */
    public int saveSysWebPolicy(SysWebPolicy sysWebPolicy);






}
