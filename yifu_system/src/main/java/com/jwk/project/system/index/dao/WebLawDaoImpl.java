package com.jwk.project.system.index.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebLaw;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 法律 数据层处理
 *
 * @author 陈志辉
 */
@Repository("webLawDao")
public class WebLawDaoImpl extends DynamicObjectBaseDao implements IWebLawDao
{

    /**
     * 根据条件分页查询法律数据
     *
     * @param pageUtilEntity 分页对象
     * @return 法律数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemSysWebLawMapper.pageInfoQuery", pageUtilEntity);
    }


    /**
     * 前端查询法律
     * @return
     */

    @Override
    public SysWebLaw selectWebLawAll()
    {
        List<SysWebLaw> WebLawList = null;
        try
        {
            WebLawList = this.findForList("SystemSysWebLawMapper.selectWebLawAll");
            return WebLawList.get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 通过法律ID查询法律
     *
     * @param id 法律ID
     * @return 法律信息
     */
    @Override
    public SysWebLaw selectSysWebLawById(int id) {
        return this.findForObject("SystemSysWebLawMapper.selectSysWebLawById", id);
    }


    /**
     * 保存法律信息
     *
     * @param sysWebLaw 法律信息
     * @return 结果
     */
    @Override
    public int updateSysWebLaw(SysWebLaw sysWebLaw)
    {
        return this.update("SystemSysWebLawMapper.updateSysWebLaw", sysWebLaw);
    }

  


}
