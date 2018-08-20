package com.jwk.project.system.wordfont.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordfont.domain.WordFont;


/**
 * 文字字体业务层
 *
 * @author 陈志辉
 */
public interface IWordFontService
{

    /**
     * 根据条件分页查询文字字体数据
     *
     * @param pageUtilEntity 分页对象
     * @return 文字字体数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过文字字体ID删除文字字体
     *
     * @param fontId 文字字体ID
     * @return 结果
     */

    public int deleteWordFontById(Long fontId);

    /**
     * 批量删除文字字号信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */

    public int batchDeleteWordFont(Long[] ids);



    /**
     * 通过文字字号ID查询文字字号
     *
     * @param fontId 文字字号ID
     * @return 文字字号信息
     */

    public WordFont selectWordFontById(Long fontId);



    /**
     * 保存文字字号信息
     *
     * @param wordfont 文字字号信息
     * @return 结果
     */
    public int saveWordFont(WordFont wordfont);






}
