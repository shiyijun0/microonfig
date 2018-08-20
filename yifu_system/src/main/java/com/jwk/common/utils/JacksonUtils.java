package com.jwk.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JacksonUtils {
	
	/**
	 * 格式化成js
	 * 
	 * @param data
	 * @return
	 */
	public static String formatJs(Map<String, Object> data) {
		StringBuffer sb = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object[] keys = data.keySet().toArray();
			for (int i = 0; i < keys.length; i++) {
				String key = (String) keys[i];
				Object value = data.get(key);
				Object jsVal = mapper.writeValueAsString(value);
				sb.append("var " + key + " = " + jsVal + ";");
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	/**
	 * 格式化成js
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String formatJs(String key, Object value) {
		StringBuffer sb = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object jsVal = mapper.writeValueAsString(value);
			sb.append("var " + key + " = " + jsVal + ";");
		} catch (Exception e) {
		}
		return sb.toString();
	}
	
	/**
	 * 转换成Bean
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换成字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换成List
	 * 
	 * @param content
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> toList(String content, Class<T> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeFactory typeFactory = mapper.getTypeFactory();
			CollectionType valueType = typeFactory.constructCollectionType(
					List.class, clazz);
			return mapper.readValue(content, valueType);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 转换成JsonNode
	 * 
	 * @param content
	 * @return
	 */
	public static JsonNode toJsonNode(String content) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readTree(content);
		} catch (Exception e) {
		}
		return null;
	}
public static void main(String[] args) {
	String s="[{\"count\":67,\"sizeImg\":1,\"content\":\"余\"},{\"count\":78,\"sizeImg\":1,\"content\":\"67回复\"}]";
	System.out.println(s);
	toJsonNode(s);
	for(JsonNode d:toJsonNode(s)) {
		System.out.println(d.get("count").asDouble());
	}
	
//	TestPerson a=new TestPerson(12,"4rt");
//	TestPerson b=new TestPerson(12,"4rt");
//	List<TestPerson> c=new ArrayList<>();
//	c.add(a);c.add(b);
//	System.out.println(toJsonString(c));
	
}
}