package com.haulmont.demoproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.haulmont.demoproject.dto.request.CreateCreditDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateCreditDtoRequest;
import com.haulmont.demoproject.dto.response.CreditDtoResponse;
import com.haulmont.demoproject.mapper.CreditMapper;
import com.haulmont.demoproject.model.Credit;
import com.haulmont.demoproject.repository.CreditRepository;
import com.haulmont.demoproject.service.CreditService;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;

    @Override
    @Transactional
    public CreditDtoResponse create(CreateCreditDtoRequest dtoRequest) {
        Credit credit = creditMapper.toEntity(dtoRequest);

        return creditMapper.toDto(creditRepository.save(credit));
    }

    @Override
    @Transactional
    public CreditDtoResponse update(UpdateCreditDtoRequest dtoRequest, UUID id) {
        Credit credit = findCreditById(id);

        creditMapper.update(credit, dtoRequest);

        return creditMapper.toDto(creditRepository.save(credit));
    }

    @Override
    public Set<Credit> findAllByIds(Iterable<UUID> ids) {
        return creditRepository.findAllActiveByIds(ids);
    }

    @Override
    public Credit findByIdLocal(UUID id) {
        return findCreditById(id);
    }

    @Override
    public Collection<CreditDtoResponse> findAll(Sort sort) {
        return creditMapper.toDtoList(creditRepository.findAllByActiveIsTrue(sort));
    }

    @Override
    @Transactional
    public CreditDtoResponse updateActivity(UUID id) {
        Credit credit = findCreditById(id);

        credit.setActive(!credit.isActive());

        return creditMapper.toDto(creditRepository.save(credit));
    }

    private Credit findCreditById(UUID id) {
        return creditRepository.findById(id)
                .orElseThrow();
    }
}
