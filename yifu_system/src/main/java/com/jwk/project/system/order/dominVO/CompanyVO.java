package com.jwk.project.system.order.dominVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVO {
    private String time;
    private  String context;
    private String ftime;
    private  String location;
    private  String week;

    private  String year;
    private  String hour;

    public CompanyVO(String time, String context, String week) {
        this.time = time;
        this.context = context;
        this.week = week;
    }

    public CompanyVO(String time, String context, String week, String year, String hour) {
        this.time = time;
        this.context = context;
        this.week = week;
        this.year = year;
        this.hour = hour;
    }
}
