package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class RedPacketController {
    private String prefix = "custom";

    /**
     * 红包
     * @return
     */
    @GetMapping("/red_packet")
    public String redPacket()
    {
        return prefix + "/red_packet";
    }
}
