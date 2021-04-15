package com.haulmont.demoproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import com.haulmont.demoproject.dto.request.CreateBankDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateBankDtoRequest;
import com.haulmont.demoproject.dto.response.BankDtoResponse;
import com.haulmont.demoproject.model.Bank;
import com.haulmont.demoproject.service.CreditService;

import java.util.List;

@Mapper(uses = CreditService.class)
public interface BankMapper {

    @Mappings({
            @Mapping(target = "credits", qualifiedByName = "findCreditsByIds"),
            @Mapping(target = "active", constant = "true")
    })
    Bank toEntity(CreateBankDtoRequest dtoRequest);

    BankDtoResponse toDto(Bank bank);

    @Mapping(target = "credits", qualifiedByName = "findCreditsByIds")
    Bank update(@MappingTarget Bank bank, UpdateBankDtoRequest dtoRequest);

    List<BankDtoResponse> toDtoList(List<Bank> banks);
}
