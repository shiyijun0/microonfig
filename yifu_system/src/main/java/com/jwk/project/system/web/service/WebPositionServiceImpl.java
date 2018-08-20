package com.jwk.project.system.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.web.dao.WebPositionDao;
import com.jwk.project.system.web.domain.SysWebPosition;


/**
 * 衣服位置业务层处理
 * 
 * @author system
 */
@Service("webPositionService")
public class WebPositionServiceImpl implements WebPositionService {

    @Autowired
    private WebPositionDao webPositionDao;

    /**
     * 根据条件分页查询衣服位置数据
     *
     * @param pageUtilEntity 分页对象
     * @return 衣服位置数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return webPositionDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 查询所有衣服位置
     *
     * @return
     */
    @Override
    public List<SysWebPosition> selectSysWebPositionAll(Long id) {
        return webPositionDao.selectSysWebPositionAll(id);
    }

    /**
     * 通过iD查询衣服位置
     *
     * @param id 衣服位置ID
     * @return 衣服位置对象信息
     */
    @Override
    public SysWebPosition selectSysWebPositionById(Long id) {
        return webPositionDao.selectSysWebPositionById(id);
    }

    /**
     * 通过衣服位置ID删除衣服位置
     *
     * @param id 衣服位置iD
     * @return 结果
     */
    @Override
    public int deleteSysWebPositionById(Long id) {
        return webPositionDao.deleteSysWebPositionById(id);
    }

    /**
     * 批量删除衣服位置信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteSysWebPosition(Long[] ids) {
        return webPositionDao.batchDeleteSysWebPosition(ids);
    }

    /**
     * 保存衣服位置信息
     *
     * @param SysWebPosition
     * @return 结果
     */
    @Override
    public int saveSysWebPosition(SysWebPosition webPosition) {
        Integer id = webPosition.getId();
        if (StringUtils.isNotNull(id)) {

            return webPositionDao.updateSysWebPosition(webPosition);
        } else {
            return webPositionDao.insertSysWebPosition(webPosition);
        }

    }

    @Override
    public SysWebPosition selectSysWebPositionByPosition(SysWebPosition position) {
        return webPositionDao.selectSysWebPositionByPosition(position);
    }



}
