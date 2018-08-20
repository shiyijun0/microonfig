package com.jwk.project.system.goods.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.JeansFile;
import com.jwk.project.system.goods.domain.SysBl;
import com.jwk.project.system.goods.service.ClothService;
import com.jwk.project.system.goods.service.FileService;


/**
 * 布料信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/goodbl")
public class SysBlController extends BaseController
{

    private String prefix = "system/goodbl";

    @Autowired
    private ClothService clothService;
    
    @Autowired
    private FileService fileService;
    
    @RequiresPermissions("system:goodbl:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/goodbl";
    }

    /**
     * 显示布料信息
     */
    @RequiresPermissions("system:goodbl:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = clothService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增布料信息
     */
    @RequiresPermissions("system:goodbl:add")
    @Log(title = "系统管理", action = "布料管理-新增布料")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改布料信息
     */
    @RequiresPermissions("system:goodbl:edit")
    @Log(title = "系统管理", action = "布料管理-修改布料信息")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	SysBl sysBl = clothService.selectSysBlById(id);
        model.addAttribute("sysBl", sysBl);
        return prefix + "/edit";
    }

    /**
     * 保存布料信息
     */
    @RequiresPermissions("system:goodbl:save")
    @Log(title = "系统管理", action = "布料管理-保存布料")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(String fileUrl,SysBl sysBl,HttpServletRequest request)
    {
     	System.out.println("**进入控制层*******"+fileUrl+"*********");
 				//解析存放图片路径并插入对应对象
 				if(StringUtils.isNotNull(fileUrl)){
 					String[] url=fileUrl.split(",");
 					if(url.length==5){//5张图片
 						if(!url[0].equals("empty")){
 							//截取图片名称
 							String img=url[0].substring(url[0].lastIndexOf("/")+1);  
 							System.out.println("*****img******"+url[0]);
 							sysBl.setCover(url[0]);
 						
 							if(!url[1].equals("empty")){
 								sysBl.setChartLet(url[1]);;;
 							}
 							
 							if(!url[2].equals("empty")){
 								sysBl.setLeftPocket(url[2]);
 							}
 							
 							if(!url[3].equals("empty")){
 								sysBl.setRightPocket(url[3]);
 							}
 							if(!url[4].equals("empty")){
 								sysBl.setBlImg(url[4]);
 							}
 							
 						
 					}
 					
 				}
 			} 
     		
 				sysBl.setStatus(SysBl.BL_STATUS_SUCCESS);
         if (clothService.saveSysBl(sysBl) > 0)
         {
         	System.out.println("******返回参数*******"+JSON.ok());
             return JSON.ok(); 
         }
         return JSON.error();
     }

    @RequiresPermissions("system:goodbl:remove")
    @Log(title = "系统管理", action = "布料管理-删除布料")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysBl SysBl = clothService.selectSysBlById(id);
        if (SysBl == null)
        {
            return JSON.error("该布料不存在");
        }
        if (clothService.deleteSysBlById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:goodbl:batchRemove")
    @Log(title = "系统管理", action = "布料管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = clothService.batchDeleteSysBl(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  
  //保存模板文件上传
    @RequiresPermissions("system:goodbl:save")
    @RequestMapping(value = "/saveUpload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request) {
    	 List<String> fileNames = new ArrayList<String>();
		if (request instanceof MultipartHttpServletRequest) { // 如果你现在是MultipartHttpServletRequest对象
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mrequest.getFiles("file");
			System.out.println("**文件数****"+files.size());
			Iterator<MultipartFile> iter = files.iterator();
			while (iter.hasNext()) {
				MultipartFile file = iter.next() ;
				if (file != null) { // 现在有文件上传	
					if(file.getSize()>0){
					 //保存文件   	      
		    	      Calendar now = Calendar.getInstance();
		    		  int year = now.get(Calendar.YEAR);
		    		  int month = now.get(Calendar.MONTH) + 1;
		    		  String savePath = request.getSession().getServletContext().getRealPath("/") + "upload"  + "/" + year+ "/" + month + "/" ;	    	      
		    	        String contentType = file.getContentType();
		    	        String fileName = file.getOriginalFilename();
		    	        System.out.println("fileName-->" + fileName);
		    	        System.out.println("getContentType-->" + contentType);
		    	        System.out.println("【*** 文件上传 ****】photoSize = " + file.getSize());
		    	       Long size=file.getSize();
		    	        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		    	 	   String custName = "" + System.currentTimeMillis() + "." + prefix;
		    	 	   System.out.println("**custName******"+custName);
		    	        System.out.println("**filePath*****"+savePath);
		    	     		
		    	        //保存文件名
		    	        JeansFile jeansFile=new JeansFile(contentType,size.intValue(),custName,savePath,JeansFile.FILE_STATUS_SUCCESS,new Date());
		    	        
		    	        if (fileService.saveJeansFile(jeansFile) > 0)
		    	        {
		    	            System.out.println("文件保存成功");
		    	        }else{
		    	        	try {
								throw new Exception("文件上传失败");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    	        }
		    	       
		    	        
		    	        try {    
		    	        	FileUtil.uploadFile(file.getBytes(), savePath, custName);
		    	        } catch (Exception e) {
		    	            // TODO: handle exception
		    	        	System.out.println("*****文件出现异常信息*****");
		    	        }
		    	        fileNames.add(savePath + custName);//文件的路径和文件名
					}
		    	        else{
							fileNames.add("empty");//文件的路径为空
						}
					
				}
			}
		}
		
		 String url = "";
         if (fileNames.size() > 0) {
          for (int i = 0; i < fileNames.size(); i++) {
           url = url + fileNames.get(i);
           if(i < fileNames.size() - 1){
            url = url + ",";
           }
          }
         }
         System.out.println("***多文件url****"+url);
         
         return url;
	}
}