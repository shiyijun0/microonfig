package com.jwk.project.system.web.controller;


import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebLabel;
import com.jwk.project.system.web.service.WebLabelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 标签信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/label")
public class WebLabelController extends BaseController
{

    private String prefix = "system/web/label";

    @Autowired
    private WebLabelService webLabelService;
      
    @RequiresPermissions("system:label:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/label";
    }

    /**
     * 显示标签信息
     */
    @RequiresPermissions("system:label:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webLabelService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增标签信息
     */
    @RequiresPermissions("system:label:add")
    @Log(title = "系统管理", action = "标签管理-新增标签")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 保存标签信息
     */
    @RequiresPermissions("system:label:save")
    @Log(title = "系统管理", action = "标签管理-保存标签")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebLabel SysWebLabel)
    {
        if (webLabelService.saveSysWebLabel(SysWebLabel) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:label:remove")
    @Log(title = "系统管理", action = "标签管理-删除标签")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebLabel SysWebLabel = webLabelService.selectSysWebLabelById(id);
        if (SysWebLabel == null)
        {
            return JSON.error("该标签不存在");
        }
        if (webLabelService.deleteSysWebLabelById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:label:batchRemove")
    @Log(title = "系统管理", action = "标签管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webLabelService.batchDeleteSysWebLabel(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    //显示数库名称位置
    @RequiresPermissions("system:label:save")
    @Log(title = "系统管理", action = "标签管理-显示标签名称")
    @RequestMapping(value = "/labelList", method = RequestMethod.POST)
    @ResponseBody
    public List<SysWebLabel> STATIC_LIST_TYPE(HttpServletRequest request) {
        //显示素库名称
        List<SysWebLabel>  webLabelList=webLabelService.selectSysWebLabelAll();
        return  webLabelList;
    }
}