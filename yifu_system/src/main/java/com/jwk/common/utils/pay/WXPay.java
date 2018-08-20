package com.jwk.common.utils.pay;

import com.alibaba.fastjson.JSONObject;
import com.jwk.common.utils.pay.payclose.WXPayCloseReqVo;
import com.jwk.common.utils.pay.payclose.WXPayCloseResVo;
import com.jwk.common.utils.pay.payquery.WXPayQueryReqVo;
import com.jwk.common.utils.pay.payquery.WXPayQueryResVo;
import com.jwk.common.utils.pay.redpack.WXRedPackReqVo;
import com.jwk.common.utils.pay.redpack.WXRedPackResVo;
import com.jwk.common.utils.pay.refund.WXPayRefundReqVo;
import com.jwk.common.utils.pay.refund.WXPayRefundResVo;
import com.jwk.common.utils.pay.refundquery.WXPayRefundQueryReqVo;
import com.jwk.common.utils.pay.refundquery.WXPayRefundQueryResVo;
import com.jwk.common.utils.pay.scan.WXScanPayReqVo;
import com.jwk.common.utils.pay.scan.WXScanPayResVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 微信支付
 */
@Slf4j
public class WXPay {

	/**
	 * <h2>有效期为2小时</h2> 扫描支付预支付处理:用于生成预支付交易ID及支付url<br/>
	 * 注意：已支付过或已调用关单、撤销的订单号不能重新发起支付<br/>
	 *
	 * @param wxScanPayReqVo
	 * @return
	 * @throws java.io.IOException
	 * @throws org.xml.sax.SAXException
	 * @throws javax.xml.parsers.ParserConfigurationException
	 */
	public static WXScanPayResVo prepay4ScanPay(WXScanPayReqVo wxScanPayReqVo)
			throws IOException, SAXException, ParserConfigurationException {

		if (null == wxScanPayReqVo) {
			throw new RuntimeException("请求参数不能为空");
		}

		log.info("微信预支付参数----------------------->{}",
				"appid:" + wxScanPayReqVo.getAppid() + "appid:" + wxScanPayReqVo.getAppid() + "mch_id:"
						+ wxScanPayReqVo.getMch_id() + "spbill_create_ip:" + wxScanPayReqVo.getSpbill_create_ip()
						+ "nonce_str:" + wxScanPayReqVo.getNonce_str() + "sign:" + wxScanPayReqVo.getSign());

		String wxResult = new HttpRequest().sendPost(WXPayConfig.UNIFIED_ORDER_URL,
				XMLUtils.object2XML(wxScanPayReqVo));

		log.info("微信预支付响应结果----------------------->{}", wxResult);

		// 校验签名是否合法
		WXSign.validSignFromResponse(wxResult);

		return XMLUtils.xml2Object(wxResult, WXScanPayResVo.class);
	}

	/**
	 * 支付情况查询：单个订单查询<br/>
	 * 该接口提供所有微信支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。<br/>
	 * 需要调用查询接口的情况：<br/>
	 * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；<br/>
	 * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；<br/>
	 * ◆ 调用被扫支付API，返回USERPAYING的状态；<br/>
	 * ◆ 调用关单或撤销接口API之前，需确认支付状态；
	 *
	 * @param payQuery
	 *            查询参数
	 * @return
	 */
	public static WXPayQueryResVo queryPayInfo(WXPayQueryReqVo payQuery) {

		if (null == payQuery) {
			throw new RuntimeException("请求参数不能为空");
		}

		String wxResult = new HttpRequest().sendPost(WXPayConfig.PAY_QUERY_URL, XMLUtils.object2XML(payQuery));

		// 校验签名是否合法
		WXSign.validSignFromResponse(wxResult);

		return XMLUtils.xml2Object(wxResult, WXPayQueryResVo.class);
	}

	/**
	 * 支付订单关闭：单个订单关闭<br/>
	 * 订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	 *
	 * @param wxPayCloseReqVo
	 *            关闭参数
	 * @return
	 */
	public static WXPayCloseResVo closePay(WXPayCloseReqVo wxPayCloseReqVo) {

		if (null == wxPayCloseReqVo) {
			throw new RuntimeException("请求参数不能为空");
		}

		String wxResult = new HttpRequest().sendPost(WXPayConfig.CLOSE_ORDER_URL, XMLUtils.object2XML(wxPayCloseReqVo));

		// 校验签名是否合法
		WXSign.validSignFromResponse(wxResult);

		return XMLUtils.xml2Object(wxResult, WXPayCloseResVo.class);
	}

