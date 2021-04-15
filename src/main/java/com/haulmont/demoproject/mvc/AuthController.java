package com.haulmont.demoproject.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }
}
