package com.jwk.project.system.order.service;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.order.domain.ExpressCompany;

import java.util.List;

/**
 * 快递公司业务层
 * 
 * @author system
 */
public interface CompanyService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 快递公司数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有快递公司
     * 
     * @return 权限列表
     */
    public List<ExpressCompany> selectExpressCompanyAll();

    /**
     * 通过ID查询快递公司
     * 
     * @param
     * @return 快递公司对象信息
     */
    public ExpressCompany selectExpressCompanyById(Long id);


    /**
     * 通过ID查询快递公司
     *
     * @param
     * @return 快递公司对象信息
     */
    public ExpressCompany selectExpressCompanyBycompany(ExpressCompany company);

    /**
     * 通过快递公司ID删除快递公司信息
     * 
     * @param id 快递公司ID
     * @return 结果
     */
    public int deleteExpressCompanyById(Long id);

    /**
     * 批量删除快递公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteExpressCompany(Long[] ids);

    /**
     * 保存快递公司信息
     * 
     * @param
     * @return 结果
     */
    public int saveExpressCompany(ExpressCompany expressCompany);

}
