package com.jwk.project.system.web.service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebLabelDao;
import com.jwk.project.system.web.domain.SysWebLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 标签业务层处理
 * 
 * @author system
 */
@Service("webLabelService")
public class WebLabelServiceImpl implements WebLabelService
{

    @Autowired
    private WebLabelDao webLabelDao;

    /**
     * 根据条件分页查询标签数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 标签数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webLabelDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有标签
     * 
     * @return 
     */
    @Override
    public List<SysWebLabel> selectSysWebLabelAll()
    {
        return webLabelDao.selectSysWebLabelAll();
    }

    /**
     * 通过iD查询标签
     * 
     * @param id 标签ID
     * @return 标签对象信息
     */
    @Override
    public SysWebLabel selectSysWebLabelById(Long id)
    {
        return webLabelDao.selectSysWebLabelById(id);
    }

    /**
     * 通过标签ID删除标签
     * 
     * @param id 标签iD
     * @return 结果
     */
    @Override
    public int deleteSysWebLabelById(Long id)
    {
        return webLabelDao.deleteSysWebLabelById(id);
    }

    /**
     * 批量删除标签信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysWebLabel(Long[] ids)
    {
        return webLabelDao.batchDeleteSysWebLabel(ids);
    }

    /**
     * 保存标签信息
     * 
     * @param SysWebLabel
     * @return 结果
     */
    @Override
    public int saveSysWebLabel(SysWebLabel SysWebLabel)
    {
    	 Integer id = SysWebLabel.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webLabelDao.updateSysWebLabel(SysWebLabel);
        }else{
        	return  webLabelDao.insertSysWebLabel(SysWebLabel);
        }
        
    }

   

}
