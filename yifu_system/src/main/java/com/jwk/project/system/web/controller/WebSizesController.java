package com.jwk.project.system.web.controller;


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
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.service.WebSizesService;


/**
 * 尺寸信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/sizes")
public class WebSizesController extends BaseController
{

    private String prefix = "system/web/sizes";

    @Autowired
    private WebSizesService webSizesService;
      
    @RequiresPermissions("system:sizes:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/sizes";
    }

    /**
     * 显示尺寸信息
     */
    @RequiresPermissions("system:sizes:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webSizesService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增尺寸信息
     */
    @RequiresPermissions("system:sizes:add")
    @Log(title = "系统管理", action = "尺寸管理-新增尺寸")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 保存尺寸信息
     */
    @RequiresPermissions("system:sizes:save")
    @Log(title = "系统管理", action = "尺寸管理-保存尺寸")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebSizes sysWebSizes)
    {
        if (webSizesService.saveSysWebSizes(sysWebSizes) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:sizes:remove")
    @Log(title = "系统管理", action = "尺寸管理-删除尺寸")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebSizes SysWebSizes = webSizesService.selectSysWebSizesById(id);
        if (SysWebSizes == null)
        {
            return JSON.error("该尺寸不存在");
        }
        if (webSizesService.deleteSysWebSizesById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:sizes:batchRemove")
    @Log(title = "系统管理", action = "尺寸管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webSizesService.batchDeleteSysWebSizes(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

 
}