package com.haulmont.demoproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.haulmont.demoproject.dto.request.CreateClientDtoRequest;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.service.ClientService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientService clientService;

    private static final String CLIENT_ID_COOKIE_NAME = "uuid";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateClientDtoRequest dtoRequest,
                       HttpServletResponse servletResponse) {
        UUID id = clientService.create(dtoRequest);

        servletResponse.addCookie(new Cookie(CLIENT_ID_COOKIE_NAME, id.toString()));
    }

    @PostMapping("/activity")
    public void updateActivity(@CookieValue(CLIENT_ID_COOKIE_NAME) UUID id) {
        clientService.updateActivity(id);
    }

    @PostMapping("/credit-offers")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public void addCreditOffer(@Valid @RequestBody CreateCreditOfferDtoRequest dtoRequest,
                               @CookieValue(CLIENT_ID_COOKIE_NAME) UUID id) {
        clientService.addCreditOffer(dtoRequest, id);
    }
}
