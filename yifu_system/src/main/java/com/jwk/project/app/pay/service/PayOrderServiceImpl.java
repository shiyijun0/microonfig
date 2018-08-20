package com.jwk.project.app.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwk.project.app.pay.dao.IPayOrderDao;
import com.jwk.project.system.order.domain.SysOrder;

@Repository("payOrderService")
public class PayOrderServiceImpl implements IPayOrderService {
	@Autowired
	private IPayOrderDao orderDao;

	@Override
	public int insertOrder(SysOrder order) {
		return orderDao.insertOrder(order);
	}

	@Override
	public int updatePayStatus(Long id) {
		return orderDao.updatePayStatus(id);
	}

	@Override
	public SysOrder selectOrderById(Long id) {
		return orderDao.selectOrderById(id);
	}

}
