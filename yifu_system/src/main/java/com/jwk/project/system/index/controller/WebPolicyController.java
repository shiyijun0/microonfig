package com.jwk.project.system.index.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebPolicy;
import com.jwk.project.system.index.service.IWebPolicyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 退换货政策信息 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/policy")
public class WebPolicyController extends BaseController {

    private String prefix = "system/web/policy";

    @Autowired
    private IWebPolicyService webPolicyService;

    /**
     *查询退换货政策信息
     */
    @RequiresPermissions("system:policy:view")
    @GetMapping()
    public String SysWebPolicy() {
        return prefix + "/policy";
    }





    /**
     *显示退换货政策信息
     */
    @RequiresPermissions("system:policy:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = webPolicyService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 修改退换货政策
     */
    @RequiresPermissions("system:policy:edit")
    @Log(title = "退换货政策管理", action = "退换货政策-修改退换货政策")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        SysWebPolicy webPolicy = webPolicyService.selectSysWebPolicyById(id);
        model.addAttribute("webPolicy", webPolicy);
        return prefix + "/edit";
    }

    /**
     * 保存退换货政策
     */
    @RequiresPermissions("system:policy:save")
    @Log(title = "退换货政策管理", action = "退换货政策-保存退换货政策")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebPolicy webPolicy) {
        if (webPolicyService.saveSysWebPolicy(webPolicy) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }



}