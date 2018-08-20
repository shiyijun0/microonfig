package com.jwk.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class MyMembersController {
    private String prefix = "custom";
    /**
     * 我的会员
     * @return
     */
    @GetMapping("/my_members")
    public String myMembers()
    {
        return prefix + "/my_members";
    }

}
