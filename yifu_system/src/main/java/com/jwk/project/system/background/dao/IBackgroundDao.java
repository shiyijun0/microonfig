package com.jwk.project.system.background.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.background.domain.Background;

/**
 *推荐背景图表 数据层
 *
 * @author 陈志辉
 */
public interface IBackgroundDao
{

    /**
     * 根据条件分页查询推荐背景图数据
     *
     * @param pageUtilEntity 分页对象
     * @return推荐背景图数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过推荐背景图ID删除推荐背景图
     *
     * @param backgroundId
     * @return 结果
     */
    public int deleteBackgroundById(Long backgroundId);


    /**
     * 批量删除推荐背景图信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteBackground(Long[] ids);


    /**
     * 通过推荐背景图ID查询推荐背景图
     *
     * @param backgroundId
     * @return推荐背景图信息
     */

    public Background selectBackgroundById(Long backgroundId);




    /**
     * 保存推荐背景图信息
     *
     * @param background
     * @return 结果
     */

    public int updateBackground(Background background);


    /**
     * 新增推荐背景图信息
     *
     * @param background
     * @return 结果
     */

    public int insertBackground(Background background);


}
