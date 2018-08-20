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
import com.jwk.project.system.goods.domain.SysFq;
import com.jwk.project.system.goods.domain.SysPj;
import com.jwk.project.system.goods.service.FileService;
import com.jwk.project.system.goods.service.PartitionService;
import com.jwk.project.system.goods.service.PartsService;


/**
 * 配件信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/goodpj")
public class SysPjController extends BaseController
{

    private String prefix = "system/goodpj";

    @Autowired
    private PartsService partsService;
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private FileService fileService;
    
    @RequiresPermissions("system:goodpj:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/goodpj";
    }

    /**
     * 显示配件信息
     */
    @RequiresPermissions("system:goodpj:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = partsService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增配件信息
     */
    @RequiresPermissions("system:goodpj:add")
    @Log(title = "系统管理", action = "配件管理-新增配件")
    @GetMapping("/add")
    public String add(Model model)
    {
    	//查找所有的分区信息
    	List<SysFq> fqlist=	partitionService.selectSysFqAll();
    	if(StringUtils.isNotEmpty(fqlist)){
    		model.addAttribute("allSysfq", fqlist);
    	}
    	System.out.println("******"+fqlist);
        return prefix + "/add";
    }

    /**
     * 修改配件信息
     */
    @RequiresPermissions("system:goodpj:edit")
    @Log(title = "系统管理", action = "配件管理-修改配件信息")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	System.out.println("****进入修改配件***"+id);
    	SysPj sysPj = partsService.selectSysPjById(id);
        model.addAttribute("sysPj", sysPj);
        
      //查找所有的分区信息
    	List<SysFq> fqlist=	partitionService.selectSysFqAll();
    	if(StringUtils.isNotEmpty(fqlist)){
    		model.addAttribute("allSysfq", fqlist);
    	}
    	System.out.println("******"+fqlist);
        return prefix + "/edit";
    }

    /**
     * 保存配件信息
     */
    @RequiresPermissions("system:goodpj:save")
    @Log(title = "系统管理", action = "配件管理-保存配件")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(String fileUrl, String numArr,SysPj sysPj,HttpServletRequest request)
   {
    	System.out.println("**进入控制层*******"+fileUrl+"*********"+numArr);
    	//Long nzId=ShiroUtils.getUserId();
				//解析存放图片路径并插入对应对象
				if(StringUtils.isNotNull(fileUrl)){
					String[] url=fileUrl.split(",");
					if(url.length==2){//2张图片
						if(!url[0].equals("empty")){			
							System.out.println("***********"+url[0]);
							//截取图片名称
							String img=url[0].substring(url[0].lastIndexOf("/")+1);  
							System.out.println("*****img******"+url[0]);
							//sysPj.setCover(url[0]);
							sysPj.setCover("/uploadPj/"+img);//图片插入路径暂时放入此路径下
							if(!url[1].equals("empty")){
								//sysPj.setChartlet(url[1]);;
								String img1=url[1].substring(url[1].lastIndexOf("/")+1);  
								System.out.println("*****img1******"+url[1]);
								sysPj.setChartlet("/uploadPj/"+img1);//图片插入路径暂时放入此路径下
							}
							
						
					}
					
				}
			} 
    		
				//解析配件位置 x  y  z
				if(StringUtils.isNotEmpty(numArr)){
					String[] fixe=numArr.split(",");
					int i=0;
					for(String fix:fixe){
						i++;
						if(i==1){
							sysPj.setFixeX(fix);
						}
						
						if(i==2){
							sysPj.setFixeY(fix);
						}
						
						if(i==3){
							sysPj.setFixeZ(fix);
						}
					}
				}
							
				
    	sysPj.setStatus(SysPj.FQ_STATUS_SUCCESS);
        if (partsService.saveSysPj(sysPj) > 0)
        {
        	System.out.println("******返回参数*******"+JSON.ok());
            return JSON.ok(); 
        }
        return JSON.error();
    }

    
    
    @RequiresPermissions("system:goodpj:remove")
    @Log(title = "系统管理", action = "配件管理-删除配件")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysPj SysPj = partsService.selectSysPjById(id);
        if (SysPj == null)
        {
            return JSON.error("该配件不存在");
        }
        if (partsService.deleteSysPjById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:goodpj:batchRemove")
    @Log(title = "系统管理", action = "配件管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = partsService.batchDeleteSysPj(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  //保存模板文件上传
    @RequiresPermissions("system:goodpj:save")
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
		    	       
		    		  String path="F:\\gi_workspace\\jwk_system\\src\\main\\resources\\static\\uploadPj/";//上传到暂时此文件夹里
		    		  
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
		    	        	//FileUtil.uploadFile(file.getBytes(), savePath, custName);
		    	        	FileUtil.uploadFile(file.getBytes(), path, custName);//暂时保存此文件夹
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