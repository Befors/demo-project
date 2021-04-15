package com.haulmont.demoproject.service;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.haulmont.demoproject.dto.request.CreateClientDtoRequest;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.dto.response.CreditOfferDtoResponse;
import com.haulmont.demoproject.model.Client;

import java.util.Collection;
import java.util.UUID;

public interface ClientService extends UserDetailsService {

    Client getCurrentClient();

    UUID create(CreateClientDtoRequest dtoRequest);

    void updateActivity(UUID id);

    void addCreditOffer(CreateCreditOfferDtoRequest dtoRequest, UUID id);

    Collection<CreditOfferDtoResponse> findCreditOffers(Sort sort);
}
