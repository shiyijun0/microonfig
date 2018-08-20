package com.jwk.common.utils.pay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * XML处理工具
 */
public class XMLUtils {

	/**
	 * 将对象转换为XML:根节点名称自定义
	 *
	 * @param object
	 * @return
	 */
	public static String object2XML(Object object, String rootName) {

		if (StringUtils.isBlank(rootName)) {
			rootName = "xml";
		}

		// 解决XStream对出现双下划线的bug
		XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		xStream.alias(rootName, object.getClass());// 根节点换成xml
		// 将要提交给API的数据对象转换成XML格式数据Post给API
		return xStream.toXML(object);
	}

	/**
	 * 将对象转换为XML:根节点为xml
	 *
	 * @param object
	 * @return
	 */
	public static String object2XML(Object object) {
		return object2XML(object, "xml");
	}

	/**
	 * 将xml转换成对象
	 *
	 * @param xml
	 * @param tClass
	 * @return
	 */
	public static <T_METHOD> T_METHOD xml2Object(String xml, Class<T_METHOD> tClass) {
		// 将从API返回的XML数据映射到Java对象
		XStream xStreamForResponseData = new XStream();
		xStreamForResponseData.alias("xml", tClass);
		xStreamForResponseData.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
		return (T_METHOD) xStreamForResponseData.fromXML(xml);
	}

	/**
	 * 将xml转成MAP
	 *
	 * @param xmlString
	 * @return
	 * @throws javax.xml.parsers.ParserConfigurationException
	 * @throws java.io.IOException
	 * @throws org.xml.sax.SAXException
	 */
	public static Map<String, Object> getMapFromXML(String xmlString)
			throws ParserConfigurationException, IOException, SAXException {
		// 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = getStringStream(xmlString);
		Document document = builder.parse(is);

		// 获取到document里面的全部结点
		NodeList allNodes = document.getFirstChild().getChildNodes();
		Node node;
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		while (i < allNodes.getLength()) {
			node = allNodes.item(i);
			if (node instanceof Element) {
				map.put(node.getNodeName(), node.getTextContent());
			}
			i++;
		}
		return map;

	}

	private static InputStream getStringStream(String sInputString) {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
		}
		return tInputStringStream;
	}

}
