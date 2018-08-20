package com.jwk.project.system.preselljeans.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.preselljeans.domain.SysPresellJeans;

/**
 * 预售款 控制层
 * 
 * @author Administrator
 *
 */
@Repository("presellJeansDao")
public class PresellJeansDaoImpl extends DynamicObjectBaseDao implements IPresellJeansDao {

	/**
	 * 根据条件分页查询预售款
	 * 
	 * @param pageUtilEntity
	 *            分页对象
	 * @return 预售款集合信息
	 */
	@Override
	public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity) {
		return this.findForList("SystemPresellJeansMapper.pageInfoQuery", pageUtilEntity);
	}

	/**
	 * 查询预售款列表
	 * @return 预售款列表
	 */
	@Override
	public List<SysPresellJeans> selectAll() {
		List<SysPresellJeans> list = null;
		try {
			list = this.findForList("SystemPresellJeansMapper.selectAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过预售款ID查询信息
	 * 
	 * @param id 预售款ID
	 * @return 预售款信息
	 */
	@Override
	public SysPresellJeans selectById(Long id) {
		return this.findForObject("SystemPresellJeansMapper.selectById", id);
	}

	/**
	 * 通过预售款ID删除信息
	 * 
	 * @param id 预售款ID
	 * @return 结果
	 */
	@Override
	public int deleteById(Long id) {
		return this.delete("SystemPresellJeansMapper.deleteById", id);
	}

	/**
	 * 批量删除预售款信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int batchDeletePresellJeans(Long[] ids) {
		return this.delete("SystemPresellJeansMapper.batchDeletePresellJeans", ids);
	}

	/**
	 * 修改预售款信息
	 * 
	 * @param sysPresellJeans 预售款信息
	 * @return 结果
	 */
	@Override
	public int updatePresellJeans(SysPresellJeans sysPresellJeans) {
		return this.update("SystemPresellJeansMapper.updatePresellJeans", sysPresellJeans);
	}

	/**
	 * 新增预售款信息
	 * 
	 * @param sysPresellJeans 预售款信息
	 * @return 结果
	 */
	@Override
	public int insertPresellJeans(SysPresellJeans sysPresellJeans) {
		return this.update("SystemPresellJeansMapper.insertPresellJeans", sysPresellJeans);
	}

	/**
	 * 查询所有上架的预售款信息
	 */
	@Override
	public List<SysPresellJeans> selectAllPut() {
		List<SysPresellJeans> list = null;
		try {
			list = this.findForList("SystemPresellJeansMapper.selectAllPut");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
