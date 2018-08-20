package com.jwk.project.system.web.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebLocation {
    private Integer id;

    private Integer jeansId;
    
    private String jeansName;

    private Integer partsId;

    private Integer status;

    private Date createTime;
    
    private String partsName;
    
    private String cover;

   
}