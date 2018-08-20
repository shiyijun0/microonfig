package com.jwk.project.web.service;

import java.text.ParseException;
import java.util.List;

import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebSizes;

/**
 * 共用类
 * 
 * @author Administrator
 *
 */
public interface ICommonService {
	/**
	 * 获取牛仔对应的尺码
	 * 
	 * @return
	 */
	public List<SysWebSizes> getSizes(String sizeIds);

	/**
	 * 获取牛仔对应的颜色
	 * 
	 * @return
	 */
	public List<SysWebColor> getColors(String colorIds);
	
	/**
	 * 获取预售款限量信息
	 * 
	 * @return
	 */
	public SysPresellJeans getLimitConfig(Long jeansId,SysPresellJeans info)throws ParseException ;
}
