package com.jwk.project.system.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.JeansFile;


/**
 * 文件 数据层处理
 * 
 * @author system
 */
@Repository("fileDao")
public class FileDaoImpl extends DynamicObjectBaseDao implements FileDao
{

	 /**
     * 根据条件分页查询文件数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文件数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemFileMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据文件ID查询文件
     * 
     * @param id 文件ID
     * @return 文件列表
     */
    @Override
    public List<JeansFile> selectJeansFileAll()
    {
        List<JeansFile> JeansFileList = null;
        try
        {
            JeansFileList = this.findForList("SystemFileMapper.selectJeansFileAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return JeansFileList;
    }

    /**
     * 通过文件ID查询文件
     * selectJeansFileByPath
     * @param id 文件ID
     * @return 文件对象信息
     */
    @Override
    public JeansFile selectJeansFileById(Long id)
    {
        return this.findForObject("SystemFileMapper.selectJeansFileById", id);
    }
    
    /**
     * 通过文件path查询文件
     * 
     * @param path 文件path
     * @return 文件对象信息
     */
    @Override
    public JeansFile selectJeansFileByPath(String path)
    {
        return this.findForObject("SystemFileMapper.selectJeansFileBySaveName", path);
    	/*List<JeansFile> JeansFileList = null;
        try
        {
            JeansFileList = this.findForList("SystemFileMapper.selectJeansFileByPath",path);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
       // return JeansFile;
    }

    /**
     * 通过文件ID删除文件
     * 
     * @param id 文件ID
     * @return 结果
     */
    @Override
    public int deleteJeansFileById(Long id)
    {
        return this.delete("SystemFileMapper.deleteJeansFileById", id);
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
        return this.delete("SystemFileMapper.batchDeleteJeansFile", ids);
    }

   
    /**
     * 保存文件信息
     * 
     * @param role 文件信息
     * @return 结果
     */
    @Override
    public int updateJeansFile(JeansFile jeansFile)
    {
        return this.update("SystemFileMapper.updateJeansFile", jeansFile);
    }

    /**
     * 新增文件信息
     * 
     * @param role 文件信息
     * @return 结果
     */
    @Override
    public int insertJeansFile(JeansFile jeansFile)
    {
        return this.update("SystemFileMapper.insertJeansFile", jeansFile);
    }

   
   
}
