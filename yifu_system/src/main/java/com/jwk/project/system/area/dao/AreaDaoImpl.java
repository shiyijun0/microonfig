package com.jwk.project.system.area.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.project.system.area.domain.Area;

/**
 * 城市表 数据实现层
 * 
 * @author system
 */
@Repository("areaDao")
public class AreaDaoImpl extends DynamicObjectBaseDao implements IAreaDao {

	/**
	 * 查询所有城市信息
	 * 
	 * @return 所有城市信息
	 */
	@Override
	public List<Area> selectAreaAll() {
		List<Area> areaList = null;
		try {
			areaList = this.findForList("SystemAreaMapper.selectAreaAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaList;
	}

	/**
	 * 查询所有省份信息
	 * 
	 * @return 所有省份信息
	 */
	@Override
	public List<Area> selectProvinceAll() {
		List<Area> provinceList = null;
		try {
			provinceList = this.findForList("SystemAreaMapper.selectProvinceAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return provinceList;
	}

	/**
	 * 根据父类id查询该省份下的市/区域
	 * 
	 * @return 市/区域
	 */
	@Override
	public List<Area> selectCityOrArea(Long parentId) {
		List<Area> dataList = null;
		try {
			dataList = this.findForList("SystemAreaMapper.selectCityOrArea", parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	/**
	 * 根据id查询该省份下的区域
	 * 
	 * @return 区域
	 */
	 @Override
	    public Area selectAreaById(Long id)
	    {
	        return this.findForObject("SystemAreaMapper.selectAreaById", id);
	    }
	
	/**
	 * 根据id查询城市信息
	 * 
	 * @return 城市信息
	 */
	@Override
	public Area selectById(Long id) {
		return this.findForObject("SystemAreaMapper.selectById", id);
	}
	
	/**
	 * 根据name查询信息
	 * 
	 * @return 信息
	 */
	@Override
	public Area selectByName(String name) {
		return this.findForObject("SystemAreaMapper.selectByName", name);
	}
}
