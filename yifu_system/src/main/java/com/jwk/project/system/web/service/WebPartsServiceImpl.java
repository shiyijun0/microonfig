package com.jwk.project.system.web.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebPartsDao;
import com.jwk.project.system.web.domain.SysWebParts;

/**
 *  业务层处理
 * 
 * @author system
 */
@Service("webPartsService")
public class WebPartsServiceImpl implements WebPartsService
{

    @Autowired
    private WebPartsDao webPartsDao;

    /**
     * 根据条件分页查询边线数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 边线数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return webPartsDao.pageInfoQuery(pageUtilEntity);
    }
    

    /**
     * 查询所有边线
     * 
     * @return 
     */
    @Override
    public List<SysWebParts> selectWebPartsAll(SysWebParts webParts)
    {
        return webPartsDao.selectWebPartsAll(webParts);
    }

    /**
     * 通过iD查询边线
     * 
     * @param id 边线ID
     * @return 边线对象信息
     */
    @Override
    public SysWebParts selectWebPartsById(Long id)
    {
        return webPartsDao.selectWebPartsById(id);
    }

    /**
     * 通过边线ID删除边线
     * 
     * @param id 边线iD
     * @return 结果
     */
    @Override
    public int deleteWebPartsById(Long id)
    {
        return webPartsDao.deleteWebPartsById(id);
    }

    /**
     * 批量删除边线信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteWebParts(Long[] ids)
    {
        return webPartsDao.batchDeleteWebParts(ids);
    }

    /**
     * 保存边线信息
     * 
     * @param WebParts
     * @return 结果
     */
    @Override
    public int saveWebParts(SysWebParts WebParts)
    {
    	Integer id = WebParts.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  webPartsDao.updateWebParts(WebParts);
        }else{
        	return webPartsDao.insertWebParts(WebParts);
        }
        
    }
 

}
