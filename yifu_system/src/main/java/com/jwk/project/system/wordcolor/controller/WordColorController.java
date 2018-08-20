package com.jwk.project.system.wordcolor.controller;


import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordcolor.domain.WordColor;
import com.jwk.project.system.wordcolor.service.IWordColorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 文字颜色  控制层
 * 
 * @author 陈志辉
 *
 */
@Controller
@RequestMapping("/system/wordcolor")
public class WordColorController extends BaseController
{

    private String prefix = "system/wordcolor";

    @Autowired
    private IWordColorService wordColorService;

    /**
     *查询文字颜色
     */
    @RequiresPermissions("system:word:color:view")
    @GetMapping()
    public String color()
    {
        return prefix + "/color";
    }

    /**
     *显示文字颜色
     */
    @RequiresPermissions("system:word:color:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = wordColorService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增文字颜色
     */
    @RequiresPermissions("system:word:color:add")
    @Log(title = "文字定制", action = "文字颜色-新增颜色")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改文字颜色
     */
    @RequiresPermissions("system:word:color:edit")
    @Log(title = "文字定制", action = "文字颜色-修改颜色")
    @GetMapping("/edit/{colorId}")
    public String edit(@PathVariable("colorId") Long colorId, Model model)
    {
        WordColor wordColor = wordColorService.selectWordColorById(colorId);
        model.addAttribute("wordColor", wordColor);
        return prefix + "/edit";
    }

    /**
     * 保存文字颜色
     */
    @RequiresPermissions("system:word:color:save")
    @Log(title = "文字定制", action = "文字颜色-保存颜色")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(WordColor wordColor)
    {
        if (wordColorService.saveWordColor(wordColor) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 通过文字颜色ID删除文字颜色
     */
    @RequiresPermissions("system:word:color:remove")
    @Log(title = "文字定制", action = "文字颜色-删除颜色")
    @RequestMapping("/remove/{colorId}")
    @ResponseBody
    public JSON remove(@PathVariable("colorId") Long colorId)
    {
        WordColor wordColor = wordColorService.selectWordColorById(colorId);
        if (wordColor == null)
        {
            return JSON.error("文字颜色不存在");
        }
        if (wordColorService.deleteWordColorById(colorId) > 0)
        {
            return JSON.ok();
        }
        
        return JSON.error();
    }


    /**
     * 批量删除文字颜色信息
     */
    @RequiresPermissions("system:word:color:batchRemove")
    @Log(title = "文字定制", action = "文字颜色-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = wordColorService.batchDeleteWordColor(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }



}