package com.jwk.project.system.index.domain;

import lombok.Data;


/**
 *常见问题解答
 */
@Data
public class SysWebQuestion {
    /*常见问题解答id*/
    private int id;
    /*内容*/
    private String content;
    /*创建时间*/
    private String ctime;

}
