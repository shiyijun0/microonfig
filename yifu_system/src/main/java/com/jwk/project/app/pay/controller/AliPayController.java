package com.jwk.project.app.pay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwk.common.utils.ServletUtils;
import com.jwk.common.utils.StringUtils;
import com.jwk.project.app.pay.domainVO.PayVO;
import com.jwk.project.app.pay.service.IAliPayService;
import com.jwk.project.system.registeruser.domain.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jwk.common.utils.StrUtils;
import com.jwk.common.utils.alipay.AlipayConfig;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.app.pay.service.IPayOrderService;
import com.jwk.project.system.order.domain.SysOrder;

/**
 * 支付宝支付
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController {

	@Autowired
	IAliPayService alipayService;
	@Autowired
	IPayOrderService orderdService;

	// 支付宝支付页
	@GetMapping("/topost")
	public String index(Model model) {
		return "alipayPost";
	}

	/**
	 * wap支付
	 * 
	 * @throws IOException
	 */
	// @RequestMapping("/topay")
	public void topay1(HttpServletRequest request, HttpServletResponse resp) throws IOException {

		/*
		 * String userId = request.getParameter("userId"); String addrId =
		 * request.getParameter("addrId"); String num = request.getParameter("num");
		 * String money = request.getParameter("money"); String jeansId =
		 * request.getParameter("jeansId");
		 */
		String orderIds = "111";
		String userId = "178";
		String addrId = "1";
		String num = "1";
		String money = "0.01";
		String jeansId = "30";
		String form = alipayService.orderPay(orderIds, userId, addrId, Integer.valueOf(num), money, jeansId);

		resp.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
		resp.getWriter().write(form);// 直接将完整的表单html输出到页面
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	/**
	 * wap支付
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/testpost")
	public void testpost(HttpServletRequest request, HttpServletResponse resp) throws IOException {

		String userId = request.getParameter("userId");
		String addrId = request.getParameter("addrId");
		String num = request.getParameter("num");
		String money = request.getParameter("money");
		String jeansId = request.getParameter("jeansId");

		/*
		 * String userId = "178"; String addrId = "1"; String num = "1"; String money =
		 * "0.01"; String jeansId = "30";
		 */
		String form = alipayService.wapPay(userId, addrId, Integer.valueOf(num), money, jeansId);

		resp.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
		resp.getWriter().write(form);// 直接将完整的表单html输出到页面
		resp.getWriter().flush();
		resp.getWriter().close();
	}

    /**
     * 支付宝支付方式
     * @return
     */
    @PostMapping("/alipaysubmit")
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


    @GetMapping("/topay")
	@Transactional
	public void topay(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        PayVO payVO= (PayVO) ServletUtils.getHttpServletRequest().getSession().getAttribute("payVO");

        ServletUtils.getHttpServletRequest().getSession().removeAttribute("payVO");

        String money= (String) ServletUtils.getHttpServletRequest().getSession().getAttribute("money");
        ServletUtils.getHttpServletRequest().getSession().removeAttribute("money");
        if(Double.valueOf(money).doubleValue()!=Double.valueOf(payVO.getMoney()).doubleValue()) {
            return ;
        }
            //String form = alipayService.orderPay(orderIds, userId, addrId, Integer.valueOf(num), money, jeansId);
        if(payVO!=null) {
            //String form = alipayService.orderPay(payVO.getOrderId(), payVO.getUserId(), payVO.getAddrId(), Integer.valueOf(payVO.getNum()), payVO.getMoney(), payVO.getJeansId());
        	String form = alipayService.orderPay(payVO.getOrderId(), payVO.getUserId(), payVO.getAddrId(), Integer.valueOf(payVO.getNum()), "0.01", payVO.getJeansId());

            resp.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            resp.getWriter().write(form);// 直接将完整的表单html输出到页面
            resp.getWriter().flush();
            resp.getWriter().close();

        }
	}

	@RequestMapping("/tocheck")
	@ResponseBody
	public JSON tocheck(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		String userId = request.getParameter("userId");
		String addrId = request.getParameter("addrId");
		String num = request.getParameter("num");
		String money = request.getParameter("money");
		String jeansId = request.getParameter("jeansId");
		String orderIds = request.getParameter("orderId");
		if (StrUtils.isEmpty(orderIds) || StrUtils.isEmpty(userId) || StrUtils.isEmpty(addrId)
				|| StrUtils.isEmpty(money) || StrUtils.isEmpty(num) || StrUtils.isEmpty(jeansId)) {
			return JSON.error("下单失败");
		}
		// 查询订单是否已经存在
		SysOrder sysOrder = orderdService.selectOrderById(Long.valueOf(orderIds));
		if (sysOrder != null) {
			if (sysOrder.getPayStatus() == 1)
				return JSON.error("该订单已经支付过");
		} else {
			return JSON.error("下单失败");
		}
		// 判断金额是否正确
		//if (!money.equals(sysOrder.getMoney().toString()))
		if(Double.valueOf(money).doubleValue()!=sysOrder.getMoney().doubleValue())
			return JSON.error("下单失败，金额不正确");

        ServletUtils.getHttpServletRequest().getSession().setAttribute("money",money);
		return JSON.ok();
	}

}
