package com.haulmont.demoproject.service;

import org.mapstruct.Named;
import org.springframework.data.domain.Sort;
import com.haulmont.demoproject.dto.request.CreateCreditDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateCreditDtoRequest;
import com.haulmont.demoproject.dto.response.CreditDtoResponse;
import com.haulmont.demoproject.model.Credit;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface CreditService {

    CreditDtoResponse create(CreateCreditDtoRequest dtoRequest);

    CreditDtoResponse update(UpdateCreditDtoRequest dtoRequest, UUID id);

    @Named("findCreditsByIds")
    Set<Credit> findAllByIds(Iterable<UUID> ids);

    Credit findByIdLocal(UUID id);

    Collection<CreditDtoResponse> findAll(Sort sort);

    CreditDtoResponse updateActivity(UUID id);
}
