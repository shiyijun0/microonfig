package com.jwk.project.system.background.domain;

import lombok.Data;

/**
 * 推荐男女背景图  sys_recommend_bg
 * @author 陈志辉
 */

@Data
public class Background {
    /**背景图id*/
    private Long backgroundId;
    /**问题*/
    private int question;
    /**图片地址*/
    private String url;
    /**性别*/
    private int sex;
    /**创建时间*/
    private String ctime;
}
