package com.jwk.project.system.order.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.jwk.common.utils.ExcelCommonUtil;
import com.jwk.common.utils.ExcelUtil;
import com.jwk.common.utils.JacksonUtils;
import com.jwk.common.utils.RandomUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.addr.domain.Addr;
import com.jwk.project.system.addr.service.IAddrService;
import com.jwk.project.system.area.domain.Area;
import com.jwk.project.system.area.service.IAreaService;
import com.jwk.project.system.fashion.domainVO.BuLiao;
import com.jwk.project.system.fashion.domainVO.PatternAll;
import com.jwk.project.system.fashion.domainVO.PatternAll.AreaLimit;
import com.jwk.project.system.fashion.domainVO.SelectSize;
import com.jwk.project.system.goods.service.FileService;
import com.jwk.project.system.goods.service.GoodsService;
import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.order.domain.SysOrderGoods;
import com.jwk.project.system.order.dominVO.OrderGoodsExcle;
import com.jwk.project.system.order.service.OrderGoodsService;
import com.jwk.project.system.order.service.OrderService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;


/**
 * 订单信息
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/order")
public class OrderController extends BaseController
{	
    private String prefix = "system/order";

    @Autowired
    private IRegisterUserService registerUserService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FileService fileService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private IAddrService addressService;
    @Autowired
    private IAreaService areaService;
    
    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 显示订单信息
     */
    @RequiresPermissions("system:order:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = orderService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }
    
   
    
    /**
     * 查询下单用户信息
     */
    @RequiresPermissions("system:order:list")
    @Log(title = "系统管理", action = "订单管理-查看订单信息")
    @PostMapping("/userinfo")
    @ResponseBody
    public String checkByuserId(@RequestParam("userId") Long userId)
    {
    	 RegisterUser registerUser= registerUserService.selectRegisterUserById(userId);
    	String json="";
       if(StringUtils.isNotNull(registerUser)){
    	   json= JacksonUtils.toJsonString(registerUser);
       }
        return json;
    }
    
   
    /**
     * 查看订单信息
     */
    @RequiresPermissions("system:order:list")
    @Log(title = "系统管理", action = "订单管理-查看订单信息")
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model model)
    {
    	SysOrder sysOrder =orderService.selectOrderById(id);
        model.addAttribute("sysOrder", sysOrder);
        //通过uid查找用户信息
        RegisterUser registerUser= registerUserService.selectRegisterUserById(Long.valueOf(sysOrder.getUserId()));
        model.addAttribute("registerUser", registerUser);
        //查找商品信息
        SysOrderGoods sysOrderGoods =orderGoodsService.selectOrderGoodsById(id);
        model.addAttribute("sysOrderGoods", sysOrderGoods);
        
        //查找选中的尺码,并通过json去解析
        String jsonSize=sysOrderGoods.getSelectSize();
        JSONObject jsSize=null;
        //查找选中的布料,并通过json去解析
        String jsonbl=sysOrderGoods.getBuliao();
        JSONObject jsbl=null;
        //查找选中的配件
        String jsonpj=sysOrderGoods.getPatternAll();
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
    	
		//查询物流信息
		if(StringUtils.isNotNull(sysOrder)){
			int addrId=sysOrder.getAddrId();
			Addr sysAddress=addressService.selectAddrById(Long.valueOf(addrId));
			model.addAttribute("sysAddress", sysAddress);
			System.out.println("***用户和电话**"+sysAddress.getLinkman()+"******"+sysAddress.getMobile());
			if(StringUtils.isNotNull(sysAddress)){
				long provinceId=sysAddress.getProvinceId();
				Area sysAreaProvince=areaService.selectAreaById(provinceId);
				model.addAttribute("sysAreaProvince", sysAreaProvince);
				
				long cityId=sysAddress.getCityId();
				Area sysAreaCity=areaService.selectAreaById(cityId);
				model.addAttribute("sysAreaCity", sysAreaCity);
				
				long areaId=sysAddress.getAreaId();
				Area sysArea=areaService.selectAreaById(areaId);
				model.addAttribute("sysArea", sysArea);
				
				System.out.println("***省份地址**"+sysAreaProvince.getName()+"******"+sysAreaCity.getName()+"********"+sysArea.getName());
			}
		}
        return prefix + "/check";
    }

    
  //处理文件上传
    @RequiresPermissions("system:order:view")
    @Log(title = "系统管理", action = "订单管理-保存修改")
    @PostMapping("/upload")
    public String upload()
    {
        return prefix + "/order";
    }
    
    

    @RequiresPermissions("system:order:batchRemove")
    @Log(title = "系统管理", action = "订单管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = orderService.batchDeleteOrder(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
  //导入Excel表格
    @RequiresPermissions("system:order:list")
    @Log(title = "系统管理", action = "订单管理-导出订单表")
   // @RequestMapping(value = "exportfeedback")
    @GetMapping("/exportfeedback/{id}")
   // public String check(@PathVariable("id") Long id, Model model)
    @ResponseBody
    public JSON exportFeedBack(HttpServletResponse response,@PathVariable("id") Long id){
        
    	//Date sDate = new Moment().fromDate(new Date()).toDate();
        String fileName = "订单明细"+RandomUtils.fromTime8()+".xls"; //文件名 
        String sheetName = "订单明细";//sheet名
        
        String []title = new String[]{"公司名称","款式图片前","款式图片后","订单编号","商品编号","商品名称","尺码","数量","布料","1区（右腿前面上方）-1","2区（右腿前面下方）-2"
        		,"3区(左腿前面上方)-1","4区( 左腿前面下方 )-1","5区( 右腿后面上方 )-2","6区( 右腿后面下方 )-1","7区(左腿后面上方)-1","8区(左腿后面下方)-1","9区(右口袋)","10区(左口袋)","11区（工字扣"
        		,"皮牌(暂无)","订单时间","运输方式","客户账号","收货人","手机","收货地址","备注"};//标题
        
        //List<Feedback> list = appinfoService.getAllFeedbackForExcel(searchText, strType, startDate, endDate);//内容list     
        SysOrderGoods sysOrderGoods =orderGoodsService.selectOrderGoodsById(id);
        SysOrder sysOrder =orderService.selectOrderById(id);
        RegisterUser registerUser= registerUserService.selectRegisterUserById(Long.valueOf(sysOrder.getUserId()));
        
        String jsonbl=sysOrderGoods.getBuliao();
        JSONObject jsbl=null;JsonNode jsonNodebl=null;
        if(StringUtils.isNotNull(jsonbl)){
			try {
				jsbl = new JSONObject(jsonbl);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        if(jsbl!=null){
			 jsonNodebl=StringUtils.toJsonNode(jsbl.toString());
			BuLiao buLiao=new BuLiao(jsonNodebl.get("num").asText(),jsonNodebl.get("price").asText(),jsonNodebl.get("attachmentUrl").asText(),jsonNodebl.get("name").asText()
					,jsonNodebl.get("iconPicUrl").asText(),jsonNodebl.get("des").asText());
			System.out.println("*****"+buLiao+"******");
		}
        
      //查找选中的尺码,并通过json去解析
        String jsonSize=sysOrderGoods.getSelectSize();
        JSONObject jsSize=null;JsonNode jsonNodeSize=null;
        if(StringUtils.isNotNull(jsonSize)){
			try {
				jsSize = new JSONObject(jsonSize);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        if(jsSize!=null){
			 jsonNodeSize=StringUtils.toJsonNode(jsSize.toString());
			SelectSize selectSize=new SelectSize(jsonNodeSize.get("descipt").asText(),jsonNodeSize.get("sizeIdx").asText());
			System.out.println("*****"+selectSize+"******");
		}
        
      //查询物流信息
        Addr sysAddress=null; String addr_area="";
      		if(StringUtils.isNotNull(sysOrder)){
      			int addrId=sysOrder.getAddrId();
      			 sysAddress=addressService.selectAddrById(Long.valueOf(addrId));
      			System.out.println("***用户地址**"+sysAddress.getAddr()+"******");
      			long provinceId=sysAddress.getProvinceId();
				Area sysAreaProvince=areaService.selectAreaById(provinceId);
				
				long cityId=sysAddress.getCityId();
				Area sysAreaCity=areaService.selectAreaById(cityId);
								
				long areaId=sysAddress.getAreaId();
			Area sysArea=areaService.selectAreaById(areaId);
					
				addr_area=sysAreaProvince.getName()+","+sysAreaCity.getName()+","+sysArea.getName()+","+sysAddress.getAddr();
				
				System.out.println("***省份地址**"+sysAreaProvince.getName()+"******"+sysAreaCity.getName()+"********"+sysArea.getName());
      		}
        String picBefore=jsonNodebl.get("attachmentUrl").asText();
        String picAfter=jsonNodebl.get("iconPicUrl").asText();//.substring(picBefore.lastIndexOf("/")+1)
        OrderGoodsExcle  orderGoodsExcle=new OrderGoodsExcle("JWK", picBefore, picAfter, sysOrder.getOrderId()+"",
        		sysOrderGoods.getId()+"", sysOrderGoods.getJeansName(), jsonNodeSize.get("sizeIdx").asText(), sysOrder.getNum()+"",jsonNodebl.get("des").asText(), "", "",
        		"", "", "", "", "", "", "", "", "", sysOrderGoods.getSizeId()+"",new SimpleDateFormat("yyyy-MM-dd").format(sysOrder.getCreateTime()),
        		"快递", registerUser.getNickname(), sysAddress.getLinkman(), sysAddress.getMobile(), addr_area, sysAddress.getRemark());
      
        List<OrderGoodsExcle> sysOrderGoodsExcleList=new ArrayList<OrderGoodsExcle>();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysOrderGoodsExcleList.add(orderGoodsExcle);
        String [][]values = new String[sysOrderGoodsExcleList.size()][];
        for(int i=0;i<sysOrderGoodsExcleList.size();i++){
            values[i] = new String[title.length];
            //将对象内容转换成string
            OrderGoodsExcle obj = sysOrderGoodsExcleList.get(i);
            values[i][0] = obj.getCompanyName();
            values[i][1] = obj.getPicBefore();
            values[i][2] = obj.getPicAfter();
        
            values[i][3] = obj.getOrderId();
            values[i][4] = obj.getGoodsId();
            values[i][5] = obj.getGoodsName();
            values[i][6] = obj.getSize();
            values[i][7] = obj.getNum();
            values[i][8] = obj.getBuliao();
            
            values[i][9] = obj.getFq_a();
            values[i][10] = obj.getFq_b();
            values[i][11] = obj.getFq_c();
            values[i][12] = obj.getFq_d();
            values[i][13] = obj.getFq_e();
            values[i][14] = obj.getFq_f();
            values[i][15] = obj.getFq_g();
            values[i][16] = obj.getFq_h();
            values[i][17] = obj.getFq_i();
            values[i][18] = obj.getFq_j();
            values[i][19] = obj.getFq_k();
            
            values[i][20] = obj.getCard();
            values[i][21] = obj.getOrderTime();
            values[i][22] = obj.getTransport();
            values[i][23] = obj.getAccount();
            values[i][24] = obj.getContact();
            values[i][25] = obj.getMobile();
            values[i][26] = obj.getAddress();
            values[i][27] = obj.getRemark();
        }
        
       
        
        HSSFWorkbook wb = ExcelCommonUtil.getHSSFWorkbook(sheetName, title, values, null);
        
        //将文件存到指定位置  
        try {  
             this.setResponseHeader(response, fileName);  
             OutputStream os = response.getOutputStream();  
             wb.write(os);  
             os.flush();  
             os.close();  
        } catch (Exception e) {  
             e.printStackTrace();
             return JSON.error();
        }  
        return JSON.ok();
    }
    
     public void setResponseHeader(HttpServletResponse response, String fileName) {  
         try {  
              try {  
                   fileName = new String(fileName.getBytes(),"ISO8859-1");  
              } catch (UnsupportedEncodingException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
              }  
              response.setContentType("application/octet-stream;charset=ISO8859-1");  
              response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
              response.addHeader("Pargam", "no-cache");  
              response.addHeader("Cache-Control", "no-cache");  
         } catch (Exception ex) {  
              ex.printStackTrace();  
         }  
    } 
 
}