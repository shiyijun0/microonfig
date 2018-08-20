package com.jwk.project.system.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpressCompany {
    private Integer id;

    private String orderId;

    private Integer company;

    private String cnumber;

    private Date ctime;

}