	/**
	 * 退款操作：<br/>
	 * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，<br/>
	 * 微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上<br/>
	 * 注意：<br/>
	 * 1、交易时间超过一年的订单无法提交退款；<br/>
	 * 2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。<br/>
	 * 一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额。
	 *
	 * @param refundReqVo
	 *            退款参数
	 * @return
	 */
	public static WXPayRefundResVo refundPay(WXPayRefundReqVo refundReqVo) {

		if (null == refundReqVo) {
			throw new RuntimeException("请求参数不能为空");
		}

		String wxResult = new HttpRequest().sendSSLRequest(WXPayConfig.CERT_FILE_PATH, WXPayConfig.MCH_ID,
				WXPayConfig.REFUND_URL, XMLUtils.object2XML(refundReqVo));
		log.info("微信退款回参：" + wxResult);
		// 校验签名是否合法
		WXSign.validSignFromResponse(wxResult);

		return XMLUtils.xml2Object(wxResult, WXPayRefundResVo.class);
	}

	/**
	 * 查询退款情况：单个订单<br/>
	 * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，<br/>
	 * 用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
	 *
	 * @param refundQueryReqVo
	 *            查询参数
	 * @return
	 */
	public static WXPayRefundQueryResVo queryRefund(WXPayRefundQueryReqVo refundQueryReqVo) {

		if (null == refundQueryReqVo) {
			throw new RuntimeException("请求参数不能为空");
		}

		String wxResult = new HttpRequest().sendPost(WXPayConfig.REFUND_QUERY_URL,
				XMLUtils.object2XML(refundQueryReqVo));

		// 校验签名是否合法
		WXSign.validSignFromResponse(wxResult);

		return XMLUtils.xml2Object(wxResult, WXPayRefundQueryResVo.class);
	}

	/**
	 * 商户可以通过该接口下载历史交易清单<br/>
	 * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；<br/>
	 * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；<br/>
	 * 3、对账单中涉及金额的字段单位为“元”。
	 *
	 * @param downloadBillReqVo
	 *            参数
	 * @return
	 */
	public static String downloadBill(WXPayDownloadBillReqVo downloadBillReqVo) {

		if (null == downloadBillReqVo) {
			throw new RuntimeException("请求参数不能为空");
		}

		String wxResult = new HttpRequest().sendPost(WXPayConfig.DOWNLOAD_BILL_URL,
				XMLUtils.object2XML(downloadBillReqVo));

		if (StringUtils.isNotBlank(wxResult) && wxResult.indexOf("FAIL") > 0) {
			// 没有对账单
			if (wxResult.indexOf("No Bill Exist") > 0) {
				return "";
			}

			throw new RuntimeException("该日期查询不到对账单，注意：次日9点启动生成前一天的对账单，建议10点后再获取");
		}

		return wxResult;
	}

	/**
	 * 微信回调时时候，用于从请求中获取xml报文，进行签名校验及报文内容转换<br/>
	 * 1、需要写一个controller（或者servlet），调用该方法<br/>
	 * 2、在controller层直接返回"SUCCESS"给微信端<br/>
	 * 以SpringMVC 为例：<br/>
	 * public String main(HttpServletRequest request) throws IOException {<br/>
	 * WXPayCallbackResVo cbVo = WXPay.handleWXPayCB(request);<br/>
	 * //各种业务逻辑处理，例如：订单支付状态修改，避免重复入库<br/>
	 * return "SUCCESS";<br/>
	 * }<br/>
	 */
	public static WXPayCallbackResVo wxPayCallback(HttpServletRequest request) {
		String wxPayResultXML = null;
		try {
			wxPayResultXML = IOUtils.toString(request.getInputStream(), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("微信支付报文解析失败");
		}

		if (StringUtils.isBlank(wxPayResultXML)) {
			throw new RuntimeException("微信支付报文为空");
		}

		// 校验签名是否合法
		WXSign.validSignFromResponse(wxPayResultXML);

		return XMLUtils.xml2Object(wxPayResultXML, WXPayCallbackResVo.class);
	}

	/**
	 * 微信红包
	 * 
	 * @param wxRedPackReqVo
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static WXRedPackResVo redpack(WXRedPackReqVo wxRedPackReqVo)
			throws IOException, SAXException, ParserConfigurationException {
		if (null == wxRedPackReqVo) {
			throw new RuntimeException("请求参数不能为空");
		}
		log.info("微信红包参数----------------------->{}", JSONObject.toJSON(wxRedPackReqVo).toString());
		// String wxResult = new HttpRequest().sendPost(WXPayConfig.REDPACK_URL,
		// XMLUtils.object2XML(wxRedPackReqVo));
		String wxResult = new HttpRequest().sendSSLRequest(WXPayConfig.CERT_FILE_PATH, WXPayConfig.MCH_ID,
				WXPayConfig.REDPACK_URL, XMLUtils.object2XML(wxRedPackReqVo));
		log.info("微信红包响应结果----------------------->{}", wxResult);
		// 校验签名是否合法
		return XMLUtils.xml2Object(wxResult, WXRedPackResVo.class);
	}
}
