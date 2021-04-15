package com.haulmont.demoproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.haulmont.demoproject.dto.request.CreateCreditDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateCreditDtoRequest;
import com.haulmont.demoproject.service.CreditService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/credits")
@PreAuthorize("hasRole('ADMIN')")
public class CreditRestController {

    private final CreditService creditService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateCreditDtoRequest dtoRequest) {
        creditService.create(dtoRequest);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody UpdateCreditDtoRequest dtoRequest,
                       @PathVariable UUID id) {
        creditService.update(dtoRequest, id);
    }

    @PostMapping("/{id}/activity")
    public void updateActivity(@PathVariable UUID id) {
        creditService.updateActivity(id);
    }
}
