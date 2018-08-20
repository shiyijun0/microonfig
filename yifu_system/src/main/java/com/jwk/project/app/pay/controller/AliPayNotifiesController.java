package com.jwk.project.app.pay.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwk.framework.web.service.RedisService;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jwk.common.utils.StringUtils;
import com.jwk.common.utils.alipay.AlipayConfig;
import com.jwk.project.app.pay.domain.PayRecord;
import com.jwk.project.app.pay.service.IAliPayService;
import com.jwk.project.app.pay.service.IPayOrderService;
import com.jwk.project.app.pay.service.IPayRecordService;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;
import com.jwk.project.system.limitconfig.service.ILimitConfigService;
import com.jwk.project.system.order.domain.SysOrder;
import com.jwk.project.system.order.domain.SysWebOrderGoods;
import com.jwk.project.system.order.service.WebOrderGoodsService;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.web.service.ICommonService;

import lombok.extern.slf4j.Slf4j;

/**
 * 支付宝支付通知
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/alipayNotifies")
@Slf4j
public class AliPayNotifiesController {

	@Autowired
	IAliPayService alipayService;

	@Autowired
	IPayRecordService payRecordService;

	@Autowired
	IPayOrderService orderdService;

	@Autowired
	WebOrderGoodsService orderGoodsService;

	@Autowired
	ICommonService commonService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private IPresellJeansService presellJeansService;

	@Autowired
	private ILimitConfigService configService;

	/**
	 * 支付宝服务器通知
	 * 
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequestMapping("/backend")
	@ResponseBody
	public String backend(HttpServletRequest request, HttpServletResponse resp)
			throws NumberFormatException, Exception {
		log.info("支付宝支付回调=============");

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)

		String out_trade_no = request.getParameter("out_trade_no");// 商户订单号
		String trade_no = request.getParameter("trade_no");// 支付宝交易号
		String trade_status = request.getParameter("trade_status");// 交易状态

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		// 计算得出通知验证结果
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
				"RSA2");
		System.out.println("订单id=====" + out_trade_no);
		System.out.println("支付宝交易号id=====" + trade_no);
		System.out.println("交易状态=====" + trade_status);
		if (verify_result) {// 验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				// 如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

				// 1.修改订单中的支付状态
				Long orderId = Long.valueOf(out_trade_no);
				SysOrder order = orderdService.selectOrderById(orderId);
				if (order != null) {
					if (order.getPayStatus() == 3) {
						orderdService.updatePayStatus(orderId);
					} else {
						throw new ClientAbortException("支付完成，无需重新支付");
					}
				} else {
					throw new ClientAbortException("找不到订单信息");
				}

				// 2.修改支付记录状态和交易号
				PayRecord record = payRecordService.selectByOrderId(orderId);
				if (record != null) {
					if (record.getStatus() == 0) {
						record.setStatus(1);
						record.setTradeNo(trade_no);
						record.setPayType(2);// 支付类型 1微信支付 2支付宝支付
						payRecordService.savePayRecord(record);
						// payRecordService.updatePayRecord(record.getId());
					} else {
						throw new ClientAbortException("支付完成");
					}
				} else {
					throw new ClientAbortException("找不到支付记录信息");
				}
				redisService.delete("webOrderGoods:" + record.getUserId());

				// 3.修改预售款的库存
				int orderType = order.getOrderType();
				if (1 == orderType) {
					List<SysWebOrderGoods> goodsList = orderGoodsService
							.selectWebOrderGoodsByOrderId(orderId.toString());
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
								} 
								//有无限制配量，都要修改预售款的库存 TODO
								int resultNum = preinfo.getResultNum();
								preinfo.setResultNum((resultNum - buy_num));
								presellJeansService.save(preinfo);
							}
						}
					}
				}
			}
			return "success";
		} else {// 验证失败
			return "fail";
		}
	}

	/**
	 * 支付宝wap前端跳转通知
	 * 
	 * @throws AlipayApiException
	 * @throws ClientAbortException 
	 * @throws ParseException 
	 */
	@GetMapping("/wap/frontend")
	public String wapFrontend(HttpServletRequest request, Model model) throws AlipayApiException, ClientAbortException, ParseException {
		log.info("支付宝wap前端跳转通知=============");

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)

		String out_trade_no = request.getParameter("out_trade_no");// 商户订单号
		String trade_no = request.getParameter("trade_no");// 支付宝交易号

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		// 计算得出通知验证结果
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
				"RSA2");
		if (verify_result) {// 验证成功
			// 请在这里加上商户的业务逻辑程序代码
			// 该页面可做页面美工编辑
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			System.out.println("return_url 验证成功");
			// 1.修改订单中的支付状态
			Long orderId = Long.valueOf(out_trade_no);
			SysOrder order = orderdService.selectOrderById(orderId);
			if (order != null) {
				if (order.getPayStatus() == 3) {
					orderdService.updatePayStatus(orderId);
				} /*else {
					throw new ClientAbortException("支付完成，无需重新支付");
				}
			} else {
				throw new ClientAbortException("找不到订单信息");*/
			}

			// 2.修改支付记录状态和交易号
			PayRecord record = payRecordService.selectByOrderId(orderId);
			if (record != null) {
				if (record.getStatus() == 0) {
					record.setStatus(1);
					record.setTradeNo(trade_no);
					record.setPayType(2);// 支付类型 1微信支付 2支付宝支付
					payRecordService.savePayRecord(record);
				} /*else {
					throw new ClientAbortException("支付完成");
				}
			} else {
				throw new ClientAbortException("找不到支付记录信息");*/
			}
			redisService.delete("webOrderGoods:" + record.getUserId());

			// 3.修改预售款的库存 TODO
			
			if(StringUtils.isNotEmpty(out_trade_no)) {
				return "redirect:/custom/order_details?id=" + out_trade_no;
			}else {
				return "/custom/index";
			}
		} else {
			// 该页面可做页面美工编辑
			System.out.println("return_url 验证失败");
			return "/custom/error";
		}
	}
}
