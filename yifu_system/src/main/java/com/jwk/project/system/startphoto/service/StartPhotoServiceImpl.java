package com.jwk.project.system.startphoto.service;


import com.jwk.common.utils.FileUtil;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.startphoto.dao.IStartPhotoDao;
import com.jwk.project.system.startphoto.domain.StartPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * 开机图片业务层处理
 * 
 * @author 陈志辉
 */
@Service("startPhotoService")
public class StartPhotoServiceImpl implements IStartPhotoService
{

    @Autowired
    private IStartPhotoDao startPhotoDao;

    /**
     * 根据条件分页查询开机图片数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 开机图片数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return startPhotoDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过开机图片ID删除开机图片
     *
     * @param startPhotoId 开机图片ID
     * @return 结果
     */
    @Override
    public int deleteStartPhotoById(Long startPhotoId)
    {
        return startPhotoDao.deleteStartPhotoById(startPhotoId);
    }

    /**
     * 批量删除开机图片信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteStartPhoto(Long[] ids)
    {
        return startPhotoDao.batchDeleteStartPhoto(ids);
    }



    /**
     * 通过开机图片ID查询开机图片
     *
     * @param startPhotoId 开机图片ID
     * @return 开机图片信息
     */
    @Override
    public StartPhoto selectStartPhotoById(Long startPhotoId)
    {
        return startPhotoDao.selectStartPhotoById(startPhotoId);
    }


    /**
     * 保存开机图片信息
     *
     * @param startPhoto 开机图片信息
     * @return 结果
     */
    @Override
    public int saveStartPhoto(StartPhoto startPhoto, MultipartFile imageFile)
    {
        Long startPhotoId = startPhoto.getStartPhotoId();
        if(imageFile.getSize()!=0){
            String filename= FileUtil.fileInput(imageFile);
            startPhoto.setFile(filename);
        }
        if (StringUtils.isNotNull(startPhotoId))
        {
            // 修改开机图片的信息
            return startPhotoDao.updateStartPhoto(startPhoto);
        }
        return startPhotoDao.insertStartPhoto(startPhoto);
    }




}
