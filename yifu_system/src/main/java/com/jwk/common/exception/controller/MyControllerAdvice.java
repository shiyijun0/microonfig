package com.jwk.common.exception.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * 
 * @author Administrator
 *
 */
@ControllerAdvice
public class MyControllerAdvice {
	
	/**
	 * 全局异常捕捉处理
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Map<String,Object> errorHandler(Exception ex) {
		Map<String,Object> map = new HashMap<>();
		map.put("code", 100);
		map.put("msg", ex.getMessage());
		return map;
	}

	/**
	 * 拦截捕捉自定义异常 ClientAbortException.class
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = ClientAbortException.class)
	public String myErrorHandler(ClientAbortException ex, Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("code", "200");
		map.put("msg", ex.getMessage());
		model.addAttribute("map", map);
		model.addAttribute("code", "100");
		model.addAttribute("msg", ex.getMessage());
		return "global_error";
	}

}
