package com.jwk.project.system.front.domainVO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAndGoodVO {
private String name;
private String size;
private String color;
private BigDecimal money;
private Integer payStatus;
private Date createTime;
}
