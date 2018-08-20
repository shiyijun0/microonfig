package com.jwk.project.system.wordcolor.service;


import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordcolor.dao.IWordColorDao;
import com.jwk.project.system.wordcolor.domain.WordColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 文字颜色 业务层处理
 * 
 * @author 陈志辉
 */
@Service("wordColorService")
public class WordColorServiceImpl implements IWordColorService
{

    @Autowired
    private IWordColorDao wordColorDao;

    /**
     * 根据条件分页查询文字颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文字颜色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return wordColorDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过文字颜色ID删除文字颜色
     *
     * @param colorId 文字颜色ID
     * @return 结果
     */
    @Override
    public int deleteWordColorById(Long colorId)
    {
        return wordColorDao.deleteWordColorById(colorId);
    }

    /**
     * 批量删除文字颜色信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWordColor(Long[] ids)
    {
        return wordColorDao.batchDeleteWordColor(ids);
    }



    /**
     * 通过文字颜色ID查询文字颜色
     *
     * @param colorId 文字颜色ID
     * @return 文字颜色信息
     */
    @Override
    public WordColor selectWordColorById(Long colorId)
    {
        return wordColorDao.selectWordColorById(colorId);
    }


    /**
     * 保存文字颜色信息
     *
     * @param wordColor 文字颜色信息
     * @return 结果
     */
    @Override
    public int saveWordColor(WordColor wordColor)
    {
        Long colorId = wordColor.getColorId();
        if (StringUtils.isNotNull(colorId))
        {
            // 修改文字颜色的信息
            return wordColorDao.updateWordColor(wordColor);
        }
        return wordColorDao.insertWordColor(wordColor);
    }




}
