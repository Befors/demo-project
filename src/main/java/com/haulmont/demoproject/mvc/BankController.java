package com.haulmont.demoproject.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.haulmont.demoproject.dto.response.BankDtoResponse;
import com.haulmont.demoproject.service.BankService;
import com.haulmont.demoproject.service.CreditService;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;
    private final CreditService creditService;

    @GetMapping
    public String getAll(Model model, @SortDefault("name") Sort sort) {
        model.addAttribute("banks", bankService.findAll(sort));

        return "banks";
    }

    @GetMapping("/save")
    public String save(@RequestParam(required = false) Optional<UUID> id,
                       @SortDefault({"limit", "interestRate"}) Sort sort, Model model) {
        model.addAttribute("credits", creditService.findAll(sort));
        model.addAttribute("current",
                id.map(bankService::findById).orElseGet(BankDtoResponse::new));

        return "savingBank";
    }
}
