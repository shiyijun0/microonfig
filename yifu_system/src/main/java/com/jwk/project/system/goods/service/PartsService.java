package com.jwk.project.system.goods.service;

import java.util.List;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.SysPj;
/**
 * 配件业务层
 * 
 * @author system
 */
public interface PartsService
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 配件数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

   

    /**
     * 查询所有配件
     * 
     * @return 权限列表
     */
    public List<SysPj> selectSysPjAll();

    /**
     * 通过ID查询配件
     * 
     * @param roleId 配件ID
     * @return 配件对象信息
     */
    public SysPj selectSysPjById(Long id);

    /**
     * 通过配件ID删除配件信息
     * 
     * @param id 配件ID
     * @return 结果
     */
    public int deleteSysPjById(Long id);

    /**
     * 批量删除配件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteSysPj(Long[] ids);

    /**
     * 保存配件信息
     * 
     * @param SysPj 配件信息
     * @return 结果
     */
    public int saveSysPj(SysPj sysPj);

}
