package com.jwk.project.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jwk.common.utils.ServletUtils;
import com.jwk.project.system.index.domain.SysWebContact;
import com.jwk.project.system.index.domain.SysWebIndex;
import com.jwk.project.system.index.domain.SysWebLaw;
import com.jwk.project.system.index.domain.SysWebPolicy;
import com.jwk.project.system.index.service.IWebContactService;
import com.jwk.project.system.index.service.IWebLawService;
import com.jwk.project.system.index.service.IWebPolicyService;
import com.jwk.project.system.index.service.WebIndexService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import com.jwk.project.system.registeruser.service.IRegisterUserService;

/**
 * 首页
 */

@Controller
@RequestMapping("/custom")
public class InController {

    @Autowired
    private IRegisterUserService registerUserService;

    @Autowired
    private WebIndexService webIndexService;

    @Autowired
    private IWebPolicyService webPolicyService;

    @Autowired
    private IWebContactService webContactService;

    @Autowired
    private IWebLawService webLawService;


    private String prefix = "custom";

    /**
     * 错误页
     * @param model
     * @return
     */
    
    @GetMapping("/error")
    public String error(Model model) {
    	return prefix + "/error";
    }
    
    /**
     * 首页
     * @param model
     * @return
     */

    @GetMapping("/index")
    public String index(Model model) {
        RegisterUser registerUser= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
        if(registerUser!=null) {
            registerUser = registerUserService.selectRegisterUserById(registerUser.getId());
            model.addAttribute("registerUser",registerUser);
        }
        List<SysWebIndex> webIndex= webIndexService.selectWebBannerAll();
        model.addAttribute("webIndex", webIndex);
        return prefix + "/index";
    }

    @GetMapping("index/banner")
    public String banner(Model model, HttpServletRequest request, HttpServletResponse resp) {
        //1.查詢bannerlist集合数据
        // List<Map<String,Object>> bannerList = "";
        //model.addAttribute("bannerList", bannerList);
        return "/banner";
    }

    /**
     * 退换货政策
     * @param model
     * @return
     */
    @GetMapping("/policy")
    public String policy(Model model) {
        SysWebPolicy webPolicy= webPolicyService.selectSysWebPolicyAll();
        model.addAttribute("webPolicy", webPolicy);
        return prefix + "/about_goods";
    }


    /**
     * 联系我们
     * @param model
     * @return
     */
    @GetMapping("/contact")
    public String contact(Model model) {
        SysWebContact webContact= webContactService.selectSysWebContactAll();
        model.addAttribute("webContact", webContact);
        return prefix + "/about_us";
    }



    /**
     * 法律
     * @param model
     * @return
     */
    @GetMapping("/law")
    public String law(Model model) {
        SysWebLaw webLaw= webLawService.selectSysWebLawAll();
        model.addAttribute("webLaw", webLaw);
        return prefix + "/about_law";
    }



}
