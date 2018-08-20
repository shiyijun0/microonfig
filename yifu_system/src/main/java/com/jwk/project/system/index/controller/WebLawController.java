package com.jwk.project.system.index.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebLaw;
import com.jwk.project.system.index.service.IWebLawService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 法律 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/law")
public class WebLawController extends BaseController {

    private String prefix = "system/web/law";

    @Autowired
    private IWebLawService webLawService;

    /**
     *查询法律信息
     */
    @RequiresPermissions("system:law:view")
    @GetMapping()
    public String SysWeblaw() {
        return prefix + "/law";
    }





    /**
     *显示法律信息
     */
    @RequiresPermissions("system:law:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = webLawService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 修改法律
     */
    @RequiresPermissions("system:law:edit")
    @Log(title = "法律管理", action = "法律-修改法律")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        SysWebLaw webLaw = webLawService.selectSysWebLawById(id);
        model.addAttribute("webLaw", webLaw);
        return prefix + "/edit";
    }

    /**
     * 保存法律
     */
    @RequiresPermissions("system:law:save")
    @Log(title = "法律管理", action = "法律-保存法律")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebLaw weblaw) {
        if (webLawService.saveSysWebLaw(weblaw) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }



}