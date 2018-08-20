package com.jwk.project.system.front.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebBanner {
    private Integer id;

    private String bannerImgs;
    
    private String name;

    private Integer orderNum;

    private Integer status;

    private Date createTime;
    
    private Integer designerId;

    private String content;
}