package com.haulmont.demoproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.dto.response.CreditOfferDtoResponse;
import com.haulmont.demoproject.mapper.CreditOfferMapper;
import com.haulmont.demoproject.model.Bank;
import com.haulmont.demoproject.model.Client;
import com.haulmont.demoproject.model.Credit;
import com.haulmont.demoproject.model.CreditOffer;
import com.haulmont.demoproject.repository.CreditOfferRepository;
import com.haulmont.demoproject.service.BankService;
import com.haulmont.demoproject.service.CreditOfferService;
import com.haulmont.demoproject.service.CreditService;
import com.haulmont.demoproject.util.AccessUtil;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditOfferServiceImpl implements CreditOfferService {

    private final CreditOfferRepository creditOfferRepository;
    private final CreditOfferMapper creditOfferMapper;
    private final BankService bankService;
    private final CreditService creditService;

    @Override
    public CreditOffer create(CreateCreditOfferDtoRequest dtoRequest, Client client) {
        Bank bank = bankService.findByIdLocal(dtoRequest.getBank());
        Credit credit = creditService.findByIdLocal(dtoRequest.getCredit());

        AccessUtil.checkAccessibility(bank);

        if (!bank.containsCredit(credit)) {
            throw new RuntimeException();
        }
        if (credit.getLimit() < dtoRequest.getTotal()) {
            throw new RuntimeException();
        }

        return creditOfferMapper.toEntity(dtoRequest, client, bank, credit);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CreditOfferDtoResponse> findAllByClient(Client client, Sort sort) {
        List<CreditOffer> creditOffers = creditOfferRepository
                .findAllByClient(client, sort);

        return creditOfferMapper.toDtoList(creditOffers);
    }
}
