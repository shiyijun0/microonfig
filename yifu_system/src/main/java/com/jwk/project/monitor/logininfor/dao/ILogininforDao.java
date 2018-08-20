package com.jwk.project.monitor.logininfor.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.monitor.logininfor.domain.Logininfor;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author system
 */
public interface ILogininforDao
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 登录记录集合
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int batchDeleteLogininfor(Long[] ids);
}
