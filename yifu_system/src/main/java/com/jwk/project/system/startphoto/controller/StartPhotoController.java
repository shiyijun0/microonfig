package com.jwk.project.system.startphoto.controller;

import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.startphoto.domain.StartPhoto;
import com.jwk.project.system.startphoto.service.IStartPhotoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 开机图片信息 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/startphoto")
public class StartPhotoController extends BaseController {

    private String prefix = "system/startphoto";

    @Autowired
    private IStartPhotoService startPhotoService;

    /**
     *查询开机图片
     */
    @RequiresPermissions("system:startphoto:view")
    @GetMapping()
    public String StartPhoto() {
        return prefix + "/startphoto";
    }

    /**
     *显示开机图片
     */
    @RequiresPermissions("system:startphoto:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = startPhotoService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增开机图片
     */
    @RequiresPermissions("system:startphoto:add")
    @Log(title = "开机图片管理", action = "开机图片-新增开机图片")
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 修改开机图片
     */
    @RequiresPermissions("system:startphoto:edit")
    @Log(title = "开机图片管理", action = "开机图片-修改开机图片")
    @GetMapping("/edit/{startPhotoId}")
    public String edit(@PathVariable("startPhotoId") Long startPhotoId, Model model){
        StartPhoto startphoto = startPhotoService.selectStartPhotoById(startPhotoId);
        model.addAttribute("startphoto", startphoto);
        return prefix + "/edit";
    }

    /**
     * 保存开机图片
     */
    @RequiresPermissions("system:startphoto:save")
    @Log(title = "开机图片管理", action = "开机图片-保存开机图片")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(StartPhoto startPhoto, @RequestParam("imageFile") MultipartFile imageFile) {
        if (startPhotoService.saveStartPhoto(startPhoto,imageFile) > 0) {
            return JSON.ok();
        }
        return JSON.error();

    }

    /**
     * 通过开机图片ID删除开机图片
     *
     * @param startPhotoId 开机图片ID
     * @return 结果
     */
    @RequiresPermissions("system:startphoto:remove")
    @Log(title = "开机图片管理", action = "开机图片-删除开机图片")
    @RequestMapping("/remove/{startPhotoId}")
    @ResponseBody
    public JSON remove(@PathVariable("startPhotoId") Long startPhotoId) {
        StartPhoto startPhoto = startPhotoService.selectStartPhotoById(startPhotoId);
        if (startPhoto == null) {
            return JSON.error("开机图片不存在");
        }
        if (startPhotoService.deleteStartPhotoById(startPhotoId) > 0) {
            return JSON.ok();
        }

        return JSON.error();
    }


    /**
     * 批量删除开机图片信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:startphoto:batchRemove")
    @Log(title = "开机图片管理", action = "开机图片-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
        int rows = startPhotoService.batchDeleteStartPhoto(ids);
        if (rows > 0) {
            return JSON.ok();
        }
        return JSON.error();
    }

}