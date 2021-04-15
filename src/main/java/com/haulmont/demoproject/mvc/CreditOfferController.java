package com.haulmont.demoproject.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.haulmont.demoproject.service.BankService;

@Controller
@RequiredArgsConstructor
public class CreditOfferController {

    private final BankService bankService;

    @GetMapping("/signing")
    public String sign(@SortDefault("name") Sort sort, Model model) {
        model.addAttribute("banks", bankService.findAll(sort));

        return "signingCreditOffer";
    }
}
