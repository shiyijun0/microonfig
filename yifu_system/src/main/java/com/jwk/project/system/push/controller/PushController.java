package com.jwk.project.system.push.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.push.domain.Push;
import com.jwk.project.system.push.service.IPushService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * APP消息推送信息 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/push")
public class PushController extends BaseController {

    private String prefix = "system/push";

    @Autowired
    private IPushService pushService;

    /**
     *查询APP消息推送
     */
    @RequiresPermissions("system:push:view")
    @GetMapping()
    public String Push() {
        return prefix + "/push";
    }

    /**
     *显示APP消息推送
     */
    @RequiresPermissions("system:push:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = pushService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增APP消息推送
     */
    @RequiresPermissions("system:push:add")
    @Log(title = "APP消息推送管理", action = "APP消息推送-新增推送")
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 修改APP消息推送
     */
    @RequiresPermissions("system:push:edit")
    @Log(title = "APP消息推送管理", action = "APP消息推送-修改推送")
    @GetMapping("/edit/{pushId}")
    public String edit(@PathVariable("pushId") Long pushId, Model model) {
        Push push = pushService.selectPushById(pushId);
        model.addAttribute("push", push);
        return prefix + "/edit";
    }

    /**
     * 保存APP消息推送
     */
    @RequiresPermissions("system:push:save")
    @Log(title = "APP消息推送管理", action = "APP消息推送-保存推送")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Push push) {
        if (pushService.savePush(push) > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }


    /**
     * 通过APP消息推送ID删除APP消息推送
     *
     * @param pushId APP消息推送ID
     * @return 结果
     */
    @RequiresPermissions("system:push:remove")
    @Log(title = "APP消息推送管理", action = "APP消息推送-删除推送")
    @RequestMapping("/remove/{pushId}")
    @ResponseBody
    public JSON remove(@PathVariable("pushId") Long pushId) {
        Push push = pushService.selectPushById(pushId);
        if (push == null) {
            return JSON.error("APP消息推送不存在");
        }
        if (pushService.deletePushById(pushId) > 0) {
            return JSON.ok();
        }

        return JSON.error();
    }


    /**
     * 批量删除APP消息推送信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:push:batchRemove")
    @Log(title = "APP消息推送管理", action = "APP消息推送-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
        int rows = pushService.batchDeletePush(ids);
        if (rows > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }

}