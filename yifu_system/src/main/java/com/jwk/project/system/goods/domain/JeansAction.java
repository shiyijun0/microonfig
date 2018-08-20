package com.jwk.project.system.goods.domain;

import java.util.Date;

import lombok.Data;
/*
 * 牛仔动作
 * 
 */
@Data
public class JeansAction {
	private int id;
	/** 商品Id */
    private int nzId;
    /** '1android 2ios 3pc' */
    private int type;
    /** '1平底 2 高跟' */
    private int type2;
    /**图片路径 */
    private String cover;
    private Date ctime;
    
    
}
