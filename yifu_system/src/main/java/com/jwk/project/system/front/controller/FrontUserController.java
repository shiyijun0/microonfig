package com.jwk.project.system.front.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.addr.domain.Addr;
import com.jwk.project.system.addr.service.IAddrService;
import com.jwk.project.system.area.domain.Area;
import com.jwk.project.system.area.service.IAreaService;
import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.service.ICouponService;
import com.jwk.project.system.front.common.Route;
import com.jwk.project.system.front.domain.SysWebBanner;
import com.jwk.project.system.front.domainVO.ConfirmOrderVO;
import com.jwk.project.system.front.domainVO.OrderAndGoodVO;
import com.jwk.project.system.front.service.WebBannerService;
import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.order.domain.SysOrderGoods;
import com.jwk.project.system.order.service.OrderGoodsService;
import com.jwk.project.system.order.service.OrderService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebDesigner;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;
import com.jwk.project.system.web.domainVO.SysWebJeansVO;
import com.jwk.project.system.web.service.WebButtonService;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebDesignerService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebSizesService;
import com.jwk.project.system.web.service.WebThreadService;
import com.jwk.project.system.web.service.WebWashService;

/**
 * 商品信息
 * 
 * @author system
 */
@Controller
@RequestMapping(Route.PATH + Route.Uesr.PATH)
public class FrontUserController {
	private String prefix = "custom";

	@Autowired
	private IRegisterUserService registerUserService;

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService orderGoodsService;

	@Autowired
	private WebBannerService webBannerService;

	@Autowired
	private WebDesignerService webDesignerService;
	@Autowired
	private WebColorService webColorService;
	@Autowired
	private WebSizesService webSizesService;
	@Autowired
	private WebButtonService webButtonService;
	@Autowired
	private WebWashService webWashService;
	@Autowired
	private WebThreadService webThreadService;
	@Autowired
	private WebPartsService webPartsService;
	@Autowired
	private IAddrService addrService;
	@Autowired
	private IAreaService areaService;

	@Autowired // 优惠券
	private ICouponService couponService;

	// 显示个人资料
	@RequestMapping(value = "/userInfo")
	@ResponseBody
	public RegisterUser userInfo(HttpServletRequest request, @RequestParam(name = "id", required = false) String id) {

		RegisterUser registerUser = registerUserService.selectRegisterUserById(68L);

		return registerUser;
	}

	// 修改用户信息资料
	@RequestMapping(value = "/userEdit")
	@ResponseBody
	public JSON userEdit(HttpServletRequest request, RegisterUser registerUser) {

		int flag = registerUserService.saveRegisterUser(registerUser);

		if (flag > 0) {
			JSON.ok();
		}
		return JSON.error();
	}

	// 我的账号资料
	@RequestMapping(value = "/myAccount")
	@ResponseBody
	public SysOrder myAccount(HttpServletRequest request, @RequestParam(name = "id", required = false) String id) {

		SysOrder order = orderService.selectOrderById(1L);
		// 通过订单查询优惠券
		Coupon coupon = couponService.selectCouponById(Long.valueOf(order.getCouponId().longValue()));

		order.setCoupon(coupon);

		return order;
	}

	// 我的设计
	@RequestMapping(value = "/myDesign")
	@ResponseBody
	public SysWebJeansVO myDesign(HttpServletRequest request, @RequestParam(name = "id", required = false) String id) {

		SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(29L);
		SysWebParts webParts = null;
		if (StringUtils.isNotNull(webDesigner)) {

			webParts = new SysWebParts();
			webParts.setType(webDesigner.getType());
			webParts.setStatus(1);
		}

		List<SysWebSizes> webSizesList = new ArrayList<SysWebSizes>();
		List<SysWebColor> webColorList = new ArrayList<SysWebColor>();
		List<SysWebButton> webButtonList = new ArrayList<SysWebButton>();
		List<SysWebThread> webThreadList = new ArrayList<SysWebThread>();
		List<SysWebWash> webWashList = new ArrayList<SysWebWash>();
		List<SysWebParts> webPartsList = new ArrayList<SysWebParts>();

		// 颜色
		String colorString = webDesigner.getColorsId();
		if (StringUtils.isNotNull(colorString)) {
			String[] colorArr = colorString.split(",");
			for (String colorId : colorArr) {
				SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(colorId));
				webColorList.add(webColor);

			}

		}

