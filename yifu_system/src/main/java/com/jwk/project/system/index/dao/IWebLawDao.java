package com.jwk.project.system.index.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebLaw;


/**
 * 法律 数据层处理
 *
 * @author 陈志辉
 */

public interface IWebLawDao {

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
     * @return
     */

    public SysWebLaw selectWebLawAll();

    /**
     * 保存法律信息
     *
     * @param sysWebLaw 法律信息
     * @return 结果
     */
    public int updateSysWebLaw(SysWebLaw sysWebLaw);
}
