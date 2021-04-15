package com.haulmont.demoproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.haulmont.demoproject.dto.request.CreateClientDtoRequest;
import com.haulmont.demoproject.model.Client;

@Mapper
public interface ClientMapper {

    @Mappings({
            @Mapping(target = "role", constant = "CLIENT"),
            @Mapping(target = "active", constant = "true")
    })
    Client toEntity(CreateClientDtoRequest dtoRequest);
}
