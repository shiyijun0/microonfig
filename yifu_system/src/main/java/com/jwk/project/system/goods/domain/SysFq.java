package com.jwk.project.system.goods.domain;

import java.util.Date;

import lombok.Data;
@Data
public class SysFq {
	private int id;
	
    private int value;
    /**分区描述 */
    private String des;

    private Date ctime;
}
