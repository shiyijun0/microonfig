package com.jwk.project.system.background.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.background.domain.Background;
import org.springframework.stereotype.Repository;

/**
 * 推荐背景图 数据层处理
 *
 * @author 陈志辉
 */
@Repository("backgroundDao")
public class BackgroundDaoImpl extends DynamicObjectBaseDao implements IBackgroundDao
{

    /**
     * 根据条件分页查询推荐背景图数据
     *
     * @param pageUtilEntity 分页对象
     * @return 推荐背景图数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemBackgroundMapper.pageInfoQuery", pageUtilEntity);
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
        return this.delete("SystemBackgroundMapper.deleteBackgroundById", backgroundId);
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
        return this.delete("SystemBackgroundMapper.batchDeleteBackground", ids);
    }


    /**
     * 通过推荐背景图ID查询推荐背景图
     *
     * @param backgroundId 推荐背景图ID
     * @return 推荐背景图信息
     */
    @Override
    public Background selectBackgroundById(Long backgroundId) {
        return this.findForObject("SystemBackgroundMapper.selectBackgroundById", backgroundId);
    }


    /**
     * 保存推荐背景图信息
     *
     * @param background 推荐背景图信息
     * @return 结果
     */
    @Override
    public int updateBackground(Background background)
    {
        return this.update("SystemBackgroundMapper.updateBackground", background);
    }

    /**
     * 新增推荐背景图信息
     *
     * @param background 推荐背景图信息
     * @return 结果
     */
    @Override
    public int insertBackground(Background background)
    {
        return this.update("SystemBackgroundMapper.insertBackground", background);
    }


}
