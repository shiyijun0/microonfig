package com.jwk.project.system.registeruser.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 注册用户信息
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/registeruser")
public class RegisterUserController extends BaseController {

    private String prefix = "system/registeruser";

    @Autowired
    private IRegisterUserService registerUserService;

    /**
     *查询注册用户信息
     */
    @RequiresPermissions("system:register:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    /**
     *显示注册用户信息
     */
    @RequiresPermissions("system:register:user:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = registerUserService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增用户
     */
    @RequiresPermissions("system:register:user:add")
    @Log(title = "注册用户管理", action = "用户-新增用户")
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:register:user:edit")
    @Log(title = "注册用户管理", action = "用户-修改用户")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        RegisterUser registerUser = registerUserService.selectRegisterUserById(id);
        model.addAttribute("registerUser", registerUser);
        return prefix + "/edit";
    }

    /**
     * 保存用户
     */
    @RequiresPermissions("system:register:user:save")
    @Log(title = "注册用户管理", action = "用户-保存用户")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(RegisterUser registerUser) {

        if (registerUserService.saveRegisterUser(registerUser) > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    @RequiresPermissions("system:register:user:remove")
    @Log(title = "注册用户管理", action = "用户-删除用户")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id) {
        RegisterUser registerUser = registerUserService.selectRegisterUserById(id);
        if (registerUser == null) {
            return JSON.error("用户不存在");
        }
        if (registerUserService.deleteRegisterUserById(id) > 0) {
            return JSON.ok();
        }

        return JSON.error();
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:register:user:batchRemove")
    @Log(title = "注册用户管理", action = "用户-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
        int rows = registerUserService.batchDeleteRegisterUser(ids);
        if (rows > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }


    /**
     * 校验手机号
     */
    @PostMapping("/checkMobileUnique")
    @ResponseBody
    public String checkMobileUnique(RegisterUser registerUser)
    {
        String uniqueFlag = "0";
        if (registerUser != null)
        {
            uniqueFlag = registerUserService.checkMobileUnique(registerUser.getMobile());
        }
        return uniqueFlag;
    }


}