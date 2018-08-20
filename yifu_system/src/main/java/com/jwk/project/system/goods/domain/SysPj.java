package com.jwk.project.system.goods.domain;

import java.util.Date;

import lombok.Data;
/**
 * 牛仔配件 sys_pj
 * 
 * @author system
 */
@Data
public class SysPj {
	
	public static final int FQ_STATUS_SUCCESS = 1;//正常
    public static final int FQ_STATUS_FILE = 0;//禁用
	
    private int id;
    /**图片 */
    private String cover;
    /** 贴图*/
    private String chartlet;

    private String name;
    /**说明 */
    private String des;
    /**性别：1男 2女 3通用 */
    private int sex;
    /**编号 */
    private String number;
    /**厂家id */
    private String fId;

    private double price;
     /** 库存**/
    private int inventory;
    /** 类型**/
    private String type;
    /** 限量**/
    private int limited;
    /**0不可旋转 1可旋转**/
    private int rotate;
    /** 可旋转最大角度**/
    private String roMax;
    /** 可旋转最小角度**/
    private String roMin;
    /** 0固定图案 1不是固定图案**/
    private int fixed;
    /** 配件位置:x**/
    private String fixeX;
    /** 配件位置:y**/
    private String fixeY;
    /** 配件位置:z**/
    private String fixeZ;
    /** 0删除 1正常**/
    private int status;

    private Date ctime;

    private Date updateTime;

   
}