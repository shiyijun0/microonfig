package com.jwk.project.system.push.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.push.domain.Push;
import org.springframework.stereotype.Repository;

/**
 * APP消息推送 数据层处理
 *
 * @author 陈志辉
 */
@Repository("pushDao")
public class PushDaoImpl extends DynamicObjectBaseDao implements IPushDao
{

    /**
     * 根据条件分页查询APP消息推送数据
     *
     * @param pageUtilEntity 分页对象
     * @return APP消息推送数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemPushMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过APP消息推送ID删除APP消息推送
     *
     * @param pushId APP消息推送ID
     * @return 结果
     */
    @Override
    public int deletePushById(Long pushId)
    {
        return this.delete("SystemPushMapper.deletePushById", pushId);
    }

    /**
     * 批量删除APP消息推送信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeletePush(Long[] ids)
    {
        return this.delete("SystemPushMapper.batchDeletePush", ids);
    }


    /**
     * 通过APP消息推送ID查询APP消息推送
     *
     * @param pushId APP消息推送ID
     * @return APP消息推送信息
     */
    @Override
    public Push selectPushById(Long pushId) {
        return this.findForObject("SystemPushMapper.selectPushById", pushId);
    }


    /**
     * 保存APP消息推送信息
     *
     * @param push APP消息推送信息
     * @return 结果
     */
    @Override
    public int updatePush(Push push)
    {
        return this.update("SystemPushMapper.updatePush", push);
    }

    /**
     * 新增APP消息推送信息
     *
     * @param push APP消息推送信息
     * @return 结果
     */
    @Override
    public int insertPush(Push push)
    {
        return this.update("SystemPushMapper.insertPush", push);
    }


}
