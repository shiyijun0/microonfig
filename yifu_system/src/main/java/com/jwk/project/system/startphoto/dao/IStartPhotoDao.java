package com.jwk.project.system.startphoto.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.startphoto.domain.StartPhoto;

/**
 *开机图片表 数据层
 *
 * @author 陈志辉
 */
public interface IStartPhotoDao
{

    /**
     * 根据条件分页查询开机图片数据
     *
     * @param pageUtilEntity 分页对象
     * @return开机图片数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过开机图片ID删除开机图片
     *
     * @param startPhotoId
     * @return 结果
     */
    public int deleteStartPhotoById(Long startPhotoId);


    /**
     * 批量删除开机图片信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteStartPhoto(Long[] ids);


    /**
     * 通过开机图片ID查询开机图片
     *
     * @param startPhotoId
     * @return开机图片信息
     */

    public StartPhoto selectStartPhotoById(Long startPhotoId);




    /**
     * 保存开机图片信息
     *
     * @param startPhoto
     * @return 结果
     */

    public int updateStartPhoto(StartPhoto startPhoto);


    /**
     * 新增开机图片信息
     *
     * @param startPhoto
     * @return 结果
     */

    public int insertStartPhoto(StartPhoto startPhoto);


}
