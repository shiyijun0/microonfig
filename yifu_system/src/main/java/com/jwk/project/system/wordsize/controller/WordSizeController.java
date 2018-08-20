package com.jwk.project.system.wordsize.controller;


import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordsize.domain.WordSize;
import com.jwk.project.system.wordsize.service.IWordSizeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 文字字号 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/wordsize")
public class WordSizeController extends BaseController
{

    private String prefix = "system/wordsize";

    @Autowired
    private IWordSizeService wordsizeService;

    /**
     *查询文字字号
     */
    @RequiresPermissions("system:word:size:view")
    @GetMapping()
    public String size()
    {
        return prefix + "/size";
    }

    /**
     *显示文字字号
     */
    @RequiresPermissions("system:word:size:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = wordsizeService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增文字字号
     */
    @RequiresPermissions("system:word:size:add")
    @Log(title = "文字定制", action = "文字字号-新增字号")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改文字字号
     */
    @RequiresPermissions("system:word:size:edit")
    @Log(title = "文字定制", action = "文字字号-修改字号")
    @GetMapping("/edit/{sizeId}")
    public String edit(@PathVariable("sizeId") Long sizeId, Model model)
    {
        WordSize wordsize = wordsizeService.selectWordSizeById(sizeId);
        model.addAttribute("wordsize", wordsize);
        return prefix + "/edit";
    }

    /**
     * 保存文字字号
     */
    @RequiresPermissions("system:word:size:save")
    @Log(title = "文字定制", action = "文字字号-保存字号")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(WordSize wordsize)
    {
        if (wordsizeService.saveWordSize(wordsize) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }


    /**
     * 通过文字字号ID删除文字字号
     *
     * @param sizeId 文字字号ID
     * @return 结果
     */

    @RequiresPermissions("system:word:size:remove")
    @Log(title = "文字定制", action = "文字字号-删除字号")
    @RequestMapping("/remove/{sizeId}")
    @ResponseBody
    public JSON remove(@PathVariable("sizeId") Long sizeId)
    {
        WordSize wordsize = wordsizeService.selectWordSizeById(sizeId);
        if (wordsize == null)
        {
            return JSON.error("文字字号不存在");
        }
        if (wordsizeService.deleteWordSizeById(sizeId) > 0)
        {
            return JSON.ok();
        }

        return JSON.error();
    }

    /**
     * 批量删除文字字号信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */

    @RequiresPermissions("system:word:size:batchRemove")
    @Log(title = "文字定制", action = "文字字号-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = wordsizeService.batchDeleteWordSize(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }




}