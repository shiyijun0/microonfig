package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class AboutController {
    private String prefix = "custom";

    /**
     * 关于我们
     * @return
     */
    @GetMapping("/a_about_we")
    public String banner()
    {
        return prefix + "/a_about_we";
    }
}
