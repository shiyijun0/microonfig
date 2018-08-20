package com.jwk.framework.web.controller;

import com.jwk.common.utils.security.ShiroUtils;
import com.jwk.framework.web.page.PageUtilEntity;
import com.jwk.framework.web.support.TableSupport;
import com.jwk.project.system.user.domain.User;

/**
 * web层通用数据处理
 * 
 * @author system
 */
public class BaseController
{
    /**
     * 获取请求分页数据
     */
    public PageUtilEntity getPageUtilEntity()
    {
        PageUtilEntity pageUtilEntity = TableSupport.buildPageRequest();
        return pageUtilEntity;
    }

    public User getUser()
    {
        return ShiroUtils.getUser();
    }

    public Long getUserId()
    {
        return getUser().getUserId();
    }

    public String getLoginName()
    {
        return getUser().getLoginName();
    }
}
