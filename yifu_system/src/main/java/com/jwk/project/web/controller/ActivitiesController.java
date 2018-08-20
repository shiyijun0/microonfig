package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class ActivitiesController {
    private String prefix = "custom";

    /**
     * 活动详情
     * @return
     */
    @GetMapping("/activities_details")
    public String activities()
    {
        return prefix + "/activities_details";
    }
}
