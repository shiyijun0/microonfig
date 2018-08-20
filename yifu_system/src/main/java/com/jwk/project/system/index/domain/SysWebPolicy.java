package com.jwk.project.system.index.domain;

import lombok.Data;


/**
 *退换货政策
 */
@Data
public class SysWebPolicy {
    /*政策id*/
    private int id;
    /*内容*/
    private String content;
    /*创建时间*/
    private String ctime;

}
