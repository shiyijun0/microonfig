package com.jwk.common.utils.wx.test;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jwk.common.utils.wx.test.ObjectUtils.DEFAULT_CHARSET;
import static com.jwk.common.utils.wx.test.ObjectUtils.MD5;

public class WechatPayUtils {

    private static final Logger log = LoggerFactory.getLogger(WechatPayUtils.class);

    private String appId = "wxffa5bc852bc062e8";
    private String mchId = "1396769602";
    private String signType = MD5.toUpperCase();
    private String secretKey = "Y58juKvoTWKWIOSRUnys4XunZ76scDMx";
    private String notifyUrl = "http://jwk.bzvs.cn/wxpay/resultPay";

    public Map<String, Object> payByH5(String body, String orderNo, int totalFee, String ip, String sceneInfo) throws PayException {
        PayRequestParams payRequestParams = new PayRequestParams();
        payRequestParams.setBody(body);
        payRequestParams.setOut_trade_no(orderNo);
        payRequestParams.setTotal_fee(totalFee);
        payRequestParams.setSpbill_create_ip(ip);
        payRequestParams.setScene_info(sceneInfo);
        return payByH5(payRequestParams);
    }

    /**
     * 统一下单.
     */
    private Map<String, Object> unifiedOrder(PayRequestParams payRequestParams) throws PayException {

        payRequestParams.setAppid(appId);
        payRequestParams.setMch_id(mchId);
        payRequestParams.setNotify_url(notifyUrl);
        payRequestParams.setSign_type(signType);

        String tradeType = payRequestParams.getTrade_type();
        Map<String, Object> sortedMap = ObjectUtils.paramsSorter(payRequestParams);
        String sign = ObjectUtils.signatureGenerator(sortedMap, MD5, DEFAULT_CHARSET, secretKey);
        sortedMap.put("sign", sign);
        String xml = ObjectUtils.mapToXML(sortedMap);
        log.info("payRequestParams: {}", payRequestParams);
        log.info("sortMap：{}", sortedMap);
        log.info("统一下单参数xml： {}", xml);
        Map<String, Object> resultMap = doWeChatPayRequest(WeChatPayTypeEnum.valueOf(tradeType).getApi(), xml);
        log.info("统一下单返回结果：{}", resultMap);
        if ("SUCCESS".equals(resultMap.get("result_code"))) {
            ObjectUtils.verifySignature(resultMap, MD5, secretKey);
            resultMap.put("secretKey", secretKey);
            return resultMap;
        }
        throw new PayException("统一下单返回失败,参数列表：{}" + resultMap);
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> payByH5(PayRequestParams payRequestParams) throws PayException {
        String[] names = {"body", "out_trade_no", "total_fee", "spbill_create_ip", "scene_info"};

        List<String> fieldNames = Arrays.asList(names);
        ObjectUtils.checkParams(payRequestParams, fieldNames);
        PayRequestParams params = payRequestParams;
        params.setTrade_type(WeChatPayTypeEnum.MWEB);
        Map<String, Object> map = unifiedOrder(params);
        Object appId = map.get("appid");
        Object partnerId = map.get("mch_id");
        Object resultCode = map.get("result_code");
        Object mwebUrl = map.get("mweb_url");
        Object nonceStr = map.get("nonce_str");
        mwebUrl = mwebUrl+"&redirect_url=http://jwk.bzvs.cn/custom/order_details?id="+payRequestParams.getOut_trade_no();
        //mwebUrl = mwebUrl+"&redirect_url=http://test.bzvs.cn/custom/order_details?id="+payRequestParams.getOut_trade_no();
        //mwebUrl = mwebUrl+"&redirect_url=http://jwk.bzvs.cn/custom/index";//支付成功后返回指定页面
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("appid", appId);
        returnMap.put("mch_id", partnerId);
        returnMap.put("nonce_str", nonceStr);
        returnMap.put("result_code", resultCode);
        returnMap.put("mweb_url", mwebUrl);
        return injector(returnMap, payRequestParams);
    }

    /**
     * 请求腾讯支付
     *
     * @param url 调用腾讯支付对应的API
     * @param xml 封装好的xml格式的参数
     * @return the map
     */
    private Map<String, Object> doWeChatPayRequest(String url, String xml) {
        String xmlResult = HttpKit.httpPost(url, xml);
        return xmlTo8859Map(xmlResult);
    }

    private Map<String, Object> xmlTo8859Map(String xmlResult) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isNotEmpty(xmlResult)) {
            String responseXml = null;
            try {
                responseXml = new String(xmlResult.getBytes("ISO-8859-1"), DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                log.debug("encode is not supported", e);
            }
            resultMap = ObjectUtils.xmlToMap(responseXml);
        }
        return resultMap;
    }

    private <T> Map<String, Object> injector(Map<String, Object> map, T t) {
        map.put("params_key", t);
        return map;
    }

}

