package com.jwk.project.system.area.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jwk.common.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.project.system.area.domain.Area;
import com.jwk.project.system.area.service.IAreaService;

/**
 * 城市表
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/area")
public class AreaController {
	@Autowired
	private IAreaService areaService;

	// @RequiresPermissions("system:area:list") 验证是否有访问数据的权限
	@GetMapping("/list")
	@ResponseBody
	public List<Area> list() throws Exception {

		List<Area> resultList = new ArrayList<>();
		List<Area> dataList = areaService.selectAreaAll();
		for (Area area : dataList) {
			Long a = 0L;
			if (a.equals(area.getParentId())) {
				resultList.add(area);
				mergeChild(area, dataList);
			}
		}
		return dataList;
	}

	private void mergeChild(Area currentArea, List<Area> dataList) throws Exception {
		List<Area> children = new ArrayList<>();
		for (Area area : dataList) {
			if (currentArea.getId().equals(area.getParentId())) {
				children.add(area);
				mergeChild(area, dataList);
			}
		}
		if (!children.isEmpty())
			currentArea.setChildList(children);
	}



	/*@GetMapping("/list2")
	@ResponseBody
	public String list2() throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Area> resultList = new ArrayList<>();
		List<Area> dataList = areaService.selectAreaAll();
		for (Area area : dataList) {
			Long a = 0L;
			if (a.equals(area.getParentId())) {
				resultList.add(area);
				mergeChild(area, dataList);
			}
		}
		map.put("dataList",dataList);
		return JacksonUtils.toJsonString(map);
	}*/
}
