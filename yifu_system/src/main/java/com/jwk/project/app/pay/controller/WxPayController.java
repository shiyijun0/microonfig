package com.jwk.project.app.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.HttpUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.pay.WXH5PayReqVo;
import com.jwk.common.utils.pay.WXPay;
import com.jwk.common.utils.pay.WXPayCallbackResVo;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.wx.XMLUtil;
import com.jwk.common.utils.wx.test.WechatPayUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.service.RedisService;
import com.jwk.project.app.pay.domain.PayRecord;
import com.jwk.project.app.pay.service.IPayOrderService;
import com.jwk.project.app.pay.service.IPayRecordService;
import com.jwk.project.app.pay.service.IWxPayService;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;
import com.jwk.project.system.limitconfig.service.ILimitConfigService;
import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.order.domain.SysWebOrderGoods;
import com.jwk.project.system.order.service.WebOrderGoodsService;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.web.service.ICommonService;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wxpay")
@Slf4j
public class WxPayController {

	@Autowired
	IWxPayService wxpayService;

	@Autowired
	IPayRecordService payRecordService;

	@Autowired
	IPayOrderService orderdService;

	@Autowired
	private RedisService redisService;
	
	@Autowired
	WebOrderGoodsService orderGoodsService;
	
	@Autowired
	ICommonService commonService;
	
	@Autowired
	private IPresellJeansService presellJeansService;
	
	@Autowired
	private ILimitConfigService configService;

	@RequestMapping(value = "/topay3")
	public String topay(HttpServletRequest request, HttpServletResponse resp, Model model) throws Exception {
		String userId = "178";
		String addrId = "1";
		String num = "1";
		String jeansId = "30";
		int totalFee = 1;

		String body = "微信支付";
		// String orderId = String.valueOf(StrUtils.generateInstanceID());
		// String ip2 = IPAddressUtils.getIpAddr(request);
		// String ip = NetUtils.getLocalhostStr();
		String orderId = wxpayService.orderPay(null, userId, addrId, Integer.parseInt(num), String.valueOf(totalFee),
				jeansId);
		String ip = HttpUtils.getRemoteAddr(request);
		String sceneInfo = WXPayConfig.scene_info;
		WechatPayUtils wechatPayUtils = new WechatPayUtils();
		// String url = wechatPayUtils.payByH5(body,orderId,totalFee,ip,sceneInfo);
		Map<String, Object> map = wechatPayUtils.payByH5(body, orderId, totalFee, ip, sceneInfo);
		System.out.println("map========" + map);
		String result_code = (String) map.get("result_code");
		String mweb_url = (String) map.get("mweb_url");
		String result = "下单失败";
		if (result_code.equals("SUCCESS")) {
			result = "下单成功";
		}
		model.addAttribute("mweb_url", mweb_url);
		model.addAttribute("result", result);
		return "wpsuccess";
	}

