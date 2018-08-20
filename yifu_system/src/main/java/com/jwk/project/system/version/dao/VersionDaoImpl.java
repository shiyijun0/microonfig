package com.jwk.project.system.version.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.version.domain.Version;
import org.springframework.stereotype.Repository;

/**
 * 版本 数据层处理
 *
 * @author 陈志辉
 */
@Repository("versionDao")
public class VersionDaoImpl extends DynamicObjectBaseDao implements IVersionDao
{

    /**
     * 根据条件分页查询版本数据
     *
     * @param pageUtilEntity 分页对象
     * @return 版本数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemVersionMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过版本ID查询版本
     *
     * @param versionId 版本ID
     * @return 版本信息
     */
    @Override
    public Version selectVersionById(int versionId) {
        return this.findForObject("SystemVersionMapper.selectVersionById", versionId);
    }


    /**
     * 保存版本信息
     *
     * @param version 版本信息
     * @return 结果
     */
    @Override
    public int updateVersion(Version version)
    {
        return this.update("SystemVersionMapper.updateVersion", version);
    }

  


}
