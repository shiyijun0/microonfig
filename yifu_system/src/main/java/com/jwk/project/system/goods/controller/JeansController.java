package com.jwk.project.system.goods.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.jwk.common.constant.Enumtype;
import com.jwk.common.constant.Enumtype2;
import com.jwk.common.constant.FileUtil;
import com.jwk.common.utils.JacksonUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.security.ShiroUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.goods.domain.Jeans;
import com.jwk.project.system.goods.domain.JeansAction;
import com.jwk.project.system.goods.domain.JeansFile;
import com.jwk.project.system.goods.domain.JeansSize;
import com.jwk.project.system.goods.service.FileService;
import com.jwk.project.system.goods.service.GoodsService;


/**
 * 商品信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/goodnz")
public class JeansController extends BaseController
{

    private String prefix = "system/goodnz";

    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private FileService fileService;
    
    @RequiresPermissions("system:goodnz:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/goods";
    }

    /**
     * 显示商品信息
     */
    @RequiresPermissions("system:goodnz:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = goodsService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
    /**
     * 新增商品信息
     */
    @RequiresPermissions("system:goodnz:add")
    @Log(title = "系统管理", action = "商品管理-新增商品")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/template";
    }
    
    /**
     * 显示商品信息
     */
    @RequiresPermissions("system:goodnz:list")
    @Log(title = "系统管理", action = "商品管理-显示商品信息")
    @PostMapping("/cover")
    @ResponseBody
    public String cover(Model model,@RequestParam("id") Long id)
    {
    	JeansAction jeansAction=goodsService.selectJeansActionBynzId(id);
    	if(StringUtils.isNotNull(jeansAction)){
    		return jeansAction.getCover();
    	}
        return "0";
    }
    
    /**
     * 新增商品信息
     */
    @RequiresPermissions("system:goodnz:add")
    @Log(title = "系统管理", action = "商品管理-新增商品")
    @GetMapping("/addinfo")
    public String adda(Model model)
    {
        return prefix + "/add";
    }
    
    
    /**
     * 修改商品信息
     */
    @RequiresPermissions("system:goodnz:edit")
    @Log(title = "系统管理", action = "商品管理-修改商品")
    @GetMapping("/edit/{id}")
    public String editAction(@PathVariable("id") Long id,Model model)
    {
    	System.out.println("********第一次进入修改页面");
    	model.addAttribute("id", id);
        return prefix + "/editTemplate";
    }

    /**
     * 修改商品信息
     */
    @RequiresPermissions("system:goodnz:edit")
    @Log(title = "系统管理", action = "商品管理-修改商品")
    @GetMapping("/editinfo/{id}")
    public String editinfo(@PathVariable("id") Long id,Model model)
    {
    	System.out.println("*******进入修改页面"+id);
    	Jeans jeans = goodsService.selectJeansById(id);
        model.addAttribute("jeans", jeans);
        JeansAction jeansAction = goodsService.selectJeansActionBynzId(id);
        model.addAttribute("jeansAction", jeansAction);
        
        //通过牛仔id查询产品尺寸信息
     List<JeansSize>  jeansSizeList= goodsService.selectJeansSizeBynzId(id);     
     model.addAttribute("jeansSizeList", jeansSizeList);
        return prefix + "/edit";
    }
    
    /**
     * 修改商品信息
     */
    @RequiresPermissions("system:goodnz:edit")
    @Log(title = "系统管理", action = "商品管理-修改商品信息")
    @PostMapping("/editAction/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
    	Jeans jeans = goodsService.selectJeansById(id);
        model.addAttribute("jeans", jeans);
        
        JeansAction jeansAction = goodsService.selectJeansActionBynzId(id);
        model.addAttribute("jeansAction", jeansAction);
        return prefix + "/edit";
    }

    
    
    /**
     * 保存商品信息
     */
    @RequiresPermissions("system:goodnz:save")
    @Log(title = "系统管理", action = "商品管理-保存商品")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(String fileUrl, String extraRules,Jeans jeans,HttpServletRequest request)
   {
    	System.out.println("**进入商城控制层*******"+fileUrl);
    	System.out.println("**进入商城控制层*******"+extraRules);
    	Long nzId=ShiroUtils.getUserId();
    	//解析产品尺寸并插入jeans对象
    	Object jsize=null;List<JeansSize> jeansSize=new ArrayList<JeansSize>();
    	if(StringUtils.isNotNull(extraRules)){
    		JSONObject js=null;
			try {
				js = new JSONObject(extraRules);
				jsize=js.get("extraRules");
				if(StringUtils.isNotNull(jsize)){
					JsonNode jsonNode=StringUtils.toJsonNode(jsize.toString());
					for(JsonNode node:jsonNode) {
					if(node.get("sizeImg").asInt()==1){//该尺寸被选中
						int jsiz=node.get("count").asInt();//牛仔裤尺寸
						String des=node.get("content").asText();
						JeansSize size=new JeansSize(nzId.intValue(),jsiz,des);
						jeansSize.add(size);
					}else{
						try {
							throw new Exception("该牛仔裤尺寸尚未选中，请选中尺寸按钮");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					}
				}
				
				jeans.setJeansSize(jeansSize);
				//解析存放图片路径并插入对应对象
				if(StringUtils.isNotNull(fileUrl)){
					String[] url=fileUrl.split(",");
					JeansAction JeansAction=new JeansAction();
					if(url.length==5){//5张图片
						if(!url[0].equals("empty")){
							//JeansAction.setCover(url[0]);//图片插入路径
							//查找图片id
							System.out.println("***********"+url[0]);
							//截取图片名称
							String img=url[0].substring(url[0].lastIndexOf("/")+1);  
							System.out.println("*****img******"+img);
							JeansAction.setCover("/upload/"+img);//图片插入路径暂时放入此路径下
						JeansFile jeanfile=	fileService.selectJeansFileByPath(img);
						if(StringUtils.isNotNull(jeanfile)){
							System.out.println("***********"+jeanfile.getId());
							jeans.setCover(jeanfile.getId());
							jeans.setSizeImg(jeanfile.getSize());
						}
							if(!url[1].equals("empty")){
								JeansAction.setType(Enumtype.PC.getIndex());//pc模型
								jeans.setPcmod(url[1]);
							}
							
							if(!url[2].equals("empty")){
								JeansAction.setType(Enumtype.ANDROID.getIndex());//安卓模型
								jeans.setAndroidmod(url[2]);
							}
							
							if(!url[3].equals("empty")){
								JeansAction.setType(Enumtype.IOS.getIndex());//IOS模型
								jeans.setIosmod(url[3]);
							}
						}
						
						//类型是平底还是高跟
						if(StringUtils.isNotNull(jeans.getType())){
							if(jeans.getType().contains("平底")){
								JeansAction.setType2(Enumtype2.FLATBASE.getIndex());//平底鞋
							}else if(jeans.getType().contains("高跟")){
								JeansAction.setType2(Enumtype2.HIGHHEEL.getIndex());//高跟鞋
							}
						}
						//插入商品id  时间
						//JeansAction.setNzId(nzId.intValue());
						JeansAction.setCtime(new Date());
						//把JeansAction对象插入jeans对象里
						jeans.setJeansAction(JeansAction);
					}
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	
        if (goodsService.saveJeans(jeans) > 0)
        {
        	System.out.println("******返回参数*******"+JSON.ok());
            return JSON.ok(); 
        }
        return JSON.error();
    }

    @RequiresPermissions("system:goodnz:remove")
    @Log(title = "系统管理", action = "商品管理-删除商品")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id)
    {
    	Jeans jeans = goodsService.selectJeansById(id);
        if (jeans == null)
        {
            return JSON.error("该商品不存在");
        }
        if (goodsService.deleteJeansById(id) > 0 && goodsService.deleteJeansActionBynzId(id)>0 && goodsService.deleteJeansSizeById(id)>0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:goodnz:batchRemove")
    @Log(title = "系统管理", action = "商品管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = goodsService.batchDeleteJeans(ids);
        
        int rowsAction = goodsService.batchDeleteJeansAction(ids);
        
        int rowsSize = goodsService.batchDeleteJeansSizeById(ids);
        
        if (rows > 0 && rowsAction>0 &&  rowsSize>0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }


  
    //保存模板文件上传
    @RequiresPermissions("system:goodnz:save")
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
		    	       String path="F:\\gi_workspace\\jwk_system\\src\\main\\resources\\static\\upload/";//上传到暂时此文件夹里
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
    
    //修改模板文件上传 ,@RequestParam("id") Long id 
    @RequiresPermissions("system:goodnz:edit")
   // @RequestMapping(value = "/editUpload", method = RequestMethod.POST)
    @PostMapping("/editUpload")
	@ResponseBody
	public String editUpload(HttpServletRequest request) {
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
		    	       
		    		  String path="F:\\gi_workspace\\jwk_system\\src\\main\\resources\\static\\upload/";//上传到暂时此文件夹里
		    		  
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