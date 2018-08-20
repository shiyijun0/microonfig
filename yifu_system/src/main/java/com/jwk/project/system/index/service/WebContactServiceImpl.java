package com.jwk.project.system.index.service;



import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.dao.IWebContactDao;
import com.jwk.project.system.index.domain.SysWebContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 联系我们业务层处理
 * 
 * @author 陈志辉
 */
@Service("webContactService")
public class WebContactServiceImpl implements IWebContactService
{

    @Autowired
    private IWebContactDao webContactDao;



    /**
     * 根据条件分页查询联系我们数据
     *
     * @param pageUtilEntity 分页对象
     * @return 联系我们数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webContactDao.pageInfoQuery(pageUtilEntity);
    }


    /**
     * 通过联系我们ID查询联系我们
     *
     * @param id 联系我们ID
     * @return 联系我们信息
     */
    @Override
    public SysWebContact selectSysWebContactById(int id)
    {
        return webContactDao.selectSysWebContactById(id);
    }


    /**
     * 前端查询联系我们
     *
     */
    @Override
    public SysWebContact selectSysWebContactAll()
    {
        return webContactDao.selectWebContactAll();
    }


    /**
     * 保存联系我们信息
     *
     * @param sysWebContact 联系我们信息
     * @return 结果
     */
    @Override
    public int saveSysWebContact(SysWebContact sysWebContact)
    {
        return webContactDao.updateSysWebContact(sysWebContact);

    }




}