		// 尺码
		String sizesString = webDesigner.getSizesId();
		if (StringUtils.isNotNull(sizesString)) {
			String[] sizesArr = sizesString.split(",");
			for (String sizesId : sizesArr) {
				SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
				webSizesList.add(webSizes);

			}

		}

		// 纽扣
		String buttonString = webDesigner.getButtonsId();
		if (StringUtils.isNotNull(buttonString)) {
			String[] buttonArr = buttonString.split(",");
			for (String buttonId : buttonArr) {
				SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(buttonId));
				webButtonList.add(webButton);

			}

		}

		// 破洞
		String washString = webDesigner.getWashId();
		if (StringUtils.isNotNull(washString)) {
			String[] washArr = washString.split(",");
			for (String washId : washArr) {
				SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(washId));
				webWashList.add(webWash);

			}

		}

		// 边线
		String threadString = webDesigner.getThreadsId();
		if (StringUtils.isNotNull(threadString)) {
			String[] threadArr = threadString.split(",");
			for (String threadId : threadArr) {
				SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(threadId));
				webThreadList.add(webThread);

			}

		}

		// 图片区域
		String partsString = webDesigner.getPartsId();
		if (StringUtils.isNotNull(partsString)) {
			String[] partsArr = partsString.split(",");
			for (String partsId : partsArr) {
				webParts = webPartsService.selectWebPartsById(Long.valueOf(partsId));
				webPartsList.add(webParts);

			}

		}

		SysWebJeansVO webJeansVO = new SysWebJeansVO(webSizesList, webColorList, webButtonList, webThreadList,
				webPartsList, webWashList);

		return webJeansVO;

	}

	// 全部订单
	@RequestMapping(value = "/allOrder")
	@ResponseBody
	public List<OrderAndGoodVO> allOrder(HttpServletRequest request,
			@RequestParam(name = "id", required = false) String id) {

		List<SysOrder> orders = orderService.selectOrderAll();

		List<OrderAndGoodVO> orderAndGoodVOList = new ArrayList<OrderAndGoodVO>();

		for (SysOrder order : orders) {
			// 查询货物信息
			Long orderId = order.getId();
			SysOrderGoods orderGood = orderGoodsService.selectOrderGoodsByOrderId(orderId);
			if (StringUtils.isNotNull(orderGood)) {
				OrderAndGoodVO vo = new OrderAndGoodVO();
				vo.setName(orderGood.getJeansName());
				vo.setCreateTime(order.getCreateTime());
				vo.setMoney(order.getMoney().setScale(2));
				vo.setPayStatus(order.getPayStatus());
				// 解析json数据
				if (StringUtils.isNotEmpty(orderGood.getSelectSize())) {
					String VOJson = com.alibaba.fastjson.JSON.toJSON(orderGood.getSelectSize()).toString();
					JSONObject VOObject = com.alibaba.fastjson.JSON.parseObject(VOJson);
					System.out.println("******" + VOObject.getString("sizeIdx"));
					vo.setSize(VOObject.getString("sizeIdx"));

				}
				vo.setColor("红色");
				orderAndGoodVOList.add(vo);
			}
		}

		return orderAndGoodVOList;
	}

	// 选中下单
	@RequestMapping(value = "/selectOrder")
	@ResponseBody
	public ConfirmOrderVO selectOrder(HttpServletRequest request,
			@RequestParam(name = "id", required = false) Long id) {

		ConfirmOrderVO confirmOrder = new ConfirmOrderVO();

		List<SysWebSizes> webSizeList = webSizesService.selectSysWebSizesAll();
		// 所有的尺寸
		String sizes = "";
		for (SysWebSizes websize : webSizeList) {
			int size = websize.getSize().intValue();
			sizes += Route.addString(size + "");
		}

		confirmOrder.setSizes(sizes.substring(0, sizes.length() - 1));
		System.out.println("****sizes****" + sizes);

		List<SysWebColor> webColorList = webColorService.selectwebColorAll();
		// 所有的颜色
		String colors = "";
		for (SysWebColor webcolor : webColorList) {
			String colorName = webcolor.getName();
			colors += Route.addString(colorName);
		}
		confirmOrder.setColors(colors);
		System.out.println("****colors****" + colors);

		SysOrder sysOrder = orderService.selectOrderById(1l);
		// 物流信息：
		// 查询物流信息
		if (StringUtils.isNotNull(sysOrder)) {
			int addrId = sysOrder.getAddrId();
			Addr sysAddress = addrService.selectAddrById(Long.valueOf(addrId));

			System.out.println("***用户和电话**" + sysAddress.getLinkman() + "******" + sysAddress.getMobile());
			if (StringUtils.isNotNull(sysAddress)) {
				long provinceId = sysAddress.getProvinceId();
				Area sysAreaProvince = areaService.selectAreaById(provinceId);

				long cityId = sysAddress.getCityId();
				Area sysAreaCity = areaService.selectAreaById(cityId);

				long areaId = sysAddress.getAreaId();
				Area sysArea = areaService.selectAreaById(areaId);

				// 通过订单中userid查询用户信息
				RegisterUser registerUser = registerUserService
						.selectRegisterUserById(sysOrder.getUserId().longValue());
				if (StringUtils.isNotNull(registerUser)) {
					confirmOrder.setName(registerUser.getNickname());
					confirmOrder.setMobile(registerUser.getMobile());

					System.out.println("***省份地址**" + sysAreaProvince.getName() + "******" + sysAreaCity.getName()
							+ "********" + sysArea.getName());
				}

				confirmOrder.setAddr(sysAreaProvince.getName() + sysAreaCity.getName() + sysArea.getName());
			}

		}
		return confirmOrder;
	}

	// 确认下单
	@RequestMapping(value = "/confirmOrder")
	@ResponseBody
	public ConfirmOrderVO confirmOrder(HttpServletRequest request, ConfirmOrderVO confirmOrder) {

		return confirmOrder;
	}

	// 订单详情
	@RequestMapping(value = "/orderDetails")
	@ResponseBody
	public ConfirmOrderVO orderDetails(HttpServletRequest request,
			@RequestParam(name = "id", required = false) Long id) {

		return null;
	}

	// 用户增加地址并保存
	@RequestMapping(value = "/addAddr")
	@ResponseBody
	public JSON addAddr(HttpServletRequest request, @RequestParam(name = "area") String area, Addr addr) {

		String[] carea = area.split(" ");
		int i = 0;
		for (String sarea : carea) {
			i++;
			Area areaDomin = areaService.selectByName(sarea);
			if (i == 1 && StringUtils.isNotNull(areaDomin)) {
				addr.setProvinceId(areaDomin.getId());
			}

			if (i == 2 && StringUtils.isNotNull(areaDomin)) {
				addr.setCityId(areaDomin.getId());
			}
			if (i == 3 && StringUtils.isNotNull(areaDomin)) {
				addr.setAreaId(areaDomin.getId());
			}
		}

		// 保存地址
		int addrId = addrService.saveAddr(addr);
		if (addrId > 0) {
			return JSON.ok();
		} else {
			return JSON.error();
		}
	}

	// 显示轮播图信息
	@RequestMapping(value = "/bannerList")
	public String addAddr(HttpServletRequest request, Model model) {
		List<SysWebBanner> webBannerList = webBannerService.selectWebBannerAll();
		if (StringUtils.isNotNull(webBannerList)) {
			model.addAttribute("webBannerList", webBannerList);
		}
		return prefix + "/banner";
	}
}
