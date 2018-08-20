package com.jwk.project.system.fashion.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
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

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.JsonNode;
import com.jwk.common.constant.FileUtil;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysFashion;
import com.jwk.project.system.fashion.domainVO.BuLiao;
import com.jwk.project.system.fashion.domainVO.PatternAll;
import com.jwk.project.system.fashion.domainVO.PatternAll.AreaLimit;
import com.jwk.project.system.fashion.domainVO.SelectSize;
import com.jwk.project.system.fashion.service.FashionService;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansFile;
import com.jwk.project.system.goods.service.FileService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;


/**
 * 潮裤社区信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/fashion")
public class SysFashionController extends BaseController
{	
    private String prefix = "system/fashion";

    @Autowired
    private FashionService fashionService;
    @Autowired
    private IRegisterUserService registerUserService;
    
    @Autowired
    private FileService fileService;
    
    @RequiresPermissions("system:fashion:view")
    @GetMapping()
    public String fashion()
    {
        return prefix + "/fashion";
    }

    /**
     * 显示潮裤社区信息
     */
    @RequiresPermissions("system:fashion:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = fashionService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 查询潮裤社区用户
     */
    @RequiresPermissions("system:fashion:list")
    @PostMapping("/checkByuid")
    @ResponseBody
    public String checkByGoodId(@RequestParam("id") Long id)
    {
    	 RegisterUser registerUser= registerUserService.selectRegisterUserById(id);
    	String mobile="";
       if(StringUtils.isNotNull(registerUser)){
    	   mobile=registerUser.getMobile();
       }
        return mobile;
    }
   

    /**
     * 查看潮裤社区信息
     */
    @RequiresPermissions("system:fashion:check")
    @Log(title = "系统管理", action = "潮裤社区管理-查看潮裤社区信息")
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model model)
    {
    	SysFashion sysFashion = fashionService.selectSysFashionById(id);
        model.addAttribute("sysFashion", sysFashion);
        //通过uid查找用户信息
        RegisterUser registerUser= registerUserService.selectRegisterUserById(Long.valueOf(sysFashion.getUid()));
        model.addAttribute("registerUser", registerUser);
        //查找选中的尺码,并通过json去解析
        String jsonSize=sysFashion.getSelectSize();
        JSONObject jsSize=null;
        //查找选中的布料,并通过json去解析
        String jsonbl=sysFashion.getBuliao();
        JSONObject jsbl=null;
        //查找选中的配件
        String jsonpj=sysFashion.getPatternAll();
        System.out.println("***配件***"+jsonpj);
        com.alibaba.fastjson.JSONArray jarr = com.alibaba.fastjson.JSON.parseArray(jsonpj);  
        List<PatternAll> patternAllList=new ArrayList<PatternAll>();
        for(int i=0,len=jarr.size();i<len;i++){
        	com.alibaba.fastjson.JSONObject temp=  jarr.getJSONObject(i);
            System.out.println("*******"+temp.getString("name"));
            com.alibaba.fastjson.JSONObject jobj = com.alibaba.fastjson.JSON.parseObject(temp.getString("areaLimitClass"));
            com.alibaba.fastjson.JSONObject jobjX = com.alibaba.fastjson.JSON.parseObject(jobj.getString("AreaLimit"));
            System.out.println("******&&&&&&&&"+jobjX.getString("X"));
            AreaLimit areaLimit=new PatternAll.AreaLimit(jobjX.getString("X")==null?"":jobjX.getString("X"),jobjX.getString("Y")==null?"":jobjX.getString("Y"),
            		jobjX.getString("Z")==null?"":jobjX.getString("Z"));
            PatternAll patternAll=new PatternAll(temp.getString("fq_id"), temp.getString("name"), 
            		temp.getString("num"), temp.getString("price"), areaLimit, temp.getString("icopicurl"), temp.getString("attachmenturl"));
            System.out.println("***patternAll*****"+patternAll);
            patternAllList.add(patternAll);//插入集合中
        }
		try {
			/*jsSize = new JSONObject(jsonSize);
			jsbl = new JSONObject(jsonbl);*/
			if(StringUtils.isNotNull(jsonSize)){
				jsSize = new JSONObject(jsonSize);
			}
			
			if(StringUtils.isNotNull(jsonbl)){
				jsbl = new JSONObject(jsonbl);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jsSize!=null){
			JsonNode jsonNodeSize=StringUtils.toJsonNode(jsSize.toString());
			SelectSize selectSize=new SelectSize(jsonNodeSize.get("descipt").asText(),jsonNodeSize.get("sizeIdx").asText());
			model.addAttribute("selectSize", selectSize);
			System.out.println("*****"+selectSize+"******");
		}
		if(jsbl!=null){
			JsonNode jsonNodebl=StringUtils.toJsonNode(jsbl.toString());
			BuLiao buLiao=new BuLiao(jsonNodebl.get("num").asText(),jsonNodebl.get("price").asText(),jsonNodebl.get("attachmentUrl").asText(),jsonNodebl.get("name").asText()
					,jsonNodebl.get("iconPicUrl").asText(),jsonNodebl.get("des").asText());
			model.addAttribute("buLiao", buLiao);
			System.out.println("*****"+buLiao+"******");
		}
    	
		if(StringUtils.isNotEmpty(patternAllList)){
			model.addAttribute("patternAllList", patternAllList);
			System.out.println("***patternAllList****"+patternAllList);
		}
    	
        return prefix + "/check";
    }

    @RequiresPermissions("system:fashion:remove")
    @Log(title = "系统管理", action = "潮裤社区管理-删除潮裤社区")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysFashion SysFashion = fashionService.selectSysFashionById(id);
        if (SysFashion == null)
        {
            return JSON.error("该潮裤社区不存在");
        }
        if (fashionService.deleteSysFashionById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:fashion:batchRemove")
    @Log(title = "系统管理", action = "潮裤社区管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = fashionService.batchDeleteSysFashion(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  
  //保存模板文件上传
    @RequiresPermissions("system:fashion:save")
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