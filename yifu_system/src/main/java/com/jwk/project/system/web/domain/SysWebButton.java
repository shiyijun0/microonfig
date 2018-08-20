package com.jwk.project.system.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysWebButton {
	 private Integer id;

	    private String name;

	    private BigDecimal price;

	    private String cover;

	    private Integer status;

	    private Date ctime;

	    private String coverTitle;

	    private String type;

	    private Integer isDefault;

	    private String fixeX;

	    private String fixeY;

	    private Date updateTime;

	private Integer label;
}