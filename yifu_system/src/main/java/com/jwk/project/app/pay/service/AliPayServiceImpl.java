package com.jwk.project.app.pay.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.alipay.AlipayConfig;
import com.jwk.project.app.pay.dao.IPayOrderDao;
import com.jwk.project.app.pay.dao.IPayRecordDao;
import com.jwk.project.app.pay.domain.PayRecord;
import com.jwk.project.system.order.domain.SysOrder;

@Repository("alipayService")
public class AliPayServiceImpl implements IAliPayService {

	@Autowired
	IPayOrderDao orderDao;

	@Autowired
	IPayRecordDao recordDao;

	/**
	 * 支付宝订单支付
	 */
	@Override
	public String orderPay(String orderId,String userId, String addrId, int num, String money, String jeansId) {
		// 1.下订单
		SysOrder order = orderDao.selectOrderById(Long.valueOf(orderId));
		order.setPayType(2);// 1:微信支付 2:支付宝支付
		order.setRemark("支付宝支付");
		orderDao.updateOrder(order);

		// 2.生产一笔支付记录
		PayRecord payRecord = recordDao.selectByOrderId(Long.valueOf(orderId));
		if(payRecord == null) {
			PayRecord record = new PayRecord();
			record.setJeansId(jeansId);
			record.setMoney(Float.valueOf(money));
			record.setPayType(2);// 1微信支付 2支付宝支付
			record.setStatus(0);// 0待支付 1已支付
			record.setUserId(Long.valueOf(userId));
			record.setOrderId(Long.valueOf(orderId));
			recordDao.insertPayRecord(record);
		}

		// 调用RSA签名方式
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);
		AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

		// 封装请求支付信息

		String timeout_express = "2m";// 超时时间 可空
		String product_code = "QUICK_WAP_WAY";// 销售产品码 必填

		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(orderId.toString());
		model.setSubject("牛仔定制");// 订单名称，必填
		model.setTotalAmount(money);
		model.setBody("支付宝支付测试");// 商品描述，可空
		model.setTimeoutExpress(timeout_express);
		model.setProductCode(product_code);
		alipay_request.setBizModel(model);
		// 设置异步通知地址  支付回调
		alipay_request.setNotifyUrl(AlipayConfig.notify_url);
		// 设置同步地址  支付宝页面跳转同步通知页面
		alipay_request.setReturnUrl(AlipayConfig.return_url);

		// form表单生产
		String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return form;
	}

	@Override
	public String wapPay(String userId, String addrId, int num, String money, String jeansId) {
		// 1.下订单
		Long orderId = StrUtils.generateInstanceID();
		SysOrder order = new SysOrder();
		order.setAddrId(Integer.valueOf(addrId));
		order.setMoney(new BigDecimal(money));
		order.setNum(num);
		order.setOrderId(orderId.toString());
		order.setId(orderId);
		order.setPayStatus(3);// 0失败,1:已支付(待生产) 2：已支付（已生产）3:待支付 4:已发货 5：完成
		order.setUserId(Integer.valueOf(userId));
		order.setPayType(2);// 1:微信支付 2:支付宝支付
		order.setRemark("测试提交支付");
		orderDao.insertOrder(order);

		// 2.生产一笔支付记录
		PayRecord record = new PayRecord();
		record.setJeansId(jeansId);
		record.setMoney(Float.valueOf(money));
		record.setPayType(2);// 1微信支付 2支付宝支付
		record.setStatus(0);// 0待支付 1已支付
		record.setUserId(Long.valueOf(userId));
		record.setOrderId(orderId);
		recordDao.insertPayRecord(record);
		
		return orderId.toString();
	}

}
