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
import com.jwk.project.system.web.domain.SysWebPosition;
import com.jwk.project.system.web.service.WebPositionService;



/**
 * 衣服位置信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/position")
public class WebPositionController extends BaseController
{

    private String prefix = "system/web/position";

    @Autowired
    private WebPositionService webPositionService;
      
    @RequiresPermissions("system:position:view")
    @GetMapping()
    public String position()
    {
        return prefix + "/position";
    }

    /**
     * 显示衣服位置信息
     */
    @RequiresPermissions("system:position:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webPositionService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增衣服位置信息
     */
    @RequiresPermissions("system:position:add")
    @Log(title = "系统管理", action = "衣服位置管理-新增衣服位置")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 保存衣服位置信息
     */
    @RequiresPermissions("system:position:save")
    @Log(title = "系统管理", action = "衣服位置管理-保存衣服位置")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebPosition webPosition)
    {
        if (webPositionService.saveSysWebPosition(webPosition) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:position:remove")
    @Log(title = "系统管理", action = "衣服位置管理-删除衣服位置")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebPosition SysWebPosition = webPositionService.selectSysWebPositionById(id);
        if (SysWebPosition == null)
        {
            return JSON.error("该衣服位置不存在");
        }
        if (webPositionService.deleteSysWebPositionById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:position:batchRemove")
    @Log(title = "系统管理", action = "衣服位置管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webPositionService.batchDeleteSysWebPosition(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

 
}