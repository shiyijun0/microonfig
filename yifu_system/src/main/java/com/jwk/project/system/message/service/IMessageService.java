package com.jwk.project.system.message.service;


import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.message.domain.Message;
import org.springframework.web.multipart.MultipartFile;


/**
 * 系统消息业务层
 *
 * @author 陈志辉
 */
public interface IMessageService
{

    /**
     * 根据条件分页查询系统消息数据
     *
     * @param pageUtilEntity 分页对象
     * @return 系统消息数据集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);


    /**
     * 通过系统消息ID删除系统消息
     *
     * @param messageId 系统消息ID
     * @return 结果
     */

    public int deleteMessageById(Long messageId);

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
     * @param messageId 系统消息ID
     * @return 系统消息信息
     */

    public Message selectMessageById(Long messageId);



    /**
     * 保存系统消息信息
     *
     * @param message 系统消息信息
     * @return 结果
     */
    public int saveMessage(Message message, MultipartFile imageFile);






}
