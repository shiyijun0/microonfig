package com.jwk.project.system.push.domain;

import lombok.Data;

/**
 * App推送消息  sys_push
 * @author 陈志辉
 */

@Data
public class Push {

    /**消息id  */
    private Long pushId;
    /**标题  */
    private String title;
    /** 内容*/
    private String content;
    /**0 发送成功 ， 1 发送失败*/
    private int status;
    /**创建时间 */
    private String ctime;

}
