package com.jwk.project.system.index.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebContact;


/**
 * 联系我们业务层
 *
 * @author 陈志辉
 */
public interface IWebContactService
{


    /**
     * 根据条件分页查询联系我们数据
     *
     * @param pageUtilEntity 分页对象
     * @return 联系我们数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过联系我们ID查询联系我们
     *
     * @param id 联系我们ID
     * @return 联系我们信息
     */

    public SysWebContact selectSysWebContactById(int id);


    /**
     * 前端查询联系我们
     *
     */
    public SysWebContact selectSysWebContactAll();


    /**
     * 保存联系我们信息
     *
     * @param sysWebContact 联系我们信息
     * @return 结果
     */
    public int saveSysWebContact(SysWebContact sysWebContact);






}
