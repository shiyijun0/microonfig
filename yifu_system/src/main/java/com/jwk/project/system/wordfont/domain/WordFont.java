package com.jwk.project.system.wordfont.domain;

import lombok.Data;


/**
 * 文字字体对象 sys_word_font
 *
 * @author 陈志辉
 */
@Data
public class WordFont
{
    /** 文字字体ID */
    private Long fontId;
    /** 字体名称 */
    private String fontName;
    /** 字体价格 */
    private Double fontPrice;
    /** 创建时间 */
    private String createTime;

}
