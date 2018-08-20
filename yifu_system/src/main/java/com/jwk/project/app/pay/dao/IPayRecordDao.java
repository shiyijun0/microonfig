package com.jwk.project.app.pay.dao;

import com.jwk.project.app.pay.domain.PayRecord;

/**
 * 支付记录
 * 
 * @author Administrator
 *
 */
public interface IPayRecordDao {

	/**
	 * 新增支付记录信息
	 * 
	 * @param PayRecord
	 * @return 结果
	 */
	public int insertPayRecord(PayRecord payRecord);
	/**
	 * 修改支付记录信息
	 * 
	 * @param PayRecord
	 * @return 结果
	 */
	public int updatePayRecord(PayRecord payRecord);

	/**
	 * 修改支付记录状态信息
	 * 
	 * @param payRecord
	 * @return 结果
	 */
	public int updatePayRecordStatus(Long id);

	/**
	 * 根据支付记录ID查询信息
	 * 
	 * @param id
	 * @return 部门信息
	 */
	public PayRecord selectPayRecordById(Long id);

	/**
	 * 根据订单ID查询信息
	 * 
	 * @param id
	 * @return 部门信息
	 */
	public PayRecord selectByOrderId(Long orderId);
}
