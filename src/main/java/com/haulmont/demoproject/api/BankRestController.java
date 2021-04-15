package com.haulmont.demoproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.haulmont.demoproject.dto.request.CreateBankDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateBankDtoRequest;
import com.haulmont.demoproject.dto.response.BankDtoResponse;
import com.haulmont.demoproject.service.BankService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banks")
@PreAuthorize("hasRole('ADMIN')")
public class BankRestController {

    private final BankService bankService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@Valid @RequestBody CreateBankDtoRequest dtoRequest) {
        BankDtoResponse dtoResponse = bankService.create(dtoRequest);

        return dtoResponse.getId();
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody UpdateBankDtoRequest dtoRequest,
                       @PathVariable UUID id) {
        bankService.update(dtoRequest, id);
    }

    @PostMapping("/{id}/activity")
    public void updateActivity(@PathVariable UUID id) {
        bankService.updateActivity(id);
    }
}
