package com.jwk.project.system.web.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebPosition {
    private Integer id;

    private Integer position;

    private String des;

    private Date ctime;

    private Integer type;

   
}