	@PostMapping("/topay")
	@ResponseBody
	@Transactional
	public JSON topay1(HttpServletRequest request, HttpServletResponse resp, Model model) throws Exception {
		String userId = request.getParameter("userId");
		String addrId = request.getParameter("addrId");
		String num = request.getParameter("num");
		// String money = request.getParameter("money");
		String money = "0.01";
		String jeansId = request.getParameter("jeansId");
		String orderIds = request.getParameter("orderId");
		if (StrUtils.isEmpty(orderIds) || StrUtils.isEmpty(userId) || StrUtils.isEmpty(addrId)
				|| StrUtils.isEmpty(money) || StrUtils.isEmpty(num) || StrUtils.isEmpty(jeansId)) {
			return JSON.error("下单失败");
		}
		// 查询订单是否已经存在
		SysOrder sysOrder = orderdService.selectOrderById(Long.valueOf(orderIds));
		if (sysOrder != null) {
			if (sysOrder.getPayStatus() == 1)
				return JSON.error("该订单已经支付过");
		}

		BigDecimal fenMoney = new BigDecimal(money).multiply(new BigDecimal(100));
		String sFenMoney = String.valueOf(fenMoney);
		if (sFenMoney.indexOf(".") != -1) {
			sFenMoney = sFenMoney.substring(0, sFenMoney.indexOf("."));
		}
		int totalFee = Integer.parseInt(String.valueOf(sFenMoney));// 总金额 单位： 分
		System.out.println("totalFee======" + totalFee);

		String body = "微信支付";

		String orderId = wxpayService.orderPay(orderIds, userId, addrId, Integer.parseInt(num), String.valueOf(money),
				jeansId);

		String ip = HttpUtils.getRemoteAddr(request);
		String sceneInfo = WXPayConfig.scene_info;
		WechatPayUtils wechatPayUtils = new WechatPayUtils();
		Map<String, Object> map = wechatPayUtils.payByH5(body, orderId, totalFee, ip, sceneInfo);// 微信支付用分
		System.out.println("map========" + map);
		String result_code = (String) map.get("result_code");
		String mweb_url = (String) map.get("mweb_url");
		String result = "下单失败";
		if (result_code.equals("SUCCESS")) {
			result = "下单成功";
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("mweb_url", mweb_url);
			maps.put("result", result);
			return JSON.ok(maps);
		}
		/*
		 * model.addAttribute("mweb_url", mweb_url); model.addAttribute("result",
		 * result);
		 */

		return JSON.error("下单失败");
	}

	/**
	 * 支付返回结果
	 *
	 * @param xmlData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resultPay")
	@ResponseBody
	public String resultPay(@RequestBody String xmlData) throws Exception {
		Map<String, Object> paramsMap = XMLUtil.toMap(xmlData);
		log.info("微信支付返回结果xml=============" + paramsMap);
		String return_code = (String) paramsMap.get("return_code");
		String out_trade_no = (String) paramsMap.get("out_trade_no");
		String transaction_id = (String) paramsMap.get("transaction_id");
		String returnCode = "SUCCESS";
		String returnMsg = "OK";
		if (return_code.equals("SUCCESS")) {// 支付成功
			// 1.修改订单中的支付状态
			Long orderId = Long.valueOf(out_trade_no);
			SysOrder order = orderdService.selectOrderById(orderId);
			if (order != null) {
				String total_fee = (String) paramsMap.get("total_fee");
				int totalFee = Integer.parseInt(total_fee);

				BigDecimal money = order.getMoney();
				int resultMoney = money.multiply(BigDecimal.valueOf(100L)).intValue();
				if (order.getPayStatus() == 3 || totalFee == resultMoney) {
					orderdService.updatePayStatus(orderId);
				}
			} else {
				returnCode = "FAIL";
				returnMsg = "找不到订单信息";
			}

			// 2.修改支付记录状态和交易号
			PayRecord record = payRecordService.selectByOrderId(orderId);
			if (record != null) {
				if (record.getStatus() == 0) {
					record.setStatus(1);
					record.setTradeNo(transaction_id);
					record.setPayType(1);// 支付类型 1微信支付 2支付宝支付
					payRecordService.savePayRecord(record);
				}
			} else {
				returnCode = "FAIL";
				returnMsg = "找不到支付记录信息";
			}
			redisService.delete("webOrderGoods:" + record.getUserId());

			// 3.修改预售款的库存
			int orderType = order.getOrderType();
			if (1 == orderType) {
				List<SysWebOrderGoods> goodsList = orderGoodsService.selectWebOrderGoodsByOrderId(orderId.toString());
				if (!goodsList.isEmpty()) {
					for (SysWebOrderGoods goods : goodsList) {
						Long jeansId = goods.getJeansId();
						int buy_num = goods.getNum();
						SysPresellJeans preinfo = presellJeansService.selectById(jeansId);
						if (preinfo != null) {
							preinfo = commonService.getLimitConfig(jeansId, preinfo);
							if (preinfo.getSysLimitConfig() != null) {// 有限量配置
								SysLimitConfig config = preinfo.getSysLimitConfig();
								int resultNum = config.getResultNum();
								config.setResultNum((resultNum - buy_num));
								configService.saveLimitConfig(config);
							} else {
								int resultNum = preinfo.getResultNum();
								preinfo.setResultNum((resultNum - buy_num));
								presellJeansService.save(preinfo);
							}
						}
					}
				}
			}
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("return_code", returnCode);
		resultMap.put("return_msg", returnMsg);
		return XMLUtil.toXml2(paramsMap);
	}

	/**
	 * PC端微信支付
	 */
	@RequestMapping(value = "/toPCWxPayMoney")
	public String toPCWxPayMoney(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		// TODO 1.获取用户登录的信息，判断是否登录超时或未登录
		String user_id = "";
		String record_id = request.getParameter("id");
		if (StringUtils.isEmpty(record_id))
			throw new ClientAbortException("牛仔裤id不能为空");
		log.info("record_id:" + record_id);

		// 获取二维码
		String qrcode_path = request.getServletContext().getRealPath("/") + "/payqrcode/";
		log.info("qrcode_path:" + qrcode_path);
		String code_url = wxpayService.toPCWxPayMoney(Long.valueOf(record_id), Long.valueOf(user_id), qrcode_path);
		model.addAttribute("record_id", record_id);
		// model.addAttribute("total_money", total_money);
		model.addAttribute("code_url", code_url);
		model.addAttribute("type", "pay_pc");

		// 2.获取相关参数跳到支付页面
		String status = request.getParameter("status");
		String jeans_id = request.getParameter("jeans_id");
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("status", status);
		dataMap.put("jeans_id", jeans_id);

		model.addAttribute("obj", dataMap);
		log.info("跳转地址==============：" + dataMap);
		log.info("code_url:" + code_url);
		return "/templates/app/pcpay";// 去支付页
	}

