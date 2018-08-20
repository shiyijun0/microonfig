package com.jwk.project.web.controller;


import com.jwk.common.utils.ServletUtils;
import com.jwk.project.system.coupon.domain.CouponUser;
import com.jwk.project.system.coupon.service.ICouponService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping("/custom")
public class MyAccountController {

    private String prefix = "custom";

    @Autowired
    private ICouponService couponService;

    /**
     * 我的账户
     * @return
     */
    @GetMapping("/my_account")
    public String myAccount(Model model) throws Exception {
        RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
        if(user!=null) {
            List<CouponUser> list = couponService.selectCouponUserById(user.getId());
            model.addAttribute("couponCount", list.size());
        }else{
            return "redirect:/custom/index";
        }
        return prefix + "/my_account";
    }




}
