package com.jwk.project.system.goods.controller;


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
import com.jwk.project.system.goods.domain.SysFq;
import com.jwk.project.system.goods.service.PartitionService;


/**
 * 分区信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/goodfq")
public class SysFqController extends BaseController
{

    private String prefix = "system/goodfq";

    @Autowired
    private PartitionService partitionService;
      
    @RequiresPermissions("system:goodfq:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/goodfq";
    }

    /**
     * 显示分区信息
     */
    @RequiresPermissions("system:goodfq:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = partitionService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增分区信息
     */
    @RequiresPermissions("system:goodfq:add")
    @Log(title = "系统管理", action = "分区管理-新增分区")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 保存分区信息
     */
    @RequiresPermissions("system:goodfq:save")
    @Log(title = "系统管理", action = "分区管理-保存分区")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysFq sysFq)
    {
        if (partitionService.saveSysFq(sysFq) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:goodfq:remove")
    @Log(title = "系统管理", action = "分区管理-删除分区")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysFq SysFq = partitionService.selectSysFqById(id);
        if (SysFq == null)
        {
            return JSON.error("该分区不存在");
        }
        if (partitionService.deleteSysFqById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:goodfq:batchRemove")
    @Log(title = "系统管理", action = "分区管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = partitionService.batchDeleteSysFq(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

 
}