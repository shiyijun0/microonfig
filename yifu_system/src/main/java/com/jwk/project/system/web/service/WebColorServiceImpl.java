package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebColorDao;
import com.jwk.project.system.web.domain.SysWebColor;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webColorService")
public class WebColorServiceImpl implements WebColorService
{

    @Autowired
    private WebColorDao webColorDao;

    /**
     * 根据条件分页查询颜色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 颜色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webColorDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有颜色
     * 
     * @return 
     */
    @Override
    public List<SysWebColor> selectwebColorAll()
    {
        return webColorDao.selectwebColorAll();
    }

    /**
     * 查询所有颜色
     *
     * @return
     */
    @Override
    public List<SysWebColor> selectwebColorByColor(SysWebColor webColor)
    {
        return webColorDao.selectwebColorByColor(webColor);
    }

    /**
     * 通过iD查询颜色
     * 
     * @param id 颜色ID
     * @return 颜色对象信息
     */
    @Override
    public SysWebColor selectwebColorById(Long id)
    {
        return webColorDao.selectwebColorById(id);
    }

    /**
     * 通过颜色ID删除颜色
     * 
     * @param id 颜色iD
     * @return 结果
     */
    @Override
    public int deletewebColorById(Long id)
    {
        return webColorDao.deletewebColorById(id);
    }

    /**
     * 批量删除颜色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeletewebColor(Long[] ids)
    {
        return webColorDao.batchDeletewebColor(ids);
    }

    /**
     * 保存颜色信息
     * 
     * @param webColor
     * @return 结果
     */
    @Override
    public int savewebColor(SysWebColor webColor)
    {
    	Integer id = webColor.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webColorDao.updatewebColor(webColor);
        }else{
        	return webColorDao.insertwebColor(webColor);
        }
        
    }

   

}
