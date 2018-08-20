package com.jwk.project.system.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebJeans {
    private Integer id;

    private String name;

    private Integer type;

    private String code;

    private BigDecimal price;

    private String title;

    private Integer status;

    private String colorsId;

    private Date ctime;

    private Date updateTime;

    private String sizesId;

    private String buttonsId;

    private String threadsId;
    
    private String washId;

   /* private String cover;

    private String coverTitle;*/

    private String model;

    private Integer isTobuy;

    private String partsId;

    private String fixeX;

    private String fixeY;

    private Integer label;

    
}