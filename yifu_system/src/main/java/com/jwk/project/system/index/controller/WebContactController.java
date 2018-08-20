package com.jwk.project.system.index.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebContact;
import com.jwk.project.system.index.service.IWebContactService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 联系我们 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/contact")
public class WebContactController extends BaseController {

    private String prefix = "system/web/contact";

    @Autowired
    private IWebContactService webContactService;

    /**
     *查询联系我们信息
     */
    @RequiresPermissions("system:contact:view")
    @GetMapping()
    public String SysWebcontact() {
        return prefix + "/contact";
    }





    /**
     *显示联系我们信息
     */
    @RequiresPermissions("system:contact:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = webContactService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 修改联系我们
     */
    @RequiresPermissions("system:contact:edit")
    @Log(title = "联系我们管理", action = "联系我们-修改联系我们")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        SysWebContact webContact = webContactService.selectSysWebContactById(id);
        model.addAttribute("webContact", webContact);
        return prefix + "/edit";
    }

    /**
     * 保存联系我们
     */
    @RequiresPermissions("system:contact:save")
    @Log(title = "联系我们管理", action = "联系我们-保存联系我们")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebContact webcontact) {
        if (webContactService.saveSysWebContact(webcontact) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }



}