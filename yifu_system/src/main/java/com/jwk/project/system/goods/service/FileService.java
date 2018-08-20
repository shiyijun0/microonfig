package com.jwk.project.system.goods.service;

import java.util.List;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.JeansFile;
/**
 * 文件业务层
 * 
 * @author system
 */
public interface FileService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 文件数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有文件
     * 
     * @return 权限列表
     */
    public List<JeansFile> selectJeansFileAll();

    /**
     * 通过ID查询文件
     * 
     * @param roleId 文件ID
     * @return 文件对象信息
     */
    public JeansFile selectJeansFileById(Long id);
    
    /**
     * 通过path查询文件
     * 
     * @param path 文件path
     * @return 文件对象信息
     */
    public JeansFile selectJeansFileByPath(String path);

    /**
     * 通过文件ID删除文件信息
     * 
     * @param id 文件ID
     * @return 结果
     */
    public int deleteJeansFileById(Long id);

    /**
     * 批量删除文件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteJeansFile(Long[] ids);

    /**
     * 保存文件信息
     * 
     * @param JeansFile 文件信息
     * @return 结果
     */
    public int saveJeansFile(JeansFile jeansFile);

}