package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class ReplenishController {
    private String prefix = "custom";

    /**
     * 充值
     * @return
     */
    @GetMapping("/replenish")
    public String replenish()
    {
        return prefix + "/replenish";
    }
}
