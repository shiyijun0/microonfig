package com.jwk.project.app.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwk.common.utils.StringUtils;
import com.jwk.project.app.pay.dao.IPayRecordDao;
import com.jwk.project.app.pay.domain.PayRecord;

import lombok.extern.slf4j.Slf4j;

@Repository("payRecordService")
@Slf4j
public class PayRecordServiceImpl implements IPayRecordService {

	@Autowired
	private IPayRecordDao payRecordDao;

	/**
	 * 新增一笔支付记录信息
	 */
	@Override
	public int insertPayRecord(PayRecord payRecord) {
		return payRecordDao.insertPayRecord(payRecord);
	}

	/**
	 * 修改支付状态
	 */
	@Override
	public int updatePayRecordStatus(Long id) {
		return payRecordDao.updatePayRecordStatus(id);
	}

	/**
	 * 查询支付记录信息
	 */
	@Override
	public PayRecord selectPayRecordById(Long id) {
		return payRecordDao.selectPayRecordById(id);
	}

	@Override
	public boolean queryPayRecordStatus(Long record_id) {
		PayRecord payRecord = payRecordDao.selectPayRecordById(record_id);
		int status = payRecord.getStatus();
		log.info("查询支付状态：" + record_id + " pay_status:" + status);
		if (status == 1) {// 表示支付成功
			return true;
		}
		return false;
	}

	/**
	 * 根据订单id查找支付记录
	 */
	@Override
	public PayRecord selectByOrderId(Long orderId) {
		return payRecordDao.selectByOrderId(orderId);
	}

	@Override
	public int savePayRecord(PayRecord payRecord) {
		if (StringUtils.isNotNull(payRecord.getId())) {
			return payRecordDao.updatePayRecord(payRecord);
		} else {
			return payRecordDao.insertPayRecord(payRecord);
		}
	}

}
