package com.jwk.project.system.web.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebDesigner {
	private Integer id;

    private String name;

    private String code;

    private Integer status;

    private String colorsId;

    private Date ctime;

    private Date updateTime;

    private String sizesId;

    private String buttonsId;

    private String washId;

    private String threadsId;

    private Integer isTobuy;

    private String partsId;

    private String remark;

    private String wordSize;

    private String wordColor;

    private String wordContent;

    private String wordFont;

    private Integer type;
    
    private Integer jeansId;

    //临时字段
    private String sizesName;
    private String colorsName;
    private String buttonsName;
    private String washsName;
    private String threadsName;
    private Map<String,Object> partsName;
   
}