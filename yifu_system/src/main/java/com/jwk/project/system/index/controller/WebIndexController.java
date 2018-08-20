package com.jwk.project.system.index.controller;


import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebIndex;
import com.jwk.project.system.index.service.WebIndexService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 首页图片
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/indexphoto")
public class WebIndexController extends BaseController
{	
    private String prefix = "system/web/indexphoto";

  
    @Autowired
    private WebIndexService webIndexService;



    /**
     *查询文字颜色
     */
    @RequiresPermissions("system:indexphoto:view")
    @GetMapping()
    public String indexphoto()
    {
        return prefix + "/indexphoto";
    }


    /**
     * 显示首页图片
     */
    @RequiresPermissions("system:indexphoto:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webIndexService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增首页图片
     */
    @RequiresPermissions("system:indexphoto:add")
    @Log(title = "系统管理", action = "首页图片管理-新增图片")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    
    
    /**
     * 保存首页图片
     */
    @RequiresPermissions("system:indexphoto:save")
    @Log(title = "系统管理", action = "首页图片管理-保存图片")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSON save(SysWebIndex webindex, @RequestParam("imageFile") MultipartFile imageFile){

		if(webIndexService.saveWebIndex(webindex,imageFile)>0){
        return JSON.ok();
		}
		return JSON.error("保存失败");
    }
    

    /**
     * 修改首页图片
     */
    @RequiresPermissions("system:indexphoto:edit")
    @Log(title = "系统管理", action = "首页图片管理-修改图片")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	SysWebIndex webindex = webIndexService.selectWebIndexById(id);
    	model.addAttribute("webindex", webindex);
        return prefix + "/edit";
    }
    
  
    @RequiresPermissions("system:indexphoto:remove")
    @Log(title = "系统管理", action = "首页图片管理-删除图片")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
        if (webIndexService.deleteWebIndexById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    

    @RequiresPermissions("system:indexphoto:batchRemove")
    @Log(title = "系统管理", action = "首页图片管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webIndexService.batchDeleteWebIndex(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  
 
}