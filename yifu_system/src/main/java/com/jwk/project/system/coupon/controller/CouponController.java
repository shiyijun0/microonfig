package com.jwk.project.system.coupon.controller;

import com.jwk.common.utils.Activatedcode;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.controller.BaseController;
import com.jwk.framework.web.domain.JSON;
import com.jwk.framework.web.page.TableDataInfo;
import com.jwk.project.system.coupon.domain.Coupon;
import com.jwk.project.system.coupon.domain.CouponDetails;
import com.jwk.project.system.coupon.service.ICouponService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 优惠券 控制层
 *
 * @author 陈志辉
 */
@Controller
@RequestMapping("/system/coupon")
public class CouponController extends BaseController {

    private String prefix = "system/coupon";

    @Autowired
    private ICouponService couponService;

    /**
     *查询优惠券
     */
    @RequiresPermissions("system:coupon:view")
    @GetMapping()
    public String user() {
        return prefix + "/coupon";
    }

    /**
     *显示优惠券
     */
    @RequiresPermissions("system:coupon:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        TableDataInfo rows = couponService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }


    /**
     * 新增优惠券
     */
    @RequiresPermissions("system:coupon:add")
    @Log(title = "优惠券管理", action = "优惠券-新增优惠券")
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 修改优惠券
     */
    @RequiresPermissions("system:coupon:edit")
    @Log(title = "优惠券管理", action = "优惠券-修改优惠券")
    @GetMapping("/edit/{couponId}")
    public String edit(@PathVariable("couponId") Long couponId, Model model) {
        Coupon coupon = couponService.selectCouponById(couponId);
        model.addAttribute("coupon", coupon);
        return prefix + "/edit";
    }

    /**
     * 保存优惠券
     */
    @RequiresPermissions("system:coupon:save")
    @Log(title = "优惠券管理", action = "优惠券-保存优惠券")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Coupon coupon) {
        if (couponService.saveCoupon(coupon) > 0) {
            for(int i=0;i<coupon.getNumber();i++){
                CouponDetails couponDetails=new CouponDetails();
                couponDetails.setCid(coupon.getCouponId());
                couponDetails.setCode(Activatedcode.code());
                couponDetails.setState(0);
                couponDetails.setCtime(coupon.getCtime());
                couponDetails.setEtime(coupon.getEndtime());
                couponService.saveCouponDetatils(couponDetails);
            }
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 通过优惠券ID删除优惠券
     *
     * @param couponId 优惠券ID
     * @return 结果
     */
    @RequiresPermissions("system:coupon:remove")
    @Log(title = "优惠券管理", action = "优惠券-删除优惠券")
    @RequestMapping("/remove/{couponId}")
    @ResponseBody
    public JSON remove(@PathVariable("couponId") Long couponId) {
        Coupon coupon = couponService.selectCouponById(couponId);
        if (coupon == null) {
            return JSON.error("优惠券不存在");
        }
        if (couponService.deleteCouponById(couponId) > 0) {
            if(couponService.deleteCouponDetatilsById(couponId)>0){
                return JSON.ok();
            }
        }

        return JSON.error();
    }

    /**
     * 批量删除优惠券信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @RequiresPermissions("system:coupon:batchRemove")
    @Log(title = "优惠券管理", action = "优惠券-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids) {
        int rows = couponService.batchDeleteCoupon(ids);
        if (rows > 0) {
            for(int i=0;i<ids.length;i++){
                couponService.deleteCouponDetatilsById(ids[i]);
            }
            return JSON.ok();
        }
        return JSON.error();
    }

}