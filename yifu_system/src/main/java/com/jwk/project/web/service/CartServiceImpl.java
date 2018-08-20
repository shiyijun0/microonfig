package com.jwk.project.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.web.dao.ICartDao;
import com.jwk.project.web.vo.CartsVO;

/**
 * 购物车管理 服务实现
 * 
 * @author system
 */
@Repository("cartService")
public class CartServiceImpl implements ICartService {
	@Autowired
	private ICartDao cartDao;

	@Override
	public List<CartsVO> selectCartByUserId(Long userId) {
		if (userId != null && userId > 0) {
			return cartDao.selectCartByUserId(userId);
		} else
			return null;
	}

	@Override
	public CartsVO selectByCartId(Long cartId) {
		return cartDao.selectByCartId(cartId);
	}

	@Override
	public int updateCartFlag(Boolean flag, Long userId) {
		return cartDao.updateCartFlag(flag, userId);
	}

	@Override
	public int savetCart(CartsVO cartsVO) {
		if (StringUtils.isNotNull(cartsVO.getCartId()))
        {
            return cartDao.updateCart(cartsVO);
        }
        else
        {
        	cartsVO.setCartId(StrUtils.generateInstanceID());
            return cartDao.insertCart(cartsVO);
        }
	}

	@Override
	public int deleteByCardId(Long cartId) {
		return cartDao.deleteByCardId(cartId);
	}

	@Override
	public CartsVO existCart(Long userId, Long jeansId, Long sizeId, Long colorId) {
		return cartDao.existCart(userId, jeansId, sizeId, colorId);
	}

	@Override
	public int updateCartDelFlag(Long cartId) {
		return cartDao.updateCartDelFlag(cartId);
	}
}
