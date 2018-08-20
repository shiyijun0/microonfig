package com.jwk.project.app.pay.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.pay.WXH5PayReqVo;
import com.jwk.common.utils.pay.WXPay;
import com.jwk.common.utils.pay.WXPayCallbackResVo;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.pay.WXSign;
import com.jwk.common.utils.pay.XMLUtils;
import com.jwk.common.utils.pay.scan.WXScanPayReqVo;
import com.jwk.common.utils.pay.scan.WXScanPayResVo;
import com.jwk.project.app.pay.dao.IPayOrderDao;
import com.jwk.project.app.pay.dao.IPayRecordDao;
import com.jwk.project.app.pay.domain.PayRecord;
import com.jwk.project.app.user.dao.IAppUserDao;
import com.jwk.project.app.user.domain.AppUser;
import com.jwk.project.system.order.domain.SysOrder;

import lombok.extern.slf4j.Slf4j;

@Repository("payService")
@Slf4j
public class WxPayServiceImpl implements IWxPayService {

	@Autowired
	private IPayRecordDao recordDao;

	@Autowired
	private IAppUserDao appUserdao;
	
	@Autowired
	IPayOrderDao orderDao;

	/**
	 * 微信支付
	 */
	@Override
	public Map<String, Object> toWxPayMoney(String open_id, Long record_id, Long user_id, WXH5PayReqVo wxh5PayReqVo) {
		log.info("微信手机支付信息：{}",
				"open_id:" + open_id + "" + "====" + "record_id:" + record_id + "" + "===" + "user_id：" + user_id + "");
		// TODO 1.查询牛仔裤信息 record_id即jeans_id,获取需要支付的金额

		String regMoney = "0.01";// 测试
		Map<String, Object> wxPhonePayMap = new LinkedCaseInsensitiveMap<>();
		// 查询用户信息
		AppUser appUser = appUserdao.selectUserById(user_id);
		log.info("微信手机支付user_id：{}", appUser.getUserId());
		try {
			String wxPayTitle = "定制牛仔裤微信支付";// 支付标题
			log.info("微信手机支付money：{}", regMoney);
			BigDecimal fenMoney = new BigDecimal(regMoney).multiply(new BigDecimal(100));
			String sFenMoney = String.valueOf(fenMoney);
			if (sFenMoney.indexOf(".") != -1) {
				sFenMoney = sFenMoney.substring(0, sFenMoney.indexOf("."));
			}
			int totalFee = Integer.parseInt(String.valueOf(sFenMoney));// 总金额
			log.info("微信手机支付totalFee：{}分", totalFee);// ，单位分

			// 新增一笔支付记录
			PayRecord payRecord = new PayRecord();
			String pay_id = StrUtils.getRandomNumber(6);
			payRecord.setId(Long.valueOf(pay_id));
			//payRecord.setJeansId(record_id);TODO
			payRecord.setMoney(totalFee);
			payRecord.setPayType(1);// 1 表示微信支付 2：表示支付宝支付
			payRecord.setStatus(0);// 0待支付 ：1 支付成功
			payRecord.setUserId(user_id);

			recordDao.insertPayRecord(payRecord);

			log.info("支付记录表id:" + pay_id);

			log.info("微信手机支付回调URL===============", WXPayConfig.PAY_CALLBACK_URL_PC);// ，单位分
			// 报文封装及生成
			WXScanPayReqVo wxScanPayReqVo = new WXScanPayReqVo(open_id, wxPayTitle, pay_id + "", totalFee,
					WXPayConfig.PAY_CALLBACK_URL_PC, "JSAPI");// JSAPI针对微信直接支付 NATIVE针对微信扫二维码后支付

			log.info("微信手机支付xml：{}", XMLUtils.object2XML(wxScanPayReqVo));
			prePayAndFillDataModel(wxPhonePayMap, wxScanPayReqVo);

		} catch (Exception e) {
			log.error("微信预支付出错", e);
		}

		return wxPhonePayMap;
	}

	private void prePayAndFillDataModel(Map<String, Object> wxPhonePayMap, WXScanPayReqVo wxScanPayReqVo)
			throws IOException, SAXException, ParserConfigurationException {

		String timeStamp = String.valueOf((System.currentTimeMillis() / 1000));
		// 微信扫描支付预付码结果报文
		WXScanPayResVo resData = WXPay.prepay4ScanPay(wxScanPayReqVo);
		wxPhonePayMap.put("resData", resData);
		wxPhonePayMap.put("wXPayPkg", "prepay_id=" + resData.getPrepay_id());
		wxPhonePayMap.put("timeStamp", timeStamp);
		Map<String, Object> map4jsPaySign = new HashMap<>();
		map4jsPaySign.put("appId", resData.getAppid());
		map4jsPaySign.put("nonceStr", resData.getNonce_str());
		map4jsPaySign.put("package", "prepay_id=" + resData.getPrepay_id());
		map4jsPaySign.put("signType", "MD5");
		map4jsPaySign.put("timeStamp", timeStamp);
		wxPhonePayMap.put("sign", WXSign.getSign(map4jsPaySign));
	}

