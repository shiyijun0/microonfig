package com.jwk.common.utils.video;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//视频上传
    
    @Controller
    @RequestMapping("/uploadflv")
    public class VideoController {
    	
    	 private String prefix = "system/web/shiping";
    	
    	 @GetMapping("/video")
 	    public String designer()
 	    {
 	        return prefix + "/video";
 	    }

        @RequestMapping(value = "/upload", method={RequestMethod.POST,RequestMethod.GET})
        @ResponseBody
        public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                HttpServletRequest request, ModelMap map,Model model) {
            String message = "";
            FileEntity entity = new FileEntity();
            FileUploadTool fileUploadTool = new FileUploadTool();
            try {
                entity = fileUploadTool.createFile(multipartFile, request);
                if (entity != null) {
//                    service.saveFile(entity);
                    message = "上传成功";
                    map.put("entity", entity);
                    map.put("result", message);
                } else {
                    message = "上传失败";
                    map.put("result", message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            model.addAttribute("webDesigner", map);
            return new ModelAndView(prefix + "/check", map);
        }
    }
    

