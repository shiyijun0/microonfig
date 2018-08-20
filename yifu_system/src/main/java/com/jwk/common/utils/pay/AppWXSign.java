package com.jwk.common.utils.pay;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * app 微信签名
 */
public class AppWXSign {

	/**
	 * 签名算法
	 *
	 * @param map
	 * @return
	 */
	public static String getSign(Map<String, Object> map) {

		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}

		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}

		String result = sb.toString();

		result += "key=" + AppWXPayConfig.KEY;

		try {
			result = DigestUtils.md5Hex(result.getBytes("utf-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("转换MD5异常", e);
		}
		return result;
	}

	/**
	 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
	 *
	 * @param responseString
	 *            API返回的XML数据字符串
	 * @return API签名是否合法
	 */
	public static void validSignFromResponse(String responseString) {

		Map<String, Object> map = null;

		try {
			map = XMLUtils.getMapFromXML(responseString);
		} catch (Exception e) {
			throw new RuntimeException("报文转换异常");
		}

		String signFromAPIResponse = map.get("sign").toString();
		if (signFromAPIResponse == "" || signFromAPIResponse == null) {
			throw new RuntimeException("API返回的数据签名不合法：签名为空");
		}

		// 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
		map.put("sign", "");

		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		String signForAPIResponse = getSign(map);

		if (!signForAPIResponse.equals(signFromAPIResponse)) {
			// 签名验不过，表示这个API返回的数据有可能已经被篡改了
			throw new RuntimeException("API返回的数据签名不合法：验证不通过，有可能被第三方篡改");
		}

	}

}