	/**
	 * pc端 微信支付
	 */
	@Override
	public String toPCWxPayMoney(Long record_id, Long user_id, String qrcode_path) throws Exception {
		log.info("牛仔裤id:" + record_id);
		log.info("微信扫一扫支付返回二维码路径qrcode_path:" + qrcode_path);
		// TODO 1.查询牛仔裤信息 record_id即jeans_id,获取需要支付的金额
		String regMoney = "0.01";// TODO
		BigDecimal fenMoney = new BigDecimal(regMoney).multiply(new BigDecimal(100));
		String sFenMoney = String.valueOf(fenMoney);
		if (sFenMoney.indexOf(".") != -1) {
			sFenMoney = sFenMoney.substring(0, sFenMoney.indexOf("."));
		}
		int total_money = Integer.parseInt(String.valueOf(sFenMoney));// 总金额
		// TODO 记得实现回调接口

		AppUser appUser = appUserdao.selectUserById(user_id);
		if (StrUtils.isEmpty(appUser.getUserId().toString()))
			throw new ClientAbortException("hrID为空");

		log.info("微信手机支付user_id：{}", appUser.getUserId());

		// 新增一笔支付记录
		PayRecord payRecord = new PayRecord();
		String pay_id = null;
		try {
			pay_id = StrUtils.getRandomNumber(6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		payRecord.setId(Long.valueOf(pay_id));
		//payRecord.setJeansId(record_id);//TODO
		payRecord.setMoney(total_money);
		payRecord.setPayType(1);// 1 表示微信支付 2：表示支付宝支付
		payRecord.setStatus(0);// 0待支付 ：1 支付成功
		payRecord.setUserId(user_id);

		recordDao.insertPayRecord(payRecord);

		log.info("支付记录表id:" + pay_id);

		WXScanPayReqVo wxScanPayReqVo = new WXScanPayReqVo("PC付款单号：" + pay_id, pay_id + "", total_money,
				WXPayConfig.PAY_CALLBACK_URL_PC, "NATIVE");// JSAPI针对微信直接支付 NATIVE针对微信扫二维码后支付
		log.info("微信支付wxScanPayReqVo{}", wxScanPayReqVo);
		WXScanPayResVo resData = null;
		try {
			resData = WXPay.prepay4ScanPay(wxScanPayReqVo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		JSONObject resJson = (JSONObject) JSONObject.toJSON(resData);
		log.info(resJson.toJSONString());
		if (resData.isSuccess()) {
			return resData.getCode_url();
		} else {
			throw new ClientAbortException("调用出错，请重试！");
		}
	}

	/**
	 * 查询支付记录状态
	 */
	@Override
	public boolean queryPayRecordStatus(Long record_id) {
		PayRecord payRecord = recordDao.selectPayRecordById(record_id);
		int status = payRecord.getStatus();
		log.info("查询支付状态：" + record_id + " pay_status:" + status);
		if (status == 1) {// 表示支付成功
			return true;
		}
		return false;
	}

	/**
	 * 微信支付回调
	 */
	@Override
	public void updateByWxPayCallback(WXPayCallbackResVo cb) throws Exception {
		JSONObject json = (JSONObject) JSONObject.toJSON(cb);
		log.info("wx pay callback:" + json.toJSONString());
		if (cb.isSuccess()) {
			String pay_id = cb.getOut_trade_no();
			log.info("微信支付回调,支付记录id:" + pay_id);
			PayRecord payRecord = recordDao.selectPayRecordById(Long.valueOf(pay_id));
			if (payRecord != null) {
				int status = payRecord.getStatus();
				if (status == 0) {
					// 修改支付记录状态
					recordDao.updatePayRecordStatus(Long.valueOf(pay_id));
				} else {
					throw new ClientAbortException("已经支付成功,无需重复操作！");
				}
			} else {
				throw new ClientAbortException("微信支付失败！");
			}

		}
	}

	/**
	 * h5下订单
	 */
	@Override
	public String orderPay(String orderId,String userId, String addrId, int num, String money, String jeansId) {
		// 1.修改订单
		SysOrder order = orderDao.selectOrderById(Long.valueOf(orderId));
		if(order == null)
			try {
				throw new ClientAbortException("修改订单状态失败");
			} catch (ClientAbortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		order.setPayType(1);//1:微信支付 2:支付宝支付
		order.setRemark("微信支付");
		orderDao.updateOrder(order);

		// 2.生产一笔支付记录
		PayRecord payRecord = recordDao.selectByOrderId(Long.valueOf(orderId));
		if (payRecord==null) {
			PayRecord record = new PayRecord();
			record.setJeansId(jeansId);
			record.setMoney(Float.valueOf(money));
			record.setPayType(1);// 1微信支付 2支付宝支付
			record.setStatus(0);// 0待支付 1已支付
			record.setUserId(Long.valueOf(userId));
			record.setOrderId(Long.valueOf(orderId));
			recordDao.insertPayRecord(record);
		}
		
		return orderId.toString();
	}

}
