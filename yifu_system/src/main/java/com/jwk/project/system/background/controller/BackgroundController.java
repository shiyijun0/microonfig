package com.jwk.project.system.background.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.background.domain.Background;
import com.jwk.project.system.background.service.IBackgroundService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 推荐背景图信息
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/background")
public class BackgroundController extends BaseController {

    private String prefix = "system/background";

    @Autowired
    private IBackgroundService backgroundService;

    /**
     *查询背景图
     */
    @RequiresPermissions("system:background:view")
    @GetMapping()
    public String background() {
        return prefix + "/background";
    }

    /**
     *显示背景图
     */
    @RequiresPermissions("system:background:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = backgroundService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增推荐背景图
     */
    @RequiresPermissions("system:background:add")
    @Log(title = "推荐背景图管理", action = "推荐背景图-新增背景图")
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 修改推荐背景图
     */
    @RequiresPermissions("system:background:edit")
    @Log(title = "推荐背景图管理", action = "推荐背景图-修改背景图")
    @GetMapping("/edit/{backgroundId}")
    public String edit(@PathVariable("backgroundId") Long backgroundId, Model model){
        Background background = backgroundService.selectBackgroundById(backgroundId);
        model.addAttribute("background", background);
        return prefix + "/edit";
    }

    /**
     * 保存推荐背景图
     */
    @RequiresPermissions("system:background:save")
    @Log(title = "推荐背景图管理", action = "推荐背景图-保存背景图")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Background background, @RequestParam("imageFile") MultipartFile imageFile) {
        if (backgroundService.saveBackground(background,imageFile) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }


    /**
     * 通过推荐背景图ID删除推荐背景图
     *
     * @param backgroundId 推荐背景图ID
     * @return 结果
     */
    @RequiresPermissions("system:background:remove")
    @Log(title = "推荐背景图管理", action = "推荐背景图-删除背景图")
    @RequestMapping("/remove/{backgroundId}")
    @ResponseBody
    public JSON remove(@PathVariable("backgroundId") Long backgroundId) {
        Background background = backgroundService.selectBackgroundById(backgroundId);
        if (background == null) {
            return JSON.error("推荐背景图不存在");
        }
        if (backgroundService.deleteBackgroundById(backgroundId) > 0) {
            return JSON.ok();
        }

        return JSON.error();
    }


    /**
     * 批量删除推荐背景图信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:background:batchRemove")
    @Log(title = "推荐背景图管理", action = "推荐背景图-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
        int rows = backgroundService.batchDeleteBackground(ids);
        if (rows > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }

}