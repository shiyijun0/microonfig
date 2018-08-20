package com.jwk.project.web.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.domain.CouponDetails;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.DateUtils;
import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.service.RedisService;
import com.jwk.project.app.pay.service.IPayOrderService;
import com.jwk.project.system.addr.domain.Addr;
import com.jwk.project.system.addr.service.IAddrService;
import com.jwk.project.system.area.domain.Area;
import com.jwk.project.system.area.service.IAreaService;
import com.jwk.project.system.coupon.domain.CouponUser;
import com.jwk.project.system.coupon.service.ICouponService;
import com.jwk.project.system.front.domainVO.ConfirmOrderVO;
import com.jwk.project.system.front.domainVO.OrderAndGoodVO;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;
import com.jwk.project.system.limitconfig.service.ILimitConfigService;
import com.jwk.project.system.order.domain.ExpressCompany;
import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.order.domain.SysWebOrderGoods;
import com.jwk.project.system.order.service.CompanyService;
import com.jwk.project.system.order.service.OrderService;
import com.jwk.project.system.order.service.WebOrderGoodsService;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebJeans;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebPosition;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebJeansService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebPositionService;
import com.jwk.project.system.web.service.WebSizesService;
import com.jwk.project.web.dto.CartOrderDTO;
import com.jwk.project.web.service.ICartService;
import com.jwk.project.web.service.ICommonService;
import com.jwk.project.web.vo.CartsVO;
import com.mzlion.easyokhttp.HttpClient;

@Controller
@RequestMapping("/custom")
public class MyOrderController {

	private String prefix = "custom";
	@Autowired
	private RedisService redisService;

	@Autowired
	private CompanyService companyService;// 快递公司
	@Autowired
	private IPresellJeansService presellJeansService;// 预售款牛仔裤信息
	@Autowired
	private IAddrService addrService;// 用户地址信息
	@Autowired
	private IPayOrderService payOrderService;// 用户下单
	@Autowired
	private WebOrderGoodsService orderGoodsService;// 订单商品

	@Autowired
	private ILimitConfigService limitConfigService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ICommonService commonService;
	@Autowired
	private ICartService cartService;// 购物车

	@Autowired
	private WebPositionService webPositionService;

	@Autowired // 优惠券
	private ICouponService couponService;

	@Autowired
	private WebJeansService webJeansService;
	@Autowired
	private WebPartsService webPartsService;
	@Autowired
	private WebSizesService webSizesService;
	@Autowired
	private WebColorService webColorService;

	@Autowired
	private WebOrderGoodsService webOrderGoodsService;

	@Autowired
	private IAddrService addressService;
	@Autowired
	private IAreaService areaService;

