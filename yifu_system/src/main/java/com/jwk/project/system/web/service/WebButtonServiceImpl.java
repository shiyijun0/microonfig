package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebButtonDao;
import com.jwk.project.system.web.domain.SysWebButton;



/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webButtonService")
public class WebButtonServiceImpl implements WebButtonService
{

    @Autowired
    private WebButtonDao webButtonDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webButtonDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<SysWebButton> selectwebColorByButton(SysWebButton webButton)
    {
        return webButtonDao.selectwebColorByButton(webButton);
    }

    /**
     * 查询所有边线
     *
     * @return
     */
    @Override
    public List<SysWebButton> selectWebButtonAll()
    {
        return webButtonDao.selectWebButtonAll();
    }

    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebButton selectWebButtonById(Long id)
    {
        return webButtonDao.selectWebButtonById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deleteWebButtonById(Long id)
    {
        return webButtonDao.deleteWebButtonById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebButton(Long[] ids)
    {
        return webButtonDao.batchDeleteWebButton(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param WebButton
     * @return 结果
     */
    @Override
    public int saveWebButton(SysWebButton webButton)
    {
    	Integer id = webButton.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webButtonDao.updateWebButton(webButton);
        }else{
        	return webButtonDao.insertWebButton(webButton);
        }
        
    }

   

}
