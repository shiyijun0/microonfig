package com.jwk.project.system.fashion.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class SysFashion {
    private int id;

    private int uid;

    private int pointint;

    private int copy;

    private int goodsId;

    private int imgA;

    private int imgB;

    private int gender;

    private String pcMod;

    private String androidMod;

    private String iosMod;

    private String goodsPicurl;

    private String goodsName;
    
    private BigDecimal price;
    
    private String selectSize;

    private String goodsIntro;
    
    private String buliao;

    private String patternAll;

    private String sizeList;
    
    private String buliaoList;

    private String orderSerials;

    private Date ctime;

    
}