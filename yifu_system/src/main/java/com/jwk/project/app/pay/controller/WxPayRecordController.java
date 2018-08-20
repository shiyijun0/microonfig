package com.jwk.project.app.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.project.app.pay.service.IWxPayService;

@Controller
@RequestMapping("/app/payrecord")
public class WxPayRecordController {

	@Autowired
	IWxPayService wxpayService;
	
	/**
	 * 查询支付记录状态
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	public Boolean findById(@RequestParam("record_id") Long record_id) throws Exception {
		Boolean result = wxpayService.queryPayRecordStatus(record_id);
		return result;
	}
}
