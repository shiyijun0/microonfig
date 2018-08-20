package com.jwk.project.app.pay.service;

import com.jwk.project.app.pay.domain.PayRecord;

public interface IPayRecordService {

	/**
	 * 保存支付记录
	 * 
	 * @param dept
	 * @return
	 */
	public int savePayRecord(PayRecord payRecord);

	/**
	 * 新增支付记录信息
	 * 
	 * @param PayRecord
	 * @return 结果
	 */
	public int insertPayRecord(PayRecord payRecord);

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

	/**
	 * 查询支付状态
	 * 
	 * @param record_id
	 * @return
	 */
	public boolean queryPayRecordStatus(Long record_id);
}
