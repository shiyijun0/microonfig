package com.jwk.project.system.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebColor {
    private Integer id;
    
    private Integer depth;

    private String name;

    private BigDecimal price;

    private String cover;

    private String coverTitle;

    private String coverBack;

    private Date ctime;

    private Integer status;

    private Integer isDefault;

    private String fixeX;

    private String fixeY;

    private Integer label;
    
    private Date updateTime;
    
    private Integer part1;
    private Integer part2;
    private Integer part3;
    private Integer part4;
    private Integer part5;
    private Integer part6;
    private Integer part7;
    private Integer part8;

   
}