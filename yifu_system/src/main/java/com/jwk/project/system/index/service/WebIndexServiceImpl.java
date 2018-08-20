package com.jwk.project.system.index.service;

import com.jwk.common.utils.FileUtil;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.dao.WebIndexDao;
import com.jwk.project.system.index.domain.SysWebIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webIndexService")
public class WebIndexServiceImpl implements WebIndexService
{

    @Autowired
    private WebIndexDao webIndexDao;

    /**
     * 根据条件分页查询首页图片数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 首页图片数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webIndexDao.pageInfoQuery(pageUtilEntity);
    }
    


    /**
     * 通过iD查询首页图片
     * 
     * @param id 首页图片ID
     * @return 首页图片对象信息
     */
    @Override
    public SysWebIndex selectWebIndexById(Long id)
    {
        return webIndexDao.selectWebIndexById(id);
    }


    /**
     * 查询所有首页图片
     *
     * @return
     */
    public List<SysWebIndex> selectWebBannerAll()
    {
        return webIndexDao.selectWebIndexAll();
    }


    /**
     * 通过首页图片ID删除首页图片
     * 
     * @param id 首页图片iD
     * @return 结果
     */
    @Override
    public int deleteWebIndexById(Long id)
    {
        return webIndexDao.deleteWebIndexById(id);
    }

    /**
     * 批量删除首页图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebIndex(Long[] ids)
    {
        return webIndexDao.batchDeleteWebIndex(ids);
    }

    /**
     * 保存首页图片信息
     * 
     * @param webIndex
     * @return 结果
     */
    @Override
    public int saveWebIndex(SysWebIndex webIndex, MultipartFile imageFile)
    {
    	Long id = webIndex.getId();
        if(imageFile.getSize()!=0){
            String filename= FileUtil.fileInput(imageFile);
            webIndex.setImg(filename);
        }
        if (StringUtils.isNotNull(id))
        {
          return  webIndexDao.updateWebIndex(webIndex);
        }
        return webIndexDao.insertWebIndex(webIndex);
    }

   

}
