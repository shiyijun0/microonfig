package com.jwk.project.system.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.dao.FileDao;
import com.jwk.project.system.goods.domain.JeansFile;


/**
 * 文件      业务层处理
 * 
 * @author system
 */
@Service("fileService")
public class FileServiceImpl implements FileService
{

    @Autowired
    private FileDao fileDao;

    /**
     * 根据条件分页查询文件数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文件数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return fileDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有文件
     * 
     * @return 
     */
    @Override
    public List<JeansFile> selectJeansFileAll()
    {
        return fileDao.selectJeansFileAll();
    }

    /**
     * 通过iD查询文件
     * 
     * @param id 文件ID
     * @return 文件对象信息
     */
    @Override
    public JeansFile selectJeansFileById(Long id)
    {
        return fileDao.selectJeansFileById(id);
    }
    
    /**
     * 通过path查询文件
     * 
     * @param path 文件path
     * @return 文件对象信息
     */
    @Override
    public JeansFile selectJeansFileByPath(String path)
    {
        return fileDao.selectJeansFileByPath(path);
    }

    /**
     * 通过文件ID删除文件
     * 
     * @param id 文件iD
     * @return 结果
     */
    @Override
    public int deleteJeansFileById(Long id)
    {
        return fileDao.deleteJeansFileById(id);
    }

    /**
     * 批量删除文件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteJeansFile(Long[] ids)
    {
        return fileDao.batchDeleteJeansFile(ids);
    }

    /**
     * 保存文件信息
     * 
     * @param JeansFile
     * @return 结果
     */
    @Override
    public int saveJeansFile(JeansFile jeansFile)
    {
        
        if (StringUtils.isNotNull(jeansFile))
        {
           
          return  fileDao.insertJeansFile(jeansFile);
        }
        return 0;
    }

   

}
