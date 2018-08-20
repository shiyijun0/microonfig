package com.jwk.project.web.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.QiniuUtils;
import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;
import com.jwk.project.system.preselljeans.service.IPresellJeansService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.web.vo.CartsVO;
import com.jwk.project.web.service.ICartService;
import com.jwk.project.web.service.ICommonService;

@Controller
@RequestMapping("/custom")
public class ShopCartController {
	private String prefix = "custom";

	@Autowired
	private IPresellJeansService presellJeansService;

	@Autowired
	private ICommonService commonService;

	@Autowired
	private ICartService cartService;

	/**
	 * 我的购物车
	 *
	 * @return
	 * @throws ClientAbortException
	 */
	@GetMapping("/shopping_cart")
	public String list(Model model) throws ClientAbortException {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			throw new ClientAbortException("未登录");
		}
		List<CartsVO> list = cartService.selectCartByUserId(user.getId());

		model.addAttribute("cartsList", list);
		model.addAttribute("qiniuUrl", QiniuUtils.publicURi);
		return prefix + "/shopping_cart";
	}

	/**
	 * 加入购物车
	 *
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/saveCart")
	@ResponseBody
	public JSON saveCart(CartsVO cartsVO, Model model) throws ParseException {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("未登录");
		}
		// 查询商品信息
		Long jeansId = cartsVO.getJeansId();
		Long sizeId = cartsVO.getSizeId();
		Long colorId = cartsVO.getColorId();
		if (StringUtils.isNull(jeansId)) {
			return JSON.error("获取不到商品Id");
		}
		if (StringUtils.isNull(colorId) || StringUtils.isNull(sizeId)) {
			return JSON.error("加入购物车失败");
		}
		SysPresellJeans preinfo = presellJeansService.selectById(jeansId);
		if (preinfo == null) {
			return JSON.error("找不到商品信息");
		}
		// 判断是否有限量配置
		preinfo = commonService.getLimitConfig(jeansId, preinfo);
		if (preinfo.getSysLimitConfig() != null) {// 有限量配置
			cartsVO.setPrice(preinfo.getSysLimitConfig().getPrice());
		} else {
			cartsVO.setPrice(preinfo.getPrice());
		}
		cartsVO.setImages(preinfo.getImages());
		cartsVO.setJeansName(preinfo.getName());
		cartsVO.setUserId(user.getId());
		List<CartsVO> list = cartService.selectCartByUserId(user.getId());
		if (list.isEmpty()) {// 表示第一次添加购物车
			cartService.savetCart(cartsVO);
		} else {// 修改购物车信息
			int result_num = 0;
			CartsVO c2 = cartService.existCart(user.getId(), jeansId, sizeId, colorId);
			if (c2 == null) {
				cartService.savetCart(cartsVO);
			} else {
				result_num = cartsVO.getNum()+c2.getNum();
				c2.setNum(result_num);
				cartService.savetCart(c2);
			}
		}
		return JSON.ok();
	}

	// 删除购物车
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JSON delete(Long cartId) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("删除失败，未登录");
		}
		if (StringUtils.isNull(cartId)) {
			return JSON.error("删除失败，找不到购物车id");
		}

		if (cartService.deleteByCardId(cartId) > 0) {
			return JSON.ok();
		}
		return JSON.error();
	}

	// 是否选中购物车
	@RequestMapping(value = "/checked")
	@ResponseBody
	public JSON checked(Long cartId, Boolean flag) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("未登录");
		}
		if (StringUtils.isNotNull(cartId)) {
			CartsVO cartsVO = cartService.selectByCartId(cartId);
			if (cartsVO != null) {
				cartsVO.setFlag(flag);
				cartService.savetCart(cartsVO);
			}

		}
		return JSON.ok();
	}

	// 是否全选购物车
	@RequestMapping(value = "/checkedAll")
	@ResponseBody
	public JSON checkedAll(Boolean flag) {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("未登录");
		}
		if (cartService.updateCartFlag(flag, user.getId()) > 0) {
			return JSON.ok();
		}
		return JSON.ok();
	}

	// 加减购物车数量
	@RequestMapping(value = "/opernum")
	@ResponseBody
	public JSON opernum(Long cartId, Long jeansId, int num) throws ParseException {
		RegisterUser user = (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return JSON.error("未登录");
		}

		if (StringUtils.isNotNull(cartId) && StringUtils.isNotNull(jeansId)) {
			int result_num = 0;
			// 判断是否有限量配置
			SysPresellJeans preinfo = presellJeansService.selectById(jeansId);
			if (preinfo == null) {
				return JSON.error("找不到商品信息");
			}
			preinfo = commonService.getLimitConfig(jeansId, preinfo);
			if (preinfo.getSysLimitConfig() != null) {// 有限量配置
				result_num = preinfo.getSysLimitConfig().getResultNum();
			} else {
				result_num = preinfo.getResultNum();
			}
			if (num > result_num) {
				return JSON.error("库存不足，只能购买" + num + "件");
			}
			CartsVO cartsVO = cartService.selectByCartId(cartId);
			if (cartsVO != null) {
				cartsVO.setNum(num);
				cartService.savetCart(cartsVO);
			}
		}
		return JSON.ok();
	}
}
