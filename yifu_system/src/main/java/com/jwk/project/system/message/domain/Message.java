package com.jwk.project.system.message.domain;

import lombok.Data;

/**
 * 系统消息  sys_message
 * @author 陈志辉
 */

@Data
public class Message {

    /**消息id*/
    private Long messageId;
    /** 消息标题 */
    private String title;
    /** 消息内容 */
    private String content;
    /**消息关联用户  */
    private Long userId;
    /**消息图片  */
    private String messageImage;
    /**1系统消息 ，2通知消息 ，3互动消息  */
    private int type;
    /**创建时间  */
    private String ctime;

}
