package com.jwk.project.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jwk.project.web.vo.PartsVO;
import com.jwk.framework.web.service.RedisService;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.service.RedisService;
import com.jwk.project.system.order.domain.SysWebOrderGoods;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;
import com.jwk.project.system.web.domain.*;
import com.jwk.project.system.web.domainVO.MyDesignerVO;
import com.jwk.project.system.web.service.MyDesignerService;
import com.jwk.project.system.web.service.WebButtonService;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebDesignerService;
import com.jwk.project.system.web.service.WebJeansService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebSizesService;
import com.jwk.project.system.web.service.WebThreadService;
import com.jwk.project.system.web.service.WebWashService;

@Controller
@RequestMapping("/custom")
public class CustomController {

	private String prefix = "custom";

	@Autowired
	private RedisService redisService;

	@Autowired
	private WebJeansService webJeansService;
	@Autowired
	private WebPartsService webPartsService;
	@Autowired
	private WebSizesService webSizesService;
	@Autowired
	private WebColorService webColorService;

	@Autowired
	private WebDesignerService webDesignerService;
	@Autowired
	private WebButtonService webButtonService;
	@Autowired
	private WebThreadService webThreadService;
	@Autowired
	private WebWashService webWashService;

	@Autowired
	private IRegisterUserService registerUserService;
	@Autowired
	private MyDesignerService myDesignerService;

