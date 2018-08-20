package com.jwk.project.system.area.dao;

import java.util.List;

import com.jwk.project.system.area.domain.Area;

/**
 * 城市表 数据层
 * 
 * @author system
 */
public interface IAreaDao
{
    /**
     * 查询所有城市信息
     * 
     * @return 所有城市信息
     */
    public List<Area> selectAreaAll();
    
    /**
     * 查询所有省份信息
     * 
     * @return 所有省份信息
     */
    public List<Area> selectProvinceAll();
    
    /**
     * 根据父类id查询该省份下的市/区域
     * 
     * @return 市/区域
     */
    public List<Area> selectCityOrArea(Long parentId);
    
    /**
     * 根据id查询城市信息
     * 
     * @return 城市信息
     */
    public Area selectById(Long id);
    /**
     * 根据id查询城市区域信息
     * 
     * @return 城市区域信息
     */
    public Area selectAreaById(Long id);
    
    /**
	 * 根据name查询信息
	 * 
	 * @return 信息
	 */
	public Area selectByName(String name);

}
