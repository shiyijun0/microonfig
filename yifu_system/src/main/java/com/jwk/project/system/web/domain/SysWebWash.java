package com.jwk.project.system.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebWash {
    private Integer id;

    private String type;

    private String coverTitle;

    private String cover;

    private BigDecimal price;

    private String name;

    private Date ctime;

    private Integer status;

    private Integer isDefault;

    private String fixeX;

    private String fixeY;

    private Date updateTime;

   
}