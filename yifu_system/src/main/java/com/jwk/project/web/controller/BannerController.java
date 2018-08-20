package com.jwk.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.system.front.domain.SysWebBanner;
import com.jwk.project.system.front.service.WebBannerService;

@Controller
@RequestMapping("/custom")
public class BannerController {
	@Autowired
	private WebBannerService webBannerService;
	
	private String prefix = "custom";

	@GetMapping("/banner")
	public String banner(Model model) {

		List<SysWebBanner> webBannerList = webBannerService.selectWebBannerAll();

		if (StringUtils.isNotNull(webBannerList)) {
			model.addAttribute("webBannerList", webBannerList);
		}
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/banner";
	}

}
