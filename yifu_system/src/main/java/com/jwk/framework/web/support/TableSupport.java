package com.jwk.framework.web.support;

import javax.servlet.http.HttpServletRequest;

import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.MapDataUtil;
import com.jwk.framework.web.page.PageUtilEntity;

/**
 * 表格数据处理
 * 
 * @author system
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageUtilEntity getPageUtilEntity()
    {
        HttpServletRequest request = ServletUtils.getHttpServletRequest();
        PageUtilEntity pageUtilEntity = new PageUtilEntity();
        pageUtilEntity.setPage(Integer.valueOf(request.getParameter("offset")));
        pageUtilEntity.setSize(Integer.valueOf(request.getParameter("limit")));
        pageUtilEntity.setOrderByColumn(request.getParameter("sort"));
        pageUtilEntity.setIsAsc(request.getParameter("order"));
        pageUtilEntity.setSearchValue(request.getParameter("search"));
        pageUtilEntity.setSearchPayStatus(request.getParameter("payStatus"));       
        pageUtilEntity.setSearchPayType(request.getParameter("payType"));
        pageUtilEntity.setReqMap(MapDataUtil.convertDataMap(request));
        return pageUtilEntity;
    }

    public static PageUtilEntity buildPageRequest()
    {
        return getPageUtilEntity();
    }

}