	/*
	 * @GetMapping("/custom") public String custom(Model model) { HttpServletRequest
	 * request = ServletUtils.getHttpServletRequest(); Object
	 * o=request.getParameter("id"); System.out.println("******"+o); return prefix +
	 * "/custom"; }
	 */
	@RequestMapping("/custom")
	public String custom(@RequestParam(value = "id", required = false) Long id, Model model) {
		RegisterUser registerUser = (RegisterUser) ServletUtils.getHttpServletRequest().getSession()
				.getAttribute("user");
		if (registerUser != null) {
			registerUser = registerUserService.selectRegisterUserById(registerUser.getId());
			model.addAttribute("registerUser", registerUser);
		}
		if (StringUtils.isNull(id)) {
			List<SysWebDesigner> designer = webDesignerService.selectWebDesignerAll();
			if (designer.size() > 0) {
				id = Long.valueOf(designer.get(0).getId());
			}

		}
		model.addAttribute("id", id);
		if (registerUser != null) {
			model.addAttribute("userId", registerUser.getId());
		}
		List<SysWebSizes> webSizesList = new ArrayList<>();
		List<SysWebColor> webColorList = new ArrayList<>();
		List<SysWebButton> webButtonList = new ArrayList<>();
		List<SysWebThread> webThreadList = new ArrayList<>();
		List<SysWebWash> webWashList = new ArrayList<>();
		List<SysWebParts> webPartsList = new ArrayList<>();
		String webAreaList = null;// 区域
		SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(id);
		model.addAttribute("webDesigner", webDesigner);
		SysWebJeans webjeans = null;
		if (StringUtils.isNotNull(webDesigner)) {
			webjeans = webJeansService.selectWebJeansById(webDesigner.getJeansId().longValue());
			model.addAttribute("webjeans", webjeans);
			// 成品库信息
			SysWebColor webJeansColor = null;
			if (StrUtils.isNotEmpty(webDesigner.getColorsId())) {
				webJeansColor = webColorService.selectwebColorById(Long.valueOf(webDesigner.getColorsId()));
			}
			SysWebSizes webJeansSizes = null;
			if (StrUtils.isNotEmpty(webDesigner.getSizesId())) {
				webJeansSizes = webSizesService.selectSysWebSizesById(Long.valueOf(webDesigner.getSizesId()));
			}
			SysWebButton webJeansButton = null;
			if (StrUtils.isNotEmpty(webDesigner.getButtonsId())) {
				webJeansButton = webButtonService.selectWebButtonById(Long.valueOf(webDesigner.getButtonsId()));
			}
			SysWebThread webJeansThread = null;
			if (StrUtils.isNotEmpty(webDesigner.getThreadsId())) {
				webJeansThread = webThreadService.selectwebThreadById(Long.valueOf(webDesigner.getThreadsId()));
			}
			SysWebWash webJeansWash = null;
			if (StrUtils.isNotEmpty(webDesigner.getWashId())) {
				webJeansWash = webWashService.selectWebWashById(Long.valueOf(webDesigner.getWashId()));
			}

			PartsVO partsVO = new PartsVO();
			String parts = webDesigner.getPartsId();
			String[] partsDesigner = parts.split(",");
			for (String part : partsDesigner) {
				if (part.equalsIgnoreCase("0")) {
					continue;
				}
				SysWebParts webJeansParts = webPartsService.selectWebPartsById(Long.valueOf(part));
				if (webJeansParts.getRegion() == 1) {
					partsVO.setParts1(webJeansParts);
				}

				if (webJeansParts.getRegion() == 2) {
					partsVO.setParts2(webJeansParts);
				}

				if (webJeansParts.getRegion() == 3) {
					partsVO.setParts3(webJeansParts);
				}

				if (webJeansParts.getRegion() == 4) {
					partsVO.setParts4(webJeansParts);
				}

				if (webJeansParts.getRegion() == 5) {
					partsVO.setParts5(webJeansParts);
				}

				if (webJeansParts.getRegion() == 6) {
					partsVO.setParts6(webJeansParts);
				}

				if (webJeansParts.getRegion() == 7) {
					partsVO.setParts7(webJeansParts);
				}

				if (webJeansParts.getRegion() == 8) {
					partsVO.setParts8(webJeansParts);
				}
			}

			model.addAttribute("webJeansColor", webJeansColor);
			model.addAttribute("webJeansSizes", webJeansSizes);
			model.addAttribute("webJeansButton", webJeansButton);
			model.addAttribute("webJeansWash", webJeansWash);
			model.addAttribute("webJeansThread", webJeansThread);
			// model.addAttribute("webJeansParts", partsVO);
			model.addAttribute("partsVO", partsVO);
			// 素库表不为空
			if (StringUtils.isNotNull(webjeans)) {

				// 颜色
				String colorString = webjeans.getColorsId();
				if (StringUtils.isNotNull(colorString)) {
					String[] colorArr = colorString.split(",");
					for (String colorId : colorArr) {
						SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(colorId));
						webColorList.add(webColor);

					}

					model.addAttribute("webColorList", webColorList);
				}

				// 尺码
				String sizesString = webjeans.getSizesId();
				if (StringUtils.isNotNull(sizesString)) {
					String[] sizesArr = sizesString.split(",");
					for (String sizesId : sizesArr) {
						SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
						webSizesList.add(webSizes);

					}

					model.addAttribute("webSizesList", webSizesList);
				}

				// 纽扣
				String buttonString = webjeans.getButtonsId();
				if (StringUtils.isNotNull(buttonString)) {
					String[] buttonArr = buttonString.split(",");
					for (String buttonId : buttonArr) {
						SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(buttonId));
						webButtonList.add(webButton);

					}
					model.addAttribute("webButtonList", webButtonList);
				}

				// 破洞
				String washString = webjeans.getWashId();
				if (StringUtils.isNotNull(washString)) {
					String[] washArr = washString.split(",");
					for (String washId : washArr) {
						SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(washId));
						webWashList.add(webWash);

					}

					model.addAttribute("webWashList", webWashList);
				}

				// 边线
				String threadString = webjeans.getThreadsId();
				if (StringUtils.isNotNull(threadString)) {
					String[] threadArr = threadString.split(",");
					for (String threadId : threadArr) {
						SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(threadId));
						webThreadList.add(webThread);

					}

