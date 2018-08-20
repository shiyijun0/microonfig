package com.jwk.project.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.front.domain.SysWebBanner;
import com.jwk.project.system.front.service.WebBannerService;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.web.service.ICommonService;

@Controller
@RequestMapping("/custom")
public class BookingController {
	private String prefix = "custom";

	@Autowired
	private IRegisterUserService registerUserService;

	@Autowired
	private IPresellJeansService presellJeansService;

	@Autowired
	private ICommonService commonService;

	@Autowired
	private WebBannerService webBannerService;

	/**
	 * 预售款
	 * 
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/booking_index")
	public String bookingIndex(Model model) throws ParseException {
		RegisterUser registerUser = (RegisterUser) ServletUtils.getHttpServletRequest().getSession()
				.getAttribute("user");
		if (registerUser != null) {
			registerUser = registerUserService.selectRegisterUserById(registerUser.getId());
			model.addAttribute("registerUser", registerUser);
		}
		// 所有上架预售款信息
		List<SysPresellJeans> presellList = presellJeansService.selectAllPut();
		if (presellList.isEmpty())
			return JSON.error("暂无数据").toString();
		// 根据预售款id查询限量信息
		for (SysPresellJeans info : presellList) {
			Long presellId = info.getId();
			info = commonService.getLimitConfig(presellId, info);
		}
		List<SysWebBanner> webBannerList = webBannerService.selectWebBannerAll();
		if (StringUtils.isNotNull(webBannerList)) {
			model.addAttribute("webBannerList", webBannerList);
		}

		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		model.addAttribute("presellList", presellList);
		return prefix + "/booking_index";
	}

	/**
	 * 详细页面
	 * 
	 * @return
	 */
	@GetMapping("/booking_shop")
	public String bookingShop(@RequestParam(name = "id", required = false) Long id, Model model) {
		RegisterUser registerUser = (RegisterUser) ServletUtils.getHttpServletRequest().getSession()
				.getAttribute("user");
		if (registerUser != null) {
			registerUser = registerUserService.selectRegisterUserById(registerUser.getId());
			model.addAttribute("registerUser", registerUser);
		}
		SysPresellJeans preinfo = presellJeansService.selectById(id);
		List<SysWebSizes> webSizesList = new ArrayList<SysWebSizes>();
		List<SysWebColor> webColorList = new ArrayList<SysWebColor>();
		if (preinfo != null) {
			// 1.分割详细图
			String[] detailImg = preinfo.getDetailImgs().split(",");
			preinfo.setDeatilImgList(detailImg);
			String colorIds = preinfo.getColorsId();
			String sizeIds = preinfo.getSizesId();
			// 2.查询牛仔裤对应的尺码
			webSizesList = commonService.getSizes(sizeIds);
			// 3.查询牛仔裤对应的颜色
			webColorList = commonService.getColors(colorIds);
		}
		model.addAttribute("webSizesList", webSizesList);
		model.addAttribute("webColorList", webColorList);
		model.addAttribute("preinfo", preinfo);
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/booking_shop";
	}

}
