package com.jwk.project.system.startphoto.domain;

import lombok.Data;

/**
 * 开机图片   sys_start
 *@author  陈志辉
 */

@Data
public class StartPhoto {
    /** 文件id */
    private Long startPhotoId;
    /** 文件类型 1：图片  2：视频 */
    private int type;
    /** 文件名 */
    private String file;
    /**标题  */
    private String title;
    /**创建时间  */
    private String ctime;
}
