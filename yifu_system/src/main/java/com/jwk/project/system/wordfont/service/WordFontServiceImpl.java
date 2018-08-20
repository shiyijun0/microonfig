package com.jwk.project.system.wordfont.service;


import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordfont.dao.IWordFontDao;
import com.jwk.project.system.wordfont.domain.WordFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 文字字体 业务层处理
 *
 * @author 陈志辉
 */
@Service("wordFontService")
public class WordFontServiceImpl implements IWordFontService
{

    @Autowired
    private IWordFontDao wordFontDao;

    /**
     * 根据条件分页查询文字字体数据
     *
     * @param pageUtilEntity 分页对象
     * @return 文字字体数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return wordFontDao.pageInfoQuery(pageUtilEntity);
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
        return wordFontDao.deleteWordFontById(fontId);
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
        return wordFontDao.batchDeleteWordFont(ids);
    }



    /**
     * 通过文字字体ID查询文字字体
     *
     * @param fontId 文字字体ID
     * @return 文字字体信息
     */
    @Override
    public WordFont selectWordFontById(Long fontId)
    {
        return wordFontDao.selectWordFontById(fontId);
    }


    /**
     * 保存文字字体信息
     *
     * @param wordfont 文字字体信息
     * @return 结果
     */
    @Override
    public int saveWordFont(WordFont wordfont)
    {
        Long fontId = wordfont.getFontId();
        if (StringUtils.isNotNull(fontId))
        {
            // 修改文字字体的信息
            return wordFontDao.updateWordFont(wordfont);
        }
        return wordFontDao.insertWordFont(wordfont);
    }




}
