package com.jwk.project.system.web.controller;

import java.util.*;

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
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebDesigner;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebLocation;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domainVO.WebLocationVO;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebDesignerService;
import com.jwk.project.system.web.service.WebJeansService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebThreadService;



/**
 * 零件图片位置
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/location")
public class WebLocationController extends BaseController
{	
    private String prefix = "system/web/location";

  
   
    
    @Autowired
    private WebDesignerService webDesignerService;
    
    @Autowired
    private WebJeansService webJeansService;
    
    @Autowired
    private WebPartsService webPartsService;
    
    @Autowired
    private WebColorService webColorService;
    
    @Autowired
    private WebThreadService webThreadService;
    
    
    @RequiresPermissions("system:location:view")
    @GetMapping()
    public String location(Model model)
    {
    	model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        return prefix + "/location";
    }
    
    @RequiresPermissions("system:location:view")
    @GetMapping("/index_location")
    public String indexlocation(Model models,@RequestParam("model") String model,@RequestParam("color") String color,
    		@RequestParam("thread") String thread,@RequestParam("partsId") String partsId)
    {
    	WebLocationVO locationVO=new WebLocationVO();
    	
    	models.addAttribute("qiniuUrl", QiniuUtils.publicURi);
    	
    	locationVO.setModel(model);
    	locationVO.setUrl(QiniuUtils.publicURi);
    	
    	if(StringUtils.isNotEmpty(color)){
    	SysWebColor colorName=webColorService.selectwebColorById(Long.valueOf(color));
    	
    	if(StringUtils.isNotNull(colorName)){
    		locationVO.setColor(colorName.getCover());
    	}
    	
    	}
    	
    	
    	
    	if(StringUtils.isNotEmpty(thread)){
        	SysWebThread threadName=webThreadService.selectwebThreadById(Long.valueOf(thread));
        	
        	if(StringUtils.isNotNull(threadName)){
        		locationVO.setThread(threadName.getCover());
        	}
        	
        	}
    	
    	SysWebParts webParts=webPartsService.selectWebPartsById(Long.valueOf(partsId));
    	if(StringUtils.isNotNull(webParts)){
    		
    		locationVO.setCover(webParts.getCover());
    		locationVO.setPartsId(partsId);
    		/*locationVO.setFixeX(webParts.getFixeX());
    		locationVO.setFixeY(webParts.getFixeY());*/
    	}
    	
    	
    	
    	
    	models.addAttribute("locationVO", locationVO);
    	
    	
        return prefix + "/index_location";
    }

   
    
    /**
     * 新增零件图片位置
     */
    @RequiresPermissions("system:location:add")
    @Log(title = "系统管理", action = "位置管理-新增零件图片位置")
    @GetMapping("/add")
    public String add(Model model)
    {
    	//显示素库名称
     List<SysWebJeans>  webJeansList=webJeansService.selectWebJeansAll();   	
     model.addAttribute("webJeansList", webJeansList);
     
     //显示零件图片名称
     SysWebParts webParts=new SysWebParts();
    // webParts.setType(0);
     webParts.setStatus(1);
     List<SysWebParts>  webPartsList= webPartsService.selectWebPartsAll(webParts);
     model.addAttribute("webPartsList", webPartsList);
        return prefix + "/add";
    }
    
    
    /**
     * 保存零件图片位置
     */
    @RequiresPermissions("system:location:save")
    @Log(title = "系统管理", action = "位置管理-保存零件图片位置") 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSON save(SysWebLocation weblocation,HttpServletRequest request,Model model){
    	
    	Map<String, Object> map =new HashMap<String, Object>();
    	
    	if(StringUtils.isNotNull(weblocation)){
    		long id=weblocation.getJeansId();
    	SysWebJeans webJeans=webJeansService.selectWebJeansById(id);
    	if(StringUtils.isNotNull(webJeans)){
    		map.put("model", webJeans.getModel());
    	}
    //	model.addAttribute("webJeans", webJeans);
    	
    	if(StringUtils.isNotNull(webJeans)){
    		String colors=webJeans.getColorsId();
    		if(StringUtils.isNotEmpty(colors)){
    		String[] color=colors.split(",");
    		map.put("color", color[0]);
    		//model.addAttribute("color", color[0]);
    		}
    		
    		String threads=webJeans.getThreadsId();
    		if(StringUtils.isNotEmpty(threads)){
    		String[] thread=threads.split(",");
    		map.put("thread", thread[0]);
    		//model.addAttribute("thread", thread[0]);
    		}
    		
    		
    		
    	}
    	//parts
    	long partsId=weblocation.getPartsId();
    	map.put("partsId", partsId);
    	/*SysWebParts webParts=webPartsService.selectWebPartsById(partsId);
    	if(StringUtils.isNotNull(webParts)){
    		map.put("cover", webParts.getCover());
    		
    	}*/
    	//model.addAttribute("webParts", webParts);
    	
    	}
		
        return JSON.ok(map);
		
    }
    
  
    /**
     * 保存零件图片位置
     */
    @RequiresPermissions("system:location:save")
    @Log(title = "系统管理", action = "位置管理-保存图片位置") 
    @RequestMapping(value = "/saveLocation", method = RequestMethod.POST)
    @ResponseBody
    public JSON saveLocation(WebLocationVO webLocationVO,HttpServletRequest request,Model model){
    	
    	if(StringUtils.isNotNull(webLocationVO)){
    		String id=webLocationVO.getPartsId();
    	SysWebParts webParts=webPartsService.selectWebPartsById(Long.valueOf(id));
    	if(StringUtils.isNull(webParts)){
    		return JSON.error("您要保存的该对象不存在");
    	}
    	
    	SysWebParts webPart=new SysWebParts();
    	webPart.setId(Integer.valueOf(id));
    	webPart.setFixeX(webLocationVO.getFixeX());
    	webPart.setFixeY(webLocationVO.getFixeY());
    	
    	if(webPartsService.saveWebParts(webPart)>0){
    		 return JSON.ok();
    	}
    
    	}
		
        return JSON.error();
		
    }
//显示数库名称位置
	@RequiresPermissions("system:location:save")
	@Log(title = "系统管理", action = "位置管理-显示图片位置")
	@RequestMapping(value = "/jeansList", method = RequestMethod.POST)
	@ResponseBody
	public List<SysWebJeans> STATIC_LIST_TYPE(HttpServletRequest request) {
		//显示素库名称
		List<SysWebJeans>  webJeansList=webJeansService.selectWebJeansAll();
		return  webJeansList;
	}
//显示区域位置
@RequiresPermissions("system:location:save")
@Log(title = "系统管理", action = "位置管理-显示区域位置")
@RequestMapping(value = "/partsList", method = RequestMethod.POST)
@ResponseBody
	public Object STATIC_LIST_INFO(@RequestParam(name = "jeansId", required = false) Integer jeansId) {
	List<SysWebParts>  webPartsList=new ArrayList<>();
		if (jeansId != null) {
			SysWebJeans webJeans=webJeansService.selectWebJeansById(jeansId.longValue());
			if(StringUtils.isNotNull(webJeans)){
				String partsId=webJeans.getPartsId();
				String[] partsLsit=partsId.split(",");
				for(String id:partsLsit){
				SysWebParts webParts=webPartsService.selectWebPartsById(Long.valueOf(id));
				if(webParts!=null){
					webPartsList.add(webParts);
				}
				}
			}


		} else {
			//显示零件图片名称
			SysWebParts webParts=new SysWebParts();
			webParts.setStatus(1);
			 webPartsList= webPartsService.selectWebPartsAll(webParts);
		}

	return  webPartsList;
	}
 
}