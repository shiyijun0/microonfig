package com.jwk.project.system.wordsize.domain;

import lombok.Data;


/**
 * 文字字号对象 sys_word_size
 *
 * @author 陈志辉
 */
@Data
public class WordSize
{
    /** 文字字号ID */
    private Long sizeId;
    /** 字号名称 */
    private String sizeName;
    /** 字号价格 */
    private Double sizePrice;
    /** 创建时间 */
    private String createTime;

}
