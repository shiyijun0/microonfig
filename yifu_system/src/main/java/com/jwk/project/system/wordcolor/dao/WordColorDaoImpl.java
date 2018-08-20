package com.jwk.project.system.wordcolor.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordcolor.domain.WordColor;
import org.springframework.stereotype.Repository;


/**
 * 文字颜色 数据层处理
 * 
 * @author 陈志辉
 */
@Repository("wordColorDao")
public class WordColorDaoImpl extends DynamicObjectBaseDao implements IWordColorDao
{

    /**
     * 根据条件分页查询文字颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文字颜色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemWordColorMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过文字颜色ID删除字体颜色
     *
     * @param colorId 文字颜色ID
     * @return 结果
     */
    @Override
    public int deleteWordColorById(Long colorId)
    {
        return this.delete("SystemWordColorMapper.deleteWordColorById", colorId);
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
        return this.delete("SystemWordColorMapper.batchDeleteWordColor", ids);
    }


    /**
     * 通过文字颜色ID查询字体颜色
     *
     * @param colorId 文字颜色ID
     * @return 文字颜色信息
     */
    @Override
    public WordColor selectWordColorById(Long colorId) {
        return this.findForObject("SystemWordColorMapper.selectWordColorById", colorId);
    }


    /**
     * 保存文字颜色信息
     *
     * @param wordColor 文字颜色信息
     * @return 结果
     */
    @Override
    public int updateWordColor(WordColor wordColor)
    {
        return this.update("SystemWordColorMapper.updateWordColor", wordColor);
    }

    /**
     * 新增文字颜色信息
     *
     * @param wordColor 文字颜色信息
     * @return 结果
     */
    @Override
    public int insertWordColor(WordColor wordColor)
    {
        return this.update("SystemWordColorMapper.insertWordColor", wordColor);
    }


}
