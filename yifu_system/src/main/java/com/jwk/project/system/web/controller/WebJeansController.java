package com.jwk.project.system.web.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.constant.FileUtil;
import com.jwk.common.utils.JacksonUtils;
import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommendBg;
import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebPosition;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;
import com.jwk.project.system.web.domainVO.SysWebJeansVO;
import com.jwk.project.system.web.service.WebButtonService;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebJeansService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebSizesService;
import com.jwk.project.system.web.service.WebThreadService;
import com.jwk.project.system.web.service.WebWashService;



/**
 * 衣服信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/jeans")
public class WebJeansController extends BaseController
{	
    private String prefix = "system/web/jeans";

  
    @Autowired
    private WebJeansService webJeansService;
    @Autowired
    private WebPartsService webPartsService;
    @Autowired
    private WebSizesService webSizesService;
    @Autowired
    private WebColorService webColorService;
    @Autowired
    private WebButtonService webButtonService;
    @Autowired
    private WebThreadService webThreadService;
    @Autowired
    private WebWashService webWashService;
    
    
    @RequiresPermissions("system:jeans:view")
    @GetMapping()
    public String Jeans(Model model)
    {
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/jeans";
    }

    /**
     * 显示衣服信息
     */
    @RequiresPermissions("system:jeans:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webJeansService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增衣服信息
     */
    @RequiresPermissions("system:jeans:add")
    @Log(title = "系统管理", action = "衣服管理-新增衣服信息")
    @GetMapping("/add")
    public String add(Model model)
    {
    	SysWebParts webParts1=new SysWebParts();
    	webParts1.setType(0);
    	webParts1.setStatus(1);
    	List<SysWebParts> webPartsList1 =webPartsService.selectWebPartsAll(webParts1);
    	if(StringUtils.isNotEmpty(webPartsList1)){
    		model.addAttribute("webPartsList1", webPartsList1);
    	}
    	//System.out.println("***webPartsList1***"+webPartsList1);
    	
    	SysWebParts webParts2=new SysWebParts();
    	webParts2.setType(1);
    	webParts2.setStatus(1);
    	List<SysWebParts> webPartsList2 =webPartsService.selectWebPartsAll(webParts2);
    	/*if(StringUtils.isNotEmpty(webPartsList2)){
    		model.addAttribute("webPartsList2", webPartsList2);
    	}*/
		model.addAttribute("webPartsList2", webPartsList2);
    	//System.out.println("***webPartsList2***"+webPartsList2);
    	
    	//颜色
    	List<SysWebColor> webColorList =webColorService.selectwebColorAll();
    	if(StringUtils.isNotEmpty(webColorList)){
    		model.addAttribute("webColorList", webColorList);
    	}
    	//System.out.println("***webColorList***"+webColorList);
    	
    	//尺码
    	List<SysWebSizes> webSizesList =webSizesService.selectSysWebSizesAll();
    	if(StringUtils.isNotEmpty(webSizesList)){
    		model.addAttribute("webSizesList", webSizesList);
    	}
    	//System.out.println("***webSizesList***"+webSizesList);
    	
    	//纽扣
    	List<SysWebButton> webButtonList =webButtonService.selectWebButtonAll();
    	if(StringUtils.isNotEmpty(webButtonList)){
    		model.addAttribute("webButtonList", webButtonList);
    	}
    	//System.out.println("***webButtonList***"+webButtonList);
    	
    	//边线
    	List<SysWebThread> webThreadList =webThreadService.selectwebThreadAll();
    	if(StringUtils.isNotEmpty(webThreadList)){
    		model.addAttribute("webThreadList", webThreadList);
    	}
    	//System.out.println("***webThreadList***"+webThreadList);
    	
    	
    	//破洞
    	List<SysWebWash> webWashList =webWashService.selectWebWashAll();
    	/*if(StringUtils.isNotEmpty(webWashList)){
    		model.addAttribute("webWashList", webWashList);
    	}*/
    	//System.out.println("***webWashList***"+webWashList);
		model.addAttribute("webWashList", webWashList);
        return prefix + "/add";
    }
    
    
    /**
     * 保存衣服信息
     */
    @RequiresPermissions("system:jeans:save")
    @Log(title = "系统管理", action = "衣服管理-保存衣服信息")
    //@PostMapping("/save") 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSON save(SysWebJeans WebJeans,HttpServletRequest request){
    	
    	 // 转型为MultipartHttpRequest：   
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        // 获得文件：   
       /* List<MultipartFile> files = multipartRequest.getFiles("file1");
        List<MultipartFile> files2 = multipartRequest.getFiles("file2");*/
        List<MultipartFile> files3 = multipartRequest.getFiles("file3");
        

		
      if(StringUtils.isNotNull(WebJeans.getId())){
        	
        	if(!files3.isEmpty()){
        		String model=this.uploadFileName(files3);
        		if(StringUtils.isNotEmpty(model)){
        			WebJeans.setModel(model);
        		}
        	}
        	
        	
        	
        	
        }else{

        	if (files3.isEmpty()  ) {
                return JSON.error("上传的图片不能为空");
            }
    		/*String cover=this.uploadFileName(files);
    		String coverTitle=this.uploadFileName(files2);*/
    		String model=this.uploadFileName(files3);
            
    		/*if(StringUtils.isNotEmpty(cover)){
      			WebJeans.setCover(cover);
    		}	
    		
    		if(StringUtils.isNotEmpty(coverTitle)){
    			WebJeans.setCoverTitle(coverTitle);;
    		}*/
    		
    		if(StringUtils.isNotEmpty(model)){
      			WebJeans.setModel(model);
    		}
        
        }
		
		
		//插入图片
		if(webJeansService.saveWebJeans(WebJeans)>0){
		
        return JSON.ok();
		}
		return JSON.error("上传衣服信息失败");
    }
    
   //写入上传文件储存工具类
    public String uploadFileName(List<MultipartFile> files ){
    	System.out.println("****进入文件上传方法******"+files.size());
    	Iterator<MultipartFile> iter = files.iterator(); String custName=null;String path="jeans/";
		while (iter.hasNext()) {
			MultipartFile file = iter.next() ;
			if (file != null) { // 现在有文件上传	
				if(file.getSize()>0){
				 //保存文件   	      
	    		 // String path="F:\\gi_workspace\\jwk_system\\src\\main\\resources\\static\\uploadWeb/jeans/";//上传到暂时此文件夹里
					
	    		    String contentType = file.getContentType();
	    	        String fileName = file.getOriginalFilename();
	    	        System.out.println("fileName-->" + fileName);
	    	        System.out.println("getContentType-->" + contentType);
	    	        System.out.println("【*** 文件上传 ****】photoSize = " + file.getSize());
	    	        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
	    	 	  custName = "" + System.currentTimeMillis() + "." + prefix;//修改
	    	       // custName =fileName;
	    	        //webJeansService.selectWebJeansAll();
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
		//return "/uploadWeb/jeans/"+custName;
		return path + custName;
    }
    
    
    /**
     * 修改衣服信息
     */
    @RequiresPermissions("system:jeans:edit")
    @Log(title = "系统管理", action = "衣服管理-修改衣服信息")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	SysWebJeans webJeans = webJeansService.selectWebJeansById(id);
    	SysWebParts webParts=null;
    	if(StringUtils.isNotNull(webJeans)){
    		  model.addAttribute("webJeans", webJeans);
    		  webParts=new SysWebParts();
    		  //webParts.setType(webJeans.getType());
    	    	webParts.setStatus(1);
    	}


    	/*List<SysWebSizes> webSizesList=new ArrayList<SysWebSizes>();
    	List<SysWebColor> webColorList=new ArrayList<SysWebColor>();
    	List<SysWebButton> webButtonList=new ArrayList<SysWebButton>();
    	List<SysWebThread> webThreadList=new ArrayList<SysWebThread>();
    	List<SysWebWash> webWashList=new ArrayList<SysWebWash>();
    	List<SysWebParts> webPartsList=new ArrayList<SysWebParts>();*/
    	
    	List<SysWebParts> webPartsList =webPartsService.selectWebPartsAll(webParts);
    	if(StringUtils.isNotEmpty(webPartsList)){
    		model.addAttribute("webPartsList", webPartsList);
    	}
    	//System.out.println("***webPartsList***"+webPartsList);
    	
    	//颜色
    	List<SysWebColor> webColorList =webColorService.selectwebColorAll();
    	if(StringUtils.isNotEmpty(webColorList)){
    		model.addAttribute("webColorList", webColorList);
    	}
    	//System.out.println("***webColorList***"+webColorList);
    	
    	//尺码
    	List<SysWebSizes> webSizesList =webSizesService.selectSysWebSizesAll();
    	if(StringUtils.isNotEmpty(webSizesList)){
    		model.addAttribute("webSizesList", webSizesList);
    	}
    	//System.out.println("***webSizesList***"+webSizesList);
    	
    	//纽扣
    	List<SysWebButton> webButtonList =webButtonService.selectWebButtonAll();
    	if(StringUtils.isNotEmpty(webButtonList)){
    		model.addAttribute("webButtonList", webButtonList);
    	}
    	//System.out.println("***webButtonList***"+webButtonList);
    	
    	//边线
    	List<SysWebThread> webThreadList =webThreadService.selectwebThreadAll();
    	if(StringUtils.isNotEmpty(webThreadList)){
    		model.addAttribute("webThreadList", webThreadList);
    	}
    	//System.out.println("***webThreadList***"+webThreadList);
    	
    	
    	//破洞
    	List<SysWebWash> webWashList =webWashService.selectWebWashAll();
    	if(StringUtils.isNotEmpty(webWashList)){
    		model.addAttribute("webWashList", webWashList);
    	}
    	//System.out.println("***webWashList***"+webWashList);
    	
    	/*//颜色
    	String colorString=webJeans.getColorsId();
    	if(StringUtils.isNotNull(colorString)){
    		String[] colorArr=colorString.split(",");
    		for(String colorId:colorArr){
    		SysWebColor webColor=webColorService.selectwebColorById(Long.valueOf(colorId));
    		webColorList.add(webColor);
    		
    		}
    	}
      
    	//尺码
    	String sizesString=webJeans.getSizesId();
    	if(StringUtils.isNotNull(sizesString)){
    		String[] sizesArr=sizesString.split(",");
    		for(String sizesId:sizesArr){
    		SysWebSizes webSizes=webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
    		webSizesList.add(webSizes);
    		
    		}
    	}
      
    	
    	//纽扣
    	String buttonString=webJeans.getButtonsId();
    	if(StringUtils.isNotNull(buttonString)){
    		String[] buttonArr=buttonString.split(",");
    		for(String buttonId:buttonArr){
    		SysWebButton webButton=webButtonService.selectWebButtonById(Long.valueOf(buttonId));
    		webButtonList.add(webButton);
    		
    		}
    	}
      
    	
    	
    	//破洞
    	String washString=webJeans.getWashId();
    	if(StringUtils.isNotNull(washString)){
    		String[] washArr=washString.split(",");
    		for(String washId:washArr){
    		SysWebWash webWash=webWashService.selectWebWashById(Long.valueOf(washId));
    		webWashList.add(webWash);
    		
    		}
    	}
      
    	//边线
    	String threadString=webJeans.getThreadsId();
    	if(StringUtils.isNotNull(threadString)){
    		String[] threadArr=threadString.split(",");
    		for(String threadId:threadArr){
    		SysWebThread webThread=webThreadService.selectwebThreadById(Long.valueOf(threadId));
    		webThreadList.add(webThread);
    		
    		}
    	}
      
    	//图片区域
    	String partsString=webJeans.getPartsId();
    	if(StringUtils.isNotNull(partsString)){
    		String[] partsArr=partsString.split(",");
    		for(String partsId:partsArr){
    		SysWebParts webParts=webPartsService.selectWebPartsById(Long.valueOf(partsId));
    		webPartsList.add(webParts);
    		
    		}
    	}*/
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/edit";
    }
    
  
    @RequiresPermissions("system:jeans:remove")
    @Log(title = "系统管理", action = "设计师推荐管理-删除设计师推荐")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebJeans webJeans = webJeansService.selectWebJeansById(id);
        if (webJeans == null)
        {
            return JSON.error("该删除的衣服不存在");
        }
        if (webJeansService.deleteWebJeansById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    

    @RequiresPermissions("system:jeans:batchRemove")
    @Log(title = "系统管理", action = "衣服管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webJeansService.batchDeleteWebJeans(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    
    //查询各种模型name
    @RequiresPermissions("system:jeans:list")
    @Log(title = "系统管理", action = "衣服管理-查看衣服名称")
    @PostMapping("/jeansModel")
    @ResponseBody
    public JSONObject jeansModel(SysWebJeans webJeans)
    {
    	
    	List<SysWebSizes> webSizesList=new ArrayList<SysWebSizes>();
    	List<SysWebColor> webColorList=new ArrayList<SysWebColor>();
    	List<SysWebButton> webButtonList=new ArrayList<SysWebButton>();
    	List<SysWebThread> webThreadList=new ArrayList<SysWebThread>();
    	List<SysWebWash> webWashList=new ArrayList<SysWebWash>();
    	List<SysWebParts> webPartsList=new ArrayList<SysWebParts>();
    	if(StringUtils.isNotNull(webJeans)){
    		//颜色
    		if(StringUtils.isNotEmpty(webJeans.getColorsId())){
    			String[] colorId=webJeans.getColorsId().split(",");
    			for(String id:colorId){
    				SysWebColor webColor=webColorService.selectwebColorById(Long.valueOf(id));
    				webColorList.add(webColor);
    			}
    		}
    		
    		//尺码
    		if(StringUtils.isNotEmpty(webJeans.getSizesId())){
    			String[] sizesId=webJeans.getSizesId().split(",");
    			for(String id:sizesId){
    				SysWebSizes webSizes=webSizesService.selectSysWebSizesById(Long.valueOf(id));
    				webSizesList.add(webSizes);
    			}
    		}
    		
    		//纽扣
    		if(StringUtils.isNotEmpty(webJeans.getButtonsId())){
    			String[] buttonId=webJeans.getButtonsId().split(",");
    			for(String id:buttonId){
    				SysWebButton webButton=webButtonService.selectWebButtonById(Long.valueOf(id));
    				webButtonList.add(webButton);
    			}
    		}
    		
    		//边线
    		if(StringUtils.isNotEmpty(webJeans.getThreadsId())){
    			String[] threadId=webJeans.getThreadsId().split(",");
    			for(String id:threadId){
    				SysWebThread webThread=webThreadService.selectwebThreadById(Long.valueOf(id));
    				webThreadList.add(webThread);
    			}
    		}
    		
    		
    		//配件
    		if(StringUtils.isNotEmpty(webJeans.getPartsId())){
    			String[] partsId=webJeans.getPartsId().split(",");
    			for(String id:partsId){
    				SysWebParts webParts=webPartsService.selectWebPartsById(Long.valueOf(id));
    				webPartsList.add(webParts);
    			}
    		}
    		
    		//破洞
    		if(StringUtils.isNotEmpty(webJeans.getWashId())){
    			String[] washId=webJeans.getWashId().split(",");
    			for(String id:washId){
    				SysWebWash webWash=webWashService.selectWebWashById(Long.valueOf(id));
    				webWashList.add(webWash);
    			}
    		}
    	}
    	
    	SysWebJeansVO webJeansVO=new SysWebJeansVO(webSizesList, webColorList, webButtonList, webThreadList, webPartsList,webWashList);
    	
    	
    	//解析json格式
    	String webJeansVOJson=com.alibaba.fastjson.JSON.toJSON(webJeansVO).toString();
	    JSONObject webJeansVOObject=com.alibaba.fastjson.JSON.parseObject(webJeansVOJson);
	    
	   // System.out.println("***webJeansVOObject*******"+webJeansVOObject);
	    
	    com.alibaba.fastjson.JSONArray result1 = webJeansVOObject.getJSONArray("webThreadList");

        if(StringUtils.isNotNull(result1)){
		   for (int i = 0; i < result1.size(); i++) {		       
		        String currentCity = result1.getJSONObject(i).getString("name");

		        
		   }
        }
    	//List<SysRecommendBg> sysrecommendBgList = recommendBgService.selectSysRecommendBgByrecommendId(recommendId);
        if (StringUtils.isNotNull(webJeansVO))
        {
            return webJeansVOObject;
        }else{      	
        	
        	 return null;
        	
        	
        }
       
           
        
    }

	@RequiresPermissions("system:jeans:list")
	@Log(title = "系统管理", action = "模型管理-查询标签")
	@PostMapping("/labelList")
	@ResponseBody
	public SysWebJeansVO checkLable(@RequestParam("label") Integer label)
	{
		SysWebJeansVO webJeans=new SysWebJeansVO();

		SysWebThread webThread=new SysWebThread();
		webThread.setLabel(label);
		SysWebButton webButton=new SysWebButton();
		webButton.setLabel(label);
		SysWebColor webColor=new SysWebColor();
		webColor.setLabel(label);
		SysWebParts webPart=new SysWebParts();
		webPart.setLabel(label);

		List<SysWebThread> webThreads=webThreadService.selectwebThreadByThread(webThread);
		if(webThreads.size()>0){
			webJeans.setWebThreadList(webThreads);
		}

		List<SysWebButton> webButtons=webButtonService.selectwebColorByButton(webButton);
		if(webButtons.size()>0){
			webJeans.setWebButtonList(webButtons);
		}

		List<SysWebColor> webColors=webColorService.selectwebColorByColor(webColor);
		if(webColors.size()>0){
			webJeans.setWebColorList(webColors);
		}
		List<SysWebParts> webParts=webPartsService.selectWebPartsAll(webPart);
		if(webParts.size()>0){
			webJeans.setWebPartsList(webParts);
		}

		return webJeans;
	}
 
}