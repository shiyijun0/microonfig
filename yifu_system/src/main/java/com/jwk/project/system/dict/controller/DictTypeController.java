package com.jwk.project.system.dict.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.dict.domain.DictType;
import com.jwk.project.system.dict.service.IDictService;

/**
 * 数据字典信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/dict")
public class DictTypeController extends BaseController
{
    private String prefix = "system/dict";

    @Autowired
    private IDictService dictService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dict()
    {
        return prefix + "/dictType";
    }

    @GetMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = dictService.pageInfoQueryDictType(getPageUtilEntity());
        return rows;
    }

    /**
     * 修改字典类型
     */
    @Log(title = "系统管理", action = "字典管理-修改字典类型")
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, Model model)
    {
        DictType dict = dictService.selectDictTypeById(dictId);
        model.addAttribute("dict", dict);
        return prefix + "/editDictType";
    }

    /**
     * 新增字典类型
     */
    @Log(title = "系统管理", action = "字典管理-新增字典类型")
    @RequiresPermissions("system:dict:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/addDictType";
    }

    /**
     * 保存字典类型
     */
    @Log(title = "系统管理", action = "字典管理-保存字典类型")
    @RequiresPermissions("system:dict:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(DictType dict)
    {
        if (dictService.saveDictType(dict) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @Log(title = "系统管理", action = "字典类型-批量删除")
    @RequiresPermissions("system:dict:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = dictService.batchDeleteDictType(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 查询字典详细
     */
    @Log(title = "系统管理", action = "字典管理-查询字典数据")
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, Model model)
    {
        DictType dict = dictService.selectDictTypeById(dictId);
        model.addAttribute("dict", dict);
        return prefix + "/dictData";
    }
    
    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public String checkDictTypeUnique(DictType dictType)
    {
        String uniqueFlag = "0";
        if (dictType != null)
        {
            uniqueFlag = dictService.checkDictTypeUnique(dictType);
        }
        return uniqueFlag;
    }
}
