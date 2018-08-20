package com.jwk.project.system.goods.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SysBl {
	
	public static final int BL_STATUS_SUCCESS = 1;//正常
    public static final int BL_STATUS_FILE = 0;//删除
	
	private int id;
	/**图片 */
    private String cover;
    /**贴图 */
    private String chartLet;
    /**选择布料相应的模型图 */
    private String blImg;

    private String leftPocket;

    private String rightPocket;

    private String name;
    /**说明 */
    private String des;
    /**1男 2女 3通用 */
    private int sex;

    private double price;
    /**编号 */
    private String number;
    /**厂家编号 */
    private String fNumber;
    /**缩水率 x */
    private String shrinkX;
    /**缩水率 y */
    private String shrinkY;
    /**0删除 1正常 */
    private int status;

    private Date ctime;

    private Date updateTime;
}
