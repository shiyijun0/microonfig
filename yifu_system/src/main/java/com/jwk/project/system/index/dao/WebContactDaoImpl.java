package com.jwk.project.system.index.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebContact;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联系我们 数据层处理
 *
 * @author 陈志辉
 */
@Repository("webContactDao")
public class WebContactDaoImpl extends DynamicObjectBaseDao implements IWebContactDao
{

    /**
     * 根据条件分页查询联系我们数据
     *
     * @param pageUtilEntity 分页对象
     * @return 联系我们数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemSysWebContactMapper.pageInfoQuery", pageUtilEntity);
    }


    /**
     * 前端查询联系我们
     * @return
     */

    @Override
    public SysWebContact selectWebContactAll()
    {
        List<SysWebContact> WebContactList = null;
        try
        {
            WebContactList = this.findForList("SystemSysWebContactMapper.selectWebContactAll");
            return WebContactList.get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 通过联系我们ID查询联系我们
     *
     * @param id 联系我们ID
     * @return 联系我们信息
     */
    @Override
    public SysWebContact selectSysWebContactById(int id) {
        return this.findForObject("SystemSysWebContactMapper.selectSysWebContactById", id);
    }


    /**
     * 保存联系我们信息
     *
     * @param sysWebContact 联系我们信息
     * @return 结果
     */
    @Override
    public int updateSysWebContact(SysWebContact sysWebContact)
    {
        return this.update("SystemSysWebContactMapper.updateSysWebContact", sysWebContact);
    }

  


}
