package com.jwk.project.system.role.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.role.domain.Role;
import com.jwk.project.system.role.domain.RoleMenu;

/**
 * 角色 数据层处理
 * 
 * @author system
 */
@Repository("roleDao")
public class RoleDaoImpl extends DynamicObjectBaseDao implements IRoleDao
{

    /**
     * 根据条件分页查询角色数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 角色数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemRoleMapper.pageInfoQuery", pageUtilEntity);
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId)
    {
        List<Role> roleList = null;
        try
        {
            roleList = this.findForList("SystemRoleMapper.selectRolesByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesAll()
    {
        List<Role> roleList = null;
        try
        {
            roleList = this.findForList("SystemRoleMapper.selectRolesAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public Role selectRoleById(Long roleId)
    {
        return this.findForObject("SystemRoleMapper.selectRoleById", roleId);
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleById(Long roleId)
    {
        return this.delete("SystemRoleMapper.deleteRoleById", roleId);
    }

    /**
     * 批量角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteRole(Long[] ids)
    {
        return this.delete("SystemRoleMapper.batchDeleteRole", ids);
    }

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleMenuByRoleId(Long roleId)
    {
        return this.delete("SystemRoleMenuMapper.deleteRoleMenuByRoleId", roleId);
    }

    /**
     * 保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        return this.update("SystemRoleMapper.updateRole", role);
    }

    /**
     * 新增角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        return this.update("SystemRoleMapper.insertRole", role);
    }

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    @Override
    public int batchRoleMenu(List<RoleMenu> roleMenuList)
    {
        return this.batchSave("SystemRoleMenuMapper.batchRoleMenu", roleMenuList);
    }
}
