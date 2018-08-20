package com.jwk.project.system.message.dao;

import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.message.domain.Message;

/**
 *系统消息表 数据层
 *
 * @author 陈志辉
 */
public interface IMessageDao
{

    /**
     * 根据条件分页查询系统消息数据
     *
     * @param pageUtilEntity 分页对象
     * @return系统消息数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过系统消息ID删除系统消息
     *
     * @param MessageId
     * @return 结果
     */
    public int deleteMessageById(Long MessageId);


    /**
     * 批量删除系统消息信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteMessage(Long[] ids);


    /**
     * 通过系统消息ID查询系统消息
     *
     * @param MessageId
     * @return系统消息信息
     */

    public Message selectMessageById(Long MessageId);




    /**
     * 保存系统消息信息
     *
     * @param message
     * @return 结果
     */

    public int updateMessage(Message message);


    /**
     * 新增系统消息信息
     *
     * @param message
     * @return 结果
     */

    public int insertMessage(Message message);


}
