package com.jwk.framework.web.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author system
 */
public class TableDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    /** 总记录数 */
    private int total;
    /** 列表数据 */
    private List<?> rows;

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param totalCount 总记录数
     * @param pageSize 每页记录数
     * @param currPage 当前页数
     */
    public TableDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

}
