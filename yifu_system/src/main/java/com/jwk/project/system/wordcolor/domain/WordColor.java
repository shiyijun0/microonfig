package com.jwk.project.system.wordcolor.domain;

import lombok.Data;


/**
 * 文字颜色对象 sys_word_color
 * 
 * @author 陈志辉
 */
@Data
public class WordColor
{
    /** 文字颜色ID */
    private Long colorId;
    /** 颜色名称 */
    private String colorName;
    /** 颜色价格 */
    private Double colorPrice;
    /** 创建时间 */
    private String createTime;

}