					model.addAttribute("webThreadList", webThreadList);
				}

				// 图片区域
				String partsString = webjeans.getPartsId();
				if (StringUtils.isNotNull(partsString)) {
					String[] partsArr = partsString.split(",");
					for (String partsId : partsArr) {
						SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(partsId));
						webPartsList.add(webParts);
						if (webParts != null) {
							webAreaList += webParts.getId() + ",";
						}

					}
					model.addAttribute("webPartsList", webPartsList);
				}

			}

		}

		// 区域所支持的颜色
		// 转化json格式 String
		// webJeansVOJson=com.alibaba.fastjson.JSON.toJSON(webJeansVO).toString();
		SysWebParts webPartsCover = new SysWebParts();
		webPartsCover.setRegion(1);
		webPartsCover.setStatus(1);
		List<SysWebParts> webParts1 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi1 = new ArrayList<>();
		for (SysWebParts webParts : webParts1) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi1.add(webParts);
			}
		}
		model.addAttribute("webParts1", webPartsLi1);

		webPartsCover.setRegion(2);
		List<SysWebParts> webParts2 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi2 = new ArrayList<>();
		for (SysWebParts webParts : webParts2) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi2.add(webParts);
			}
		}

		model.addAttribute("webParts2", webPartsLi2);

		webPartsCover.setRegion(3);
		List<SysWebParts> webParts3 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi3 = new ArrayList<>();
		for (SysWebParts webParts : webParts3) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi3.add(webParts);
			}
		}

		model.addAttribute("webParts3", webPartsLi3);

		webPartsCover.setRegion(4);
		List<SysWebParts> webParts4 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi4 = new ArrayList<>();
		for (SysWebParts webParts : webParts4) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi4.add(webParts);
			}
		}

		model.addAttribute("webParts4", webPartsLi4);
		webPartsCover.setRegion(5);
		List<SysWebParts> webParts5 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi5 = new ArrayList<>();
		for (SysWebParts webParts : webParts5) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi5.add(webParts);
			}
		}

		model.addAttribute("webParts5", webPartsLi5);

		webPartsCover.setRegion(6);
		List<SysWebParts> webParts6 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi6 = new ArrayList<>();
		for (SysWebParts webParts : webParts6) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi6.add(webParts);
			}
		}

		model.addAttribute("webParts6", webPartsLi6);
		webPartsCover.setRegion(7);
		List<SysWebParts> webParts7 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi7 = new ArrayList<>();
		for (SysWebParts webParts : webParts7) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi7.add(webParts);
			}
		}

		model.addAttribute("webParts7", webPartsLi7);

		webPartsCover.setRegion(8);
		List<SysWebParts> webParts8 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi8 = new ArrayList<>();
		for (SysWebParts webParts : webParts8) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi8.add(webParts);
			}
		}

		model.addAttribute("webParts8", webPartsLi8);
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);

		if (webDesigner.getType() == 0) {
			return prefix + "/custom";
		} else if (webDesigner.getType() == 1) {
			return prefix + "/clothes";
		}
		return prefix + "/custom";
	}

	@PostMapping("/checkPicInfo")
	@ResponseBody
	public SysWebParts checkPicInfo(@RequestParam(value = "id") Long id, Model model) {
		SysWebParts webPartsInfo = webPartsService.selectWebPartsById(id);
		if (StringUtils.isNotNull(webPartsInfo)) {
			return webPartsInfo;
		} else {
			return null;
		}

	}

	@PostMapping("/checkmenu")
	@ResponseBody
	public Object checkPicInfo(@RequestParam(value = "thread") Long thread, @RequestParam(value = "button") Long button,
			@RequestParam(value = "color") Long color, Model model) {
		if (thread > 0) {
			SysWebThread webThread = webThreadService.selectwebThreadById(thread);
			return webThread;
		}

		if (button > 0) {
			SysWebButton webButton = webButtonService.selectWebButtonById(button);
			return webButton;
		}

		if (color > 0) {
			SysWebColor webColor = webColorService.selectwebColorById(color);
			return webColor;
		}

		return null;

	}

	@PostMapping("/saveorderGoods")
	@ResponseBody
	public JSON saveorderGoods(SysWebOrderGoods webOrderGoods, Model model) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotNull(user)) {
			if (StringUtils.isNotNull(webOrderGoods)) {
				webOrderGoods.setUserId(user.getId().intValue());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				map.put("webOrderGoods", webOrderGoods);
				redisService.set("webOrderGoods:" + user.getId(), webOrderGoods);
				return JSON.ok(map);
			}
		} else {
			return JSON.error("请重新登录");
		}
		model.addAttribute("userId", user.getId());
		model.addAttribute("webOrderGoods", webOrderGoods);
		return JSON.error("请重新登录");

	}

	@PostMapping("/saveGoods")
	@ResponseBody
	public JSON saveGoods(SysWebJeans sysWebJeans, Model model) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotNull(user) && StringUtils.isNotNull(sysWebJeans)) {
			try {
				redisService.set("good:" + user.getId(), sysWebJeans);
				// redisService.set("webOrderGoods:" + user.getId(), sysWebJeans);
			} catch (Exception e) {
				e.printStackTrace();
				return JSON.error("redis异常");
			}
			return JSON.ok("保存成功");
		}
		return JSON.error("redis储存失败");
	}

	/**
	 * 保存设计（redis）
	 * 
	 * @param sysWebJeans
	 * @param model
	 * @return
	 */
	@PostMapping("/saveDesigner")
	@ResponseBody
	public JSON saveDesigner(MyDesigner sysWebDesigner, Model model) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotNull(user) && StringUtils.isNotNull(sysWebDesigner)) {
			try {
				sysWebDesigner.setUserId(user.getId().intValue());
				// 并把信息储存到mysql里
				int i = myDesignerService.saveMyDesigner(sysWebDesigner);
				if (i > 0) {
					return JSON.ok("保存成功");
				} else {
					return JSON.error("请重新登录");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return JSON.error("redis异常");
			}

		}
		return JSON.error("请重新登录");
	}

	/**
	 * 显示设计（redis）
	 * 
	 * @param sysWebJeans
	 * @param model
	 * @return
	 */
	@GetMapping("/showDesigner")
	public String showDesigner(@RequestParam(value = "id") Long id, Model model) {
		MyDesigner myDesigner = null;
		long designerId = 0;
		if (StringUtils.isNotNull(id)) {
			myDesigner = myDesignerService.selectMyDesignerById(id);
		}

		if (StringUtils.isNotNull(myDesigner)) {

			designerId = myDesigner.getDesignerId().longValue();
			model.addAttribute("id", designerId);
		}

		List<SysWebSizes> webSizesList = new ArrayList<>();
		List<SysWebColor> webColorList = new ArrayList<>();
		List<SysWebButton> webButtonList = new ArrayList<>();
		List<SysWebThread> webThreadList = new ArrayList<>();
		List<SysWebWash> webWashList = new ArrayList<>();
		List<SysWebParts> webPartsList = new ArrayList<>();

		String webAreaList = null;// 区域

		SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(designerId);
		// 我的设计注入相应的值
		if (StringUtils.isNotNull(webDesigner) && StringUtils.isNotNull(myDesigner)) {
			webDesigner.setButtonsId(myDesigner.getButtonsId());
			webDesigner.setColorsId(myDesigner.getColorsId());
			webDesigner.setPartsId(myDesigner.getPartsId());
			webDesigner.setThreadsId(myDesigner.getThreadsId());
			webDesigner.setWashId(myDesigner.getWashId());

			webDesigner.setWordColor(myDesigner.getWordColor());
			webDesigner.setWordContent(myDesigner.getWordContent());
			webDesigner.setWordFont(myDesigner.getWordFont());
		}

		model.addAttribute("webDesigner", webDesigner);

		if (StringUtils.isNotNull(webDesigner)) {
			SysWebJeans webjeans = webJeansService.selectWebJeansById(webDesigner.getJeansId().longValue());
			model.addAttribute("webjeans", webjeans);
			// 成品库信息
			SysWebColor webJeansColor = null;
			if (StrUtils.isNotEmpty(webDesigner.getColorsId())) {
				webJeansColor = webColorService.selectwebColorById(Long.valueOf(webDesigner.getColorsId()));
			}
			SysWebSizes webJeansSizes = null;
			if (StrUtils.isNotEmpty(webDesigner.getSizesId())) {
				webJeansSizes = webSizesService.selectSysWebSizesById(Long.valueOf(webDesigner.getSizesId()));
			}
			SysWebButton webJeansButton = null;
			if (StrUtils.isNotEmpty(webDesigner.getButtonsId())) {
				webJeansButton = webButtonService.selectWebButtonById(Long.valueOf(webDesigner.getButtonsId()));
			}
			SysWebThread webJeansThread = null;
			if (StrUtils.isNotEmpty(webDesigner.getThreadsId())) {
				webJeansThread = webThreadService.selectwebThreadById(Long.valueOf(webDesigner.getThreadsId()));
			}
			SysWebWash webJeansWash = null;
			if (StrUtils.isNotEmpty(webDesigner.getWashId())) {
				webJeansWash = webWashService.selectWebWashById(Long.valueOf(webDesigner.getWashId()));
			}

			PartsVO partsVO = new PartsVO();
			String parts = webDesigner.getPartsId();
			String[] partsDesigner = parts.split(",");
			for (String part : partsDesigner) {
				SysWebParts webJeansParts = webPartsService.selectWebPartsById(Long.valueOf(part));
				if (webJeansParts.getRegion() == 1) {
					partsVO.setParts1(webJeansParts);
				}

				if (webJeansParts.getRegion() == 2) {
					partsVO.setParts2(webJeansParts);
				}

				if (webJeansParts.getRegion() == 3) {
					partsVO.setParts3(webJeansParts);
				}

				if (webJeansParts.getRegion() == 4) {
					partsVO.setParts4(webJeansParts);
				}

				if (webJeansParts.getRegion() == 5) {
					partsVO.setParts5(webJeansParts);
				}

				if (webJeansParts.getRegion() == 6) {
					partsVO.setParts6(webJeansParts);
				}

				if (webJeansParts.getRegion() == 7) {
					partsVO.setParts7(webJeansParts);
				}

				if (webJeansParts.getRegion() == 8) {
					partsVO.setParts8(webJeansParts);
				}
			}

			model.addAttribute("webJeansColor", webJeansColor);
			model.addAttribute("webJeansSizes", webJeansSizes);
			model.addAttribute("webJeansButton", webJeansButton);
			model.addAttribute("webJeansWash", webJeansWash);
			model.addAttribute("webJeansThread", webJeansThread);
			// model.addAttribute("webJeansParts", partsVO);
			model.addAttribute("partsVO", partsVO);
			// 素库表不为空
			if (StringUtils.isNotNull(webjeans)) {

				// 颜色
				String colorString = webjeans.getColorsId();
				if (StringUtils.isNotNull(colorString)) {
					String[] colorArr = colorString.split(",");
					for (String colorId : colorArr) {
						SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(colorId));
						webColorList.add(webColor);

					}

					model.addAttribute("webColorList", webColorList);
				}

				// 尺码
				String sizesString = webjeans.getSizesId();
				if (StringUtils.isNotNull(sizesString)) {
					String[] sizesArr = sizesString.split(",");
					for (String sizesId : sizesArr) {
						SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
						webSizesList.add(webSizes);

					}

					model.addAttribute("webSizesList", webSizesList);
				}

				// 纽扣
				String buttonString = webjeans.getButtonsId();
				if (StringUtils.isNotNull(buttonString)) {
					String[] buttonArr = buttonString.split(",");
					for (String buttonId : buttonArr) {
						SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(buttonId));
						webButtonList.add(webButton);

					}
					model.addAttribute("webButtonList", webButtonList);
				}

				// 破洞
				String washString = webjeans.getWashId();
				if (StringUtils.isNotNull(washString)) {
					String[] washArr = washString.split(",");
					for (String washId : washArr) {
						SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(washId));
						webWashList.add(webWash);

					}

					model.addAttribute("webWashList", webWashList);
				}

				// 边线
				String threadString = webjeans.getThreadsId();
				if (StringUtils.isNotNull(threadString)) {
					String[] threadArr = threadString.split(",");
					for (String threadId : threadArr) {
						SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(threadId));
						webThreadList.add(webThread);

					}

					model.addAttribute("webThreadList", webThreadList);
				}

				// 图片区域
				String partsString = webjeans.getPartsId();
				if (StringUtils.isNotNull(partsString)) {
					String[] partsArr = partsString.split(",");
					for (String partsId : partsArr) {
						SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(partsId));
						webPartsList.add(webParts);

						if (webParts != null) {
							webAreaList += webParts.getId() + ",";
						}
					}
					model.addAttribute("webPartsList", webPartsList);
				}

			}

		}

		SysWebParts webPartsCover = new SysWebParts();
		webPartsCover.setRegion(1);
		webPartsCover.setStatus(1);
		List<SysWebParts> webParts1 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi1 = new ArrayList<>();
		for (SysWebParts webParts : webParts1) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi1.add(webParts);
			}
		}
		model.addAttribute("webParts1", webPartsLi1);

		webPartsCover.setRegion(2);
		List<SysWebParts> webParts2 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi2 = new ArrayList<>();
		for (SysWebParts webParts : webParts2) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi2.add(webParts);
			}
		}

		model.addAttribute("webParts2", webPartsLi2);

		webPartsCover.setRegion(3);
		List<SysWebParts> webParts3 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi3 = new ArrayList<>();
		for (SysWebParts webParts : webParts3) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi3.add(webParts);
			}
		}

		model.addAttribute("webParts3", webPartsLi3);

		webPartsCover.setRegion(4);
		List<SysWebParts> webParts4 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi4 = new ArrayList<>();
		for (SysWebParts webParts : webParts4) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi4.add(webParts);
			}
		}

		model.addAttribute("webParts4", webPartsLi4);

		webPartsCover.setRegion(5);
		List<SysWebParts> webParts5 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi5 = new ArrayList<>();
		for (SysWebParts webParts : webParts5) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi5.add(webParts);
			}
		}

		model.addAttribute("webParts5", webPartsLi5);

		webPartsCover.setRegion(6);
		List<SysWebParts> webParts6 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi6 = new ArrayList<>();
		for (SysWebParts webParts : webParts6) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi6.add(webParts);
			}
		}

		model.addAttribute("webParts6", webPartsLi6);

		webPartsCover.setRegion(7);
		List<SysWebParts> webParts7 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi7 = new ArrayList<>();
		for (SysWebParts webParts : webParts7) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi7.add(webParts);
			}
		}

		model.addAttribute("webParts7", webPartsLi7);

		webPartsCover.setRegion(8);
		List<SysWebParts> webParts8 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi8 = new ArrayList<>();
		for (SysWebParts webParts : webParts8) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi8.add(webParts);
			}
		}

		model.addAttribute("webParts8", webPartsLi8);
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/custom";
	}

	/**
	 * 显示设计（redis）
	 * 
	 * @param sysWebJeans
	 * @param model
	 * @return
	 */
	@PostMapping("/showDesigner1")
	public String showDesigner1(MyDesignerVO myDesigner, Model model) {
		long id = 0;
		if (StringUtils.isNotNull(myDesigner)) {
			// System.err.println("********" + myDesigner.getWebDesignerId());
			id = myDesigner.getWebDesignerId();
			model.addAttribute("id", id);
		}

		List<SysWebSizes> webSizesList = new ArrayList<>();
		List<SysWebColor> webColorList = new ArrayList<>();
		List<SysWebButton> webButtonList = new ArrayList<>();
		List<SysWebThread> webThreadList = new ArrayList<>();
		List<SysWebWash> webWashList = new ArrayList<>();
		List<SysWebParts> webPartsList = new ArrayList<>();

		String webAreaList = null;// 区域

		SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(id);
		// 我的设计注入相应的值
		if (StringUtils.isNotNull(webDesigner) && StringUtils.isNotNull(myDesigner)) {
			webDesigner.setButtonsId(myDesigner.getButton());
			webDesigner.setColorsId(myDesigner.getColor());
			webDesigner.setPartsId(myDesigner.getWebParts());
			webDesigner.setThreadsId(myDesigner.getWebParts());
			webDesigner.setWashId(myDesigner.getPush());

		}

		model.addAttribute("webDesigner", webDesigner);

		if (StringUtils.isNotNull(webDesigner)) {
			SysWebJeans webjeans = webJeansService.selectWebJeansById(webDesigner.getJeansId().longValue());
			model.addAttribute("webjeans", webjeans);
			// 成品库信息
			SysWebColor webJeansColor = null;
			if (StrUtils.isNotEmpty(webDesigner.getColorsId())) {
				webJeansColor = webColorService.selectwebColorById(Long.valueOf(webDesigner.getColorsId()));
			}
			SysWebSizes webJeansSizes = null;
			if (StrUtils.isNotEmpty(webDesigner.getSizesId())) {
				webJeansSizes = webSizesService.selectSysWebSizesById(Long.valueOf(webDesigner.getSizesId()));
			}
			SysWebButton webJeansButton = null;
			if (StrUtils.isNotEmpty(webDesigner.getButtonsId())) {
				webJeansButton = webButtonService.selectWebButtonById(Long.valueOf(webDesigner.getButtonsId()));
			}
			SysWebThread webJeansThread = null;
			if (StrUtils.isNotEmpty(webDesigner.getThreadsId())) {
				webJeansThread = webThreadService.selectwebThreadById(Long.valueOf(webDesigner.getThreadsId()));
			}
			SysWebWash webJeansWash = null;
			if (StrUtils.isNotEmpty(webDesigner.getWashId())) {
				webJeansWash = webWashService.selectWebWashById(Long.valueOf(webDesigner.getWashId()));
			}

			PartsVO partsVO = new PartsVO();
			String parts = webDesigner.getPartsId();
			String[] partsDesigner = parts.split(",");
			for (String part : partsDesigner) {
				SysWebParts webJeansParts = webPartsService.selectWebPartsById(Long.valueOf(part));
				if (webJeansParts.getRegion() == 1) {
					partsVO.setParts1(webJeansParts);
				}

				if (webJeansParts.getRegion() == 2) {
					partsVO.setParts2(webJeansParts);
				}

				if (webJeansParts.getRegion() == 3) {
					partsVO.setParts3(webJeansParts);
				}

				if (webJeansParts.getRegion() == 4) {
					partsVO.setParts4(webJeansParts);
				}

				if (webJeansParts.getRegion() == 5) {
					partsVO.setParts5(webJeansParts);
				}

				if (webJeansParts.getRegion() == 6) {
					partsVO.setParts6(webJeansParts);
				}

				if (webJeansParts.getRegion() == 7) {
					partsVO.setParts7(webJeansParts);
				}

				if (webJeansParts.getRegion() == 8) {
					partsVO.setParts8(webJeansParts);
				}
			}

			model.addAttribute("webJeansColor", webJeansColor);
			model.addAttribute("webJeansSizes", webJeansSizes);
			model.addAttribute("webJeansButton", webJeansButton);
			model.addAttribute("webJeansWash", webJeansWash);
			model.addAttribute("webJeansThread", webJeansThread);
			// model.addAttribute("webJeansParts", partsVO);
			model.addAttribute("partsVO", partsVO);
			// 素库表不为空
			if (StringUtils.isNotNull(webjeans)) {

				// 颜色
				String colorString = webjeans.getColorsId();
				if (StringUtils.isNotNull(colorString)) {
					String[] colorArr = colorString.split(",");
					for (String colorId : colorArr) {
						SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(colorId));
						webColorList.add(webColor);

					}

					model.addAttribute("webColorList", webColorList);
				}

				// 尺码
				String sizesString = webjeans.getSizesId();
				if (StringUtils.isNotNull(sizesString)) {
					String[] sizesArr = sizesString.split(",");
					for (String sizesId : sizesArr) {
						SysWebSizes webSizes = webSizesService.selectSysWebSizesById(Long.valueOf(sizesId));
						webSizesList.add(webSizes);

					}

					model.addAttribute("webSizesList", webSizesList);
				}

				// 纽扣
				String buttonString = webjeans.getButtonsId();
				if (StringUtils.isNotNull(buttonString)) {
					String[] buttonArr = buttonString.split(",");
					for (String buttonId : buttonArr) {
						SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(buttonId));
						webButtonList.add(webButton);

					}
					model.addAttribute("webButtonList", webButtonList);
				}

				// 破洞
				String washString = webjeans.getWashId();
				if (StringUtils.isNotNull(washString)) {
					String[] washArr = washString.split(",");
					for (String washId : washArr) {
						SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(washId));
						webWashList.add(webWash);

					}

					model.addAttribute("webWashList", webWashList);
				}

				// 边线
				String threadString = webjeans.getThreadsId();
				if (StringUtils.isNotNull(threadString)) {
					String[] threadArr = threadString.split(",");
					for (String threadId : threadArr) {
						SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(threadId));
						webThreadList.add(webThread);

					}

					model.addAttribute("webThreadList", webThreadList);
				}

				// 图片区域
				String partsString = webjeans.getPartsId();
				if (StringUtils.isNotNull(partsString)) {
					String[] partsArr = partsString.split(",");
					for (String partsId : partsArr) {
						SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(partsId));
						webPartsList.add(webParts);

						if (webParts != null) {
							webAreaList += webParts.getId() + ",";
						}
					}
					model.addAttribute("webPartsList", webPartsList);
				}

			}

		}

		SysWebParts webPartsCover = new SysWebParts();
		webPartsCover.setRegion(1);
		webPartsCover.setStatus(1);
		List<SysWebParts> webParts1 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi1 = new ArrayList<>();
		for (SysWebParts webParts : webParts1) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi1.add(webParts);
			}
		}
		model.addAttribute("webParts1", webPartsLi1);

		webPartsCover.setRegion(2);
		List<SysWebParts> webParts2 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi2 = new ArrayList<>();
		for (SysWebParts webParts : webParts2) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi2.add(webParts);
			}
		}

		model.addAttribute("webParts2", webPartsLi2);

		webPartsCover.setRegion(3);
		List<SysWebParts> webParts3 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi3 = new ArrayList<>();
		for (SysWebParts webParts : webParts3) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi3.add(webParts);
			}
		}

		model.addAttribute("webParts3", webPartsLi3);

		webPartsCover.setRegion(4);
		List<SysWebParts> webParts4 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi4 = new ArrayList<>();
		for (SysWebParts webParts : webParts4) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi4.add(webParts);
			}
		}

		model.addAttribute("webParts4", webPartsLi4);

		webPartsCover.setRegion(5);
		List<SysWebParts> webParts5 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi5 = new ArrayList<>();
		for (SysWebParts webParts : webParts5) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi5.add(webParts);
			}
		}

		model.addAttribute("webParts5", webPartsLi5);

		webPartsCover.setRegion(6);
		List<SysWebParts> webParts6 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi6 = new ArrayList<>();
		for (SysWebParts webParts : webParts6) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi6.add(webParts);
			}
		}

		model.addAttribute("webParts6", webPartsLi6);

		webPartsCover.setRegion(7);
		List<SysWebParts> webParts7 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi7 = new ArrayList<>();
		for (SysWebParts webParts : webParts7) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi7.add(webParts);
			}
		}

		model.addAttribute("webParts7", webPartsLi7);

		webPartsCover.setRegion(8);
		List<SysWebParts> webParts8 = webPartsService.selectWebPartsAll(webPartsCover);
		List<SysWebParts> webPartsLi8 = new ArrayList<>();
		for (SysWebParts webParts : webParts8) {
			if (webAreaList.contains(webParts.getId() + "")) {
				webPartsLi8.add(webParts);
			}
		}

		model.addAttribute("webParts8", webPartsLi8);
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/custom";
	}
}
