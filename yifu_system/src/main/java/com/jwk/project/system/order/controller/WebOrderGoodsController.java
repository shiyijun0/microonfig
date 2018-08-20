package com.jwk.project.system.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.weekDate;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.addr.service.IAddrService;
import com.jwk.project.system.area.service.IAreaService;
import com.jwk.project.system.front.domainVO.ConfirmOrderVO;
import com.jwk.project.system.goods.service.FileService;
import com.jwk.project.system.goods.service.GoodsService;
import com.jwk.project.system.order.dao.OrderDao;
import com.jwk.project.system.order.domain.ExpressCompany;
import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.order.domain.SysWebOrderGoods;
import com.jwk.project.system.order.dominVO.CompanyVO;
import com.jwk.project.system.order.dominVO.WebOrderGoodsVO;
import com.jwk.project.system.order.service.CompanyService;
import com.jwk.project.system.order.service.OrderService;
import com.jwk.project.system.order.service.WebOrderGoodsService;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import com.jwk.project.system.web.domain.*;
import com.jwk.project.system.web.service.*;
import com.jwk.project.web.vo.PartsVO;
import com.mzlion.easyokhttp.HttpClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 订单明细信息
 *
 * @author system
 */
@Controller
@RequestMapping("/system/details")
public class WebOrderGoodsController extends BaseController {
    private String prefix = "system/order/details";

    @Autowired
    private IRegisterUserService registerUserService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FileService fileService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private WebOrderGoodsService orderGoodsService;
    @Autowired
    private IAddrService addressService;
    @Autowired
    private IAreaService areaService;

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
    @Autowired
    private IPresellJeansService presellJeansService;

    @RequiresPermissions("system:details:view")
    @GetMapping()
    public String details() {
        return prefix + "/details";
    }

    /**
     * 显示订单明细信息
     */
    @RequiresPermissions("system:details:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Model model) {
        model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        TableDataInfo rows = orderGoodsService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }

