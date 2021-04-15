package com.haulmont.demoproject.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.dto.response.CreditOfferDtoResponse;
import com.haulmont.demoproject.model.Bank;
import com.haulmont.demoproject.model.Client;
import com.haulmont.demoproject.model.Credit;
import com.haulmont.demoproject.model.CreditOffer;
import com.haulmont.demoproject.service.BankService;
import com.haulmont.demoproject.service.ClientService;
import com.haulmont.demoproject.service.CreditService;

import java.time.LocalDate;
import java.util.List;

@Mapper(uses = {ClientService.class, CreditService.class, BankService.class,
        PaymentMapper.class}, imports = LocalDate.class)
@DecoratedWith(CreditOfferDecorator.class)
public interface CreditOfferMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "client", source = "client"),
            @Mapping(target = "bank", source = "bank"),
            @Mapping(target = "credit", source = "credit"),
            @Mapping(target = "startDate", expression = "java(LocalDate.now())")
    })
    CreditOffer toEntity(CreateCreditOfferDtoRequest dtoRequest, Client client,
                         Bank bank, Credit credit);

    @Mapping(target = "bankName", expression = "java(creditOffer.getBank().getName())")
    CreditOfferDtoResponse toDto(CreditOffer creditOffer);

    List<CreditOfferDtoResponse> toDtoList(List<CreditOffer> creditOffers);
}
