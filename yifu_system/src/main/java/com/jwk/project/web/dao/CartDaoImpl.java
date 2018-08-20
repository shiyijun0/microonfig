package com.jwk.project.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.web.vo.CartsVO;

@Repository("cartDao")
public class CartDaoImpl extends DynamicObjectBaseDao implements ICartDao {

	/**
	 * 根据用户id查询该用户的购物车信息
	 */
	@Override
	public List<CartsVO> selectCartByUserId(Long userId) {
		List<CartsVO> cartList = null;
		try {
			cartList = this.findForList("SystemCartMapper.selectCartByUserId", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	/**
	 * 根据id查询购物车信息
	 */
	@Override
	public CartsVO selectByCartId(Long cartId) {
		return this.findForObject("SystemCartMapper.selectByCartId", cartId);
	}

	/**
	 * 修改购物车信息
	 */
	@Override
	public int updateCart(CartsVO cartsVO) {
		return this.update("SystemCartMapper.updateCart", cartsVO);
	}

	/**
	 * 修改购物车选中状态
	 */
	@Override
	public int updateCartFlag(Boolean flag, Long userId) {
		Map<String, Object> parmars = new HashMap<>();
		parmars.put("flag", flag);
		parmars.put("userId", userId);
		return this.update("SystemCartMapper.updateCartFlag", parmars);
	}

	/**
	 * 保存购物车
	 */
	@Override
	public int insertCart(CartsVO cartsVO) {
		return this.save("SystemCartMapper.insertCart", cartsVO);
	}

	/**
	 * 删除购物车
	 */
	@Override
	public int deleteByCardId(Long cartId) {
		return this.delete("SystemCartMapper.deleteByCardId", cartId);
	}

	@Override
	public CartsVO existCart(Long userId, Long jeansId, Long sizeId, Long colorId) {
		Map<String, Object> parmars = new HashMap<>();
		parmars.put("jeansId", jeansId);
		parmars.put("userId", userId);
		parmars.put("sizeId", sizeId);
		parmars.put("colorId", colorId);
		return this.findForObject("SystemCartMapper.existCart", parmars);
	}

	/**
	 * 修改删除状态
	 */
	@Override
	public int updateCartDelFlag(Long cartId) {
		return this.update("SystemCartMapper.updateCartDelFlag", cartId);
	}
}
