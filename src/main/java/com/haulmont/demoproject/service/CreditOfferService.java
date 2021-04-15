package com.haulmont.demoproject.service;

import org.springframework.data.domain.Sort;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.dto.response.CreditOfferDtoResponse;
import com.haulmont.demoproject.model.Client;
import com.haulmont.demoproject.model.CreditOffer;

import java.util.Collection;

public interface CreditOfferService {

    CreditOffer create(CreateCreditOfferDtoRequest dtoRequest, Client client);

    Collection<CreditOfferDtoResponse> findAllByClient(Client client, Sort sort);
}