	/**
	 * 我的订单
	 * 
	 * @return
	 */
	@GetMapping("/my_order")
	public String myOrder(Model model, @RequestParam(name = "payStatus", required = false) Integer payStatus,
			@RequestParam(name = "orderIndex", required = false) Integer orderIndex) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotNull(user)) {
			model.addAttribute("userId", user.getId());
		} else {
			return prefix + "/index";
		}

		List<SysOrder> orderList = null;
		if (StringUtils.isNull(payStatus)) {
			// orderList = orderService.selectOrderAll();
			SysOrder order = new SysOrder();
			order.setUserId(user.getId().intValue());
			orderList = orderService.selectOrderByOrder(order);
		} else {
			SysOrder order = new SysOrder();
			order.setPayStatus(payStatus);
			order.setUserId(user.getId().intValue());
			orderList = orderService.selectOrderByOrder(order);
		}

		if (orderList.size() > 0) {
			Collections.reverse(orderList);
			model.addAttribute("orderList", orderList);

		}

		return prefix + "/my_order";
	}

	/**
	 * 订单详情
	 * 
	 * @return
	 */
	@GetMapping("/order_details")
	public String orderDetails(Model model, @RequestParam(name = "id", required = false) Long id) {

		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNull(user)) {
			return prefix + "/index";
		}

		ConfirmOrderVO confirmOrder = new ConfirmOrderVO();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotNull(id)) {
			SysOrder order = orderService.selectOrderById(id);
			if (StringUtils.isNotNull(order)) {
				Addr addrs = addressService.selectAddrById(order.getAddrId().longValue());
				String addrdetail = null;// 详细地址
				if (StringUtils.isNotNull(addrs)) {
					addrdetail = addrs.getAddr();
					long provinceId = addrs.getProvinceId();
					Area sysAreaProvince = areaService.selectAreaById(provinceId);

					long cityId = addrs.getCityId();
					Area sysAreaCity = areaService.selectAreaById(cityId);
					confirmOrder.setAddrId(order.getOrderId());
					String sysName = "";
					if (StringUtils.isNotNull(addrs.getAreaId())) {
						long areaId = addrs.getAreaId();
						Area sysArea = areaService.selectAreaById(areaId);
						sysName = sysArea.getName();
					}

					confirmOrder.setAddr(sysAreaProvince.getName() + "  " + sysAreaCity.getName() + "  " + sysName
							+ "  " + addrdetail);

				}
				if (addrs != null) {
					confirmOrder.setName(addrs.getLinkman());
					confirmOrder.setMobile(addrs.getMobile());
				}
				confirmOrder.setSizes(order.getSize());
				confirmOrder.setColors(order.getColor());
				confirmOrder.setJeansName(order.getJeansName());
				confirmOrder.setActualPayment(order.getMoney().doubleValue());
				confirmOrder.setCount(order.getNum());
				confirmOrder.setFreight(10.0);
				confirmOrder.setPrice(order.getMoney().doubleValue() - 10);// 小计

				confirmOrder.setCreateTime(df.format(order.getCreateTime()));
				if (StringUtils.isNotNull(order.getPayTime())) {
					confirmOrder.setPayTime(df.format(order.getPayTime()));
				}

				// confirmOrder.setDeliveryTime(df.format(order.getPayTime()));// 发货时间
				// 查找是否有发货状态
				ExpressCompany company = new ExpressCompany();
				company.setOrderId(id.toString());
				ExpressCompany company1 = companyService.selectExpressCompanyBycompany(company);
				if (company1 != null) {
					confirmOrder.setDeliveryTime(df.format(company1.getCtime()));// 发货时间

					// 通过判断订单状态查看物流信息
					if (order.getPayStatus() == 4) { // 处于已发货中，
						confirmOrder.setTemp(System.currentTimeMillis() + "");
						confirmOrder.setPostid(company1.getCnumber());
						confirmOrder.setUrl(ConfirmOrderVO.kuaidi);
						if (company1.getCompany() == 1) {// 顺丰
							confirmOrder.setType("shunfeng");
						} else if (company1.getCompany() == 2) {// 申通
							confirmOrder.setType("shentong");
						} else if (company1.getCompany() == 3) {// 圆通
							confirmOrder.setType("yuantong");
						} else if (company1.getCompany() == 4) {// 韵达
							confirmOrder.setType("yunda");
						} else if (company1.getCompany() == 5) {// 中通
							confirmOrder.setType("zhongtong");
						} else if (company1.getCompany() == 6) {// 汇通
							confirmOrder.setType("huitongkuaidi");
						} else if (company1.getCompany() == 7) {// 天天
							confirmOrder.setType("tiantian");
						}
					}
				}
				;

				// 订单交易状态
				if (order.getPayStatus() == 1) {
					confirmOrder.setPayStatus("待生产");
				} else if (order.getPayStatus() == 2) {
					confirmOrder.setPayStatus("已生产");
				} else if (order.getPayStatus() == 4) {
					confirmOrder.setPayStatus("已发货");
				} else if (order.getPayStatus() == 5) {
					confirmOrder.setPayStatus("已收货");
				} else if (order.getPayStatus() == 3) {
					confirmOrder.setPayStatus("待支付");
				} else if (order.getPayStatus() == 0) {
					confirmOrder.setPayStatus("订单失败");
				}

				// 通过订单号查询订单详细列表
				// SysWebOrderGoods orderGoods =
				// getWebOrderGoodsDao().selectWebOrderGoodsByOrderId(order.getOrderId());
				List<SysWebOrderGoods> orderGoodsList = webOrderGoodsService
						.selectWebOrderGoodsByOrderId(order.getOrderId());
				/* if(!orderGoodsList.isEmpty()) { */
				if (orderGoodsList != null && orderGoodsList.size() > 0) {
					for (SysWebOrderGoods orderGoods : orderGoodsList) {
						String parts = orderGoods.getPartsId();
						String[] part = parts.split(",");
						Map<String, Object> webPart = new HashMap<String, Object>();

						for (String par : part) {
							SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(par));
							if (StringUtils.isNotNull(webParts)) {
								SysWebPosition position = new SysWebPosition();
								position.setPosition(webParts.getRegion());
								position.setType(webParts.getType());
								SysWebPosition webPosition = webPositionService
										.selectSysWebPositionByPosition(position);
								if (StringUtils.isNotNull(webPosition)) {
									webPart.put(webPosition.getDes(), webParts.getPrice().doubleValue());
								}
							}
						}
						confirmOrder.setParts(webPart);
					}
				}

				model.addAttribute("confirmOrder", confirmOrder);
			}

		}

		return prefix + "/order_details";
	}

	// 物流信息
	@GetMapping("/logistics_details")
	public String list(Model model, @RequestParam(name = "id") Long id) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNull(user)) {
			return prefix + "/index";
		}
		ConfirmOrderVO confirmOrder = new ConfirmOrderVO();
		SysOrder order = orderService.selectOrderById(id);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (order != null) {
			// 地址
			Addr addrs = addressService.selectAddrById(order.getAddrId().longValue());
			String addrdetail = null;// 详细地址
			if (StringUtils.isNotNull(addrs)) {
				addrdetail = addrs.getAddr();
				long provinceId = addrs.getProvinceId();
				Area sysAreaProvince = areaService.selectAreaById(provinceId);

				long cityId = addrs.getCityId();
				Area sysAreaCity = areaService.selectAreaById(cityId);
				confirmOrder.setAddrId(order.getOrderId());
				String sysName = "";
				if (StringUtils.isNotNull(addrs.getAreaId())) {
					long areaId = addrs.getAreaId();
					Area sysArea = areaService.selectAreaById(areaId);
					sysName = sysArea.getName();
				}

				confirmOrder.setAddr(
						sysAreaProvince.getName() + "  " + sysAreaCity.getName() + "  " + sysName + "  " + addrdetail);

			}

			confirmOrder.setName(addrs.getLinkman());
			confirmOrder.setMobile(addrs.getMobile());
			// 查找是否有发货状态
			confirmOrder.setId(id.toString());
			ExpressCompany company = new ExpressCompany();
			company.setOrderId(id.toString());
			ExpressCompany company1 = companyService.selectExpressCompanyBycompany(company);
			if (company1 != null) {
				confirmOrder.setDeliveryTime(df.format(company1.getCtime()));// 发货时间
				// 通过判断订单状态查看物流信息
				if (order.getPayStatus() == 4) { // 处于已发货中，
					confirmOrder.setTemp(System.currentTimeMillis() + "");
					confirmOrder.setPostid(company1.getCnumber());
					confirmOrder.setUrl(ConfirmOrderVO.kuaidi);
					if (company1.getCompany() == 1) {// 顺丰
						confirmOrder.setTypeName("顺丰快递");
						confirmOrder.setType("shunfeng");
					} else if (company1.getCompany() == 2) {// 申通
						confirmOrder.setTypeName("申通快递");
						confirmOrder.setType("shentong");
					} else if (company1.getCompany() == 3) {// 圆通
						confirmOrder.setTypeName("圆通快递");
						confirmOrder.setType("yuantong");
					} else if (company1.getCompany() == 4) {// 韵达
						confirmOrder.setTypeName("韵达快递");
						confirmOrder.setType("yunda");
					} else if (company1.getCompany() == 5) {// 中通
						confirmOrder.setTypeName("中通快递");
						confirmOrder.setType("zhongtong");
					} else if (company1.getCompany() == 6) {// 汇通
						confirmOrder.setTypeName("百世汇通");
						confirmOrder.setType("huitongkuaidi");
					} else if (company1.getCompany() == 7) {// 天天
						confirmOrder.setTypeName("天天快递");
						confirmOrder.setType("tiantian");
					}

					// 发起请求获取json数据
					String json = HttpClient
							.get(confirmOrder.getUrl() + "?type=" + confirmOrder.getType() + "&postid="
									+ confirmOrder.getPostid() + "&temp=" + confirmOrder.getTemp())
							.execute().asString();
					// 解析json数据
					if (json != null) {
						String vOJson = com.alibaba.fastjson.JSON.toJSON(json).toString();
						JSONObject vOObject = com.alibaba.fastjson.JSON.parseObject(vOJson);
						model.addAttribute("vOObject", vOObject);
						confirmOrder.setJSONObject(vOObject);
					}
				}
			}
			model.addAttribute("confirmOrder", confirmOrder);

		}
		return prefix + "/logistics_details";
	}

	// 我的订单
	@RequestMapping(value = "/allOrder")
	@ResponseBody
	public List<OrderAndGoodVO> allOrder(HttpServletRequest request,
			@RequestParam(name = "id", required = false) String id) {

		List<SysOrder> orders = orderService.selectOrderAll();

		List<OrderAndGoodVO> orderAndGoodVOList = new ArrayList<OrderAndGoodVO>();

		for (SysOrder order : orders) {
			// 查询货物信息
			Long orderId = order.getId();
			// SysWebOrderGoods orderGood =
			// getWebOrderGoodsDao().selectWebOrderGoodsByOrderId(orderId + "");
			List<SysWebOrderGoods> orderGoodsList = webOrderGoodsService
					.selectWebOrderGoodsByOrderId(order.getOrderId());
			if (!orderGoodsList.isEmpty()) {
				for (SysWebOrderGoods orderGood : orderGoodsList) {
					OrderAndGoodVO vo = new OrderAndGoodVO();
					vo.setCreateTime(order.getCreateTime());
					vo.setMoney(order.getMoney().setScale(2));
					vo.setPayStatus(order.getPayStatus());
					// 解析json数据
					if (StringUtils.isNotEmpty(orderGood.getSizesId())) {
						SysWebSizes webSizes = webSizesService
								.selectSysWebSizesById(Long.valueOf(orderGood.getSizesId()));
						if (StringUtils.isNull(webSizes)) {
							vo.setSize(String.valueOf(webSizes.getSize()));
						}
						SysWebColor webColor = webColorService
								.selectwebColorById(Long.valueOf(orderGood.getColorsId()));
						if (StringUtils.isNull(webColor)) {
							vo.setColor(webColor.getName());
						}
					}

					orderAndGoodVOList.add(vo);
				}
			}
		}

		return orderAndGoodVOList;
	}

	/**
	 * 订单删除
	 * 
	 * @return
	 */
	@PostMapping("/my_order_delete")
	@ResponseBody
	public JSON myOrderDelete(Model model, @RequestParam(name = "id") Long id) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");

		if (StringUtils.isNull(user)) {
			return JSON.error("该用户尚未登录，请登录");
		}

		SysOrder order = orderService.selectOrderById(id);

		if (order == null) {
			return JSON.error("该删除的订单不存在");
		} else {
			int i = orderService.deleteOrderById(id);
			// 同时删除订单货物详细明细
			int j = webOrderGoodsService.deleteWebOrderGoodsByOrderId(Long.valueOf(order.getOrderId()));

			if (i > 0 && j > 0) {
				return JSON.ok();
			}
		}

		return JSON.error();
	}

	/**
	 * 预售款，购买
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws ClientAbortException
	 * @throws ParseException
	 */
	@GetMapping("/goOrder")
	public String goOrder(HttpServletRequest request, Model model) throws ClientAbortException, ParseException {
		String presellId = request.getParameter("jeansId");
		String orderType = request.getParameter("orderType");
		String requrl = request.getParameter("requrl");
		if (StringUtils.isNotEmpty(requrl)) {
			ServletUtils.getHttpServletRequest().getSession().setAttribute("requrl", requrl);// 针对订单页返回上一页的请求url
		}
		// 判断是否是当前用户登录下单
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			throw new ClientAbortException("登录超时，请重新登录");
		}
		if (StrUtils.isEmpty(presellId)) {
			throw new ClientAbortException("获取不到商品信息");
		}

		List<SysWebSizes> webSizesList = new ArrayList<SysWebSizes>();
		List<SysWebColor> webColorList = new ArrayList<SysWebColor>();
		List<ConfirmOrderVO> confirmOrderVOList = new ArrayList<ConfirmOrderVO>();
		ConfirmOrderVO confirmOrder = new ConfirmOrderVO();

		String sizes = "";
		String colors = "";
		if (orderType.equals("1")) {// 表示预售款下单
			// 1.查询预售款牛仔裤信息
			SysPresellJeans presellJeans = presellJeansService.selectById(Long.valueOf(presellId));
			if (presellJeans == null) {
				throw new ClientAbortException("获取不到商品信息");
			}
			presellJeans = getConfig(presellJeans);
			confirmOrder.setJeansName(presellJeans.getName());
			if (presellJeans.getSysLimitConfig() == null) {
				confirmOrder.setPrice(Double.valueOf(presellJeans.getPrice()));
			} else {
				confirmOrder.setPrice(Double.valueOf(presellJeans.getSysLimitConfig().getPrice()));
			}
			sizes = presellJeans.getSizesId();
			colors = presellJeans.getColorsId();

		} else {// 表示定制款下单
			SysWebOrderGoods webOrderGoods = (SysWebOrderGoods) redisService
					.getToObj("webOrderGoods:" + user.getId().toString());
			if (webOrderGoods == null) {
				throw new ClientAbortException("获取不到定制商品的信息");
			}
			// 1.查询定制素裤牛仔裤信息
			SysWebJeans webjeans = webJeansService.selectWebJeansById(Long.valueOf(presellId));
			if (webjeans == null) {
				throw new ClientAbortException("获取不到定制牛仔的信息");
			}
			confirmOrder.setJeansName(webjeans.getName());
			confirmOrder.setPrice(webOrderGoods.getPrice().doubleValue());
			colors = webjeans.getColorsId();
			sizes = webjeans.getSizesId();
			String colorId = webOrderGoods.getColorsId();
			model.addAttribute("colorId", colorId);// 针对定制款返回
			if (StringUtils.isNotEmpty(colorId)) {
				SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(colorId));
				if (webColor != null) {
					model.addAttribute("color", webColor.getName());// 针对定制款返回
				}
			}

		}

		confirmOrder.setJeansId(presellId);
		// 2.查询牛仔裤对应的尺码
		webSizesList = commonService.getSizes(sizes);
		model.addAttribute("webSizesList", webSizesList);

		// 3.查询牛仔裤对应的颜色
		webColorList = commonService.getColors(colors);
		model.addAttribute("webColorList", webColorList);

		// 4.查询用户地址
		Addr addrs = null;
		String addrId = request.getParameter("addrId");
		if (StrUtils.isEmpty(addrId)) {// 默认选择默认地址
			addrs = addrService.selectDeafultByUserId(user.getId());
		} else {
			addrs = addressService.selectAddrById(Long.valueOf(addrId));
		}
		if (StringUtils.isNotNull(addrs)) {
			long provinceId = addrs.getProvinceId();
			Area sysAreaProvince = areaService.selectAreaById(provinceId);

			long cityId = addrs.getCityId();
			Area sysAreaCity = areaService.selectAreaById(cityId);
			confirmOrder.setAddrId(addrs.getAddrId() + "");
			String sysName = "";
			if (StringUtils.isNotNull(addrs.getAreaId())) {
				long areaId = addrs.getAreaId();
				Area sysArea = areaService.selectAreaById(areaId);
				sysName = sysArea.getName();
			}

			confirmOrder.setAddr(sysAreaProvince.getName() + sysAreaCity.getName() + sysName);
			if (StringUtils.isNotNull(user)) {
				confirmOrder.setName(addrs.getLinkman());
				confirmOrder.setMobile(addrs.getMobile());

			}
		}

		confirmOrderVOList.add(confirmOrder);
		model.addAttribute("confirmOrderVOList", confirmOrderVOList);
		if (StringUtils.isNotNull(confirmOrderVOList) && confirmOrderVOList.size() > 0) {
			model.addAttribute("confirmOrderVO", confirmOrderVOList.get(0));
		}

		// 查询优惠券
		try {
			List<CouponUser> couponUserList = couponService.selectCouponUserById(user.getId());
			//在时间内的优惠券
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = DateUtils.dateTimeStr();
			Date ntime = sdf.parse(time);// 当前系统时间
			List<CouponUser> couponUserList1=new ArrayList<>();
			if(couponUserList.size()>0){
				for( CouponUser couponUser:couponUserList){
					if(StringUtils.isNotEmpty(couponUser.getCtime()) && StringUtils.isNotEmpty(couponUser.getEndtime())){
						int a=ntime.compareTo( sdf.parse(couponUser.getCtime()));
						int b=ntime.compareTo( sdf.parse(couponUser.getEndtime()));
						if(a>=0 && b<0){
  							couponUserList1.add(couponUser);
						}
					}
				}

			}

			model.addAttribute("couponUserList", couponUserList1);
		} catch (Exception e) {

			e.printStackTrace();
		}

		Map<Object, Object> orMap = redisService.hGetAll("usSizeOrColor:" + user.getId());
		model.addAttribute("orSize", orMap.get("orSize"));
		model.addAttribute("orColor", orMap.get("orColor"));
		model.addAttribute("userId", user.getId());
		model.addAttribute("orderType", orderType);
		model.addAttribute("requrl", requrl);
		return prefix + "/order";
	}

	private SysPresellJeans getConfig(SysPresellJeans info) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String time = DateUtils.dateTimeStr();
		Date ntime = sdf.parse(time);// 当前系统时间

		SysLimitConfig config = limitConfigService.selectUseByPresellId(info.getId());
		if (config != null) {
			if (config.getLimited() == 0)
				config.setLimited(info.getInventory());// 如果没有限量，就默认是库存量
			String startTime = config.getStartTime();
			String endTime = config.getEndTime();
			// 1.限时开始时间，但没有限时结束时间
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isEmpty(endTime)) {
				Date stime = sdf.parse(startTime);
				if (stime.getTime() >= ntime.getTime()) {// 如果限时开始时间大于当前时间
					info.setSysLimitConfig(config);
				}
			}
			// 2.没有限时开始时间，有限时结束时间
			if (StringUtils.isEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				Date etime = sdf.parse(endTime);
				if (etime.getTime() <= ntime.getTime()) {// 如果限时结束时间小于当前时间
					info.setSysLimitConfig(config);
				}
			}
			// 3.既限时开始时间也限时结束时间
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				Date stime = sdf.parse(startTime);
				Date etime = sdf.parse(endTime);
				if (stime.getTime() <= ntime.getTime() && etime.getTime() >= ntime.getTime()) {// 如果限时结束时间小于当前时间
					info.setSysLimitConfig(config);
				}
			}
		}
		return info;
	}

	/**
	 * 确认订单
	 *
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/confirmOrder")
	@ResponseBody
	@Transactional
	public JSON confirmOrder(SysOrder order, SysWebOrderGoods orderGoods, HttpServletRequest request, Model model,@RequestParam(name = "couponId",required = false) Long couponId)
			throws ParseException {

		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("登录超时，请重新登录");
		}
		redisService.delete("usSizeOrColor:" + user.getId());// 删除用户选择的尺码/颜色缓存

		// 1.下订单
		Long orderId = StrUtils.generateInstanceID();// 订单号，唯一
		order.setId(orderId);
		order.setOrderId(String.valueOf(orderId));
		order.setPayStatus(3);// 支付状态,0失败,1:已支付(待生产) 2：已支付（已生产）3:待支付 4:已发货 5：完成
		order.setOrderType(orderGoods.getOrderType());
		int i = payOrderService.insertOrder(order);
		// 预售款需要判断库存
		int orderType = orderGoods.getOrderType();
		int num = 0;
		if (1 == orderType) {
			Long jeansId = orderGoods.getJeansId();
			if (StringUtils.isNull(jeansId)) {
				return JSON.error("找不到商品id");
			}
			// 判断是否有限量配置
			SysPresellJeans preinfo = presellJeansService.selectById(jeansId);
			if (preinfo == null) {
				return JSON.error("找不到商品信息");
			}
			// 判断是否有限量配置
			preinfo = commonService.getLimitConfig(jeansId, preinfo);
			if (preinfo.getSysLimitConfig() != null) {// 有限量配置
				num = preinfo.getSysLimitConfig().getResultNum();
			} else {
				num = preinfo.getResultNum();
			}
			if (order.getNum() > num) {
				return JSON.error("库存不足，只能购买" + num + "件");
			}
		}

		SysWebOrderGoods resultOrderGoods = new SysWebOrderGoods();
		// 2.保存订单商品信息
		if (orderGoods.getOrderType() == 0) {// 定制款订单
			resultOrderGoods = (SysWebOrderGoods) redisService.getToObj("webOrderGoods:" + user.getId().toString());
			/* resultOrderGoods.setSizesId(orderGoods.getSizesId()); */
			// resultOrderGoods.setColorsId(order.getColorsId());
			resultOrderGoods.setSizesId(order.getSizesId());
			resultOrderGoods.setJeansName(orderGoods.getJeansName());
		} else {
			resultOrderGoods = orderGoods;
		}
		resultOrderGoods.setOrderId(orderId.toString());
		resultOrderGoods.setStatus(1);// 状态：1正常，2删除
		resultOrderGoods.setPrice(order.getMoney());
		resultOrderGoods.setNum(order.getNum());
		// orderGoods.setOrderType(1);//下单类型 0定制款 1预售款
		int j = orderGoodsService.saveWebOrderGoods(resultOrderGoods);

		if (i > 0 && j > 0) {
			/*
			 * if (orderGoods.getOrderType() == 0) // 定制款订单
			 * redisService.del("webOrderGoods:" + user.getId());
			 */
        //下单成功后，删除优惠券
			int c=0;
			if(StringUtils.isNotNull(couponId)){
				CouponUser couponUser=new CouponUser();
				couponUser.setId(couponId);
				couponUser.setState(2);
				 c=couponService.updateCouponUser(couponUser);
				if(c==0){
					return JSON.error("优惠券操作失败");
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", order.getOrderId());
			map.put("userId", user.getId());
			map.put("price", order.getMoney().intValue());
			map.put("num", order.getNum());
			map.put("addrId", order.getAddrId());
			return JSON.ok(map);
		}

		return JSON.error("系统发生错误");
	}

	// 是否确认收货
	@PostMapping("/deliveryGoods")
	@ResponseBody
	public JSON deliveryGoods(@RequestParam(name = "id") Long id) {
		SysOrder sysOrder = orderService.selectOrderById(id);
		if (sysOrder != null) {
			sysOrder.setPayStatus(5);
			// 更新订单状态 并删除对应的快递号
			// int row=orderService.saveOrder(sysOrder);
			int row = orderService.updateOrderByPayStatus(sysOrder);
			if (row > 0) {
				ExpressCompany company = new ExpressCompany();
				company.setOrderId(sysOrder.getOrderId());
				ExpressCompany expressCompany = companyService.selectExpressCompanyBycompany(company);

				if (expressCompany != null) {
					// 删除表
					companyService.deleteExpressCompanyById(expressCompany.getId().longValue());
				}
				return JSON.ok();
			}

		}
		return JSON.error();
	}

	/**
	 *
	 * 购物车确认订单
	 *
	 * @return
	 * @throws ClientAbortException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/cartConfirmOrder")
	@ResponseBody
	@Transactional
	public JSON cartConfirmOrder(SysOrder order, HttpServletRequest request, Model model,@RequestParam(name = "couponId",required = false) Long couponId)
			throws ClientAbortException, NumberFormatException, ParseException {

		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("登录超时，请重新登录");
		}
		// 1.下订单
		Long orderId = StrUtils.generateInstanceID();// 订单号，唯一
		order.setId(orderId);
		order.setOrderId(String.valueOf(orderId));
		order.setPayStatus(3);// 支付状态,0失败,1:已支付(待生产) 2：已支付（已生产）3:待支付 4:已发货 5：完成
		order.setOrderType(1);// 下单类型 0 定制款，1预售款
		// int i = payOrderService.insertOrder(order);
		String sizesName = "";
		String colorsName = "";
		String jeansName = "";

		String cartId = request.getParameter("cartId");
		if (StringUtils.isNull(cartId)) {
			return JSON.error("提交失败");
		}
		String[] cartIds = cartId.split(",");

		for (String cart_id : cartIds) {
			if (StrUtils.isEmpty(cart_id)) {
				return JSON.error("购物车id为空");
			}
			CartsVO cartsVO = cartService.selectByCartId(Long.valueOf(cart_id));
			if (cartsVO == null) {
				return JSON.error("提交失败");
			}
			if (cartsVO.getDelFlag() == 1) {
				return JSON.error("请勿重复提交订单");
			}
			int num = 0;
			SysPresellJeans presellJeans = presellJeansService.selectById(cartsVO.getJeansId());
			if (presellJeans == null) {
				throw new ClientAbortException("获取不到商品信息");
			}

			presellJeans = commonService.getLimitConfig(cartsVO.getJeansId(), presellJeans);
			if (presellJeans.getSysLimitConfig() != null) {// 有限量配置
				num = presellJeans.getSysLimitConfig().getResultNum();
			} else {
				num = presellJeans.getResultNum();
			}
			if (order.getNum() > num) {
				return JSON.error("库存不足");
			}

			if (cartsVO.getJeansId().equals(presellJeans.getId())) {
				SysWebOrderGoods orderGoods = new SysWebOrderGoods();
				orderGoods.setColorsId(cartsVO.getColorId().toString());
				orderGoods.setJeansId(cartsVO.getJeansId());
				orderGoods.setJeansName(cartsVO.getJeansName());
				orderGoods.setOrderId(orderId.toString());
				orderGoods.setOrderType(1);// 下单类型 0定制款 1预售款
				orderGoods.setSizesId(cartsVO.getSizeId().toString());
				orderGoods.setStatus(1);// 状态：1正常，2删除
				orderGoods.setUserId(user.getId().intValue());
				orderGoods.setNum(cartsVO.getNum());
				String money = cartsVO.getPrice();
				Double price = 0d;
				if (StringUtils.isNotNull(money)) {
					price = Double.parseDouble(money) * cartsVO.getNum();
				}
				orderGoods.setPrice(BigDecimal.valueOf(price));
				orderGoodsService.saveWebOrderGoods(orderGoods);
				// order.setJeansName(cartsVO.getJeansName());
				jeansName += cartsVO.getJeansName() + ",";
				SysWebSizes webSizes = webSizesService.selectSysWebSizesById(cartsVO.getSizeId().longValue());
				if (webSizes != null) {
					// order.setSize(webSizes.getSize()+"");
					sizesName += webSizes.getSize() + ",";
				}

				SysWebColor webColor = webColorService.selectwebColorById(cartsVO.getColorId().longValue());
				if (webColor != null) {
					// order.setColor(webColor.getName());
					colorsName += webColor.getName() + ",";
				}
			}
		}
		if (jeansName != "") {
			order.setJeansName(jeansName);
		}

		if (sizesName != "") {
			order.setSize(sizesName.substring(0, sizesName.length() - 1));
		}
		if (colorsName != "") {
			order.setColor(colorsName.substring(0, colorsName.length() - 1));
		}

		int i = payOrderService.insertOrder(order);
		if (i > 0) {
			for (String cart_id : cartIds) {
				cartService.updateCartDelFlag(Long.valueOf(cart_id));
			}

			//下单成功后，删除优惠券
			int c=0;
			if(StringUtils.isNotNull(couponId)){
				CouponUser couponUser=new CouponUser();
				couponUser.setId(couponId);
				couponUser.setState(2);
				c=couponService.updateCouponUser(couponUser);
				if(c==0){
					return JSON.error("优惠券操作失败");
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", order.getOrderId());
			map.put("userId", user.getId());
			map.put("price", order.getMoney().intValue());
			map.put("num", order.getNum());
			map.put("addrId", order.getAddrId());
			return JSON.ok(map);
		}
		return JSON.error("系统发生错误");
	}

	/**
	 * 购物车下单
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws ClientAbortException
	 * @throws ParseException
	 */
	@RequestMapping("/cartOrder")
	public String cartOrder(CartOrderDTO cartOrderDTO, HttpServletRequest request, Model model)
			throws ClientAbortException {
		// 判断是否是当前用户登录下单
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			throw new ClientAbortException("登录超时，请重新登录");
		}
		if (cartOrderDTO == null) {
			throw new ClientAbortException("获取不到商品信息");
		}
		if (StrUtils.isEmpty(cartOrderDTO.getCartId())) {
			throw new ClientAbortException("获取不到商品信息");
		}

		List<ConfirmOrderVO> confirmOrderVOList = new ArrayList<ConfirmOrderVO>();
		ConfirmOrderVO confirmOrder = new ConfirmOrderVO();

		String[] cartIds = cartOrderDTO.getCartId().split(",");
		int num = 0;
		Double price = 0d;
		String jeansId = "";
		for (String cart_id : cartIds) {
			if (StrUtils.isEmpty(cart_id)) {
				throw new ClientAbortException("购物车id为空");
			}
			CartsVO cartsVO = cartService.selectByCartId(Long.valueOf(cart_id));
			if (jeansId == "") {
				jeansId = cartsVO.getJeansId().toString();
			} else {
				jeansId = jeansId + "," + cartsVO.getJeansId().toString();
			}
			SysPresellJeans presellJeans = presellJeansService.selectById(cartsVO.getJeansId());
			if (presellJeans == null) {
				throw new ClientAbortException("获取不到商品信息");
			}

			if (cartsVO.getJeansId().equals(presellJeans.getId())) {
				String money = cartsVO.getPrice();
				num += cartsVO.getNum();
				if (StringUtils.isNotNull(money)) {
					Double result = Double.parseDouble(money) * cartsVO.getNum();
					price += result;
				}
			}
		}
		confirmOrder.setPrice(price);
		confirmOrder.setCount(num);
		confirmOrder.setJeansId(jeansId);

		// 4.查询用户地址
		Addr addrs = null;
		if (StrUtils.isEmpty(cartOrderDTO.getAddrId())) {// 默认选择默认地址
			addrs = addrService.selectDeafultByUserId(user.getId());
		} else {
			addrs = addressService.selectAddrById(Long.valueOf(cartOrderDTO.getAddrId()));
		}
		if (StringUtils.isNotNull(addrs)) {
			long provinceId = addrs.getProvinceId();
			Area sysAreaProvince = areaService.selectAreaById(provinceId);

			long cityId = addrs.getCityId();
			Area sysAreaCity = areaService.selectAreaById(cityId);
			confirmOrder.setAddrId(addrs.getAddrId() + "");
			String sysName = "";
			if (StringUtils.isNotNull(addrs.getAreaId())) {
				long areaId = addrs.getAreaId();
				Area sysArea = areaService.selectAreaById(areaId);
				sysName = sysArea.getName();
			}

			confirmOrder.setAddr(sysAreaProvince.getName() + sysAreaCity.getName() + sysName);
			if (StringUtils.isNotNull(user)) {
				confirmOrder.setName(addrs.getLinkman());
				confirmOrder.setMobile(addrs.getMobile());

			}
		}

		confirmOrderVOList.add(confirmOrder);
		model.addAttribute("confirmOrderVOList", confirmOrderVOList);
		if (StringUtils.isNotNull(confirmOrderVOList) && confirmOrderVOList.size() > 0) {
			model.addAttribute("confirmOrderVO", confirmOrderVOList.get(0));
		}

		// 查询优惠券
		try {
			List<CouponUser> couponUserList = couponService.selectCouponUserById(user.getId());
			//在时间内的优惠券
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = DateUtils.dateTimeStr();
			Date ntime = sdf.parse(time);// 当前系统时间
			List<CouponUser> couponUserList1=new ArrayList<>();
			if(couponUserList.size()>0){
				for( CouponUser couponUser:couponUserList){
					if(StringUtils.isNotEmpty(couponUser.getCtime()) && StringUtils.isNotEmpty(couponUser.getEndtime())){
						int a=ntime.compareTo( sdf.parse(couponUser.getCtime()));
						int b=ntime.compareTo( sdf.parse(couponUser.getEndtime()));
						if(a>=0 && b<0){
							couponUserList1.add(couponUser);
						}
					}
				}

			}

			model.addAttribute("couponUserList", couponUserList1);
		} catch (Exception e) {

			e.printStackTrace();
		}

		model.addAttribute("userId", user.getId());
		model.addAttribute("cartId", cartOrderDTO.getCartId());
		return prefix + "/cart_order";
	}

	//查询优惠券
	@PostMapping("/coupon_user")
	@ResponseBody
	public JSON coupon_user(@RequestParam(name = "id") Long id) {
		CouponUser couponUser=couponService.selectCouUserById(id);
		if (couponUser != null) {
		Long cid=couponUser.getCid();
			CouponDetails couponDetails= couponService.selectCouponDetailsById(cid);
			if(couponDetails!=null){
				Long couponId=couponDetails.getCid();
				Coupon coupon=couponService.selectCouponById(couponId);
				if(coupon!=null){
					//查询折扣
               double zhekou=(coupon.getFull()-coupon.getRates())/coupon.getFull();
         if(zhekou<1 && zhekou>0){
         	//有折扣
			 Map<String,Object> map=new HashMap<>();
			 map.put("zhekou",zhekou);
			 //立马去除优惠券

			 return JSON.ok(map);
		 }
				}
			}


		}
		return JSON.error("暂无折扣");
	}
}
