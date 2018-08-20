package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class SysMsgController {
    private String prefix = "custom";

    /**
     * 系统消息
     * @return
     */
    @GetMapping("/system_message")
    public String sysMsg()
    {
        return prefix + "/system_message";
    }
}
