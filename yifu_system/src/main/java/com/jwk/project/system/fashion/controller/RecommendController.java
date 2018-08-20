package com.jwk.project.system.fashion.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.jwk.common.utils.HttpUtils;
import com.jwk.common.utils.JacksonUtils;
import com.jwk.common.utils.Moment;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommend;
import com.jwk.project.system.fashion.domain.SysRecommendBg;
import com.jwk.project.system.fashion.domain.SysRecommendConfig;
import com.jwk.project.system.fashion.domainVO.BuLiao;
import com.jwk.project.system.fashion.domainVO.PatternAll;
import com.jwk.project.system.fashion.domainVO.PatternAll.AreaLimit;
import com.jwk.project.system.fashion.domainVO.SelectSize;
import com.jwk.project.system.fashion.service.RecommendBgService;
import com.jwk.project.system.fashion.service.RecommendConfigService;
import com.jwk.project.system.fashion.service.RecommendService;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansFile;
import com.jwk.project.system.goods.domain.SysFq;
import com.jwk.project.system.goods.service.FileService;
import com.jwk.project.system.goods.service.GoodsService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;


/**
 * 设计师推荐信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/recommend")
public class RecommendController extends BaseController
{	
    private String prefix = "system/recommend";

    @Autowired
    private RecommendService recommendService;
    @Autowired
    private RecommendBgService recommendBgService;
    @Autowired
    private RecommendConfigService recommendConfigService;
    @Autowired
    private IRegisterUserService registerUserService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FileService fileService;
    
    @RequiresPermissions("system:recommend:view")
    @GetMapping()
    public String recommend()
    {
        return prefix + "/recommend";
    }

    /**
     * 显示设计师推荐信息
     */
    @RequiresPermissions("system:recommend:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = recommendService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
      
    
    @RequiresPermissions("system:recommend:list")
    @Log(title = "系统管理", action = "设计师推荐管理-查看设计师推荐问题答案")
    //@RequestMapping("/question/{recommendId}")
    @PostMapping("/question")
    @ResponseBody
   // public String question(@PathVariable("recommendId") Long recommendId)
    public String question(@RequestParam("recommendId") Long recommendId)
    {
    	List<SysRecommendBg> sysrecommendBgList = recommendBgService.selectSysRecommendBgByrecommendId(recommendId);
        if (sysrecommendBgList == null)
        {
            return "该设计师推荐的问题不存在";
        }else{      	
        	//System.out.println("***sysrecommendBg***"+sysrecommendBgList);
        	/*SysRecommendConfig	recommendConfig=recommendConfigService.selectSysRecommendConfigById(recommendId);
        	Map<String,Object> map=new HashMap<String, Object>();*/
        	   // Map集合转化为json二位数组
              /*map.put("recommendConfig", recommendConfig);
              map.put("sysrecommendBgList", sysrecommendBgList);*/
        	 return JacksonUtils.toJsonString(sysrecommendBgList);
        	
        	
        }
       
           
        
    }
    
    
    @RequiresPermissions("system:recommend:list")
    @Log(title = "系统管理", action = "设计师推荐管理-查看设计款限量限时限价")
    @PostMapping("/recommendConfig")
    @ResponseBody
    public String recommendConfig(@RequestParam("recommendId") Long recommendId)
    {
    	SysRecommendConfig	recommendConfig=recommendConfigService.selectSysRecommendConfigById(recommendId);
        if (recommendConfig == null)
        {
            return "0";
        }else{      	
        	
        	 return JacksonUtils.toJsonString(recommendConfig);
        	
        	
        }
       
           
        
    }
    
    
    /**
     * 通过goodid查询商品信息
     */
    @RequiresPermissions("system:recommend:list")
    @Log(title = "系统管理", action = "商品管理-修改商品信息")
    @PostMapping("/checkByGoodId")
    @ResponseBody
    public Integer checkByGoodId(@RequestParam("id") Long id)
    {
    	Jeans jeans = goodsService.selectJeansById(id);
    	int stauts=0;
       if(StringUtils.isNotNull(jeans)){
    	   stauts=jeans.getStatus();
       }
        return stauts;
    }
    
    /**
     * 新增设计师推荐款信息
     */
    @RequiresPermissions("system:recommend:add")
    @Log(title = "系统管理", action = "设计师推荐管理-增加设计款限量限时限价")
    @GetMapping("/add")
    public String add(Model model)
    {
   List<SysRecommend>  sysRecommendList=recommendService.selectSysRecommendAll();
   model.addAttribute("sysRecommendList", sysRecommendList);
        return prefix + "/add";
    }
    
    /**
     * 保存设计款限量限时限价信息
     */
    @RequiresPermissions("system:recommend:save")
    @Log(title = "系统管理", action = "设计师推荐管理-保存设计款限量限时限价")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(SysRecommendConfig sysRecommendConfig,@RequestParam(name = "sTime", required = false) String sTime,
			@RequestParam(name = "eTime", required = false) String eTime)
    {
    		/*if(StringUtils.isNotNull(sTime) && StringUtils.isNotNull(eTime)){
    			if(new Moment().fromTime(sTime).toDate().after(new Moment().fromTime(eTime).toDate())){
    				JSON.error(1,"限时结束时间不能小于开始时间");
    			}
    		}
    	*/
		if(StringUtils.isNotNull(sTime)){
			Date sDate = new Moment().fromTime(sTime).toDate();
			sysRecommendConfig.setStartTime(sDate);
		}
		
		if(StringUtils.isNotNull(eTime)){
			Date eDate = new Moment().fromTime(eTime).toDate();
			sysRecommendConfig.setEndTime(eDate);
		}
		
		//通过设计师推荐款式名字查找对应id
	/*SysRecommend recommend=recommendService.selectSysRecommendByGoodsName(recommendName);
	if(StringUtils.isNotNull(recommend)){
		sysRecommendConfig.setRecommendId(Long.valueOf(recommend.getId()));;
	}*/
        if (recommendConfigService.saveSysRecommendConfig(sysRecommendConfig) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    
    
    /**
     * 查看设计师推荐信息
     */
    @RequiresPermissions("system:recommend:check")
    @Log(title = "系统管理", action = "设计师推荐管理-编辑设计款限量限时限价信息")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	
    	
    	
    	SysRecommend sysRecommend = recommendService.selectSysRecommendById(id);
    	if(StringUtils.isNotNull(sysRecommend)){
    		model.addAttribute("sysRecommend", sysRecommend);
    		
   	}else{
   		return prefix + "/error";
   	}
    	
    	SysRecommendConfig sysRecommendConfig=recommendConfigService.selectSysRecommendConfigById(id);
    	if(StringUtils.isNotNull(sysRecommendConfig)){
    		 model.addAttribute("sysRecommendConfig", sysRecommendConfig);
    	}else{
    		return prefix + "/error";
    	}
        
    	return prefix + "/edit";
    }
    
    /**
     * 查看设计师推荐信息
     */
    @RequiresPermissions("system:recommend:check")
    @Log(title = "系统管理", action = "设计师推荐管理-查看设计师推荐信息")
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model model)
    {
    	SysRecommend sysRecommend = recommendService.selectSysRecommendById(id);
        model.addAttribute("sysRecommend", sysRecommend);
        //通过uid查找用户信息
        RegisterUser registerUser= registerUserService.selectRegisterUserById(Long.valueOf(sysRecommend.getUid()));
        model.addAttribute("registerUser", registerUser);
        //查找选中的尺码,并通过json去解析
        String jsonSize=sysRecommend.getSelectSize();
        JSONObject jsSize=null;
        //查找选中的布料,并通过json去解析
        String jsonbl=sysRecommend.getBuliao();
        JSONObject jsbl=null;
        //查找选中的配件
        String jsonpj=sysRecommend.getPatternAll();
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

    
  //处理文件上传
    @RequiresPermissions("system:recommend:list")
    @Log(title = "系统管理", action = "设计师推荐管理-效果图上传保存")
   // @PostMapping("/checkByGoodId")
   // @ResponseBody
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public  String uploadImg(@RequestParam("file") MultipartFile file,
    		@RequestParam("recommendId") Long recommendId,HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = request.getSession().getServletContext().getRealPath("goods\\")+ "picfile\\";
        System.out.println("**filePath*****"+filePath);
        //修改文件名
        String prefixImg = fileName.substring(fileName.lastIndexOf(".") + 1);
	 	String custName = "" + System.currentTimeMillis() + "." + prefixImg;
        try {
        	FileUtil.uploadFile(file.getBytes(), filePath, custName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //修改并保存文件路径
        SysRecommend sysRecommend =new  SysRecommend();
        sysRecommend.setId(recommendId.intValue());
        sysRecommend.setSizeImage(filePath+custName);
        int a = recommendService.saveSysRecommend(sysRecommend);
       if(a>0){
    	   System.out.println("****文件保存成功***"+a);
       }
        return prefix + "/recommend";
    }
    
    
    @RequiresPermissions("system:recommend:remove")
    @Log(title = "系统管理", action = "设计师推荐管理-删除设计师推荐")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	SysRecommend Sysrecommend = recommendService.selectSysRecommendById(id);
        if (Sysrecommend == null)
        {
            return JSON.error("该设计师推荐不存在");
        }
        if (recommendService.deleteSysRecommendById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:recommend:batchRemove")
    @Log(title = "系统管理", action = "设计师推荐管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = recommendService.batchDeleteSysRecommend(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  
  //保存模板文件上传
    @RequiresPermissions("system:recommend:save")
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