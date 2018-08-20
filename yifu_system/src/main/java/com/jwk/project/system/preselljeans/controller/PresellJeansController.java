package com.jwk.project.system.preselljeans.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.limitconfig.domain.SysLimitConfig;
import com.jwk.project.system.limitconfig.service.ILimitConfigService;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.system.web.domain.SysWebColor;
import com.jwk.project.system.web.domain.SysWebSizes;
import com.jwk.project.system.web.service.WebColorService;
import com.jwk.project.system.web.service.WebSizesService;

/**
 * 预售款
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/presell")
public class PresellJeansController extends BaseController {
	private String prefix = "system/presell";

	@Autowired
	private IPresellJeansService service;

	@Autowired
	private WebColorService colorservice;

	@Autowired
	private WebSizesService sizeservice;

	@Autowired
	ILimitConfigService limitConfigService;

	@RequiresPermissions("system:presell:view")
	@GetMapping()
	public String presell(Model model) {
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/presell";
	}

	/**
	 * 新增
	 */
	@RequiresPermissions("system:presell:add")
	@Log(title = "系统管理", action = "预售款管理-新增")
	@GetMapping("/add")
	public String add(Model model) {
		List<SysWebColor> colorlist = colorservice.selectwebColorAll();
		model.addAttribute("colorlist", colorlist);

		List<SysWebSizes> sizelist = sizeservice.selectSysWebSizesAll();
		model.addAttribute("sizelist", sizelist);
		return prefix + "/add";
	}

	/**
	 * 显示预售款数据
	 */
	@RequiresPermissions("system:presell:list")
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo list() {
		TableDataInfo rows = service.pageInfoQuery(getPageUtilEntity());
		return rows;
	}

	/**
	 * 保存预售款
	 */
	@RequiresPermissions("system:presell:save")
	@Log(title = "系统管理", action = "预售款管理-保存预售款")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public JSON save(SysPresellJeans sysPresellJeans, HttpServletRequest request) {

		String imgs2 = request.getParameter("imgs2");
		String imgs = request.getParameter("imgs");

		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		multipartRequest.getFileMap();
		if (StrUtils.isEmpty(imgs)) {
			List<MultipartFile> files = multipartRequest.getFiles("file1");
			if (files.isEmpty()) {
				return JSON.error("上传的商品图片不能为空");
			}
			String images = this.uploadFileName(files);
			if (StringUtils.isNotEmpty(images)) {
				sysPresellJeans.setImages(images);
			}
		}
		if (StrUtils.isEmpty(imgs2)) {
			List<MultipartFile> files2 = multipartRequest.getFiles("file2");
			List<MultipartFile> files3 = multipartRequest.getFiles("file3");
			List<MultipartFile> files4 = multipartRequest.getFiles("file4");
			String deatailImgs = "";
			if (files2.isEmpty()) {
				return JSON.error("上传的商品详细图片不能为空");
			}
			String img1 = this.uploadFileName(files2);
			sysPresellJeans.setDetailImg1(img1);
			deatailImgs = img1;
			if (!files3.isEmpty()) {
				String img2 = this.uploadFileName(files3);
				deatailImgs = deatailImgs+","+img2;
				sysPresellJeans.setDetailImg2(img2);
			}
			if (!files4.isEmpty()) {
				String img3 = this.uploadFileName(files4);
				deatailImgs = deatailImgs+","+img3;
				sysPresellJeans.setDetailImg3(img3);
			}
			if (StringUtils.isNotEmpty(deatailImgs)) {
				sysPresellJeans.setDetailImgs(deatailImgs);
			}
		}
		sysPresellJeans.setResultNum(sysPresellJeans.getInventory());
		// 插入图片
		if (service.save(sysPresellJeans) > 0) {
			return JSON.ok();
		}
		return JSON.error("提交失败");
	}

	// 写入上传文件储存工具类
	public String uploadFileName(List<MultipartFile> files) {
		Iterator<MultipartFile> iter = files.iterator();
		String custName = null;
		String path = "presell/";
		while (iter.hasNext()) {
			MultipartFile file = iter.next();
			if (file != null) { // 现在有文件上传
				if (file.getSize() > 0) {
					// String contentType = file.getContentType();
					String fileName = file.getOriginalFilename();
					String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
					custName = "" + System.currentTimeMillis() + "." + prefix;
					try {
						QiniuUtils.upload(file.getBytes(), path + custName);
					} catch (Exception e) {
						System.out.println("*****文件出现异常信息*****");
					}
				}
			}
		}
		return path + custName;
	}

	/**
	 * 修改预售款信息
	 */
	@RequiresPermissions("system:presell:edit")
	@Log(title = "系统管理", action = "预售款管理-修改预售款")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		SysPresellJeans presellJeans = service.selectById(id);
		if (StringUtils.isNotNull(presellJeans)) {
			model.addAttribute("presellJeans", presellJeans);
		}
		List<SysWebColor> colorlist = colorservice.selectwebColorAll();
		model.addAttribute("colorlist", colorlist);

		List<SysWebSizes> sizelist = sizeservice.selectSysWebSizesAll();
		model.addAttribute("sizelist", sizelist);

		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/edit";
	}

	@RequiresPermissions("system:presell:batchRemove")
	@Log(title = "系统管理", action = "预售款管理-批量删除")
	@PostMapping("/batchRemove")
	@ResponseBody
	public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
		int rows = service.batchDeletePresellJeans(ids);
		if (rows > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}

	@RequiresPermissions("system:presell:remove")
	@Log(title = "系统管理", action = "预售款管理-删除")
	@RequestMapping("/remove/{id}")
	@ResponseBody
	@Transactional
	public JSON remove(@PathVariable("id") Long id) {
		SysPresellJeans presellJeans = service.selectById(id);
		if (presellJeans == null) {
			return JSON.error("该预售款不存在");
		}
		Long presellId = presellJeans.getId();
		SysLimitConfig config = limitConfigService.selectByPresellId(presellId);
		if (config != null) {// 删除预售款限量信息
			limitConfigService.deleteLimitConfigById(config.getId());
		}
		if (service.deleteById(id) > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}

	/**
	 * 查看预售款限量信息
	 */
	@RequiresPermissions("system:presell:editconfig")
	@Log(title = "系统管理", action = "预售款管理-限时限量限价")
	@GetMapping("/editconfig/{id}")
	public String editconfig(@PathVariable("id") Long id, Model model) {
		SysLimitConfig config = limitConfigService.selectByPresellId(id);

		if (StringUtils.isNull(config))
			config = new SysLimitConfig();

		model.addAttribute("limitconfig", config);
		model.addAttribute("presellId", id);
		return prefix + "/editconfig";
	}

	/**
	 * 保存限量
	 */
	@RequiresPermissions("system:presell:saveconfig")
	@Log(title = "系统管理", action = "预售款管理-保存限量设置")
	@PostMapping("/saveconfig")
	@ResponseBody
	public JSON saveconfig(SysLimitConfig config) {
		System.out.println("config==========" + config);
		Long presellId = config.getPresellId();
		if (StringUtils.isNull(presellId))
			return JSON.error("获取不到当前的预售款信息");
		SysPresellJeans presellJeans = service.selectById(presellId);
		if (presellJeans == null)
			return JSON.error("获取不到当前的预售款信息");
		if (config.getLimited() > presellJeans.getInventory())
			return JSON.error("限量不能大于当前预售款的库存量");

		config.setResultNum(config.getLimited());
		if (limitConfigService.saveLimitConfig(config) > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}
}