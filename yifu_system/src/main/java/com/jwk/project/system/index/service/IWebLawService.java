package com.jwk.project.system.index.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebLaw;


/**
 * 法律业务层
 *
 * @author 陈志辉
 */
public interface IWebLawService
{


    /**
     * 根据条件分页查询法律数据
     *
     * @param pageUtilEntity 分页对象
     * @return 法律数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过法律ID查询法律
     *
     * @param id 法律ID
     * @return 法律信息
     */

    public SysWebLaw selectSysWebLawById(int id);


    /**
     * 前端查询法律
     *
     */
    public SysWebLaw selectSysWebLawAll();


    /**
     * 保存法律信息
     *
     * @param sysWebLaw 法律信息
     * @return 结果
     */
    public int saveSysWebLaw(SysWebLaw sysWebLaw);






}
