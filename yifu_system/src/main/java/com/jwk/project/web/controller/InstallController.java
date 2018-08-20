package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class InstallController {
    private String prefix = "custom";

    /**
     * 设置
     * @return
     */
    @GetMapping("/install")
    public String Install()
    {
        return prefix + "/install";
    }
}
