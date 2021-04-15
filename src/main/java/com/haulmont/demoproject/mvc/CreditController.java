package com.haulmont.demoproject.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.haulmont.demoproject.service.CreditService;

@Controller
@RequestMapping("/admin/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    public String getAll(Model model, @SortDefault({"limit", "interestRate"}) Sort sort) {
        model.addAttribute("credits", creditService.findAll(sort));

        return "credits";
    }
}
