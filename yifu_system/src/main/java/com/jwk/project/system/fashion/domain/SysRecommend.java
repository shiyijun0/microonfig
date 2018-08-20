package com.jwk.project.system.fashion.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class SysRecommend {
    private int id;

    private int uid;

    private int goodsId;

    private int imgA;

    private int imgB;

    private int gender;

    private String goodsPicurl;

    private String pcMod;

    private String androidMod;

    private String iosMod;

    private String goodsName;

    private BigDecimal price;

    private String goodsIntro;

    private String sizeImage;

    private int count;

    private BigDecimal totalPrice;

    private String fId;

    private String number;

    private String selectSize;
    
    private String buliao;

    private String patternAll;

    private String sizeList;
    
    private String buliaoList;

    
    
    private Date ctime;

   
}