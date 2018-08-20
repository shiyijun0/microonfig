package com.jwk.common.utils.pay.redpack;

import com.jwk.common.utils.pay.MapUtil;
import com.jwk.common.utils.pay.RandomStringGenerator;
import com.jwk.common.utils.pay.WXPayConfig;
import com.jwk.common.utils.pay.WXSign;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 发生红包请求参数VO
 * 
 */
public class WXRedPackReqVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String wxappid = "";// 公众账号ID:必填
	private String sign = "";// 签名:必填
	private String nonce_str = "";// 随机字符串:必填(随机字符串，不长于32位)
	private String mch_id = "";// 商户号:必填
	private String mch_billno = "";// 商户订单号 必填
	private String send_name = "";// 商户名称 必填
	private String re_openid = "";// 用户openid 必填
	private int total_amount = 0;// 付款金额，单位分 必填
	private int total_num = 1;// 红包发放总人数 必填
	private String wishing = "";// 红包祝福语 必填
	private String client_ip = "";// Ip地址 必填
	private String act_name = "";// 活动名称 必填
	private String remark = "";// 备注 必填
	private String scene_id = "";// 场景id
	private String consume_mch_id = "";// 资金授权商户号
	private String risk_info = "";// 活动信息

	/**
	 * 发生红包请求
	 * 
	 * @param openid
	 * @param mch_billno
	 *            订单号
	 * @param total_amount
	 *            付款金额，单位分
	 * @param wishing
	 *            红包祝福语
	 * @param act_name
	 *            活动名称
	 * @param remark
	 *            备注
	 */
	public WXRedPackReqVo(String openid, String mch_billno, int total_amount, String wishing, String act_name,
			String remark) {
		if (StringUtils.isBlank(openid)) {
			throw new RuntimeException("缺少openid");
		}

		this.wxappid = WXPayConfig.APP_ID;
		this.mch_id = WXPayConfig.MCH_ID;
		this.send_name = WXPayConfig.MCH_NAME;
		this.client_ip = WXPayConfig.IP;
		this.re_openid = openid;
		this.mch_billno = mch_billno;
		this.total_amount = total_amount;
		this.total_num = 1;
		this.wishing = wishing;
		this.act_name = act_name;
		this.remark = remark;

		this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		// 根据API给的签名规则进行签名
		this.sign = WXSign.getSign(MapUtil.objectToMap(WXRedPackReqVo.class, this));
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getConsume_mch_id() {
		return consume_mch_id;
	}

	public void setConsume_mch_id(String consume_mch_id) {
		this.consume_mch_id = consume_mch_id;
	}

	public String getRisk_info() {
		return risk_info;
	}

	public void setRisk_info(String risk_info) {
		this.risk_info = risk_info;
	}

}
