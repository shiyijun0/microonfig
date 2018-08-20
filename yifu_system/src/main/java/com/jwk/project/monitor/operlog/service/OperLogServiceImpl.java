package com.jwk.project.monitor.operlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.monitor.operlog.dao.IOperLogDao;
import com.jwk.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志 服务层处理
 * 
 * @author system
 */
@Service("operLogService")
public class OperLogServiceImpl implements IOperLogService
{
    @Autowired
    private IOperLogDao operLogDao;

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog)
    {
        operLogDao.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 操作日志集合
     */
    @Override
    public TableDataInfo pageInfoQueryOperLog(PageUtilEntity pageUtilEntity)
    {
        return operLogDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int batchDeleteOperLog(Long[] ids)
    {
        return operLogDao.batchDeleteOperLog(ids);
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public OperLog selectOperLogById(Long operId)
    {
        return operLogDao.selectOperLogById(operId);
    }
}
