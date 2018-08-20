package com.jwk.project.system.fashion.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.fashion.domain.SysRecommend;
import com.jwk.project.system.fashion.domain.SysRecommendBg;



/**
 * 设计师推荐 数据层处理
 * 
 * @author system
 */
@Repository("recommendBgDao")
public class RecommendBgDaoImpl extends DynamicObjectBaseDao implements RecommendBgDao
{

	

	@Override
	public List<SysRecommendBg> selectSysRecommendBgByrecommendId(Long recommendId) {
		 List<SysRecommendBg> sysRecommendBgList = null;
		 try {
			sysRecommendBgList = this.findForList("SystemRecommendBgMapper.selectSysRecommendBgByrecommendId",recommendId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sysRecommendBgList;
	}

	@Override
	public List<SysRecommendBg> selectSysRecommendBgAll() {
		 List<SysRecommendBg> sysRecommendBgList = null;
		 try {
			sysRecommendBgList = this.findForList("SystemRecommendBgMapper.selectSysRecommendBgAll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sysRecommendBgList;
	}

	@Override
	public SysRecommendBg selectSysRecommendBgById(Long id) {
		 return this.findForObject("SystemRecommendBgMapper.selectSysRecommendBgById", id);
	}

	
    

   
   
}
