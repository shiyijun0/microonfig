package com.jwk.project.app.pay.dao;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.system.order.domain.SysOrder;

@Repository("payOrderDao")
public class PayOrderImpl extends DynamicObjectBaseDao implements IPayOrderDao{

	@Override
	public int insertOrder(SysOrder order) {
		return this.save("SystemOrderMapper.insertOrder", order);
	}

	@Override
	public int updateOrder(SysOrder order) {
		return this.update("SystemOrderMapper.updateOrderByOrderId", order);
		//return this.save("SystemOrderMapper.insertOrder", order);
	}
	
	
	@Override
	public int updatePayStatus( Long id) {
		return this.update("SystemOrderMapper.updatePayStatus",id);
	}

	@Override
	public SysOrder selectOrderById(Long id) {
		return this.findForObject("SystemOrderMapper.selectOrderById", id);
	}

}
