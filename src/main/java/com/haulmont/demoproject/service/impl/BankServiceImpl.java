package com.haulmont.demoproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.haulmont.demoproject.dto.request.CreateBankDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateBankDtoRequest;
import com.haulmont.demoproject.dto.response.BankDtoResponse;
import com.haulmont.demoproject.mapper.BankMapper;
import com.haulmont.demoproject.model.Bank;
import com.haulmont.demoproject.repository.BankRepository;
import com.haulmont.demoproject.service.BankService;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BankServiceImpl implements BankService {

    private final BankMapper bankMapper;
    private final BankRepository bankRepository;

    @Override
    public BankDtoResponse create(CreateBankDtoRequest dtoRequest) {
        Bank bank = bankMapper.toEntity(dtoRequest);

        return bankMapper.toDto(bankRepository.save(bank));
    }

    @Override
    public BankDtoResponse update(UpdateBankDtoRequest dtoRequest, UUID id) {
        Bank bank = findBankById(id);

        bankMapper.update(bank, dtoRequest);

        return bankMapper.toDto(bankRepository.save(bank));
    }

    @Override
    @Transactional(readOnly = true)
    public Bank findByIdLocal(UUID id) {
        return findBankById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BankDtoResponse findById(UUID id) {
        return bankMapper.toDto(findBankById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BankDtoResponse> findAll(Sort sort) {
        return bankMapper.toDtoList(bankRepository.findAllByActiveIsTrue(sort));
    }

    @Override
    public BankDtoResponse updateActivity(UUID id) {
        Bank bank = findBankById(id);

        bank.setActive(!bank.isActive());

        return bankMapper.toDto(bankRepository.save(bank));
    }

    private Bank findBankById(UUID id) {
        return bankRepository.findById(id)
                .orElseThrow();
    }
}
