package com.jwk.project.system.web.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyDesigner {
	private Integer id;

    private Integer userId;

    private String colorsId;

    private Date ctime;

    private Date updateTime;

    private String sizesId;

    private String buttonsId;

    private String washId;

    private String threadsId;

    private String partsId;



    private String wordSize;

    private String wordColor;

    private String wordContent;

    private String wordFont;

    
    private Integer designerId;

   
}