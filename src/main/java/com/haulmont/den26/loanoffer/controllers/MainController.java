package com.haulmont.den26.loanoffer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("main")
    public String getMainPage() {
        return "/main";
    }
}
