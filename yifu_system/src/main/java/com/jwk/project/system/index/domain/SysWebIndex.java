package com.jwk.project.system.index.domain;

import lombok.Data;

@Data
public class SysWebIndex {

    /*首页图片id*/
    private Long id;
    /*图片地址*/
    private String img;
    /*图片排序*/
    private int ordernum;
    /*图片上传时间*/
    private String createtime;

}
