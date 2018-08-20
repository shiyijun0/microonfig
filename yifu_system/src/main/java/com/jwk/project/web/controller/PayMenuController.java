package com.jwk.project.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.app.pay.domainVO.PayVO;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.order.domain.SysOrder;

@Controller
@RequestMapping("/custom")
public class PayMenuController {
    private String prefix = "custom";


    /**
     * 支付方式
     * @return
     */
    @PostMapping("/pay_menu")
	@ResponseBody
    public JSON payMenu(@RequestParam(name = "userId", required = false) Integer userId,@RequestParam(name = "orderId", required = false) String orderId,
    		@RequestParam(name = "price", required = false) Double price,@RequestParam(name = "addrId", required = false) Integer addrId,
    		@RequestParam(name = "num", required = false) Integer num,
    		@RequestParam(name = "jeansId", required = false) String jeansId,Model model)
    {
		RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if(user==null){
			return  JSON.error("您尚未登陆，请登陆");
		}
    	SysOrder order=new SysOrder(orderId,userId,addrId,num,BigDecimal.valueOf(price));
    	/*model.addAttribute("order", order);
    	model.addAttribute("jeansId", jeansId);*/
    	if(order!=null){
    		order.setJeansId(jeansId);
			ServletUtils.getHttpServletRequest().getSession().setAttribute("order",order);
			return JSON.ok();
		}

		return JSON.error();
    }

	/**
	 *提交下单成功
	 * @return
	 */
	@GetMapping("/pay_menu_success")
	public String pay_menu_success(Model model)
	{
		RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if(user==null){
			return prefix + "/index";
		}
		SysOrder order= (SysOrder) ServletUtils.getHttpServletRequest().getSession().getAttribute("order");
		model.addAttribute("order", order);
		model.addAttribute("jeansId", order.getJeansId());
		ServletUtils.getHttpServletRequest().getSession().removeAttribute("order");
		return prefix + "/pay_menu";
	}



	/**
	 * 微信支付方式
	 * @return
	 */
	@PostMapping("/wpsubmit")
	@ResponseBody
	public JSON wpsubmit(PayVO payVO)
	{
		RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if(user==null){
			return  JSON.error("您尚未登陆，请登陆");
		}

		if(StringUtils.isNotNull(payVO) ){
			ServletUtils.getHttpServletRequest().getSession().setAttribute("payVO",payVO);
			return JSON.ok();
		}

		return JSON.error();
	}


	/**
     *微信 支付方式
     * @return
     */
    @GetMapping("/wpsuccess")
   // public String wpsuccess(@RequestParam(name = "mweb_url", required = false) String mweb_url,@RequestParam(name = "result", required = false) String result,Model model)
	public String wpsuccess(Model model)
    {

		RegisterUser user= (RegisterUser) ServletUtils.getHttpServletRequest().getSession().getAttribute("user");
		if(user==null){
			return prefix + "/index";
		}
		PayVO payVO= (PayVO) ServletUtils.getHttpServletRequest().getSession().getAttribute("payVO");
		model.addAttribute("mweb_url", payVO.getMweb_url());
		model.addAttribute("result", payVO.getResult());
		ServletUtils.getHttpServletRequest().getSession().removeAttribute("payVO");
    	/*model.addAttribute("mweb_url", mweb_url);
    	model.addAttribute("result", result);*/
    	
        return prefix + "/wpsuccess";
    }
    
    /**
     *支付宝 支付方式
     * @return
     */
    @GetMapping("/alipayPost")
    public String alypayPost(@RequestParam(name = "userId", required = false) Integer userId,@RequestParam(name = "jeansId", required = false) String jeansId,
    		@RequestParam(name = "money", required = false) Double money,@RequestParam(name = "addrId", required = false) Integer addrId,
    		@RequestParam(name = "num", required = false) Integer num,Model model)
    {
    	
    	
    	model.addAttribute("userId", userId);
    	model.addAttribute("jeansId", jeansId);
    	model.addAttribute("money", money);
    	model.addAttribute("addrId", addrId);
    	model.addAttribute("num", num);
    	
    	
        return prefix + "/alipayPost";
    }
    
    /**
     *支付宝 支付方式
     * @return
     */
    @PostMapping("/alipay")
    @ResponseBody
    public JSON alypay(@RequestParam(name = "userId", required = false) Integer userId,@RequestParam(name = "jeansId", required = false) String jeansId,
    		@RequestParam(name = "money", required = false) Double money,@RequestParam(name = "addrId", required = false) Integer addrId,
    		@RequestParam(name = "num", required = false) Integer num,Model model)
    {
    	
    	
    	model.addAttribute("userId", userId);
    	model.addAttribute("jeansId", jeansId);
    	model.addAttribute("money", money);
    	model.addAttribute("addrId", addrId);
    	model.addAttribute("num", num);
    	
    	Map<String, Object> maps=new HashMap<String, Object>();
	    maps.put("userId", userId);
	    maps.put("jeansId", jeansId);
	    maps.put("money", money);
	    maps.put("addrId", addrId);
	    maps.put("num", num);
	    return JSON.ok(maps);
    	
        
    }
}
