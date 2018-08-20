package com.jwk.project.system.order.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.ExpressCompany;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 快递公司 数据层处理
 * 
 * @author system
 */
@Repository("companyDao")
public class CompanyDaoImpl extends DynamicObjectBaseDao implements CompanyDao
{

	 /**
     * 根据条件分页查询快递公司数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 快递公司数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
    	
        return this.findForList("SystemExpressCompanyMapper.pageInfoQuery", pageUtilEntity);
    }

   
    /**
     * 根据快递公司ID查询快递公司
     * 
     * @param
     * @return 快递公司列表
     */
    @Override
    public List<ExpressCompany> selectExpressCompanyAll()
    {
        List<ExpressCompany> ExpressCompanyList = null;
        try
        {
            ExpressCompanyList = this.findForList("SystemExpressCompanyMapper.selectExpressCompanyAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ExpressCompanyList;
    }

    /**
     * 通过快递公司ID查询快递公司
     * 
     * @param id 快递公司ID
     * @return 快递公司对象信息
     */
    @Override
    public ExpressCompany selectExpressCompanyById(Long id)
    {
        return this.findForObject("SystemExpressCompanyMapper.selectExpressCompanyById", id);
    }

    /**
     * 通过快递公司ID删除快递公司
     * 
     * @param id 快递公司ID
     * @return 结果
     */
    @Override
    public int deleteExpressCompanyById(Long id)
    {
        return this.delete("SystemExpressCompanyMapper.deleteExpressCompanyById", id);
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
        return this.delete("SystemExpressCompanyMapper.batchDeleteExpressCompany", ids);
    }

   
    /**
     * 保存快递公司信息
     * 
     * @param 
     * @return 结果
     */
    @Override
    public int updateExpressCompany(ExpressCompany expressCompany)
    {
        return this.update("SystemExpressCompanyMapper.updateExpressCompany", expressCompany);
    }

    /**
     * 新增快递公司信息
     * 
     * @param
     * @return 结果
     */
    @Override
    public int insertExpressCompany(ExpressCompany expressCompany)
    {
        return this.update("SystemExpressCompanyMapper.insertExpressCompany", expressCompany);
    }

    @Override
    public ExpressCompany selectExpressCompanyBycompany(ExpressCompany company) {
        return this.findForObject("SystemExpressCompanyMapper.selectExpressCompanyBycompany",company);
    }


}
