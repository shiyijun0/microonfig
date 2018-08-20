package com.jwk.project.system.goods.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;


/**
 * 牛仔对象 sys_jeans
 * 
 * @author system
 */
@Data
public class Jeans {
	
	public static final int INIT_STATUS_SUCCESS = 1;//正常
    public static final int INIT_STATUS_FILE = 2;//禁用
    public static final int INIT_STATUS_NO = 0;//禁用
	
	/** ID */
    private Long id;
    /** 商品名称 */
    private String name;
    /** 商品图片id */
    private int cover;
    /** 商品图片 */
    private String pcmod;//电脑端模型
    private String androidmod;//安卓模型
    private String iosmod;//ios模型
    /** 尺寸图片 */
    private int sizeImg;
    /**性别 :1男 2女 */
    private int sex;
    /**编号 */
    private String number;
    /**厂家id */
    private String fId;
    /**价格 */
    private double price;
    /**库存 */
    private long inventory;
    /**类型 */
    private String type;
    /**限量 */
    private long limited;
    /**说明 */
    private String des;
    /**1 添加动作 2 添加布料 3 添加区域 4 添加区域位置 5 完成 */
    private int init;
    /**状态 ：0未上架 1正常 2删除*/
    private int status;
    /**更新时间*/
    private Date updateTime;
    /**创建时间*/
    private Date createTime;
    /**逻辑关联尺寸*/
    private List<JeansSize> jeansSize;
    
    /**逻辑图片路径*/
    private JeansAction jeansAction;
    
}
