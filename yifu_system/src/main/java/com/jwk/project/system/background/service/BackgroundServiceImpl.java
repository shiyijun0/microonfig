package com.jwk.project.system.background.service;


import com.jwk.common.utils.FileUtil;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.background.dao.IBackgroundDao;
import com.jwk.project.system.background.domain.Background;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 推荐背景图 业务层处理
 * 
 * @author 陈志辉
 */
@Service("backgroundService")
public class BackgroundServiceImpl implements IBackgroundService
{

    @Autowired
    private IBackgroundDao backgroundDao;

    /**
     * 根据条件分页查询推荐背景图数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 推荐背景图数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return backgroundDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过推荐背景图ID删除推荐背景图
     *
     * @param backgroundId 推荐背景图ID
     * @return 结果
     */
    @Override
    public int deleteBackgroundById(Long backgroundId)
    {
        return backgroundDao.deleteBackgroundById(backgroundId);
    }

    /**
     * 批量删除推荐背景图信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteBackground(Long[] ids)
    {
        return backgroundDao.batchDeleteBackground(ids);
    }



    /**
     * 通过推荐背景图ID查询推荐背景图
     *
     * @param backgroundId 推荐背景图ID
     * @return 推荐背景图信息
     */
    @Override
    public Background selectBackgroundById(Long backgroundId)
    {
        return backgroundDao.selectBackgroundById(backgroundId);
    }


    /**
     * 保存推荐背景图信息
     *
     * @param background 推荐背景图信息
     * @return 结果
     */
    @Override
    public int saveBackground(Background background, MultipartFile imageFile)
    {
        Long backgroundId = background.getBackgroundId();
        if(imageFile.getSize()!=0){
            String filename= FileUtil.fileInput(imageFile);
            background.setUrl(filename);
        }
        if (StringUtils.isNotNull(backgroundId))
        {
            // 修改推荐背景图的信息
            return backgroundDao.updateBackground(background);
        }
        return backgroundDao.insertBackground(background);
    }




}
