package com.jwk.project.system.version.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.version.domain.Version;
import com.jwk.project.system.version.service.IVersionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 版本信息 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/version")
public class VersionController extends BaseController {

    private String prefix = "system/version";

    @Autowired
    private IVersionService versionService;

    /**
     *查询版本信息
     */
    @RequiresPermissions("system:version:view")
    @GetMapping()
    public String version() {
        return prefix + "/version";
    }

    /**
     *显示版本信息
     */
    @RequiresPermissions("system:version:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = versionService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 修改版本
     */
    @RequiresPermissions("system:version:edit")
    @Log(title = "版本管理", action = "版本-修改版本")
    @GetMapping("/edit/{versionId}")
    public String edit(@PathVariable("versionId") int versionId, Model model){
        Version version = versionService.selectVersionById(versionId);
        model.addAttribute("version", version);
        return prefix + "/edit";
    }

    /**
     * 保存版本
     */
    @RequiresPermissions("system:version:save")
    @Log(title = "版本管理", action = "版本-保存版本")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Version version) {
        if (versionService.saveVersion(version) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }



}