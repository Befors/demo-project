package com.haulmont.demoproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.haulmont.demoproject.dto.request.CreateClientDtoRequest;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.dto.response.CreditOfferDtoResponse;
import com.haulmont.demoproject.mapper.ClientMapper;
import com.haulmont.demoproject.model.Client;
import com.haulmont.demoproject.model.CreditOffer;
import com.haulmont.demoproject.repository.ClientRepository;
import com.haulmont.demoproject.service.ClientService;
import com.haulmont.demoproject.service.CreditOfferService;
import com.haulmont.demoproject.service.SecurityService;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final SecurityService securityService;
    private final CreditOfferService creditOfferService;

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow();
    }

    @Override
    public Client getCurrentClient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return Optional.of(auth)
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken))
                .map(authentication -> (Client) authentication.getPrincipal())
                .orElse(null);
    }

    @Override
    @Transactional
    public UUID create(CreateClientDtoRequest dtoRequest) {
        Client client = clientRepository.save(clientMapper.toEntity(dtoRequest));

        securityService.autoLogin(client, client.getPassword());

        return client.getId();
    }

    @Override
    @Transactional
    public void updateActivity(UUID id) {
        Client client = findClientById(id);

        if (client.isActive()) {
            SecurityContextHolder.clearContext();
        }

        client.setActive(!client.isActive());
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void addCreditOffer(CreateCreditOfferDtoRequest dtoRequest, UUID id) {
        Client client = findClientById(id);
        CreditOffer creditOffer = creditOfferService.create(dtoRequest, client);

        client.addCreditOffer(creditOffer);
        clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CreditOfferDtoResponse> findCreditOffers(Sort sort) {
        return creditOfferService.findAllByClient(getCurrentClient(), sort);
    }

    private Client findClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow();
    }
}
