package com.jwk.project.system.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebParts {
    private Integer id;

    private String cover;

    private String name;

    private BigDecimal price;

    private Integer status;

    private Date ctime;

    private Integer region;

    private String coverTitle;

    private String code;
    
    private Integer type;

    private String fixeX;

    private String fixeY;

    private Date updateTime;

    private Integer label;
   
}