package com.jwk.common.utils.wx;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 微信的统一下单接口提交参数要求我们使用xml格式，返回数据类型也是xml格式，所以我们先写一个xml与map之间互相转换的工具类XMLUti
 * 
 * @author Administrator
 *
 */
public class XMLUtil {
	/**
	 * xml格式字符串与map集合之间互相转换的工具
	 */

	// map集合转xml字符串
	public static String toXml(Map<String, Object> params) {
		StringBuffer xml = new StringBuffer();
		//xml.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?><xml>");
		xml.append("<xml>");

		ArrayList<String> arr = new ArrayList<String>();
		for (String key : params.keySet()) {
			if (params.get(key) != null && !params.get(key).equals("")) {
				arr.add(key);
			}
		}
		Collections.sort(arr);
		for (int i = 0; i < arr.size(); i++) {
			String k = arr.get(i);
			if (params.get(k) != null) {
				String v = params.get(k).toString();
				//xml.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
				xml.append("<" + k + ">"  + v + "</" + k + ">");
			}
		}

		xml.append("</xml>");
		String xml2 = "";
		try {
			xml2 = new String(xml.toString().getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml2;
	}

	public static String toXml2(Map<String, Object> params) {
		StringBuffer xml = new StringBuffer();
		xml.append("<xml>");

		ArrayList<String> arr = new ArrayList<String>();
		for (String key : params.keySet()) {
			if (params.get(key) != null && !params.get(key).equals("")) {
				arr.add(key);
			}
		}
		Collections.sort(arr);
		for (int i = 0; i < arr.size(); i++) {
			String k = arr.get(i);
			if (params.get(k) != null) {
				String v = params.get(k).toString();
				xml.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			}
		}

		xml.append("</xml>");
		String xml2 = "";
		try {
			xml2 = new String(xml.toString().getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml2;
	}
	// xml字符串转map集合
	public static Map<String, Object> toMap(String xml) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (xml.equals("")) {
			return result;
		}

		try {
			StringReader read = new StringReader(xml);
			InputSource source = new InputSource(read);
			SAXBuilder sb = new SAXBuilder();

			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();
			result.put(root.getName(), root.getText());
			result = parse(root, result);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.remove("xml");
		return result;
	}

	private static Map<String, Object> parse(Element root, Map<String, Object> result) {
		List<Element> nodes = root.getChildren();
		int len = nodes.size();
		if (len == 0) {
			result.put(root.getName(), root.getText());
		} else {
			for (int i = 0; i < len; i++) {
				Element element = (Element) nodes.get(i);// 循环依次得到子元素
				result.put(element.getName(), element.getText());
				// parse(element,result);
			}
		}
		return result;
	}
}
