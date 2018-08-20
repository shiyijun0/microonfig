package com.jwk.project.app.pay.dao;

import com.jwk.project.system.order.domain.SysOrder;

public interface IPayOrderDao {
	/**
	 * 新增支付订单
	 * 
	 * @param PayRecord
	 * @return 结果
	 */
	public int insertOrder(SysOrder order);

	/**
	 * 修改支付记录状态信息
	 * 
	 * @param payRecord
	 * @return 结果
	 */
	public int updatePayStatus(Long id);

	/**
	 * 根据支付记录ID查询信息
	 * 
	 * @param id
	 * @return 部门信息
	 */
	public SysOrder selectOrderById(Long id);
	/**
	 * 更新支付订单
	 * 
	 * @param PayRecord
	 * @return 结果
	 */
	public int updateOrder(SysOrder order);
}
