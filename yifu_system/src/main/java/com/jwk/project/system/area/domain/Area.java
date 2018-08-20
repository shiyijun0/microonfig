package com.jwk.project.system.area.domain;

import java.util.List;

import lombok.Data;

/**
 * 城市表 sys_area
 * 
 * @author system
 */
@Data
public class Area
{
    /** ID */
    private Long id;
    /** 父类ID */
    private Long parentId;
    /** 名称 */
    private String name;
    /** 排序 */
    private int orderNum;
    /** 级别 */
    private int level;
    /** 路径 */
    private String path;
    /** 是否启用：否 1：是 */
    private int status;
    /** 创建时间 */
    private String createTime;
    
    private List<Area> childList;

}
