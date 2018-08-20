package com.jwk.project.system.dept.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.system.dept.domain.Dept;

/**
 * 部门管理 数据实现层
 * 
 * @author system
 */
@Repository("deptDao")
public class DeptDaoImpl extends DynamicObjectBaseDao implements IDeptDao
{
    /**
     * 查询部门管理集合
     * 
     * @return 所有部门信息
     */
    @Override
    public List<Dept> selectDeptAll()
    {
        List<Dept> deptList = null;
        try
        {
            deptList = this.findForList("SystemDeptMapper.selectDeptAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return deptList;
    }

    /**
     * 查询部门人数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int selectDeptCount(Dept dept)
    {
        return this.count("SystemDeptMapper.selectDeptCount", dept);
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int checkDeptExistUser(Long deptId)
    {
        return this.count("SystemDeptMapper.checkDeptExistUser", deptId);
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId)
    {
        return this.delete("SystemDeptMapper.deleteDeptById", deptId);
    }

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(Dept dept)
    {
        return this.save("SystemDeptMapper.insertDept", dept);
    }

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(Dept dept)
    {
        return this.save("SystemDeptMapper.updateDept", dept);
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public Dept selectDeptById(Long deptId)
    {
        return this.findForObject("SystemDeptMapper.selectDeptById", deptId);
    }
}
