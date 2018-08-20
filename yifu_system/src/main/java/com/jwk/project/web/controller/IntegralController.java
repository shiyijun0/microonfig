package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class IntegralController {

    private String prefix = "custom";

    /**
     * 积分规则
     * @return
     */
    @GetMapping("/a_integral")
    public String Integral()
    {
        return prefix + "/a_integral";
    }
}
