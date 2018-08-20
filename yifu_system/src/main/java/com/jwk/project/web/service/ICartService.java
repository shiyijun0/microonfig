package com.jwk.project.web.service;

import java.util.List;

import com.jwk.project.web.vo.CartsVO;

/**
 * 购物车管理 服务层
 * 
 * @author system
 */
public interface ICartService {
	/**
	 * 根据用户id查询该用户的购物车信息
	 */
	public List<CartsVO> selectCartByUserId(Long userId);

	/**
	 * 根据id查询购物车信息
	 */
	public CartsVO selectByCartId(Long cartId);

	/**
	 * 修改购物车选中状态
	 * @param flag
	 * @param userId
	 * @return
	 */
	public int updateCartFlag(Boolean flag, Long userId);

	/**
	 * 保存购物车
	 * @param cartsVO
	 * @return
	 */
	public int savetCart(CartsVO cartsVO);

	/**
	 * 删除购物车
	 * @param cartId
	 * @return
	 */
	public int deleteByCardId(Long cartId);
	
	/**
	 * 判断是否存在
	 * @param flag
	 * @param userId
	 * @return
	 */
	public CartsVO existCart(Long userId,Long jeansId,Long sizeId,Long colorId);
	
	/**
	 * 修改删除状态
	 */
	public int updateCartDelFlag(Long cartId);
}
