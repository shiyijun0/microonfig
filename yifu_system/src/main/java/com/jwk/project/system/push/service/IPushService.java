package com.jwk.project.system.push.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.push.domain.Push;


/**
 * APP消息推送业务层
 *
 * @author 陈志辉
 */
public interface IPushService
{

    /**
     * 根据条件分页查询APP消息推送数据
     *
     * @param pageUtilEntity 分页对象
     * @return APP消息推送数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过APP消息推送ID删除APP消息推送
     *
     * @param pushId APP消息推送ID
     * @return 结果
     */

    public int deletePushById(Long pushId);

    /**
     * 批量删除APP消息推送信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */

    public int batchDeletePush(Long[] ids);



    /**
     * 通过APP消息推送ID查询APP消息推送
     *
     * @param pushId APP消息推送ID
     * @return APP消息推送信息
     */

    public Push selectPushById(Long pushId);



    /**
     * 保存APP消息推送信息
     *
     * @param push APP消息推送信息
     * @return 结果
     */
    public int savePush(Push push);






}
