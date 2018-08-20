package com.jwk.project.system.index.service;



import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.dao.IWebLawDao;
import com.jwk.project.system.index.domain.SysWebLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 法律业务层处理
 * 
 * @author 陈志辉
 */
@Service("webLawService")
public class WebLawServiceImpl implements IWebLawService
{

    @Autowired
    private IWebLawDao webLawDao;



    /**
     * 根据条件分页查询法律数据
     *
     * @param pageUtilEntity 分页对象
     * @return 法律数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webLawDao.pageInfoQuery(pageUtilEntity);
    }


    /**
     * 通过法律ID查询法律
     *
     * @param id 法律ID
     * @return 法律信息
     */
    @Override
    public SysWebLaw selectSysWebLawById(int id)
    {
        return webLawDao.selectSysWebLawById(id);
    }


    /**
     * 前端查询法律
     *
     */
    @Override
    public SysWebLaw selectSysWebLawAll()
    {
        return webLawDao.selectWebLawAll();
    }


    /**
     * 保存法律信息
     *
     * @param sysWebLaw 法律信息
     * @return 结果
     */
    @Override
    public int saveSysWebLaw(SysWebLaw sysWebLaw)
    {
        return webLawDao.updateSysWebLaw(sysWebLaw);

    }




}
