package com.haulmont.demoproject.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.haulmont.demoproject.model.Client;

@Controller
public class HomeController {

    @GetMapping
    public String home(@AuthenticationPrincipal Client client, Model model) {
        model.addAttribute("client", client);

        return "index";
    }
}
