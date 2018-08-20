package com.jwk.project.system.startphoto.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.startphoto.domain.StartPhoto;
import org.springframework.stereotype.Repository;

/**
 * 开机图片 数据层处理
 *
 * @author 陈志辉
 */
@Repository("StartPhotoDao")
public class StartPhotoDaoImpl extends DynamicObjectBaseDao implements IStartPhotoDao
{

    /**
     * 根据条件分页查询开机图片数据
     *
     * @param pageUtilEntity 分页对象
     * @return 开机图片数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemStartPhotoMapper.pageInfoQuery", pageUtilEntity);
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
        return this.delete("SystemStartPhotoMapper.deleteStartPhotoById", startPhotoId);
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
        return this.delete("SystemStartPhotoMapper.batchDeleteStartPhoto", ids);
    }


    /**
     * 通过开机图片ID查询开机图片
     *
     * @param startPhotoId 开机图片ID
     * @return 开机图片信息
     */
    @Override
    public StartPhoto selectStartPhotoById(Long startPhotoId) {
        return this.findForObject("SystemStartPhotoMapper.selectStartPhotoById", startPhotoId);
    }


    /**
     * 保存开机图片信息
     *
     * @param startPhoto 开机图片信息
     * @return 结果
     */
    @Override
    public int updateStartPhoto(StartPhoto startPhoto)
    {
        return this.update("SystemStartPhotoMapper.updateStartPhoto", startPhoto);
    }

    /**
     * 新增开机图片信息
     *
     * @param startPhoto 开机图片信息
     * @return 结果
     */
    @Override
    public int insertStartPhoto(StartPhoto startPhoto)
    {
        return this.update("SystemStartPhotoMapper.insertStartPhoto", startPhoto);
    }


}
