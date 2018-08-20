package com.jwk.project.system.wordfont.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordfont.domain.WordFont;
import org.springframework.stereotype.Repository;

/**
 * 文字字体 数据层处理
 * 
 * @author 陈志辉
 */
@Repository("wordFontDao")
public class WordFontDaoImpl extends DynamicObjectBaseDao implements IWordFontDao
{

    /**
     * 根据条件分页查询文字字体数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文字字体数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemWordFontMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过文字字体ID删除文字字体
     *
     * @param fontId 文字字体ID
     * @return 结果
     */
    @Override
    public int deleteWordFontById(Long fontId)
    {
        return this.delete("SystemWordFontMapper.deleteWordFontById", fontId);
    }

    /**
     * 批量删除文字字体信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWordFont(Long[] ids)
    {
        return this.delete("SystemWordFontMapper.batchDeleteWordFont", ids);
    }


    /**
     * 通过文字字体ID查询文字字体
     *
     * @param fontId 文字字体ID
     * @return 文字字体信息
     */
    @Override
    public WordFont selectWordFontById(Long fontId) {
        return this.findForObject("SystemWordFontMapper.selectWordFontById", fontId);
    }


    /**
     * 保存文字字体信息
     *
     * @param wordfont 文字字体信息
     * @return 结果
     */
    @Override
    public int updateWordFont(WordFont wordfont)
    {
        return this.update("SystemWordFontMapper.updateWordFont", wordfont);
    }

    /**
     * 新增文字字体信息
     *
     * @param wordfont 文字字体信息
     * @return 结果
     */
    @Override
    public int insertWordFont(WordFont wordfont)
    {
        return this.update("SystemWordFontMapper.insertWordFont", wordfont);
    }


}
