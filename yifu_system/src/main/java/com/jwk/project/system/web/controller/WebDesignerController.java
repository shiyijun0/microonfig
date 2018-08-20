package com.jwk.project.system.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jwk.common.constant.R;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebDesigner;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebLocation;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;
import com.jwk.project.system.web.domainVO.JeansVO;
import com.jwk.project.system.web.domainVO.SysWebJeansVO;
import com.jwk.project.system.web.service.WebButtonService;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebDesignerService;
import com.jwk.project.system.web.service.WebJeansService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebSizesService;
import com.jwk.project.system.web.service.WebThreadService;
import com.jwk.project.system.web.service.WebWashService;



/**
 * 设计师成品信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/designer")
public class WebDesignerController extends BaseController
{	
    private String prefix = "system/web/designer";

  
    @Autowired
    private WebJeansService webJeansService;
    @Autowired
    private WebDesignerService webDesignerService;
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
    
    
    @RequiresPermissions("system:designer:view")
    @GetMapping()
    public String designer()
    {
        return prefix + "/designer";
    }

    /**
     * 显示设计师成品信息
     */
    @RequiresPermissions("system:designer:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = webDesignerService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 修改成品信息
     */
    @RequiresPermissions("system:designer:check")
    @Log(title = "系统管理", action = "设计师成品管理-修改设计师成品信息")
    @GetMapping("/check/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(id);
    	SysWebParts webParts=null;
    	if(StringUtils.isNotNull(webDesigner)){
    		  model.addAttribute("webDesigner", webDesigner);
    		  webParts=new SysWebParts();
    		  webParts.setType(webDesigner.getType());
    	    	webParts.setStatus(1);
    	}
    	
    	List<SysWebSizes> webSizesList=new ArrayList<SysWebSizes>();
    	List<SysWebColor> webColorList=new ArrayList<SysWebColor>();
    	List<SysWebButton> webButtonList=new ArrayList<SysWebButton>();
    	List<SysWebThread> webThreadList=new ArrayList<SysWebThread>();
    	List<SysWebWash> webWashList=new ArrayList<SysWebWash>();
    	List<SysWebParts> webPartsList=new ArrayList<SysWebParts>();
    	
    	/*List<SysWebParts> webPartsList =webPartsService.selectWebPartsAll(webParts);
    	if(StringUtils.isNotEmpty(webPartsList)){
    		model.addAttribute("webPartsList", webPartsList);
    	}
    	System.out.println("***webPartsList***"+webPartsList);
    	
    	//颜色
    	List<SysWebColor> webColorList =webColorService.selectwebColorAll();
    	if(StringUtils.isNotEmpty(webColorList)){
    		model.addAttribute("webColorList", webColorList);
    	}
    	System.out.println("***webColorList***"+webColorList);
    	
    	//尺码
    	List<SysWebSizes> webSizesList =webSizesService.selectSysWebSizesAll();
    	if(StringUtils.isNotEmpty(webSizesList)){
    		model.addAttribute("webSizesList", webSizesList);
    	}
    	System.out.println("***webSizesList***"+webSizesList);
    	
    	//纽扣
    	List<SysWebButton> webButtonList =webButtonService.selectWebButtonAll();
    	if(StringUtils.isNotEmpty(webButtonList)){
    		model.addAttribute("webButtonList", webButtonList);
    	}
    	System.out.println("***webButtonList***"+webButtonList);
    	
    	//边线
    	List<SysWebThread> webThreadList =webThreadService.selectwebThreadAll();
    	if(StringUtils.isNotEmpty(webThreadList)){
    		model.addAttribute("webThreadList", webThreadList);
    	}
    	System.out.println("***webThreadList***"+webThreadList);
    	
    	
    	//破洞
    	List<SysWebWash> webWashList =webWashService.selectWebWashAll();
    	if(StringUtils.isNotEmpty(webWashList)){
    		model.addAttribute("webWashList", webWashList);
    	}
    	System.out.println("***webWashList***"+webWashList);*/
    	
    	//颜色
    	String colorString=webDesigner.getColorsId();
    	if(StringUtils.isNotNull(colorString)){
    		String[] colorArr=colorString.split(",");
    		for(String colorId:colorArr){
    		SysWebColor webColor=webColorService.selectwebColorById(Long.valueOf(colorId));
    		webColorList.add(webColor);
    		
    		}
    		
    		model.addAttribute("webColorList", webColorList);
    	}
      
    	//尺码
    	String sizesString=webDesigner.getSizesId();
    	if(StringUtils.isNotNull(sizesString)){
    		String[] sizesArr=sizesString.split(",");
    		for(String sizesId:sizesArr){
    		SysWebSizes webSizes=webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
    		webSizesList.add(webSizes);
    		
    		}
    		
    		model.addAttribute("webSizesList", webSizesList);
    	}
      
    	
    	//纽扣
    	String buttonString=webDesigner.getButtonsId();
    	if(StringUtils.isNotNull(buttonString)){
    		String[] buttonArr=buttonString.split(",");
    		for(String buttonId:buttonArr){
    		SysWebButton webButton=webButtonService.selectWebButtonById(Long.valueOf(buttonId));
    		webButtonList.add(webButton);
    		
    		}
    		model.addAttribute("webButtonList", webButtonList);
    	}
      
    	
    	
    	//破洞
    	String washString=webDesigner.getWashId();
    	if(StringUtils.isNotNull(washString)){
    		String[] washArr=washString.split(",");
    		for(String washId:washArr){
    		SysWebWash webWash=webWashService.selectWebWashById(Long.valueOf(washId));
    		webWashList.add(webWash);
    		
    		}
    		
    		model.addAttribute("webWashList", webWashList);
    	}
      
    	//边线
    	String threadString=webDesigner.getThreadsId();
    	if(StringUtils.isNotNull(threadString)){
    		String[] threadArr=threadString.split(",");
    		for(String threadId:threadArr){
    		SysWebThread webThread=webThreadService.selectwebThreadById(Long.valueOf(threadId));
    		webThreadList.add(webThread);
    		
    		}
    		
    		model.addAttribute("webThreadList", webThreadList);
    	}
      
    	//图片区域
    	String partsString=webDesigner.getPartsId();
    	if(StringUtils.isNotNull(partsString)){
    		String[] partsArr=partsString.split(",");
    		for(String partsId:partsArr){
    		 webParts=webPartsService.selectWebPartsById(Long.valueOf(partsId));
    		webPartsList.add(webParts);
    		
    		}
    		model.addAttribute("webPartsList", webPartsList);
    	}
    	
        return prefix + "/check";
    }
    
  
    @RequiresPermissions("system:designer:remove")
    @Log(title = "系统管理", action = "设计师推荐管理-删除设计师推荐")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(id);
        if (webDesigner == null)
        {
            return JSON.error("该删除的设计师成品不存在");
        }
        if (webDesignerService.deleteWebDesignerById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    

    @RequiresPermissions("system:designer:batchRemove")
    @Log(title = "系统管理", action = "设计师成品管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = webDesignerService.batchDeleteWebDesigner(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    
    //查询各种模型name
    @RequiresPermissions("system:designer:list")
    @Log(title = "系统管理", action = "设计师成品管理-查看设计师成品名称")
    @PostMapping("/designerModel")
    @ResponseBody
    public JSONObject designerModel(SysWebDesigner webDesigner)
    {
    	
    	List<SysWebSizes> webSizesList=new ArrayList<SysWebSizes>();
    	List<SysWebColor> webColorList=new ArrayList<SysWebColor>();
    	List<SysWebButton> webButtonList=new ArrayList<SysWebButton>();
    	List<SysWebThread> webThreadList=new ArrayList<SysWebThread>();
    	List<SysWebWash> webWashList=new ArrayList<SysWebWash>();
    	List<SysWebParts> webPartsList=new ArrayList<SysWebParts>();
    	if(StringUtils.isNotNull(webDesigner)){
    		//颜色
    		if(StringUtils.isNotEmpty(webDesigner.getColorsId())){
    			String[] colorId=webDesigner.getColorsId().split(",");
    			for(String id:colorId){
    				SysWebColor webColor=webColorService.selectwebColorById(Long.valueOf(id));
    				webColorList.add(webColor);
    			}
    		}
    		
    		//尺码
    		if(StringUtils.isNotEmpty(webDesigner.getSizesId())){
    			String[] sizesId=webDesigner.getSizesId().split(",");
    			for(String id:sizesId){
    				SysWebSizes webSizes=webSizesService.selectSysWebSizesById(Long.valueOf(id));
    				webSizesList.add(webSizes);
    			}
    		}
    		
    		//纽扣
    		if(StringUtils.isNotEmpty(webDesigner.getButtonsId())){
    			String[] buttonId=webDesigner.getButtonsId().split(",");
    			for(String id:buttonId){
    				SysWebButton webButton=webButtonService.selectWebButtonById(Long.valueOf(id));
    				webButtonList.add(webButton);
    			}
    		}
    		
    		//边线
    		if(StringUtils.isNotEmpty(webDesigner.getThreadsId())){
    			String[] threadId=webDesigner.getThreadsId().split(",");
    			for(String id:threadId){
    				SysWebThread webThread=webThreadService.selectwebThreadById(Long.valueOf(id));
    				webThreadList.add(webThread);
    			}
    		}
    		
    		
    		//配件
    		if(StringUtils.isNotEmpty(webDesigner.getPartsId())){
    			String[] partsId=webDesigner.getPartsId().split(",");
    			for(String id:partsId){
    				SysWebParts webParts=webPartsService.selectWebPartsById(Long.valueOf(id));
    				webPartsList.add(webParts);
    			}
    		}
    		
    		//破洞
    		if(StringUtils.isNotEmpty(webDesigner.getWashId())){
    			String[] washId=webDesigner.getWashId().split(",");
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
		//   System.out.println("&&&&&&&&&"+result1);
        if(StringUtils.isNotNull(result1)){
		   for (int i = 0; i < result1.size(); i++) {		       
		        String currentCity = result1.getJSONObject(i).getString("name");
		        System.out.println("currentCity:" + currentCity);
		        
		   }
        }
        if (StringUtils.isNotNull(webJeansVO))
        {
            return webJeansVOObject;
        }else{      	
        	
        	 return null;
        	
        	
        }
 
    }
 
    /**
     * 新增衣服信息
     */
    @RequiresPermissions("system:designer:add")
    @Log(title = "系统管理", action = "衣服管理-新增衣服信息")
    @GetMapping("/add")
    public String add(Model model)
    {
    	
   List<SysWebJeans>  webJeans=	webJeansService.selectWebJeansAll();
   
   List<JeansVO>  jeansVOList=new ArrayList<JeansVO>();
   if(webJeans.size()>0){
	 //  for(SysWebJeans webJean:webJeans){
	   
	   SysWebJeans webJean=webJeans.get(0);
		   JeansVO jeansVO=new JeansVO();
		   Map<String,Object> jeansName=new HashMap<String, Object>();
		   List<SysWebJeans> jeansList=new ArrayList<SysWebJeans>();
		   for(SysWebJeans webJ:webJeans){
			   jeansName.put(webJ.getId()+"", webJ.getName());
			   jeansList.add(webJ);
		   }
		   jeansVO.setJeansName(jeansName);
		   jeansVO.setJeansList(jeansList);
		   jeansVO.setName(webJean.getName());
		   jeansVO.setType(webJean.getType());
		   jeansVO.setJeansId(webJean.getId());
		   
		   String colorsId=webJean.getColorsId();
		   String[] colors=colorsId.split(",");
		   Map<String,Object> mapColors=new HashMap<String, Object>();
		   for(String color:colors){
			SysWebColor webvo  = webColorService.selectwebColorById(Long.valueOf(color));
			if(StringUtils.isNotNull(webvo)){
				
				mapColors.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setColors(mapColors);
		   
		   
		   String buttonsId=webJean.getButtonsId();
		   String[] buttons=buttonsId.split(",");
		   Map<String,Object> mapbuttons=new HashMap<String, Object>();
		   for(String button:buttons){
			SysWebButton webvo  = webButtonService.selectWebButtonById(Long.valueOf(button));
			if(StringUtils.isNotNull(webvo)){
				
				mapbuttons.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setButtons(mapbuttons);
		   
		   
		   String partsId=webJean.getPartsId();
		   String[] parts=partsId.split(",");
		   Map<String,Object> mapparts1=new HashMap<String, Object>();
		   Map<String,Object> mapparts2=new HashMap<String, Object>();
		   Map<String,Object> mapparts3=new HashMap<String, Object>();
		   Map<String,Object> mapparts4=new HashMap<String, Object>();
		   Map<String,Object> mapparts5=new HashMap<String, Object>();
		   Map<String,Object> mapparts6=new HashMap<String, Object>();
		   Map<String,Object> mapparts7=new HashMap<String, Object>();
		   Map<String,Object> mapparts8=new HashMap<String, Object>();
		   for(String part:parts){
			SysWebParts webvo  = webPartsService.selectWebPartsById(Long.valueOf(part));
			if(StringUtils.isNotNull(webvo)){
				if(webvo.getRegion()==R.parts1){
					mapparts1.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts2){
					mapparts2.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts3){
					mapparts3.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts4){
					mapparts4.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts5){
					mapparts5.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts6){
					mapparts6.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts7){
					mapparts7.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts8){
					mapparts8.put(webvo.getId()+"", webvo.getName());
				}
				
				
			}
		   }
		   jeansVO.setParts1(mapparts1);
		   jeansVO.setParts2(mapparts2);
		   jeansVO.setParts3(mapparts3);
		   jeansVO.setParts4(mapparts4);
		   jeansVO.setParts5(mapparts5);
		   jeansVO.setParts7(mapparts7);
		   jeansVO.setParts8(mapparts8);
		   jeansVO.setParts6(mapparts6);
		   
		   String sizesId=webJean.getSizesId();
		   String[] sizes=sizesId.split(",");
		   Map<String,Object> mapsizes=new HashMap<String, Object>();
		   for(String size:sizes){
			SysWebSizes webvo  = webSizesService.selectSysWebSizesById(Long.valueOf(size));
			if(StringUtils.isNotNull(webvo)){
				
				mapsizes.put(webvo.getId()+"", webvo.getSize());
			}
		   }
		   jeansVO.setSizes(mapsizes);
		   
		   
		   
		   String threadsId=webJean.getThreadsId();
		   String[] threads=threadsId.split(",");
		   Map<String,Object> mapthreads=new HashMap<String, Object>();
		   for(String thread:threads){
			SysWebThread webvo  = webThreadService.selectwebThreadById(Long.valueOf(thread));
			if(StringUtils.isNotNull(webvo)){
				
				mapthreads.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setThreads(mapthreads);
		   
		   
		   
		   String washsId=webJean.getWashId();
		   String[] washs=washsId.split(",");
		   Map<String,Object> mapwashs=new HashMap<String, Object>();
		   for(String wash:washs){
		   	if(wash.equals("0") && StringUtils.isNotEmpty(wash)){
		   		continue;
			}
			SysWebWash webvo  = webWashService.selectWebWashById(Long.valueOf(wash));
			if(StringUtils.isNotNull(webvo)){
				
				mapwashs.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setWashs(mapwashs);
		   
		   jeansVOList.add(jeansVO);
	  // }  //for循环
	   
	   model.addAttribute("jeansVOList", jeansVOList);
   }
    	
        return prefix + "/add";
    }
     
    /**
     * 保存衣服信息
     */
    @RequiresPermissions("system:designer:save")
    @Log(title = "系统管理", action = "衣服管理-保存衣服信息")
    //@PostMapping("/save") 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSON save(SysWebDesigner webDesigner,HttpServletRequest request){
    	  	
		if(webDesignerService.saveWebDesigner(webDesigner)>0){
		
        return JSON.ok();
		}
		return JSON.error("保存信息失败");
    }
    
    
    /**
     * 保存零件图片位置
     */
    @RequiresPermissions("system:designer:save")
    @Log(title = "系统管理", action = "素库模型管理-素库模型信息") 
    @RequestMapping(value = "/jeansId", method = RequestMethod.POST)
    @ResponseBody
    public JeansVO save(@RequestParam("id") String id,HttpServletRequest request,Model model){
    	
    	 JeansVO jeansVO=new JeansVO(); 
    	
    	if(StringUtils.isNotEmpty(id)){
    		long jeansId=Long.valueOf(id);
    	SysWebJeans webJean=webJeansService.selectWebJeansById(jeansId);
    	if(StringUtils.isNotNull(webJean)){   		   	
    				     				  
    				   jeansVO.setName(webJean.getName());
    				   jeansVO.setType(webJean.getType());
    				   jeansVO.setJeansId(webJean.getId());
  				   
    				   String colorsId=webJean.getColorsId();
    				   String[] colors=colorsId.split(",");
    				   List<SysWebColor> colorList=new ArrayList<SysWebColor>();
    				   for(String color:colors){
    					SysWebColor webvo  = webColorService.selectwebColorById(Long.valueOf(color));
    					if(StringUtils.isNotNull(webvo)){
    						colorList.add(webvo);					
    					}
    				   }
    				   
    				   jeansVO.setColorList(colorList);
    				       				   
    				   String buttonsId=webJean.getButtonsId();
    				   String[] buttons=buttonsId.split(",");  
    				   List<SysWebButton> buttonList=new ArrayList<SysWebButton>();
    				   for(String button:buttons){
    					SysWebButton webvo  = webButtonService.selectWebButtonById(Long.valueOf(button));
    					if(StringUtils.isNotNull(webvo)){
    						buttonList.add(webvo);  						
    					}
    				   }
    				   jeansVO.setButtonList(buttonList);
    				   
    				   List<SysWebParts> partList1=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList2=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList3=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList4=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList5=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList6=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList7=new ArrayList<SysWebParts>();
    				   List<SysWebParts> partList8=new ArrayList<SysWebParts>();
    				   
    				   String partsId=webJean.getPartsId();
    				   String[] parts=partsId.split(",");   				  
    				   for(String part:parts){
    					SysWebParts webvo  = webPartsService.selectWebPartsById(Long.valueOf(part));
    					if(StringUtils.isNotNull(webvo)){
    						if(webvo.getRegion()==R.parts1){
    							partList1.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts2){
    							partList2.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts3){
    							partList3.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts4){
    							partList4.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts5){
    							partList5.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts6){
    							partList6.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts7){
    							partList7.add(webvo);
    						}
    						
    						if(webvo.getRegion()==R.parts8){
    							partList8.add(webvo);
    						}
    						
    						
    					}
    				   }
    				   jeansVO.setPartList1(partList1);
    				   jeansVO.setPartList2(partList2);
    				   jeansVO.setPartList3(partList3);
    				   jeansVO.setPartList4(partList4);
    				   jeansVO.setPartList5(partList5);
    				   jeansVO.setPartList6(partList6);
    				   jeansVO.setPartList7(partList7);
    				   jeansVO.setPartList8(partList8);
    				   
    				   String sizesId=webJean.getSizesId();
    				   String[] sizes=sizesId.split(",");
    				   List<SysWebSizes> sizeList=new ArrayList<SysWebSizes>();
    				   for(String size:sizes){
    					SysWebSizes webvo  = webSizesService.selectSysWebSizesById(Long.valueOf(size));
    					if(StringUtils.isNotNull(webvo)){
    						
    						sizeList.add(webvo);
    					}
    				   }
    				   jeansVO.setSizeList(sizeList);
    				   
    				   
    				   
    				   String threadsId=webJean.getThreadsId();
    				   String[] threads=threadsId.split(",");
    				   List<SysWebThread> threadList=new ArrayList<SysWebThread>();
    				   for(String thread:threads){
    					SysWebThread webvo  = webThreadService.selectwebThreadById(Long.valueOf(thread));
    					if(StringUtils.isNotNull(webvo)){
    						threadList.add(webvo);
    					}
    				   }
    				   jeansVO.setThreadList(threadList);
    				   
    				   
    				   
    				   String washsId=webJean.getWashId();
    				   String[] washs=washsId.split(",");
    				   List<SysWebWash> washList=new ArrayList<SysWebWash>();   				   
    				   for(String wash:washs){
    					SysWebWash webvo  = webWashService.selectWebWashById(Long.valueOf(wash));
    					if(StringUtils.isNotNull(webvo)){
    						washList.add(webvo);
    					}
    				   }
    				   jeansVO.setWashList(washList);
    				
    			 
    	}
    
    	}
    	
    	if(StringUtils.isNotNull(jeansVO)){
    		//System.out.println("*******"+jeansVO);
    		
    		return jeansVO;
    	}
    	
    	
		
        return null;
		
    }
    
    
    /**
     * 新增衣服信息
     */
    @RequiresPermissions("system:designer:add")
    @Log(title = "系统管理", action = "衣服管理-新增衣服信息")
    @GetMapping("/adds")
    public String adds(@RequestParam("id") String id,Model model)
    {
    	
   List<SysWebJeans>  webJeans=	webJeansService.selectWebJeansAll();
   
   List<JeansVO>  jeansVOList=new ArrayList<JeansVO>();
   if(webJeans.size()>0){
	 //  for(SysWebJeans webJean:webJeans){
	   
	   SysWebJeans webJean=webJeansService.selectWebJeansById(Long.valueOf(id));
		   JeansVO jeansVO=new JeansVO();
		   Map<String,Object> jeansName=new HashMap<String, Object>();
		   
		   List<SysWebJeans> jeansList=new ArrayList<SysWebJeans>();
		   jeansList.add(webJean);
		   jeansName.put(id, webJean.getName());
		   for(SysWebJeans webJ:webJeans){
			   if(Integer.valueOf(id)==webJ.getId()){
				   continue;
			   }
			   jeansList.add(webJ);
			   jeansName.put(webJ.getId()+"", webJ.getName());
		   }
		   jeansVO.setJeansName(jeansName);
		   jeansVO.setJeansList(jeansList);
		   jeansVO.setName(webJean.getName());
		   jeansVO.setType(webJean.getType());
		   jeansVO.setJeansId(webJean.getId());
		   
		   String colorsId=webJean.getColorsId();
		   String[] colors=colorsId.split(",");
		   Map<String,Object> mapColors=new HashMap<String, Object>();
		   for(String color:colors){
			SysWebColor webvo  = webColorService.selectwebColorById(Long.valueOf(color));
			if(StringUtils.isNotNull(webvo)){
				
				mapColors.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setColors(mapColors);
		   
		   
		   String buttonsId=webJean.getButtonsId();
		   String[] buttons=buttonsId.split(",");
		   Map<String,Object> mapbuttons=new HashMap<String, Object>();
		   for(String button:buttons){
			SysWebButton webvo  = webButtonService.selectWebButtonById(Long.valueOf(button));
			if(StringUtils.isNotNull(webvo)){
				
				mapbuttons.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setButtons(mapbuttons);
		   
		   
		   String partsId=webJean.getPartsId();
		   String[] parts=partsId.split(",");
		   Map<String,Object> mapparts1=new HashMap<String, Object>();
		   Map<String,Object> mapparts2=new HashMap<String, Object>();
		   Map<String,Object> mapparts3=new HashMap<String, Object>();
		   Map<String,Object> mapparts4=new HashMap<String, Object>();
		   Map<String,Object> mapparts5=new HashMap<String, Object>();
		   Map<String,Object> mapparts6=new HashMap<String, Object>();
		   Map<String,Object> mapparts7=new HashMap<String, Object>();
		   Map<String,Object> mapparts8=new HashMap<String, Object>();
		   for(String part:parts){
			SysWebParts webvo  = webPartsService.selectWebPartsById(Long.valueOf(part));
			if(StringUtils.isNotNull(webvo)){
				if(webvo.getRegion()==R.parts1){
					mapparts1.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts2){
					mapparts2.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts3){
					mapparts3.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts4){
					mapparts4.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts5){
					mapparts5.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts6){
					mapparts6.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts7){
					mapparts7.put(webvo.getId()+"", webvo.getName());
				}
				
				if(webvo.getRegion()==R.parts8){
					mapparts8.put(webvo.getId()+"", webvo.getName());
				}
				
				
			}
		   }
		   jeansVO.setParts1(mapparts1);
		   jeansVO.setParts2(mapparts2);
		   jeansVO.setParts3(mapparts3);
		   jeansVO.setParts4(mapparts4);
		   jeansVO.setParts5(mapparts5);
		   jeansVO.setParts7(mapparts7);
		   jeansVO.setParts8(mapparts8);
		   jeansVO.setParts6(mapparts6);
		   
		   String sizesId=webJean.getSizesId();
		   String[] sizes=sizesId.split(",");
		   Map<String,Object> mapsizes=new HashMap<String, Object>();
		   for(String size:sizes){
			SysWebSizes webvo  = webSizesService.selectSysWebSizesById(Long.valueOf(size));
			if(StringUtils.isNotNull(webvo)){
				
				mapsizes.put(webvo.getId()+"", webvo.getSize());
			}
		   }
		   jeansVO.setSizes(mapsizes);
		   
		   
		   
		   String threadsId=webJean.getThreadsId();
		   String[] threads=threadsId.split(",");
		   Map<String,Object> mapthreads=new HashMap<String, Object>();
		   for(String thread:threads){
			SysWebThread webvo  = webThreadService.selectwebThreadById(Long.valueOf(thread));
			if(StringUtils.isNotNull(webvo)){
				
				mapthreads.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setThreads(mapthreads);
		   
		   
		   
		   String washsId=webJean.getWashId();
		   String[] washs=washsId.split(",");
		   Map<String,Object> mapwashs=new HashMap<String, Object>();
		   for(String wash:washs){
			SysWebWash webvo  = webWashService.selectWebWashById(Long.valueOf(wash));
			if(StringUtils.isNotNull(webvo)){
				
				mapwashs.put(webvo.getId()+"", webvo.getName());
			}
		   }
		   jeansVO.setWashs(mapwashs);
		   
		   jeansVOList.add(jeansVO);
	  // }  //for循环
	   
	   model.addAttribute("jeansVOList", jeansVOList);
	   model.addAttribute("num", 1);
   }
    	
        return prefix + "/add";
    }
    
}