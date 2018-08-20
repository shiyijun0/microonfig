package com.jwk.project.system.fashion.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.jwk.common.utils.Moment;

import lombok.Data;
@Data
public class SysRecommendConfig {
    private Integer id;

    private Long recommendId;

    private Integer limited;

    private Integer inventory;

    private BigDecimal price;

    private Integer status;

    private Date startTime;

    private Date endTime;
    
    /*public void setEndTime(String endTime){
    	this.endTime = new Moment().fromTime(endTime).toDate();
    }

    public void setStartTime(String startTime){
    	this.startTime = new Moment().fromTime(startTime).toDate();
    }*/
    private Date createTime;

    
}