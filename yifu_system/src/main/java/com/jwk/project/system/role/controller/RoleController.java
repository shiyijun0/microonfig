package com.jwk.project.system.role.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.role.domain.Role;
import com.jwk.project.system.role.service.IRoleService;

/**
 * 角色信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController
{

    private String prefix = "system/role";

    @Autowired
    private IRoleService roleService;
    
    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = roleService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增角色
     */
    @RequiresPermissions("system:role:add")
    @Log(title = "系统管理", action = "角色管理-新增角色")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "系统管理", action = "角色管理-修改角色")
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, Model model)
    {
        Role role = roleService.selectRoleById(roleId);
        model.addAttribute("role", role);
        return prefix + "/edit";
    }

    /**
     * 保存角色
     */
    @RequiresPermissions("system:role:save")
    @Log(title = "系统管理", action = "角色管理-保存角色")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Role role)
    {
        if (roleService.saveRole(role) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:role:remove")
    @Log(title = "系统管理", action = "角色管理-删除角色")
    @RequestMapping("/remove/{roleId}")
    @ResponseBody
    public JSON remove(@PathVariable("roleId") Long roleId)
    {
        Role role = roleService.selectRoleById(roleId);
        if (role == null)
        {
            return JSON.error("角色不存在");
        }
        if (roleService.deleteRoleById(roleId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:role:batchRemove")
    @Log(title = "系统管理", action = "角色管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = roleService.batchDeleteRole(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree()
    {
        return prefix + "/tree";
    }

}