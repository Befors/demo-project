package com.haulmont.demoproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.haulmont.demoproject.dto.request.CreateCreditDtoRequest;
import com.haulmont.demoproject.dto.request.UpdateCreditDtoRequest;
import com.haulmont.demoproject.dto.response.CreditDtoResponse;
import com.haulmont.demoproject.model.Credit;

import java.util.List;

@Mapper
public interface CreditMapper {

    @Mapping(target = "active", constant = "true")
    Credit toEntity(CreateCreditDtoRequest dtoRequest);

    CreditDtoResponse toDto(Credit credit);

    Credit update(@MappingTarget Credit credit, UpdateCreditDtoRequest dtoRequest);

    List<CreditDtoResponse> toDtoList(List<Credit> credits);
}
