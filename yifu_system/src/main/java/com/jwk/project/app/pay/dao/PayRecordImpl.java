package com.jwk.project.app.pay.dao;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.app.pay.domain.PayRecord;

@Repository("payRecordDao")
public class PayRecordImpl extends DynamicObjectBaseDao implements IPayRecordDao {

	/**
	 * 新增支付记录信息
	 */
	@Override
	public int insertPayRecord(PayRecord payRecord) {
		return this.save("AppPayRecordMapper.insertPayRecord", payRecord);
	}

	/**
	 * 根据id查询支付记录信息
	 */
	@Override
	public PayRecord selectPayRecordById(Long id) {
		return this.findForObject("AppPayRecordMapper.selectPayRecordById", id);
	}

	/**
	 * 根据订单id查询支付记录
	 */
	@Override
	public PayRecord selectByOrderId(Long orderId) {
		return this.findForObject("AppPayRecordMapper.selectByOrderId", orderId);
	}

	/**
	 * 修改支付记录信息
	 */
	@Override
	public int updatePayRecord(PayRecord payRecord) {
		return this.update("AppPayRecordMapper.updatePayRecord", payRecord);
	}

	/**
	 * 修改支付记录状态
	 */
	@Override
	public int updatePayRecordStatus(Long id) {
		return this.update("AppPayRecordMapper.updatePayRecordStatus", id);
	}

}
