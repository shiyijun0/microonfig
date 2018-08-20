package com.jwk.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class StrUtils {

	private static Logger logger = LoggerFactory.getLogger(StrUtils.class);
	
	public static int instance_no = 0;
	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	public static Set<String> split2Set(String input) throws Exception {
		Set<String> valueSet = new HashSet<>();
		if (StrUtils.isNotEmpty(input)) {
			for (String temp : input.split(",")) {
				valueSet.add(temp);
			}
		}
		return valueSet;
	}

	public static boolean floatEquals(float float1, float float2) throws Exception {
		boolean result = false;
		if (float1 - float2 < 0.00000001) {
			result = true;
		}
		return result;
	}

	public static String buildCurrentUrl(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		sb.append(uri);
		if (StrUtils.isNotEmpty(queryString))
			sb.append("?").append(queryString);
		logger.info("uri:{},queryString:{}", uri, queryString);
		return sb.toString();
	}

	public static boolean isEmpty(String input) {
		boolean rs = false;
		if (input == null || input.isEmpty() || "null".equals(input))
			rs = true;
		return rs;
	}

	public static boolean isNotEmpty(String input) {
		boolean rs = true;
		if (input == null || input.isEmpty() || "null".equals(input))
			rs = false;
		return rs;
	}

	public static boolean isMobile(String input) {
		boolean rs = false;
		String regex = "(?is)^1\\d{10,10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			rs = true;
		}
		return rs;
	}

	public static long generateInstanceID() {
		return LongID.id(instance_no).get();
	}
	
	/*private void initInstanceId() throws Exception {
		String path = StrUtils.class.getClassLoader().getResource("").getPath();
		logger.info("tomcat-path:{}", path);
		String regex = "(?is)tomcat.*?-(\\d+).*?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(path);
		if (matcher.find()) {
			int instance = Integer.parseInt(matcher.group(1));
			if (instance < 1 || instance > 15) {
				throw new ClientAbortException("instance号必须在0到15之间! ");
			} else {
				instance_no = instance;
			}
		}
		logger.info("initInstanceId:{}", instance_no);
	}*/
	
	public static String generateID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	public static String getWebRootPath() {
		String path = StrUtils.class.getClassLoader().getResource("").getPath();
		try {
			path = path.substring(1, path.indexOf("WebContent"));
			path += "WebContent";
		} catch (Exception e) {
			path = path.substring(1, path.indexOf("WEB-INF"));
		}
		return path;
	}

	public static String getWebAppNamePath(String webapp_name) {
		String path = StrUtils.class.getClassLoader().getResource("").getPath();
		logger.info("webapp_path:{}", path);
		if (path.indexOf(webapp_name) != -1) {
			path = path.substring(1, path.indexOf(webapp_name));
			path += webapp_name;
		} else {
			path = path.substring(1, path.indexOf("ROOT"));
			path += "ROOT";
		}
		return path;
	}

	public static String getWebInfPath() {
		String path = StrUtils.class.getClassLoader().getResource("").getPath();
		logger.info("cloassLoader path:{}", path);
		path = path.substring(1, path.indexOf("WEB-INF"));
		return path;
	}

	public static Map<String, String> requestParasToMap(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String[]> paras = request.getParameterMap();
		if (paras != null && !paras.isEmpty()) {
			for (Iterator<Map.Entry<String, String[]>> it = paras.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, String[]> entry = it.next();
				if (entry.getValue().length == 1) {
					String tempValue = null;
					try {
						tempValue = URLDecoder.decode(entry.getValue()[0], "utf-8");
					} catch (Exception e) {
						tempValue = entry.getValue()[0];
						logger.error(tempValue, e);
					}
					result.put(entry.getKey(), tempValue);
					if (entry.getKey().indexOf(".") != -1)
						result.put(entry.getKey().replaceAll("\\.", "_"), tempValue);
				} else {
					if (entry.getValue() != null && entry.getValue().length > 1) {
						StringBuilder sb = new StringBuilder();
						for (String value : entry.getValue()) {
							String tempValue = null;
							try {
								tempValue = URLDecoder.decode(value, "utf-8");
							} catch (Exception e) {
								tempValue = value;
								logger.error(tempValue, e);
							}
							sb.append(tempValue).append(",");
						}
						sb.setLength(sb.length() - 1);
						result.put(entry.getKey(), sb.toString());
						if (entry.getKey().indexOf(".") != -1)
							result.put(entry.getKey().replaceAll("\\.", "_"), sb.toString());
					} else {
						result.put(entry.getKey(), "");
						if (entry.getKey().indexOf(".") != -1)
							result.put(entry.getKey().replaceAll("\\.", "_"), "");
					}
				}
			}
		}
		result.put("client_ip", getRemoteAddress(request));
		result.put("request_page_name", getPageName(request));
		result.put("thread_id", Thread.currentThread().getId() + "_" + System.currentTimeMillis());
		return result;
	}

	private static String getPageName(HttpServletRequest request) throws Exception {
		String url = request.getRequestURI();
		String result = "";
		Pattern pattern = Pattern.compile("(?is)(\\w+)\\.");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			result = matcher.group(1);
		}
		return result;
	}

	public static boolean isSplitInteger(String input) {
		boolean rs = false;
		String regex = "^(\\d+,?)+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			rs = true;
		}
		return rs;
	}

	public static boolean isNaturalNumber(String input) {
		boolean rs = false;
		if (input != null) {
			String regex = "^[1-9]\\d*$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				rs = true;
			}
		}
		return rs;
	}

	public static boolean isPositiveNumber(String input) {
		boolean rs = false;
		String regex = "^(\\d+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			rs = true;
		}
		return rs;
	}

	public static boolean isNumber(String input) {
		boolean rs = false;
		String regex = "^(-?\\d+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			rs = true;
		}
		return rs;
	}

	public static boolean isFloat(String input) {
		boolean rs = false;
		String regex = "^((-?\\d+)||(-?\\d+\\.\\d+))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			rs = true;
		}
		return rs;
	}

	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getRemoteAddr();
		return ip;
	}

	public static String getMAC() {
		return null;
	}

	public static String getClientTrid(String epp_client_id) {
		return "ABC:" + epp_client_id + ":" + System.currentTimeMillis();
	}

	public static String readFile(String filePath) throws Exception {
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		StringBuilder sb = new StringBuilder();
		try {
			// File file = new File(filePath);
			// if (!file.isFile() || !file.exists())
			// throw new ServerException("file not found:" + filePath);
			String pathString = StrUtils.class.getResource("").getPath();
			pathString = pathString.substring(0, pathString.indexOf("!") + 1);
			logger.info("==========={}", pathString);
			InputStream inputStream = new FileInputStream(new File(pathString + "/" + filePath));
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				sb.append(lineTxt);
			}
		} finally {
			if (inputStreamReader != null)
				inputStreamReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}
		return sb.toString();

	}

	public static String readFile(File file) throws Exception {
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				sb.append(lineTxt);
			}
		} finally {
			if (inputStreamReader != null)
				inputStreamReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}
		return sb.toString();

	}

	public static List<String> readSqlFileNameFromClasses(String sql_dir) throws Exception {
		List<String> files = new ArrayList<>();
		Resource resource = new ClassPathResource(sql_dir);
		try {
			File file = resource.getFile();
			for (File sqlFile : file.listFiles()) {
				files.add(sql_dir + sqlFile.getName());
			}
		} catch (Exception e) {
			logger.info(String.format("文件夹【%s】不存在", sql_dir));
			// throw new ClientException(String.format("文件夹【%s】不存在", sql_dir));
		}
		return files;
	}

	public static String readFromFile(String pathInClasses) throws Exception {
		StringBuilder sb = new StringBuilder();
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			Resource resource = new ClassPathResource(pathInClasses);
			InputStream inputStream = new FileInputStream(resource.getFile());
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
			if (inputStreamReader != null)
				inputStreamReader.close();
		}
		return sb.toString();
	}

	public static String readFromFile(File file) throws Exception {
		StringBuilder sb = new StringBuilder();
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
			if (inputStreamReader != null)
				inputStreamReader.close();
		}
		return sb.toString();
	}

	public static long defaultLongValue(String paraValue, long defaultValue) {
		if (StrUtils.isNotEmpty(paraValue))
			return Long.parseLong(paraValue);
		return defaultValue;
	}

	public static float defaultFloatValue(String paraValue, float defaultValue) {
		if (StrUtils.isNotEmpty(paraValue))
			return Float.valueOf(paraValue);
		return defaultValue;
	}

	public static String valueOf(Object obj) throws Exception {
		return (obj == null) ? null : obj.toString();
	}

	public static long getPage(Map<String, String> para) {
		long page = 1;
		if (StrUtils.isNotEmpty(para.get("page")))
			page = Long.parseLong(para.get("page"));
		return page;
	}

	public static long getPageSize(Map<String, String> para) {
		long pagesize = 20;
		if (StrUtils.isNotEmpty(para.get("pagesize")))
			pagesize = Long.parseLong(para.get("pagesize"));
		return pagesize;
	}

	public static boolean validIP(String need_to_valid_ip, String allow_ip) {
		boolean valid = false;
		if (StrUtils.isEmpty(allow_ip))
			return valid;
		need_to_valid_ip = need_to_valid_ip.replaceAll("\\.", "_");
		allow_ip = allow_ip.replaceAll("\\.", "_");
		allow_ip = allow_ip.replaceAll("\\*", "\\.\\*?");
		for (String temp : allow_ip.split(",")) {
			String regex = temp + "$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(need_to_valid_ip);
			if (matcher.find())
				return true;
		}
		return valid;
	}

	public static String getRandomNumber(int length) throws Exception {
		StringBuilder code = new StringBuilder();
		int[] array = new int[length];
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			array[i] = r.nextInt(10);
			code.append(array[i]);
		}
		return code.toString();
	}

	public static int getRandomInt(int maxInt) throws Exception {
		Random r = new Random();
		return r.nextInt(maxInt);
	}

	public static String toDate(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.format(date);
		return sdf.format(date);
	}

	public static Date toDate(String input) throws Exception {
		Date date = null;
		if (StrUtils.isNotEmpty(input)) {
			String regex = "^\\d\\d\\d\\d-\\d\\d-\\d\\d$";
			String regex2 = "^\\d\\d\\d\\d-\\d\\d-\\d\\d\\s+\\d\\d:\\d\\d:\\d\\d$";
			String regex3 = "^\\d{13,13}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(input);
			} else {
				pattern = Pattern.compile(regex2);
				matcher = pattern.matcher(input);
				if (matcher.find()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					date = sdf.parse(input);
				} else {
					pattern = Pattern.compile(regex3);
					matcher = pattern.matcher(input);
					if (matcher.find()) {
						date = new Date(Long.parseLong(input));
					}
				}
			}
		}
		return date;
	}

	public static Date toDate(String input, String format) throws Exception {
		if (StrUtils.isEmpty(format))
			format = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(input);
		return date;
	}

	public static String toDate(Date date, String format) throws Exception {
		String dateString = null;
		if (date != null) {
			if (StrUtils.isEmpty(format))
				format = "yyyy-MM-dd hh:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateString = sdf.format(date);
		}
		return dateString;
	}

	public static String parsePhone(String input) throws Exception {
		String phone = null;
		if (StrUtils.isNotEmpty(input)) {
			String regex = "1\\d{10,10}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				phone = matcher.group(0);
			}
		}
		return phone;
	}

	public static String getClientId(HttpServletRequest request) {
		String client_id = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie ck : cookies) {
				if ("client_id".equals(ck.getName())) {
					client_id = ck.getValue();
					break;
				}
			}
		}
		return client_id;
	}

	/**
	 * 密码不能小于6位，大于20位<br>
	 * 密码不能是纯字母<br>
	 * 密码不能是纯数字<br>
	 * 密码不能是纯字符<br>
	 * 
	 * @throws Exception
	 */
	public static void validPassword(String password) throws Exception {
		String regex = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if (matcher.find()) {
			throw new ClientAbortException("密码不能是纯字母！");
		}
		regex = "^[0-9]+$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(password);
		if (matcher.find()) {
			throw new ClientAbortException("密码不能是纯数字！");
		}
		regex = "^[!@#$%^&*(){}:_=+|~?]+$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(password);
		if (matcher.find()) {
			throw new ClientAbortException("密码不能是纯字符！");
		}
	}

	public static void main(String[] args) throws Exception {
		//StrUtils.validPassword("?");
		System.out.println(StrUtils.generateID());
		System.out.println(StrUtils.generateInstanceID());
	}

	/**
	 * 后台模糊查询中文时，翻页查询中文会乱码，使用此方法将乱码转回来
	 * 
	 * @param code
	 * @return
	 */
	public static String decode(String code) {
		String tempValue = "";
		try {
			if (StrUtils.isNotEmpty(code)) {
				tempValue = URLDecoder.decode(code, "utf-8");
			}
		} catch (Exception e) {
			logger.error(tempValue, e);
		}
		return tempValue;
	}
	
	/**
     * 匹配是否为数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
