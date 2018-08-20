package com.jwk.project.system.wordsize.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordsize.domain.WordSize;

/**
 * 文字字号表 数据层
 *
 * @author 陈志辉
 */
public interface IWordSizeDao
{

    /**
     * 根据条件分页查询文字字号数据
     *
     * @param pageUtilEntity 分页对象
     * @return 角色数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过文字字号ID删除文字字号
     *
     * @param sizeId 文字字号ID
     * @return 结果
     */
    public int deleteWordSizeById(Long sizeId);


    /**
     * 批量删除文字字号信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWordSize(Long[] ids);


    /**
     * 通过文字字号ID查询文字字号
     *
     * @param sizeId 文字字号ID
     * @return 文字字号信息
     */

    public WordSize selectWordSizeById(Long sizeId);




    /**
     * 保存文字字号信息
     *
     * @param wordsize 文字字号信息
     * @return 结果
     */

    public int updateWordSize(WordSize wordsize);


    /**
     * 新增文字字号信息
     *
     * @param wordsize 文字字号信息
     * @return 结果
     */

    public int insertWordSize(WordSize wordsize);


}
