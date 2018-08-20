package com.jwk.project.system.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class SysOrderGoods {
    private Long id;

    private Integer orderId;

    private Integer userId;

    private Integer jeansId;

    private Integer blId;

    private Integer sizeId;

    private String jeansName;

    private Integer num;

    private BigDecimal price;

    private BigDecimal money;

    private Date createTime;

    private String selectSize;
    
    private String buliao;

    private String patternAll;

    private String sizeList;
    
    private String buliaoList;
    
    private String jeansDesc;
}