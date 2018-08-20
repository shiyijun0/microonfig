package com.jwk.project.system.wordfont.controller;


import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.wordfont.domain.WordFont;
import com.jwk.project.system.wordfont.service.IWordFontService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 文字字体 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/wordfont")
public class WordFontController extends BaseController
{

    private String prefix = "system/wordfont";

    @Autowired
    private IWordFontService wordFontService;

    /**
     * 查询文字字体
     */
    @RequiresPermissions("system:word:font:view")
    @GetMapping()
    public String font()
    {
        return prefix + "/font";
    }


    /**
     * 显示文字字体
     */
    @RequiresPermissions("system:word:font:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = wordFontService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增文字字体
     */
    @RequiresPermissions("system:word:font:add")
    @Log(title = "文字定制", action = "文字字体-新增字体")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改文字字体
     */
    @RequiresPermissions("system:word:font:edit")
    @Log(title = "文字定制", action = "文字字体-修改字体")
    @GetMapping("/edit/{fontId}")
    public String edit(@PathVariable("fontId") Long fontId, Model model)
    {
        WordFont wordfont = wordFontService.selectWordFontById(fontId);
        model.addAttribute("wordfont", wordfont);
        return prefix + "/edit";
    }

    /**
     * 保存文字字体
     */
    @RequiresPermissions("system:word:font:save")
    @Log(title = "文字定制", action = "文字字体-保存字体")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(WordFont wordfont)
    {
        if (wordFontService.saveWordFont(wordfont) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 通过文字字体ID删除文字字体
     *
     * @param fontId 文字字体ID
     * @return 结果
     */
    @RequiresPermissions("system:word:font:remove")
    @Log(title = "文字定制", action = "文字字体-删除字体")
    @RequestMapping("/remove/{fontId}")
    @ResponseBody
    public JSON remove(@PathVariable("fontId") Long fontId)
    {
        WordFont wordfont = wordFontService.selectWordFontById(fontId);
        if (wordfont == null)
        {
            return JSON.error("文字字体不存在");
        }
        if (wordFontService.deleteWordFontById(fontId) > 0)
        {
            return JSON.ok();
        }

        return JSON.error();
    }

    /**
     * 批量删除文字字体信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:word:font:batchRemove")
    @Log(title = "文字定制", action = "文字字体-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = wordFontService.batchDeleteWordFont(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }




}