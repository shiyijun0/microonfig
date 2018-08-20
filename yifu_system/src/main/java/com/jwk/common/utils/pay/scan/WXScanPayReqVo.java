package com.jwk.common.utils.pay.scan;

import com.jwk.common.utils.pay.MapUtil;
import com.jwk.common.utils.pay.RandomStringGenerator;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.pay.WXSign;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 微信扫描支付生成支付url的请求参数对象
 */
public class WXScanPayReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appid = "";// 公众账号ID:必填
	private String mch_id = "";// 商户号:必填
	private String device_info = "";// 设备号
	private String nonce_str = "";// 随机字符串:必填(随机字符串，不长于32位)
	private String sign = "";// 签名:必填
	private String body = "";// 商品描述:必填
	private String detail = "";// 商品详情
	private String attach = "";// 附加数据
	private String out_trade_no = "";// 商户订单号:必填(商户系统内部的订单号,32个字符)
	private String fee_type = "";// 货币类型
	private int total_fee = 0;// 总金额:必填(参数支付金额单位为【分】)
	private String spbill_create_ip = "";// 终端IP:必填(机器IP)
	private String time_start = "";// 交易起始时间
	private String time_expire = "";// 交易结束时间
	private String goods_tag = "";// 商品标记
	private String notify_url = "";// 通知地址:必填(接收微信支付异步通知回调地址)
	private String trade_type = "";// 交易类型:必填(JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付)
	private String product_id = "";// 商品ID
	private String limit_pay = "";// 指定支付方式
	private String openid = "";// 用户标识

	/**
	 * 必填的参数:需要先对WXPayConfig 中的参数进行初始化
	 *
	 * @param body
	 *            商品描述
	 * @param out_trade_no
	 *            商户订单号:必填(商户系统内部的订单号,32个字符)
	 * @param total_fee
	 *            总金额:必填(参数支付金额单位为【分】)
	 * @param notify_url
	 *            通知地址:必填(接收微信支付异步通知回调地址)
	 * @param trade_type
	 *            交易类型:必填(JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付)
	 */
	public WXScanPayReqVo(String openid, String body, String out_trade_no, int total_fee, String notify_url,
			String trade_type) {
		if (StringUtils.isBlank(openid)) {
			throw new RuntimeException("缺少openid");
		}

		this.openid = openid;

		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
		this.trade_type = trade_type;

		this.appid = WXPayConfig.APP_ID;
		;
		this.mch_id = WXPayConfig.MCH_ID;
		this.spbill_create_ip = WXPayConfig.IP;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXScanPayReqVo.class, this));
	}

	/**
	 * 必填的参数:需要先对WXPayConfig 中的参数进行初始化
	 *
	 * @param body
	 *            商品描述
	 * @param out_trade_no
	 *            商户订单号:必填(商户系统内部的订单号,32个字符)
	 * @param total_fee
	 *            总金额:必填(参数支付金额单位为【分】)
	 * @param notify_url
	 *            通知地址:必填(接收微信支付异步通知回调地址)
	 * @param trade_type
	 *            交易类型:必填(JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付)
	 */
	public WXScanPayReqVo(String body, String out_trade_no, int total_fee, String notify_url, String trade_type) {
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
		this.trade_type = trade_type;

		this.appid = WXPayConfig.APP_ID;
		this.mch_id = WXPayConfig.MCH_ID;
		this.spbill_create_ip = WXPayConfig.IP;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXScanPayReqVo.class, this));
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
