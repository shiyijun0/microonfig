package com.jwk.project.web.controller;


import com.jwk.common.utils.JacksonUtils;
import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.domain.CouponDetails;
import com.jwk.project.system.coupon.domain.CouponUser;
import com.jwk.project.system.coupon.service.ICouponService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/custom")
public class VolumeController {
    private String prefix = "custom";

    @Autowired
    private ICouponService couponService;


    /**
     * 优惠券
     * @return
     */
    @GetMapping("/volume")
    public String volume(Model model) throws Exception {
        RegisterUser  user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
        if(user==null) {
            return "redirect:/custom/index";
        }
        List<CouponUser> couponUser = couponService.selectCouponUserById(user.getId());
        if (!couponUser.isEmpty()) {
            for (CouponUser cou : couponUser) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date ctime=sdf.parse(cou.getCtime());
                Date endtime=sdf.parse(cou.getEndtime());
                sdf = new SimpleDateFormat("yyyy.M.d");
                cou.setCtime(sdf.format(ctime));
                cou.setEndtime(sdf.format(endtime));
            }
            model.addAttribute("couponUser", couponUser);
        }
        return prefix + "/volume";
    }



    /**
     * 校验优惠码是否正确
     */
    @PostMapping("/couponcode")
    @ResponseBody
    public String couponcode(String code)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
        if(user!=null) {
            if (StringUtils.isNotEmpty(code)) {
                Coupon coupon = couponService.selectCouponByCode(code);
                if (coupon != null) {
                    if (coupon.getCouponDetails().getState() == 0) {
                        CouponUser couponUser=new CouponUser();
                        couponUser.setUid(user.getId());
                        couponUser.setCid(coupon.getCouponDetails().getDetailsId());
                        System.out.println(coupon.getCouponDetails().getDetailsId());
                        couponUser.setState(1);
                        couponUser.setRank(coupon.getRank());
                        couponUser.setCtime(coupon.getCtime());
                        couponUser.setEndtime(coupon.getEndtime());
                        couponService.saveCouponUser(couponUser);
                        CouponDetails details=new CouponDetails();
                        details.setDetailsId(coupon.getCouponDetails().getDetailsId());
                        details.setState(1);
                        couponService.updateCouponDetails(details);
                        map.put("code",0);
                        map.put("coupon",coupon);
                        return JacksonUtils.toJsonString(map);
                    } else {
                        map.put("code",1);
                        map.put("msg","该优惠券已被领取");
                        return JacksonUtils.toJsonString(map);
                    }
                }else{
                    map.put("code",2);
                    map.put("msg","该优惠码不存在");
                    return JacksonUtils.toJsonString(map);
                }
            }
        }
        map.put("code",3);
        map.put("msg","请先登陆");
        return JacksonUtils.toJsonString(map);
    }


    /**
     * 根据用户id查询当前用户所有优惠券
     */
    @PostMapping("/selectcoupon")
    @ResponseBody
    public String selectcoupon() throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
        if(user!=null) {
            List<CouponUser> couponUser = couponService.selectCouponUserById(user.getId());
            if (couponUser != null) {
                map.put("code", 0);
                map.put("couponUser", couponUser);
                map.put("size",couponUser.size());
                return JacksonUtils.toJsonString(map);
            }
        }
        map.put("code",1);
        return JacksonUtils.toJsonString(map);
    }


}
