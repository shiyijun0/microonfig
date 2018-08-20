package com.jwk.project.system.wordcolor.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordcolor.domain.WordColor;

/**
 * 文字颜色表 数据层
 * 
 * @author 陈志辉
 */
public interface IWordColorDao
{

    /**
     * 根据条件分页查询文字颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 角色数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过文字颜色ID删除文字颜色
     *
     * @param colorId 文字颜色ID
     * @return 结果
     */
    public int deleteWordColorById(Long colorId);


    /**
     * 批量删除文字颜色信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWordColor(Long[] ids);


    /**
     * 通过文字颜色ID查询文字颜色
     *
     * @param colorId 文字颜色ID
     * @return 文字颜色信息
     */

    public WordColor selectWordColorById(Long colorId);




    /**
     * 保存文字颜色信息
     *
     * @param wordColor 文字颜色信息
     * @return 结果
     */

    public int updateWordColor(WordColor wordColor);


    /**
     * 新增文字颜色信息
     *
     * @param wordColor 文字颜色信息
     * @return 结果
     */

    public int insertWordColor(WordColor wordColor);


}
