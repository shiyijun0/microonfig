package com.jwk.project.system.fashion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.project.system.fashion.dao.RecommendBgDao;
import com.jwk.project.system.fashion.domain.SysRecommendBg;





/**
 * 设计师推荐业务层处理
 * 
 * @author system
 */
@Service("recommendBgService")
public class RecommendBgServiceImpl implements RecommendBgService
{

    @Autowired
    private RecommendBgDao recommendBgDao;

	@Override
	public List<SysRecommendBg> selectSysRecommendBgAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysRecommendBg selectSysRecommendBgById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysRecommendBg> selectSysRecommendBgByrecommendId(
			Long recommendId) {
		// TODO Auto-generated method stub
		return recommendBgDao.selectSysRecommendBgByrecommendId(recommendId);
	}

    

   

}
