package com.jwk.common.utils.pay;

import java.io.Serializable;

/**
 * 微信支付下载对账单请求参数
 */
public class WXPayDownloadBillReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appid = "";// 公众账号ID
	private String mch_id = "";// 商户号
	private String device_info = "";// 设备号
	private String nonce_str = "";// 随机字符串
	private String sign = "";// 签名
	private String bill_date = "";// 下载对账单的日期，格式：20140603

	/**
	 * 账单类型:<br/>
	 * ALL，返回当日所有订单信息，默认值<br/>
	 * SUCCESS，返回当日成功支付的订单<br/>
	 * REFUND，返回当日退款订单<br/>
	 * REVOKED，已撤销的订单
	 */
	private String bill_type = "";

	/**
	 * @param bill_date
	 *            下载对账单的日期，格式：20140603
	 * @param billTypeEnum
	 *            账单类型
	 */
	public WXPayDownloadBillReqVo(String bill_date, BillTypeEnum billTypeEnum) {
		this.bill_date = bill_date;
		this.bill_type = billTypeEnum.name();

		this.appid = WXPayConfig.APP_ID;
		this.mch_id = WXPayConfig.MCH_ID;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXPayDownloadBillReqVo.class, this));
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}
}
