package com.jwk.project.system.message.dao;

import com.jwk.framework.web.dao.DynamicObjectBaseDao;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.message.domain.Message;
import org.springframework.stereotype.Repository;

/**
 * 系统消息 数据层处理
 *
 * @author 陈志辉
 */
@Repository("messageDao")
public class MessageDaoImpl extends DynamicObjectBaseDao implements IMessageDao
{

    /**
     * 根据条件分页查询系统消息数据
     *
     * @param pageUtilEntity 分页对象
     * @return 系统消息数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemMessageMapper.pageInfoQuery", pageUtilEntity);
    }



    /**
     * 通过系统消息ID删除系统消息
     *
     * @param MessageId 系统消息ID
     * @return 结果
     */
    @Override
    public int deleteMessageById(Long MessageId)
    {
        return this.delete("SystemMessageMapper.deleteMessageById", MessageId);
    }

    /**
     * 批量删除系统消息信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteMessage(Long[] ids)
    {
        return this.delete("SystemMessageMapper.batchDeleteMessage", ids);
    }


    /**
     * 通过系统消息ID查询系统消息
     *
     * @param MessageId 系统消息ID
     * @return 系统消息信息
     */
    @Override
    public Message selectMessageById(Long MessageId) {
        return this.findForObject("SystemMessageMapper.selectMessageById", MessageId);
    }


    /**
     * 保存系统消息信息
     *
     * @param message 系统消息信息
     * @return 结果
     */
    @Override
    public int updateMessage(Message message)
    {
        return this.update("SystemMessageMapper.updateMessage", message);
    }

    /**
     * 新增系统消息信息
     *
     * @param message 系统消息信息
     * @return 结果
     */
    @Override
    public int insertMessage(Message message)
    {
        return this.update("SystemMessageMapper.insertMessage", message);
    }


}
