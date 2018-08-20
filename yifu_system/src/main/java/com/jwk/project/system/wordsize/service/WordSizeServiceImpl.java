package com.jwk.project.system.wordsize.service;


import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordsize.dao.IWordSizeDao;
import com.jwk.project.system.wordsize.domain.WordSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 文字字号 业务层处理
 *
 * @author 陈志辉
 */
@Service("wordSizeService")
public class WordSizeServiceImpl implements IWordSizeService
{

    @Autowired
    private IWordSizeDao wordSizeDao;

    /**
     * 根据条件分页查询文字字号数据
     *
     * @param pageUtilEntity 分页对象
     * @return 文字字号数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return wordSizeDao.pageInfoQuery(pageUtilEntity);
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
        return wordSizeDao.deleteWordSizeById(sizeId);
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
        return wordSizeDao.batchDeleteWordSize(ids);
    }



    /**
     * 通过文字字号ID查询文字字号
     *
     * @param sizeId 文字字号ID
     * @return 文字字号信息
     */
    @Override
    public WordSize selectWordSizeById(Long sizeId)
    {
        return wordSizeDao.selectWordSizeById(sizeId);
    }


    /**
     * 保存文字字号信息
     *
     * @param wordsize 文字字号信息
     * @return 结果
     */
    @Override
    public int saveWordSize(WordSize wordsize)
    {
        Long sizeId = wordsize.getSizeId();
        if (StringUtils.isNotNull(sizeId))
        {
            // 修改文字字号的信息
            return wordSizeDao.updateWordSize(wordsize);
        }
        return wordSizeDao.insertWordSize(wordsize);
    }




}
