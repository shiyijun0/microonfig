package com.jwk.common.utils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

public class HttpUtils {

	/**
	 * 获取远程请求ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(final HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
				String ipAddress = inetAddress.getHostAddress();
				ip = ipAddress;
			} catch (Exception e) {

			}
		}
		if (ip.split(",").length > 0) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	/**
	 * 格式化session
	 * 
	 * @param request
	 * @return
	 */
	public static String formatSessionId(final HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sid = session.getId();
		// example: 012345678901234567890123456789AB!0123456789A
		// example: 012345678901234567890123456789AB!0123456789B
		if (sid.indexOf("!") != -1) {
			sid = sid.substring(0, sid.indexOf("!"));
		}
		// example: 012345678901234567890123456789AB.tomcat1
		// example: 012345678901234567890123456789AB.tomcat2
		if (sid.indexOf(".") != -1) {
			sid = sid.substring(0, sid.indexOf("."));
		}
		return sid;
	}

	/**
	 * 格式化客户端信息
	 * 
	 * @param client
	 * @return
	 */
	public static String formatClient(String client) {
		if (client.indexOf("Win") != -1) {
			return "Win";
		}
		if (client.indexOf("Mac") != -1) {
			return "Mac";
		}
		if (client.indexOf("iOS") != -1) {
			return "iOS";
		}
		if (client.indexOf("Android") != -1) {
			return "Android";
		}
		return "Unknow";
	}

	/**
	 * 预处理时间
	 * 
	 * @param time
	 * @param day
	 * @return
	 */
	public static Moment prepareQueryDate(String time, int day) {
		if (!StringUtils.isEmpty(time)) {
			if (time.length() == 10) {
				return new Moment().fromDate(time);
			} else {
				return new Moment().fromTime(time);
			}
		} else {
			/*String thisDate = new Moment().toSimpleDate();
			return new Moment().fromDate(thisDate).add(day, "days");*/
			return null;
		}
	}

	/**
	 * 输出脚本
	 * 
	 * @param response
	 * @param content
	 */
	public static void writeJs(HttpServletResponse response, String content) {
		if (!StringUtils.isEmpty(content)) {
			try {
				response.setContentType("application/javascript");
				response.setCharacterEncoding("utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(content);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 输出网页
	 * 
	 * @param response
	 * @param content
	 */
	public static void writeHtml(HttpServletResponse response, String content) {
		if (!StringUtils.isEmpty(content)) {
			try {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(content);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载Excel
	 * 
	 * @param response
	 * @param workbook
	 * @param filename
	 */
	public static void downloadExcel(HttpServletResponse response, HSSFWorkbook workbook, String filename) {
		try (OutputStream os = response.getOutputStream()) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			filename = java.net.URLEncoder.encode(filename, "utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}