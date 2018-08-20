package com.jwk.project.system.order.service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.dao.CompanyDao;
import com.jwk.project.system.order.domain.ExpressCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 快递公司业务层处理
 * 
 * @author system
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService
{

    @Autowired
    private CompanyDao companyDao;

    /**
     * 根据条件分页查询快递公司数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 快递公司数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return companyDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有快递公司
     * 
     * @return 
     */
    @Override
    public List<ExpressCompany> selectExpressCompanyAll()
    {
        return companyDao.selectExpressCompanyAll();
    }

    /**
     * 通过iD查询快递公司
     * 
     * @param id 快递公司ID
     * @return 快递公司对象信息
     */
    @Override
    public ExpressCompany selectExpressCompanyById(Long id)
    {
        return companyDao.selectExpressCompanyById(id);
    }

    @Override
    public ExpressCompany selectExpressCompanyBycompany(ExpressCompany company) {
        return companyDao.selectExpressCompanyBycompany(company);
    }

    /**
     * 通过快递公司ID删除快递公司
     * 
     * @param id 快递公司iD
     * @return 结果
     */
    @Override
    public int deleteExpressCompanyById(Long id)
    {
        return companyDao.deleteExpressCompanyById(id);
    }

    /**
     * 批量删除快递公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteExpressCompany(Long[] ids)
    {
        return companyDao.batchDeleteExpressCompany(ids);
    }

    /**
     * 保存快递公司信息
     * 
     * @param
     * @return 结果
     */
    @Override
    public int saveExpressCompany(ExpressCompany expressCompany)
    {
    	 Integer id = expressCompany.getId();
        if (StringUtils.isNotNull(id))
        {
           
          return  companyDao.updateExpressCompany(expressCompany);
        }else{
        	return  companyDao.insertExpressCompany(expressCompany);
        }
        
    }

   

}