    @RequiresPermissions("system:details:remove")
    @Log(title = "系统管理", action = "设计师推荐管理-删除设计师推荐")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id) {
        SysWebOrderGoods sysWebOrderGoods = orderGoodsService.selectWebOrderGoodsById(id);
        if (sysWebOrderGoods == null) {
            return JSON.error("该删除的订单明细不存在");
        }
        if (orderGoodsService.deleteWebOrderGoodsById(id) > 0) {
            //删除有关的订单
           int j= orderService.deleteOrderById(Long.valueOf(sysWebOrderGoods.getOrderId()));
            return JSON.ok();
        }
        return JSON.error();
    }


    @RequiresPermissions("system:details:batchRemove")
    @Log(title = "系统管理", action = "订单明细管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {

        for(Long id:ids){
            SysWebOrderGoods sysWebOrderGoods = orderGoodsService.selectWebOrderGoodsById(id);
            if(sysWebOrderGoods!=null){
                orderService.deleteOrderById(Long.valueOf(sysWebOrderGoods.getOrderId()));
            }
        }

        int rows = orderGoodsService.batchDeleteWebOrderGoods(ids);
        if (rows > 0) {

            return JSON.ok();
        }
        return JSON.error();
    }

    //查询订单明细详细信息
    @RequiresPermissions("system:details:list")
    @Log(title = "系统管理", action = "订单明细管理-查看订单明细信息")
    @PostMapping("/orderDetails")
    @ResponseBody
    public WebOrderGoodsVO jeansModel(SysWebOrderGoods webOrderGoods) {
        WebOrderGoodsVO webOrderGoodsVO = new WebOrderGoodsVO();
        //查找用户名
        if (StringUtils.isNotNull(webOrderGoods.getUserId())) {
            RegisterUser user = registerUserService.selectRegisterUserById(webOrderGoods.getUserId().longValue());
            if (user != null) {
                webOrderGoodsVO.setUser(user);
            }
        }


        //查找订单支付状态和支付数量和价格
        SysOrder order = new SysOrder();
        order.setOrderId(webOrderGoods.getOrderId());
        List<SysOrder> sysOrder = orderDao.selectOrderByOrder(order);
        if (sysOrder.size() > 0) {
            webOrderGoodsVO.setOrder(sysOrder.get(0));
        }


        //查找成品库名称

        if (StringUtils.isNotNull(webOrderGoods.getJeansId())) {
            //定制款
            if (webOrderGoods.getOrderType() == 0) {

                SysWebDesigner jeans = webDesignerService.selectWebDesignerById(webOrderGoods.getJeansId().longValue());
                if (jeans != null) {
                    //查看成品库的纽扣和尺码
                    SysWebSizes webSize = webSizesService.selectSysWebSizesById(Long.valueOf(jeans.getSizesId()));

                    SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(jeans.getButtonsId()));

                    if (webSize != null) {
                        jeans.setSizesId(webSize.getSize() + "");
                    }

                    if (webButton != null) {
                        jeans.setButtonsId(webButton.getName());
                    }
                    webOrderGoodsVO.setWebDesigner(jeans);
                }
            } else if (webOrderGoods.getOrderType() == 1) {
                //预售款
                SysPresellJeans resellJeans = presellJeansService.selectById(webOrderGoods.getJeansId().longValue());
                if (resellJeans != null) {
                    SysWebDesigner jeans = new SysWebDesigner();
                    jeans.setName(resellJeans.getName());
                    jeans.setSizesId("");
                    jeans.setButtonsId("");
                    webOrderGoodsVO.setWebDesigner(jeans);
                }
            }
        }

        if (StringUtils.isNotNull(webOrderGoodsVO)) {
            return webOrderGoodsVO;
        } else {
            return null;
        }
    }

    /**
     * 查看订单信息
     */
    @RequiresPermissions("system:details:check")
    @Log(title = "系统管理", action = "订单详细管理-查看订单详细信息")
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, Model model) {
        model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
        SysWebOrderGoods webOrderGoods = orderGoodsService.selectWebOrderGoodsById(id);
        if (StringUtils.isNotNull(webOrderGoods)) {

            //通过uid查找用户信息
            RegisterUser registerUser = registerUserService.selectRegisterUserById(Long.valueOf(webOrderGoods.getUserId()));
          //  model.addAttribute("registerUser", registerUser);
            model.addAttribute("webOrderGoods", webOrderGoods);

            //显示物流信息
            /*SysOrder order=new SysOrder();
            order.setOrderId(webOrderGoods.getOrderId());
            orderDao.selectOrderByOrder(order);*/
          SysOrder order=  orderService.selectOrderById(Long.valueOf(webOrderGoods.getOrderId()));
          if(order!=null){
              if(order.getPayStatus()==4){
                  //货物已发货，显示物流信息
                  //查找是否有发货状态
                  ExpressCompany company=new ExpressCompany();
                  company.setOrderId(order.getOrderId());
                  ExpressCompany company1=companyService.selectExpressCompanyBycompany(company);
                  if(company1!=null){
                      //通过判断订单状态查看物流信息
                         String temp=System.currentTimeMillis()+"";
                         String postid=company1.getCnumber();//运单号
                          String url=ConfirmOrderVO.kuaidi;//快递100物流网址
                         String type=null; String kuaidi=null;
                          if(company1.getCompany()==1){//顺丰
                             type= "shunfeng";
                              kuaidi="顺丰";
                          }else if(company1.getCompany()==2){//申通
                              type="shentong";
                              kuaidi="申通";
                          }else if(company1.getCompany()==3){//圆通
                              type="yuantong";
                              kuaidi="圆通";
                          }else if(company1.getCompany()==4){//韵达
                              type="yunda";
                              kuaidi="韵达";
                          }else if(company1.getCompany()==5){//中通
                              type="zhongtong";
                              kuaidi="中通";
                          }else if(company1.getCompany()==6){//汇通
                              type="huitongkuaidi";
                              kuaidi="百世汇通";
                          }else if(company1.getCompany()==7){//天天
                              type="tiantian";
                              kuaidi="天天";
                          }
                      registerUser.setKuaidi(kuaidi);

                        //发起请求获取json数据
                      String json = HttpClient.get(url +"?type=" + type + "&postid="+ postid+"&temp="+temp ).execute().asString();
                      //解析json数据
                      JSONObject vOObject=null;String message=null;
                      if(json!=null) {
                      String vOJson=com.alibaba.fastjson.JSON.toJSON(json).toString();
                          vOObject = com.alibaba.fastjson.JSON.parseObject(vOJson);
                          message=vOObject.getString("message");
                          String nu=vOObject.getString("nu");
                          String com=vOObject.getString("com ");
                      }
                     // System.out.println("***webJeansVOObject*******"+vOObject.getString("status"));

                       if(message.equalsIgnoreCase("ok")){
                           List<CompanyVO> companyList=new ArrayList<>();
                           //获取物流信息成功
                           com.alibaba.fastjson.JSONArray result = vOObject.getJSONArray("data");
                           //System.out.println("&&&&&&&&&"+result);
                           if(StringUtils.isNotNull(result)){
                               for (int i = 0; i < result.size(); i++) {
                                   String context = result.getJSONObject(i).getString("context");
                                  // System.out.println("context:" + context);

                                   String time = result.getJSONObject(i).getString("time");
                                   //显示周几
                                   Date date = null;
                                   try {
                                       date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
                                   } catch (ParseException e) {
                                       e.printStackTrace();
                                   }
                                   String year=time.substring(0, 10);
                                   String hour=time.substring(11,16);

                                   String  week = weekDate.getWeekOfDate(date);

                                   CompanyVO companyVO=new CompanyVO(time,context,week,year,hour);
                                   companyList.add(companyVO);
                               }
                               model.addAttribute("companyList",companyList);
                           }

                       }



                  };

              }
          }

            model.addAttribute("registerUser", registerUser);
            if (webOrderGoods.getOrderType() == 0) {
                //定制款 跳入定制款页面
                SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(webOrderGoods.getJeansId().longValue());

                if (webDesigner != null) {
                    SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(webOrderGoods.getColorsId()));
                    if (webColor != null) {
                        webDesigner.setColorsId(webColor.getName());
                        webDesigner.setColorsName(webColor.getCover());
                        model.addAttribute("webColor", webColor);
                    }

                    SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(webOrderGoods.getSizesId()));
                    if (webSizes != null) {
                        webDesigner.setSizesId(webSizes.getSize() + "");
                        webDesigner.setSizesName(webSizes.getDes());
                        model.addAttribute("webSizes", webSizes);
                    }

                    SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(webOrderGoods.getButtonsId()));
                    if (webButton != null) {
                        webDesigner.setButtonsId(webButton.getName() + "");
                        webDesigner.setButtonsName(webButton.getCover());
                        model.addAttribute("webButton", webButton);
                    }

                    SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(webOrderGoods.getThreadsId()));
                    if (webThread != null) {
                        webDesigner.setThreadsId(webThread.getName() + "");
                        webDesigner.setThreadsName(webThread.getCover());
                        model.addAttribute("webThread", webThread);
                    }

                    SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(webOrderGoods.getWashId()));
                    if (webWash != null) {
                        webDesigner.setThreadsId(webWash.getName() + "");
                        webDesigner.setThreadsName(webWash.getCover());
                        model.addAttribute("webWash", webWash);
                    }

                    Map<String, Object> partsName = new HashMap<String, Object>();
                    List<SysWebParts> webPartsList = new ArrayList<SysWebParts>();
                    //配件
                    if (StringUtils.isNotEmpty(webOrderGoods.getPartsId())) {
                        String[] partsId = webOrderGoods.getPartsId().split(",");
                        for (String ids : partsId) {
                            SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(ids));
                            if (webParts != null) {
                                partsName.put(webParts.getName(), webParts.getCover());
                                webPartsList.add(webParts);
                            }

                        }
                        model.addAttribute("webPartsList", webPartsList);
                        webDesigner.setPartsName(partsName);
                    }


                }

                model.addAttribute("webDesigner", webDesigner);
                return prefix + "/designer";
            } else if (webOrderGoods.getOrderType() == 1) {
                //跳转到预售款页面
                SysPresellJeans resellJeans = presellJeansService.selectById(webOrderGoods.getJeansId().longValue());
                model.addAttribute("resellJeans", resellJeans);


                if (resellJeans != null) {
                    SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(webOrderGoods.getColorsId()));
                    if (webColor != null) {
                        resellJeans.setColorsId(webColor.getName());
                        resellJeans.setColorsName(webColor.getCover());
                        model.addAttribute("webColor", webColor);
                    }

                    SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(webOrderGoods.getSizesId()));
                    if (webSizes != null) {
                        resellJeans.setSizesId(webSizes.getSize() + "");
                        resellJeans.setSizesName(webSizes.getDes());
                        model.addAttribute("webSizes", webSizes);
                    }



                }


                return prefix + "/resell";
            }

        }

        return prefix + "/details";
    }

    /**
     * 修改订单支付状态
     */
    @RequiresPermissions("system:details:edit")
    @Log(title = "订单管理", action = "订单管理-修改订单支付状态")
    @GetMapping("/edit/{orderId}")
    public String edit(@PathVariable("orderId") Long orderId, Model model) {
        //查找订单支付状态和支付数量和价格
        SysOrder order = new SysOrder();
        order.setOrderId(orderId + "");
        List<SysOrder> sysOrderList = orderDao.selectOrderByOrder(order);
        if (sysOrderList.size() > 0) {
            SysOrder sysOrder = sysOrderList.get(0);
            model.addAttribute("sysOrder", sysOrder);
        }

        return prefix + "/edit";
    }


    @RequiresPermissions("system:details:edit")
    @Log(title = "系统管理", action = "订单明细管理-更改支付状态")
    @PostMapping("/payStatus")
    @ResponseBody
    public JSON payStatus(SysOrder order) {
       // int rows = orderService.saveOrder(order);
        int rows = orderService.updateOrderByPayStatus(order);
        if (rows > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", order.getPayStatus());
            map.put("orderId", order.getOrderId());
            map.put("id", order.getId());

            ExpressCompany company=new ExpressCompany();
            company.setOrderId(order.getOrderId());
            ExpressCompany expressCompany=companyService.selectExpressCompanyBycompany(company);

            if(expressCompany!=null){
                //删除表
                companyService.deleteExpressCompanyById(expressCompany.getId().longValue());
            }

            return JSON.ok(map);
        }
        return JSON.error();
    }

    /**
     * 增加订单物流信息
     */
    @RequiresPermissions("system:details:edit")
    @Log(title = "订单管理", action = "订单管理-修改订单支付状态")
    @GetMapping("/deliveryInfo/{id}")
    public String deliveryInfo(@PathVariable("id") Long id, Model model) {
        //查找订单支付状态和支付数量和价格
       /* SysOrder order = new SysOrder();
        order.setOrderId(orderId + "");
        List<SysOrder> sysOrderList = orderDao.selectOrderByOrder(order);
        if (sysOrderList.size() > 0) {
            SysOrder sysOrder = sysOrderList.get(0);
            model.addAttribute("sysOrder", sysOrder);

        }*/
        SysOrder sysOrder=  orderDao.selectOrderById(id);
        if(sysOrder!=null){
            model.addAttribute("sysOrder", sysOrder);
        }

        return prefix + "/delivery";
    }


    @RequiresPermissions("system:details:edit")
    @Log(title = "系统管理", action = "订单明细管理-保存物流信息")
    @PostMapping("/saveCompany")
    @ResponseBody
    public JSON saveCompany(ExpressCompany company) {

        ExpressCompany expressCompany=companyService.selectExpressCompanyBycompany(company);

        if(expressCompany!=null){
            //更新表
            company.setId(expressCompany.getId());
        }

        int rows = companyService.saveExpressCompany(company);
        if (rows > 0) {

            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 预览订单信息
     */
    @RequiresPermissions("system:details:check")
    @Log(title = "系统管理", action = "订单详细管理-预览订单效果图")
    @GetMapping("/yulan/{id}")
    public String yulan(@PathVariable("id") Long id, Model model) {
        model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
       Long jeansId=0L;

  SysWebOrderGoods webOrderGoods=orderGoodsService.selectWebOrderGoodsById(id);
        if(webOrderGoods!=null){
            jeansId=webOrderGoods.getJeansId();
            model.addAttribute("id", jeansId);
        }

        List<SysWebSizes> webSizesList = new ArrayList<>();
        List<SysWebColor> webColorList = new ArrayList<>();
        List<SysWebButton> webButtonList = new ArrayList<>();
        List<SysWebThread> webThreadList = new ArrayList<>();
        List<SysWebWash> webWashList = new ArrayList<>();
        List<SysWebParts> webPartsList = new ArrayList<>();
        String webAreaList = null;//区域
        SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(jeansId);
        //我的设计注入相应的值
        if (StringUtils.isNotNull(webDesigner) && StringUtils.isNotNull(webOrderGoods) ) {
            webDesigner.setButtonsId(webOrderGoods.getButtonsId());
            webDesigner.setColorsId(webOrderGoods.getColorsId());
            webDesigner.setPartsId(webOrderGoods.getPartsId());
            webDesigner.setThreadsId(webOrderGoods.getThreadsId());
            webDesigner.setWashId(webOrderGoods.getWashId());
            webDesigner.setSizesId(webOrderGoods.getSizesId());

        }

        model.addAttribute("webDesigner", webDesigner);

        if (StringUtils.isNotNull(webDesigner)) {
            SysWebJeans webjeans = webJeansService.selectWebJeansById(webDesigner.getJeansId().longValue());
            model.addAttribute("webjeans", webjeans);
            // 成品库信息
            SysWebColor webJeansColor = null;
            if(StrUtils.isNotEmpty(webDesigner.getColorsId())){
                webJeansColor = webColorService
                        .selectwebColorById(Long.valueOf(webDesigner.getColorsId()));
            }
            SysWebSizes webJeansSizes = null;
            if(StrUtils.isNotEmpty(webDesigner.getSizesId())){
                webJeansSizes = webSizesService
                        .selectSysWebSizesById(Long.valueOf(webDesigner.getSizesId()));
            }
            SysWebButton webJeansButton = null;
            if(StrUtils.isNotEmpty(webDesigner.getButtonsId())){
                webJeansButton = webButtonService
                        .selectWebButtonById(Long.valueOf(webDesigner.getButtonsId()));
            }
            SysWebThread webJeansThread  = null;
            if(StrUtils.isNotEmpty(webDesigner.getThreadsId())){
                webJeansThread = webThreadService
                        .selectwebThreadById(Long.valueOf(webDesigner.getThreadsId()));
            }
            SysWebWash webJeansWash  = null;
            if(StrUtils.isNotEmpty(webDesigner.getWashId())){
                webJeansWash = webWashService.selectWebWashById(Long.valueOf(webDesigner.getWashId()));
            }

            PartsVO partsVO = new PartsVO();
            String parts = webDesigner.getPartsId();
            String[] partsDesigner = parts.split(",");

            for (String part : partsDesigner) {
                if(part.equalsIgnoreCase("0")){
                    continue;
                }
                SysWebParts webJeansParts = webPartsService.selectWebPartsById(Long.valueOf(part));
                if (webJeansParts.getRegion() == 1) {
                    partsVO.setParts1(webJeansParts);
                }

                if (webJeansParts.getRegion() == 2) {
                    partsVO.setParts2(webJeansParts);
                }

                if (webJeansParts.getRegion() == 3) {
                    partsVO.setParts3(webJeansParts);
                }

                if (webJeansParts.getRegion() == 4) {
                    partsVO.setParts4(webJeansParts);
                }

                if (webJeansParts.getRegion() == 5) {
                    partsVO.setParts5(webJeansParts);
                }

                if (webJeansParts.getRegion() == 6) {
                    partsVO.setParts6(webJeansParts);
                }

                if (webJeansParts.getRegion() == 7) {
                    partsVO.setParts7(webJeansParts);
                }

                if (webJeansParts.getRegion() == 8) {
                    partsVO.setParts8(webJeansParts);
                }
            }

            model.addAttribute("webJeansColor", webJeansColor);
            model.addAttribute("webJeansSizes", webJeansSizes);
            model.addAttribute("webJeansButton", webJeansButton);
            model.addAttribute("webJeansWash", webJeansWash);
            model.addAttribute("webJeansThread", webJeansThread);
            // model.addAttribute("webJeansParts", partsVO);
            model.addAttribute("partsVO", partsVO);
            // 素库表不为空
            if (StringUtils.isNotNull(webjeans)) {

                // 颜色
                String colorString = webjeans.getColorsId();
                if (StringUtils.isNotNull(colorString)) {
                    String[] colorArr = colorString.split(",");
                    for (String colorId : colorArr) {
                        SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(colorId));
                        webColorList.add(webColor);

                    }

                    model.addAttribute("webColorList", webColorList);
                }

                // 尺码
                String sizesString = webjeans.getSizesId();
                if (StringUtils.isNotNull(sizesString)) {
                    String[] sizesArr = sizesString.split(",");
                    for (String sizesId : sizesArr) {
                        SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
                        webSizesList.add(webSizes);

                    }

                    model.addAttribute("webSizesList", webSizesList);
                }

                // 纽扣
                String buttonString = webjeans.getButtonsId();
                if (StringUtils.isNotNull(buttonString)) {
                    String[] buttonArr = buttonString.split(",");
                    for (String buttonId : buttonArr) {
                        SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(buttonId));
                        webButtonList.add(webButton);

                    }
                    model.addAttribute("webButtonList", webButtonList);
                }

                // 破洞
                String washString = webjeans.getWashId();
                if (StringUtils.isNotNull(washString)) {
                    String[] washArr = washString.split(",");
                    for (String washId : washArr) {
                        SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(washId));
                        webWashList.add(webWash);

                    }

                    model.addAttribute("webWashList", webWashList);
                }

                // 边线
                String threadString = webjeans.getThreadsId();
                if (StringUtils.isNotNull(threadString)) {
                    String[] threadArr = threadString.split(",");
                    for (String threadId : threadArr) {
                        SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(threadId));
                        webThreadList.add(webThread);

                    }

                    model.addAttribute("webThreadList", webThreadList);
                }

                // 图片区域
                String partsString = webjeans.getPartsId();
                if (StringUtils.isNotNull(partsString)) {
                    String[] partsArr = partsString.split(",");
                    for (String partsId : partsArr) {
                        SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(partsId));
                        webPartsList.add(webParts);
                        if(webParts!=null){
                            webAreaList+=webParts.getId()+",";
                        }


                    }
                    model.addAttribute("webPartsList", webPartsList);
                }

            }

        }

        // 区域所支持的颜色
        // 转化json格式 String
        // webJeansVOJson=com.alibaba.fastjson.JSON.toJSON(webJeansVO).toString();
        SysWebParts webPartsCover = new SysWebParts();
        webPartsCover.setRegion(1);
        webPartsCover.setStatus(1);
        List<SysWebParts> webParts1 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi1=new ArrayList<>();
        for(SysWebParts webParts:webParts1){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi1.add(webParts);
            }
        }
        model.addAttribute("webParts1", webPartsLi1);

        webPartsCover.setRegion(2);
        List<SysWebParts> webParts2 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi2=new ArrayList<>();
        for(SysWebParts webParts:webParts2){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi2.add(webParts);
            }
        }

        model.addAttribute("webParts2", webPartsLi2);

        webPartsCover.setRegion(3);
        List<SysWebParts> webParts3 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi3=new ArrayList<>();
        for(SysWebParts webParts:webParts3){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi3.add(webParts);
            }
        }

        model.addAttribute("webParts3", webPartsLi3);

        webPartsCover.setRegion(4);
        List<SysWebParts> webParts4 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi4=new ArrayList<>();
        for(SysWebParts webParts:webParts4){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi4.add(webParts);
            }
        }

        model.addAttribute("webParts4", webPartsLi4);
        webPartsCover.setRegion(5);
        List<SysWebParts> webParts5 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi5=new ArrayList<>();
        for(SysWebParts webParts:webParts5){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi5.add(webParts);
            }
        }

        model.addAttribute("webParts5", webPartsLi5);

        webPartsCover.setRegion(6);
        List<SysWebParts> webParts6 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi6=new ArrayList<>();
        for(SysWebParts webParts:webParts6){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi6.add(webParts);
            }
        }

        model.addAttribute("webParts6", webPartsLi6);
        webPartsCover.setRegion(7);
        List<SysWebParts> webParts7 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi7=new ArrayList<>();
        for(SysWebParts webParts:webParts7){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi7.add(webParts);
            }
        }

        model.addAttribute("webParts7", webPartsLi7);

        webPartsCover.setRegion(8);
        List<SysWebParts> webParts8 = webPartsService.selectWebPartsAll(webPartsCover);
        List<SysWebParts> webPartsLi8=new ArrayList<>();
        for(SysWebParts webParts:webParts8){
            if(webAreaList.contains(webParts.getId()+"")){
                webPartsLi8.add(webParts);
            }
        }

        model.addAttribute("webParts8", webPartsLi8);
        return prefix + "/custom";
    }
}