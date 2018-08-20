package com.jwk.common.utils.pay;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * map操作工具
 */
public class MapUtil {

	/**
	 * 将对象转为Map
	 *
	 * @param clazz
	 * @param objInstance
	 * @param <T_METHOD>
	 * @return
	 */
	public static <T_METHOD> Map<String, Object> objectToMap(Class<T_METHOD> clazz, T_METHOD objInstance) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				if ("serialVersionUID".equals(field.getName()))
					continue;
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				Object o = getMethod.invoke(objInstance);// 执行get方法返回一个Object

				if (null == o) {
					continue;
				}
				map.put(field.getName(), o);
			}
		} catch (Exception e) {
			throw new RuntimeException("属性转换失败", e);
		}
		return map;
	}

}
