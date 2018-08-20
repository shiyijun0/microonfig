package com.jwk.project.system.push.service;


import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.push.dao.IPushDao;
import com.jwk.project.system.push.domain.Push;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * APP消息推送 业务层处理
 * 
 * @author 陈志辉
 */
@Service("pushService")
public class PushServiceImpl implements IPushService
{

    @Autowired
    private IPushDao pushDao;

    /**
     * 根据条件分页查询APP消息推送数据
     * 
     * @param pageUtilEntity 分页对象
     * @return APP消息推送数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return pushDao.pageInfoQuery(pageUtilEntity);
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
        return pushDao.deletePushById(pushId);
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
        return pushDao.batchDeletePush(ids);
    }



    /**
     * 通过APP消息推送ID查询APP消息推送
     *
     * @param pushId APP消息推送ID
     * @return APP消息推送信息
     */
    @Override
    public Push selectPushById(Long pushId)
    {
        return pushDao.selectPushById(pushId);
    }


    /**
     * 保存APP消息推送信息
     *
     * @param push APP消息推送信息
     * @return 结果
     */
    @Override
    public int savePush(Push push)
    {
        Long pushId = push.getPushId();
        if (StringUtils.isNotNull(pushId))
        {
            // 修改APP消息推送的信息
            return pushDao.updatePush(push);
        }
        return pushDao.insertPush(push);
    }




}
