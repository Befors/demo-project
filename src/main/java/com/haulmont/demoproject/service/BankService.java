package com.haulmont.demoproject.service;

import org.springframework.data.domain.Sort;
import com.haulmont.demoproject.dto.request.CreateBankDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateBankDtoRequest;
import com.haulmont.demoproject.dto.response.BankDtoResponse;
import com.haulmont.demoproject.model.Bank;

import java.util.Collection;
import java.util.UUID;

public interface BankService {

    BankDtoResponse create(CreateBankDtoRequest dtoRequest);

    BankDtoResponse update(UpdateBankDtoRequest dtoRequest, UUID id);

    Bank findByIdLocal(UUID id);

    BankDtoResponse findById(UUID id);

    Collection<BankDtoResponse> findAll(Sort sort);

    BankDtoResponse updateActivity(UUID id);
}
