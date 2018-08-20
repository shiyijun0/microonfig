package com.jwk.project.system.menu.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.system.menu.domain.Menu;

/**
 * 菜单 数据层处理
 * 
 * @author system
 */
@Repository("menuDao")
public class MenuDaoImpl extends DynamicObjectBaseDao implements IMenuDao
{

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenusByUserId(Long userId)
    {
        List<Menu> menuList = null;
        try
        {
            menuList = this.findForList("SystemMenuMapper.selectMenusByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<String> selectPermsByUserId(Long userId)
    {
        List<String> menuList = null;
        try
        {
            menuList = this.findForList("SystemMenuMapper.selectPermsByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    @Override
    public List<String> selectMenuTree(Long roleId)
    {
        List<String> menuList = null;
        try
        {
            menuList = this.findForList("SystemMenuMapper.selectMenuTree", roleId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @Override
    public List<Menu> selectMenuAll()
    {
        List<Menu> menuList = null;
        try
        {
            menuList = this.findForList("SystemMenuMapper.selectMenuAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId)
    {
        return this.delete("SystemMenuMapper.deleteMenuById", menuId);
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public Menu selectMenuById(Long menuId)
    {
        return this.findForObject("SystemMenuMapper.selectMenuById", menuId);
    }

    /**
     * 新增菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu)
    {
        return this.save("SystemMenuMapper.insertMenu", menu);
    }

    /**
     * 修改菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu)
    {
        return this.update("SystemMenuMapper.updateMenu", menu);
    }

}
