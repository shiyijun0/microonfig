package com.jwk.project.system.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwk.project.system.area.dao.IAreaDao;
import com.jwk.project.system.area.domain.Area;

/**
 * 城市表 服务实现
 * 
 * @author system
 */
@Repository("areaService")
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private IAreaDao areaDao;

	@Override
	public List<Area> selectAreaAll() {
		return areaDao.selectAreaAll();
	}

	@Override
	public List<Area> selectProvinceAll() {
		return areaDao.selectProvinceAll();
	}

	@Override
	public List<Area> selectCityOrArea(Long parentId) {
		return areaDao.selectCityOrArea(parentId);
	}

    @Override
    public Area selectAreaById(Long id)
    {
        return areaDao.selectAreaById(id);
    }
    
	@Override
	public Area selectById(Long id) {
		return areaDao.selectById(id);
	}

	@Override
	public Area selectByName(String name) {
		return areaDao.selectByName(name);
	}

}
