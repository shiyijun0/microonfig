package com.jwk.project.system.message.service;


import com.jwk.common.utils.FileUtil;
import com.jwk.common.utils.StringUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.message.dao.IMessageDao;
import com.jwk.project.system.message.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.jwk.common.utils.security.ShiroUtils.getUser;


/**
 * 系统消息 业务层处理
 * 
 * @author 陈志辉
 */
@Service("messageService")
public class MessageServiceImpl implements IMessageService
{

    @Autowired
    private IMessageDao messageDao;

    /**
     * 根据条件分页查询系统消息数据
     * 
     * @param pageUtilEntity 分页对象
     * @return 系统消息数据集合信息
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return messageDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 通过系统消息ID删除系统消息
     *
     * @param messageId 系统消息ID
     * @return 结果
     */
    @Override
    public int deleteMessageById(Long messageId)
    {
        return messageDao.deleteMessageById(messageId);
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
        return messageDao.batchDeleteMessage(ids);
    }



    /**
     * 通过系统消息ID查询系统消息
     *
     * @param messageId 系统消息ID
     * @return 系统消息信息
     */
    @Override
    public Message selectMessageById(Long messageId)
    {
        return messageDao.selectMessageById(messageId);
    }


    /**
     * 保存系统消息信息
     *
     * @param message 系统消息信息
     * @return 结果
     */
    @Override
    public int saveMessage(Message message, MultipartFile imageFile)
    {
        Long messageId = message.getMessageId();
        if(imageFile.getSize()!=0){
            String filename= FileUtil.fileInput(imageFile);
            message.setMessageImage(filename);
        }
        if(getUser().getUserId()!=null&&getUser().getUserId()!=0){
            message.setUserId(getUser().getUserId());
        }
        if (StringUtils.isNotNull(messageId))
        {
            // 修改系统消息的信息
            return messageDao.updateMessage(message);
        }
        return messageDao.insertMessage(message);
    }




}
