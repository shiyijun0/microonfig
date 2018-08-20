package com.jwk.project.system.coupon.controller;


import com.jwk.common.utils.ExcelUtil;
import com.jwk.framework.aspectj.lang.annotation.Log;
import com.jwk.framework.web.domain.JSON;
import com.jwk.project.system.coupon.domain.CouponDetails;
import com.jwk.project.system.coupon.service.ICouponService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 导出优惠券
 * @author  陈志辉
 */

@Controller
@RequestMapping("/system/coupon")
public class ExcelController {

    @Autowired
    private ICouponService iCouponService;

    @RequiresPermissions("system:coupon:export")
    @Log(title = "excel导出", action = "优惠券口令-excel导出")
    @GetMapping("/export/{couponId}")
    @ResponseBody
    public JSON exportFeedBack(HttpServletResponse response, @PathVariable("couponId")Long couponId) throws Exception {

        String fileName = "优惠口令"+System.currentTimeMillis()+".xls"; //文件名
        String sheetName = "优惠口令";//sheet名

        String []title = new String[]{"优惠ID","优惠口令","失效时间"};//标题

       List<CouponDetails> couponDetails=iCouponService.selectCouponDetailsByCid(couponId);
       String [][]values = new String[couponDetails.size()][];
       for(int i=0;i<couponDetails.size();i++){
           values[i]=new String[title.length];
           values[i][0]=couponDetails.get(i).getDetailsId().toString();
           values[i][1]=couponDetails.get(i).getCode();
           values[i][2]=couponDetails.get(i).getEtime();
       }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);

        //将文件存到指定位置
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.error();
        }
        return JSON.ok();
    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
