package com.jwk.project.system.preselljeans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.preselljeans.dao.IPresellJeansDao;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;

/**
 * 预售款 业务层处理
 * 
 * @author system
 */
@Service("presellJeansService")
public class PresellJeansServiceImpl implements IPresellJeansService {

	@Autowired
	IPresellJeansDao presellJeansDao;

	/**
     * 根据条件分页查询预售款
     * 
     * @param pageUtilEntity 分页对象
     * @return 预售款集合信息
     */
	@Override
	public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity) {
		return presellJeansDao.pageInfoQuery(pageUtilEntity);
	}

	 /**
     * 查询预售款列表
     * 
     * @return 预售款列表
     */
	@Override
	public List<SysPresellJeans> selectAll() {
		return presellJeansDao.selectAll();
	}

	 /**
     * 通过预售款ID查询信息
     * 
     * @param id 预售款ID
     * @return 预售款信息
     */
	@Override
	public SysPresellJeans selectById(Long id) {
		return presellJeansDao.selectById(id);
	}

	/**
     * 通过预售款ID删除信息
     * 
     * @param id 预售款ID
     * @return 结果
     */
	@Override
	public int deleteById(Long id) {
		return presellJeansDao.deleteById(id);
	}

	/**
     * 批量删除预售款信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int batchDeletePresellJeans(Long[] ids) {
		return presellJeansDao.batchDeletePresellJeans(ids);
	}

	/**
     * 保存预售款信息
     * 
     * @param sysPresellJeans 预售款信息
     * @return 结果
     */
	@Override
	public int save(SysPresellJeans sysPresellJeans) {
		Long id = sysPresellJeans.getId();
		if (StringUtils.isNotNull(id)) {
			return presellJeansDao.updatePresellJeans(sysPresellJeans);
		} else {
			sysPresellJeans.setId(StrUtils.generateInstanceID());
			return presellJeansDao.insertPresellJeans(sysPresellJeans);
		}
	}

	 /**
     * 查询所有上架的预售款列表
     * 
     * @return 预售款列表
     */
	@Override
	public List<SysPresellJeans> selectAllPut() {
		return presellJeansDao.selectAllPut();
	}

}
