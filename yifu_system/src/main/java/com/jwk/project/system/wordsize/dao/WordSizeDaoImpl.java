package com.jwk.project.system.wordsize.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordsize.domain.WordSize;
import org.springframework.stereotype.Repository;

/**
 * 文字字号 数据层处理
 * 
 * @author 陈志辉
 */
@Repository("wordSizeDao")
public class WordSizeDaoImpl extends DynamicObjectBaseDao implements IWordSizeDao
{

    /**
     * 根据条件分页查询文字字号数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文字字号数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemWordSizeMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过文字字号ID删除文字字号
     *
     * @param sizeId 文字字号ID
     * @return 结果
     */
    @Override
    public int deleteWordSizeById(Long sizeId)
    {
        return this.delete("SystemWordSizeMapper.deleteWordSizeById", sizeId);
    }

    /**
     * 批量删除文字字号信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWordSize(Long[] ids)
    {
        return this.delete("SystemWordSizeMapper.batchDeleteWordSize", ids);
    }


    /**
     * 通过文字字号ID查询文字字号
     *
     * @param sizeId 文字字号ID
     * @return 文字字号信息
     */
    @Override
    public WordSize selectWordSizeById(Long sizeId) {
        return this.findForObject("SystemWordSizeMapper.selectWordSizeById", sizeId);
    }


    /**
     * 保存文字字号信息
     *
     * @param wordsize 文字字号信息
     * @return 结果
     */
    @Override
    public int updateWordSize(WordSize wordsize)
    {
        return this.update("SystemWordSizeMapper.updateWordSize", wordsize);
    }

    /**
     * 新增文字字号信息
     *
     * @param wordsize 文字字号信息
     * @return 结果
     */
    @Override
    public int insertWordSize(WordSize wordsize)
    {
        return this.update("SystemWordSizeMapper.insertWordSize", wordsize);
    }


}
