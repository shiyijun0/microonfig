package com.jwk.project.system.web.controller;

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
import com.jwk.project.system.goods.domain.SysFq;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebPosition;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebPositionService;



/**
 * 零件图片信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/parts")
public class WebPartsController extends BaseController
{	
    private String prefix = "system/web/parts";

  
    @Autowired
    private WebPartsService webPartsService;
   
    @Autowired
    private WebPositionService webPositionService;
    
    @RequiresPermissions("system:parts:view")
    @GetMapping()
    public String parts(Model model)
    {
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/parts";
    }

    /**
     * 显示零件图片信息
     */
    @RequiresPermissions("system:parts:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webPartsService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增零件图片信息
     */
    @RequiresPermissions("system:parts:add")
    @Log(title = "系统管理", action = "零件图片管理-新增零件图片信息")
    @GetMapping("/add")
    public String add(Model model)
    {
    	
    	List<SysWebPosition> webPosition1 =webPositionService.selectSysWebPositionAll(0l);
    	if(StringUtils.isNotEmpty(webPosition1)){
    		model.addAttribute("webPosition1", webPosition1);
    	}
    	//System.out.println("***webPosition1***"+webPosition1);
    	
    /*	List<SysWebPosition> webPosition2 =webPositionService.selectSysWebPositionAll(1l);
    	if(StringUtils.isNotEmpty(webPosition2)){
    		model.addAttribute("webPosition2", webPosition2);
    	}*/
    	//System.out.println("***webPosition2***"+webPosition2);
        return prefix + "/add";
    }
    
    
    /**
     * 保存零件图片信息
     */
    @RequiresPermissions("system:parts:save")
    @Log(title = "系统管理", action = "零件图片管理-保存零件图片信息")
    //@PostMapping("/save") 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSON save(SysWebParts WebParts,HttpServletRequest request){
    	
    	 // 转型为MultipartHttpRequest：   
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        // 获得文件：   
        List<MultipartFile> files = multipartRequest.getFiles("file1");
        List<MultipartFile> files2 = multipartRequest.getFiles("file2");
      
       
		
		 if(StringUtils.isNotNull(WebParts.getId())){
	        	
	        	if(!files.isEmpty()){
	        		String cover=this.uploadFileName(files);
	        		if(StringUtils.isNotEmpty(cover)){
	        			WebParts.setCover(cover);
	        		}
	        	}
	        	
	        	if(!files2.isEmpty()){
	        		String coverTitle=this.uploadFileName(files2);;
	        		if(StringUtils.isNotEmpty(coverTitle)){
	        			WebParts.setCoverTitle(coverTitle);
	        		}
	        		
	        	}
	        	
	        	
	        }else{

	        	 if (files.isEmpty() || files2.isEmpty()  ) {
	                 return JSON.error("上传的图片不能为空");
	             }
	     		String cover=this.uploadFileName(files);
	     		String coverTitle=this.uploadFileName(files2);
	             
	     		if(StringUtils.isNotEmpty(cover)){
	       			WebParts.setCover(cover);
	     		}	
	     		
	     		if(StringUtils.isNotEmpty(coverTitle)){
	     			WebParts.setCoverTitle(coverTitle);;
	     		}
	        }
	        
		
		
		//插入图片
		if(webPartsService.saveWebParts(WebParts)>0){
		
        return JSON.ok();
		}
		return JSON.error("上传零件图片信息失败");
    }
    
   //写入上传文件储存工具类
    public String uploadFileName(List<MultipartFile> files ){
    	System.out.println("****进入文件上传方法******"+files.size());
    	Iterator<MultipartFile> iter = files.iterator(); String custName=null;String path="parts/";
		while (iter.hasNext()) {
			MultipartFile file = iter.next() ;
			if (file != null) { // 现在有文件上传	
				if(file.getSize()>0){
				 //保存文件   	      
	    		 // String path="F:\\gi_workspace\\jwk_system\\src\\main\\resources\\static\\uploadWeb/parts/";//上传到暂时此文件夹里
					
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
		//return "/uploadWeb/parts/"+custName;
		return path + custName;
    }
    
    
    /**
     * 修改布料信息
     */
    @RequiresPermissions("system:parts:edit")
    @Log(title = "系统管理", action = "零件图片管理-修改零件图片信息")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	SysWebParts webParts = webPartsService.selectWebPartsById(id);
    	if(StringUtils.isNotNull(webParts)){
    		  model.addAttribute("webParts", webParts);
    	}
      
    	List<SysWebPosition> webPosition1 =webPositionService.selectSysWebPositionAll(0l);
    	if(StringUtils.isNotEmpty(webPosition1)){
    		model.addAttribute("webPosition1", webPosition1);
    	}
    	//System.out.println("***webPosition1***"+webPosition1);
    	
    	List<SysWebPosition> webPosition2 =webPositionService.selectSysWebPositionAll(1l);
    	if(StringUtils.isNotEmpty(webPosition2)){
    		model.addAttribute("webPosition2", webPosition2);
    	}
    //	System.out.println("***webPosition2***"+webPosition2);
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/edit";
    }
    
  
    @RequiresPermissions("system:parts:remove")
    @Log(title = "系统管理", action = "设计师推荐管理-删除设计师推荐")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebParts SysWebParts = webPartsService.selectWebPartsById(id);
        if (SysWebParts == null)
        {
            return JSON.error("该删除的零件图片不存在");
        }
        if (webPartsService.deleteWebPartsById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    

    @RequiresPermissions("system:parts:batchRemove")
    @Log(title = "系统管理", action = "零件图片管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webPartsService.batchDeleteWebParts(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  
 
}