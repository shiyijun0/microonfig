package com.jwk.project.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.service.RedisService;
import com.jwk.project.system.addr.domain.Addr;
import com.jwk.project.system.addr.domain.AddrVo;
import com.jwk.project.system.addr.service.IAddrService;
import com.jwk.project.system.area.domain.Area;
import com.jwk.project.system.area.service.IAreaService;
import com.jwk.project.system.registeruser.domain.RegisterUser;

@Controller
@RequestMapping("/custom")
public class AddressController{

	private String prefix = "custom";

	@Autowired
	private IAddrService iAddrService;

	@Autowired
	private IAreaService areaService;

	@Autowired
	private RedisService redisService;

	/**
	 * 添加地址
	 * 
	 * @return
	 */
	@GetMapping("/address_add")
	public String address_add(@RequestParam(name = "jeansId", required = false) String jeansId,
			@RequestParam(name = "orderType", required = false) String orderType,
			@RequestParam(name = "cartId", required = false) String cartId,
			@RequestParam(name = "cartOrder", required = false) String cartOrder,Model model) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotEmpty(jeansId)) {
			model.addAttribute("jeansId", jeansId);
			if (StringUtils.isNotNull(user)) {
				model.addAttribute("userId", user.getId());
			}

		} else {
			model.addAttribute("jeansId", 0);
			model.addAttribute("userId", 0);
		}
		model.addAttribute("orderType", orderType);
		model.addAttribute("cartOrder", cartOrder);
		model.addAttribute("cartId", cartId);
		return prefix + "/address_add";
	}

	/**
	 * 编辑地址
	 */
	@GetMapping("/addredit")
	public String edit(@RequestParam(name = "addrId", required = false) Long addrId, Model model,
			@RequestParam(name = "jeansId", required = false) String jeansId,
			@RequestParam(name = "cartOrder", required = false) String cartOrder,
			@RequestParam(name = "cartId", required = false) String cartId,
			@RequestParam(name = "orderType", required = false) String orderType) {

		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotEmpty(jeansId)) {
			model.addAttribute("jeansId", jeansId);
			if (StringUtils.isNotNull(user)) {
				model.addAttribute("userId", user.getId());
			}

		} else {
			model.addAttribute("jeansId", 0);
			model.addAttribute("userId", 0);
		}
		List<Area> dataList = areaService.selectAreaAll();
		Map<Long, String> areaMap = new HashMap();
		for (Area area : dataList) {
			areaMap.put((long) area.getOrderNum(), area.getName());
		}
		Addr addr = iAddrService.selectAddrById(addrId);
		AddrVo addrVo = new AddrVo();
		BeanUtils.copyProperties(addr, addrVo);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(areaMap.get(addr.getProvinceId())).append(" ").append(areaMap.get(addr.getCityId()))
				.append(" ");
		String area = areaMap.get(addr.getAreaId());
		if (StringUtils.isNotEmpty(area)) {
			stringBuilder.append(area).append(" ");
		}
		addrVo.setArea(stringBuilder.toString());
		model.addAttribute("addr", addrVo);
		model.addAttribute("orderType", orderType);
		model.addAttribute("cartOrder", cartOrder);
		model.addAttribute("cartId", cartId);

		return prefix + "/address_edit";
	}

	/**
	 * 地址管理
	 * 
	 * @return
	 * @throws ClientAbortException 
	 */
	@GetMapping("/address")
	public String address(@RequestParam(name = "jeansId", required = false) String jeansId, HttpServletRequest request,
			Model model) throws Exception {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if(user ==null)
			throw new ClientAbortException("登录超时，请重新登录");
		
		if (StringUtils.isNotEmpty(jeansId)) {
			model.addAttribute("jeansId", jeansId);
			if (StringUtils.isNotNull(user)) {
				model.addAttribute("userId", user.getId());
			}

		} else {
			model.addAttribute("jeansId", 0);
			model.addAttribute("userId", 0);
		}


		if (user != null) {
			List<Area> dataList = areaService.selectAreaAll();
			Map<Long, String> areaMap = new HashMap();
			for (Area area : dataList) {
				areaMap.put((long) area.getOrderNum(), area.getName());
			}

			List<Addr> list = (List<Addr>) iAddrService.selectAddrAll(user.getId());
			List<AddrVo> addrVoList = new ArrayList<>();
			AddrVo addrVo = null;
			for (Addr addr : list) {
				addrVo = new AddrVo();
				BeanUtils.copyProperties(addr, addrVo);
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(areaMap.get(addr.getProvinceId())).append(",")
						.append(areaMap.get(addr.getCityId())).append(",");
				String area = areaMap.get(addr.getAreaId());
				if (StringUtils.isNotEmpty(area)) {
					stringBuilder.append(area).append(",");
				}
				stringBuilder.append(addr.getAddr());
				addrVo.setAddr(stringBuilder.toString());
				addrVoList.add(addrVo);
			}
			if (addrVoList != null) {
				model.addAttribute("address", addrVoList);
			}
			System.out.println(addrVoList);
		}


		String orSize = request.getParameter("orSize");
		String orColor = request.getParameter("orColor");
		if(StrUtils.isNotEmpty(orSize) && StrUtils.isNotEmpty(orColor)) {
			Map<String, Object> orMap = new LinkedHashMap<>();
			orMap.put("orSize", orSize);
			orMap.put("orColor", orColor);
			redisService.hSet("usSizeOrColor:" + user.getId(), orMap);
		}
		model.addAttribute("addrId", request.getParameter("addrId"));
		model.addAttribute("orderType", request.getParameter("orderType"));
		model.addAttribute("cartOrder", request.getParameter("cartOrder"));
		model.addAttribute("cartId", request.getParameter("cartId"));
		return prefix + "/address";
	}

	/**
	 * 地址管理
	 * 
	 * @return
	 */
	@GetMapping("/toaddr")
	public String toaddr(@RequestParam(name = "jeansId", required = false) String jeansId, Model model,
			HttpServletRequest request) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (StringUtils.isNotEmpty(jeansId)) {
			model.addAttribute("jeansId", jeansId);
			if (StringUtils.isNotNull(user)) {
				model.addAttribute("userId", user.getId());
			}

		} else {
			model.addAttribute("jeansId", 0);
			model.addAttribute("userId", 0);
		}

		String orSize = request.getParameter("orSize");
		String orColor = request.getParameter("orColor");
		if(StrUtils.isNotEmpty(orSize) && StrUtils.isNotEmpty(orColor)) {
			Map<String, Object> orMap = new LinkedHashMap<>();
			orMap.put("orSize", orSize);
			orMap.put("orColor", orColor);
			redisService.hSet("usSizeOrColor:" + user.getId(), orMap);
		}

		model.addAttribute("firstAddr", 0);
		model.addAttribute("orderType", request.getParameter("orderType"));
		model.addAttribute("cartId", request.getParameter("cartId"));
		model.addAttribute("cartOrder", request.getParameter("cartOrder"));
		return prefix + "/address_add";
	}

	// 用户增加地址并保存
	@RequestMapping(value = "/addAddr")
	@ResponseBody
	public JSON addAddr(AddrVo addrVo) {

		List<Area> dataList = areaService.selectAreaAll();
		Map<String, Long> areaMap = new HashMap();

		for (Area area : dataList) {
			areaMap.put(area.getName(), (long) area.getOrderNum());
		}

		Addr addr = new Addr();
		BeanUtils.copyProperties(addrVo, addr);
		String[] split = addrVo.getArea().split(" ");
		addr.setProvinceId(areaMap.get(split[0]));
		String key = split[1];
		Long cityId = areaMap.get(key);
		if (cityId == null) {
			key = split[1] + "市";
			cityId = areaMap.get(key);
		}
		addr.setCityId(cityId);
		if (split.length > 2) {
			addr.setAreaId(areaMap.get(split[2]));
		}

		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user != null) {
			if (addr.getDefaultFlag() == 1) {
				if (iAddrService.checkDefaultAddr(user.getId())) {
					iAddrService.cancelDefaultFlag(user.getId());
				}
			}
			// 保存地址
			addr.setUserId(user.getId());
			int addrId = iAddrService.saveAddr(addr);
			if (addrId > 0) {
				return JSON.ok();
			}
		}

		return JSON.error();

	}

	// 删除地址
	@RequestMapping(value = "/addrclose")
	@ResponseBody
	public JSON Addrclose(Long id) {
		if (iAddrService.deleteAddrById(id) > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}

	// 更改默认地址
	@RequestMapping(value = "/updatedefault")
	@ResponseBody
	public JSON updatedefault(Long id) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user != null) {
			if (iAddrService.checkDefaultAddr(user.getId())) {
				iAddrService.cancelDefaultFlag(user.getId());
				iAddrService.updateDefaultFlag(id);
				return JSON.ok();
			} else {
				iAddrService.updateDefaultFlag(id);
				return JSON.ok();
			}
		}
		return JSON.error();
	}

}
