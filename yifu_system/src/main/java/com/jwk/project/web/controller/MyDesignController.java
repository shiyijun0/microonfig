package com.jwk.project.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.web.domain.MyDesigner;
import com.jwk.project.system.web.domain.SysWebButton;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebDesigner;
import com.jwk.project.system.web.domain.SysWebParts;
import com.jwk.project.system.web.domain.SysWebPosition;
import com.jwk.project.system.web.domain.SysWebThread;
import com.jwk.project.system.web.domain.SysWebWash;
import com.jwk.project.system.web.domainVO.MyDesignerVO;
import com.jwk.project.system.web.service.MyDesignerService;
import com.jwk.project.system.web.service.WebButtonService;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebDesignerService;
import com.jwk.project.system.web.service.WebPartsService;
import com.jwk.project.system.web.service.WebPositionService;
import com.jwk.project.system.web.service.WebThreadService;
import com.jwk.project.system.web.service.WebWashService;

@Controller
@RequestMapping("/custom")
public class MyDesignController {
	private String prefix = "custom";

	@Autowired
	private MyDesignerService myDesignerService;

	@Autowired
	private WebPositionService webPositionService;

	@Autowired
	private WebPartsService webPartsService;
	@Autowired
	private WebColorService webColorService;
	@Autowired
	private WebButtonService webButtonService;
	@Autowired
	private WebDesignerService webDesignerService;
	@Autowired
	private WebThreadService webThreadService;
	@Autowired
	private WebWashService webWashService;

	/**
	 * 我的设计
	 * 
	 * @return
	 */
	@GetMapping("/my_design")
	public String myDesign(Model model) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNull(user)) {
			return prefix + "/index";
		}

		// 查询我的设计表
		// List<MyDesigner> myDesignerList=myDesignerService.selectMyDesignerAll();
		MyDesigner myDesigner = new MyDesigner();
		myDesigner.setUserId(user.getId().intValue());
		List<MyDesigner> myDesignerList = myDesignerService.selectMyDesignerByMyDesigner(myDesigner);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MyDesignerVO> dvoList = new ArrayList<MyDesignerVO>();

		if (myDesignerList.size() > 0) {
			MyDesignerVO dvo = null;
			Collections.reverse(myDesignerList);
			for (MyDesigner des : myDesignerList) {
				dvo = new MyDesignerVO();
				dvo.setId(des.getId());
				dvo.setWebDesignerId(des.getDesignerId());
				dvo.setWebParts(des.getPartsId());
				String subtime = df.format(des.getUpdateTime());
				dvo.setSubtime(subtime);
				SysWebDesigner webDesigner = webDesignerService.selectWebDesignerById(des.getDesignerId().longValue());
				if (StringUtils.isNotNull(webDesigner)) {
					String name = webDesigner.getName();
					dvo.setName(name);
				}
				SysWebButton webButton = webButtonService.selectWebButtonById(Long.valueOf(des.getButtonsId()));
				if (StringUtils.isNotNull(webButton)) {
					String button = webButton.getName();
					dvo.setButton(button);
				}

				SysWebThread webThread = webThreadService.selectwebThreadById(Long.valueOf(des.getThreadsId()));
				if (StringUtils.isNotNull(webThread)) {
					String thread = webThread.getName();
					dvo.setThread(thread);
				}

				SysWebWash webWash = webWashService.selectWebWashById(Long.valueOf(des.getWashId()));
				if (StringUtils.isNotNull(webWash)) {
					String push = webWash.getName();
					dvo.setPush(push);
				}

				SysWebColor webColor = webColorService.selectwebColorById(Long.valueOf(des.getColorsId()));
				if (StringUtils.isNotNull(webColor)) {
					String color = webColor.getName();
					dvo.setColor(color);
				}

				String parts = des.getPartsId();
				String[] part = parts.split(",");
				// List<String> webPart=new ArrayList<String>();
				Map<String, String> webPart = new HashMap<String, String>();
				/*
				 * for(String par:part){ SysWebParts
				 * webParts=getWebPartsService().selectWebPartsById(Long.valueOf(par));
				 * if(StringUtils.isNotNull(webParts)){ webPart.add(webParts.getName()); } }
				 */

				for (String par : part) {
					SysWebParts webParts = webPartsService.selectWebPartsById(Long.valueOf(par));
					if (StringUtils.isNotNull(webParts)) {
						// webPart.add(webParts.getName());
						SysWebPosition position = new SysWebPosition();
						position.setPosition(webParts.getRegion());
						position.setType(webParts.getType());
						SysWebPosition webPosition = webPositionService.selectSysWebPositionByPosition(position);
						if (StringUtils.isNotNull(webPosition)) {
							webPart.put(webPosition.getDes(), webParts.getName());
						}
					}
				}
				dvo.setParts(webPart);
				dvoList.add(dvo);

			}

		}

		if (dvoList.size() > 0) {
			model.addAttribute("myDesignerList", dvoList);
		}

		return prefix + "/my_design";
	}

	/**
	 * 我的设计 删除
	 * 
	 * @return
	 */
	@PostMapping("/my_design_delete")
	@ResponseBody
	public JSON myDesignDelete(@RequestParam("id") Long id) {
		MyDesigner myDesigner = myDesignerService.selectMyDesignerById(id);
		if (myDesigner == null) {
			return JSON.error("该删除的设计款不存在");
		}
		if (myDesignerService.deleteMyDesignerById(id) > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}
}