	/**
	 * 微信支付
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @param wxh5PayReqVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toWxPayMoney")
	public String toWxPayMoney(HttpServletRequest request, HttpServletResponse response, Model model,
			WXH5PayReqVo wxh5PayReqVo) throws Exception {
		// 1.判断用户是否登录（公众号登录）,或登录是否超时
		String open_id = "";
		String user_id = "";
		log.info("获取openid:{}", open_id);
		if (StringUtils.isEmpty(open_id)) {
			throw new ClientAbortException("获取OpenId失败，请重新登录！");
		}
		String record_id = request.getParameter("id");
		if (StringUtils.isEmpty(record_id)) {
			throw new ClientAbortException("支付失败，牛仔裤id为空！");
		}
		log.info("record_id:" + record_id);
		Map<String, Object> wxPhonePayMap = wxpayService.toWxPayMoney(open_id, Long.valueOf(record_id),
				Long.valueOf(user_id), wxh5PayReqVo);
		log.info("toWxPayMoney---------------wxPhonePayMap:" + JSONObject.toJSONString(wxPhonePayMap));
		model.addAttribute("type", "wx_pay");
		model.addAttribute("resData", wxPhonePayMap.get("resData"));
		model.addAttribute("wXPayPkg", wxPhonePayMap.get("wXPayPkg"));
		model.addAttribute("timeStamp", wxPhonePayMap.get("timeStamp"));
		model.addAttribute("sign", wxPhonePayMap.get("sign"));

		Map<String, Object> dataMap = new LinkedCaseInsensitiveMap<>();
		dataMap.put("jeans_id", record_id);
		model.addAttribute("obj", dataMap);
		return "/templates/app/wxpay";
	}

	/**
	 * 微信支付回调
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wxPayCB")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public String wxPayCB(HttpServletRequest request) throws Exception {
		log.info("微信支付回调...........................");
		WXPayCallbackResVo cbVo = WXPay.wxPayCallback(request);
		log.info("微信支付报文..........................." + JSONObject.toJSONString(cbVo));
		wxpayService.updateByWxPayCallback(cbVo);
		return "SUCCESS";
	}

}
