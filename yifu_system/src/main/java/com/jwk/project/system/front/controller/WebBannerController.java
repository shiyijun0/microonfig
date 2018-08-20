package com.jwk.project.system.front.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jwk.common.constant.FileUtil;
import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.front.domain.SysWebBanner;
import com.jwk.project.system.front.service.WebBannerService;
import com.jwk.project.system.web.domain.SysWebDesigner;
import com.jwk.project.system.web.service.WebDesignerService;



/**
 * 轮播图
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/banner")
public class WebBannerController extends BaseController
{	
    private String prefix = "system/web/banner";

  
    @Autowired
    private WebBannerService webBannerService;
    
    @Autowired
    private WebDesignerService webDesignerService;
    
    
    @RequiresPermissions("system:banner:view")
    @GetMapping()
    public String banner(Model model)
    {
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/banner";
    }

    /**
     * 显示轮播图
     */
    @RequiresPermissions("system:banner:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webBannerService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增轮播图
     */
    @RequiresPermissions("system:banner:add")
    @Log(title = "系统管理", action = "颜色管理-新增轮播图")
    @GetMapping("/add")
    public String add(Model model)
    {
    	//显示成品库名称
  List<SysWebDesigner>  webDesignerList=webDesignerService.selectWebDesignerAll();   	
  model.addAttribute("webDesignerList", webDesignerList);
        return prefix + "/add";
    }
    
    
    /**
     * 保存轮播图
     */
    @RequiresPermissions("system:banner:save")
    @Log(title = "系统管理", action = "颜色管理-保存轮播图") 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSON save(SysWebBanner WebBanner,HttpServletRequest request){
    	
    	 // 转型为MultipartHttpRequest：   
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        // 获得文件：   
        List<MultipartFile> files = multipartRequest.getFiles("file1");
        
        
		
		 if(StringUtils.isNotNull(WebBanner.getId())){
	        	
	        	if(!files.isEmpty()){
	        		String cover=this.uploadFileName(files);
	        		if(StringUtils.isNotEmpty(cover)){
	        			WebBanner.setBannerImgs(cover);
	        		}
	        	}
	        	
	        	
	        }else{

	        	 if (files.isEmpty()  ) {
	                 return JSON.error("上传的图片不能为空");
	             }
	             
	           //  System.out.println("**文件数****"+files.size());
	     		String cover=this.uploadFileName(files);
	     		
	     		if(StringUtils.isNotEmpty(cover)){
	       			WebBanner.setBannerImgs(cover);
	     		}
	     		
	        
	        }
	        
		
		
		//插入图片
		if(webBannerService.saveWebBanner(WebBanner)>0){
		
        return JSON.ok();
		}
		return JSON.error("上传轮播图失败");
    }
    
   //写入上传文件储存工具类
    public String uploadFileName(List<MultipartFile> files ){
    	System.out.println("****进入文件上传方法******"+files.size());
    	Iterator<MultipartFile> iter = files.iterator(); String custName=null;String path="banner/";
		while (iter.hasNext()) {
			MultipartFile file = iter.next() ;
			if (file != null) { // 现在有文件上传	
				if(file.getSize()>0){
				 //保存文件   	      
	    		//  String path="F:\\gi_workspace\\jwk_system\\src\\main\\resources\\static\\uploadWeb/banner/";//上传到暂时此文件夹里
	    		  
	    		    String contentType = file.getContentType();
	    	        String fileName = file.getOriginalFilename();
	    	        System.out.println("fileName-->" + fileName);
	    	        System.out.println("getContentType-->" + contentType);
	    	        System.out.println("【*** 文件上传 ****】photoSize = " + file.getSize());
	    	        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
	    	 	  custName = "" + System.currentTimeMillis() + "." + prefix;
	    	 	   System.out.println("**custName******"+custName);
	    	   
	    	        try {    
	    	        	
	    	        	//FileUtil.uploadFile(file.getBytes(), path, custName);//暂时保存此文件夹
	    	        	QiniuUtils.upload(file.getBytes(), path + custName);
	    	        } catch (Exception e) {
	    	            // TODO: handle exception
	    	        	System.out.println("*****文件出现异常信息*****");
	    	        }
	    	       
				}
	    	        
				
			}
        
		}
		//return "/uploadWeb/banner/"+custName;
		return path + custName;
    }
    
    
    /**
     * 修改l轮播信息
     */
    @RequiresPermissions("system:banner:edit")
    @Log(title = "系统管理", action = "颜色管理-修改轮播图")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	SysWebBanner webBanner = webBannerService.selectWebBannerById(id);
    	if(StringUtils.isNotNull(webBanner)){
    		  model.addAttribute("webBanner", webBanner);
    	}

        //显示成品库名称
        List<SysWebDesigner>  webDesignerList=webDesignerService.selectWebDesignerAll();
        model.addAttribute("webDesignerList", webDesignerList);
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/edit";
    }
    
  
    @RequiresPermissions("system:banner:remove")
    @Log(title = "系统管理", action = "设计师推荐管理-删除设计师推荐")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebBanner sysWebBanner = webBannerService.selectWebBannerById(id);
        if (sysWebBanner == null)
        {
            return JSON.error("该删除的颜色不存在");
        }
        if (webBannerService.deleteWebBannerById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    

    @RequiresPermissions("system:banner:batchRemove")
    @Log(title = "系统管理", action = "颜色管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webBannerService.batchDeleteWebBanner(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  
 
}