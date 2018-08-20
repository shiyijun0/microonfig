package com.jwk.project.system.version.service;



import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.version.dao.IVersionDao;
import com.jwk.project.system.version.domain.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 版本业务层处理
 * 
 * @author 陈志辉
 */
@Service("versionService")
public class VersionServiceImpl implements IVersionService
{

    @Autowired
    private IVersionDao versionDao;



    /**
     * 根据条件分页查询版本数据
     *
     * @param pageUtilEntity 分页对象
     * @return 版本数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return versionDao.pageInfoQuery(pageUtilEntity);
    }


    /**
     * 通过版本ID查询版本
     *
     * @param versionId 版本ID
     * @return 版本信息
     */
    @Override
    public Version selectVersionById(int versionId)
    {
        return versionDao.selectVersionById(versionId);
    }


    /**
     * 保存版本信息
     *
     * @param version 版本信息
     * @return 结果
     */
    @Override
    public int saveVersion(Version version)
    {

        // 修改版本的信息
        return versionDao.updateVersion(version);

    }




}
