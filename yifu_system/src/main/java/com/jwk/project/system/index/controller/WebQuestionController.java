package com.jwk.project.system.index.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.index.domain.SysWebQuestion;
import com.jwk.project.system.index.service.IWebQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 常见问题解答信息 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/question")
public class WebQuestionController extends BaseController {

    private String prefix = "system/web/question";

    @Autowired
    private IWebQuestionService webQuestionService;

    /**
     *查询常见问题解答信息
     */
    @RequiresPermissions("system:question:view")
    @GetMapping()
    public String SysWebQuestion() {
        return prefix + "/question";
    }





    /**
     *显示常见问题解答信息
     */
    @RequiresPermissions("system:question:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = webQuestionService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 修改常见问题解答
     */
    @RequiresPermissions("system:question:edit")
    @Log(title = "常见问题解答管理", action = "常见问题解答-修改常见问题解答")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        SysWebQuestion webquestion = webQuestionService.selectSysWebQuestionById(id);
        model.addAttribute("webQuestion", webquestion);
        return prefix + "/edit";
    }

    /**
     * 保存常见问题解答
     */
    @RequiresPermissions("system:question:save")
    @Log(title = "常见问题解答管理", action = "常见问题解答-保存常见问题解答")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysWebQuestion webQuestion) {
        if (webQuestionService.saveSysWebQuestion(webQuestion) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }



}