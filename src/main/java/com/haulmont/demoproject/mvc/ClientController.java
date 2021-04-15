package com.haulmont.demoproject.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.haulmont.demoproject.service.ClientService;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/credit-offers")
    public String getCreditOffers(Model model, @SortDefault("startDate") Sort sort) {
        model.addAttribute("creditOffers", clientService.findCreditOffers(sort));

        return "creditOffers";
    }

    @GetMapping("/account")
    public String account() {
        return "personalAccount";
    }
}
