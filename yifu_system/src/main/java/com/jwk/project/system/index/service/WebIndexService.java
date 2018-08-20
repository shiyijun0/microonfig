package com.jwk.project.system.index.service;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebIndex;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 业务层
 * 
 * @author system
 */
public interface WebIndexService
{

    /**
     * 根据条件分页查询数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);



    /**
     * 通过ID查询
     * 
     * @param id
     * @return 对象信息
     */
    public SysWebIndex selectWebIndexById(Long id);



    /**
     * 查询所有首页图片
     *
     * @return
     */
    public List<SysWebIndex> selectWebBannerAll();


    /**
     * 通过ID删除信息
     * 
     * @param id ID
     * @return 结果
     */
    public int deleteWebIndexById(Long id);

    /**
     * 批量删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteWebIndex(Long[] ids);

    /**
     * 保存信息
     * 
     * @param webIndex 信息
     * @return 结果
     */
    public int saveWebIndex(SysWebIndex webIndex,MultipartFile imageFile);

}
