package com.jwk.project.system.message.controller;


import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.message.domain.Message;
import com.jwk.project.system.message.service.IMessageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 系统消息信息
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/message")
public class MessageController extends BaseController {

    private String prefix = "system/message";

    @Autowired
    private IMessageService messageService;

    /**
     *查询系统消息
     */
    @RequiresPermissions("system:message:view")
    @GetMapping()
    public String Message() {
        return prefix + "/message";
    }

    /**
     *显示系统消息
     */
    @RequiresPermissions("system:message:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = messageService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增系统消息
     */
    @RequiresPermissions("system:message:add")
    @Log(title = "系统消息管理", action = "系统消息-新增消息")
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 修改系统消息
     */
    @RequiresPermissions("system:message:edit")
    @Log(title = "系统消息管理", action = "系统消息-修改消息")
    @GetMapping("/edit/{messageId}")
    public String edit(@PathVariable("messageId") Long messageId, Model model){
        Message message = messageService.selectMessageById(messageId);
        model.addAttribute("message", message);
        return prefix + "/edit";
    }

    /**
     * 保存系统消息
     */
    @RequiresPermissions("system:message:save")
    @Log(title = "系统消息管理", action = "系统消息-保存消息")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Message message, @RequestParam("imageFile") MultipartFile imageFile) {
        if (messageService.saveMessage(message,imageFile) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }

    /**
     * 通过系统消息ID删除系统消息
     *
     * @param messageId 系统消息ID
     * @return 结果
     */
    @RequiresPermissions("system:message:remove")
    @Log(title = "系统消息管理", action = "系统消息-删除消息")
    @RequestMapping("/remove/{messageId}")
    @ResponseBody
    public JSON remove(@PathVariable("messageId") Long messageId) {
        Message message = messageService.selectMessageById(messageId);
        if (message == null) {
            return JSON.error("系统消息不存在");
        }
        if (messageService.deleteMessageById(messageId) > 0) {
            return JSON.ok();
        }

        return JSON.error();
    }


    /**
     * 批量删除系统消息信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:message:batchRemove")
    @Log(title = "系统消息管理", action = "系统消息-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
        int rows = messageService.batchDeleteMessage(ids);
        if (rows > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }

}