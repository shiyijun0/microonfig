package com.jwk.project.app.pay.service;

import java.util.Map;

import com.jwk.common.utils.pay.WXH5PayReqVo;
import com.jwk.common.utils.pay.WXPayCallbackResVo;

/**
 * 微信支付管理 服务层
 * 
 * @author Administrator
 *
 */
public interface IWxPayService {

	/**
	 * 微信支付
	 * 
	 * @param open_id
	 * @param record_id
	 * @param user_id
	 * @param wxh5PayReqVo
	 * @return
	 */
	public Map<String, Object> toWxPayMoney(String open_id, Long record_id, Long user_id, WXH5PayReqVo wxh5PayReqVo);

	/**
	 * pc端微信支付
	 * 
	 * @param record_id
	 * @param qrcode_path
	 * @return
	 */
	public String toPCWxPayMoney(Long record_id, Long user_id, String qrcode_path) throws Exception;

	/**
	 * 查询支付状态
	 * 
	 * @param record_id
	 * @return
	 */
	public boolean queryPayRecordStatus(Long record_id);

	/**
	 * 支付回调
	 * 
	 * @param cb
	 * @throws Exception
	 */
	public void updateByWxPayCallback(WXPayCallbackResVo cb) throws Exception;
	
	/**
	 * 订单支付
	 * @return
	 */
	public String orderPay(String orderId,String userId, String addrId, int num, String money, String jeansId);
}
