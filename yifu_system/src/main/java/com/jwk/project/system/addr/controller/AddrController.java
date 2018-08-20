package com.jwk.project.system.addr.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.common.utils.StringUtils;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.addr.domain.Addr;
import com.jwk.project.system.addr.service.IAddrService;
import com.jwk.project.system.dept.domain.Dept;
import com.jwk.project.system.dept.service.IDeptService;

/**
 * 收货地址
 * 
 * @author system
 */
@Controller
@RequestMapping("/system/addr")
public class AddrController
{
	@Autowired
    private IAddrService addrService;
	
	@PostMapping("/save")
    @ResponseBody
    public JSON ajaxLogin(Addr addr)
    {
       /* UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return JSON.ok();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
		return JSON.error(msg);
        }*/
		return JSON.error("");
    }
}
