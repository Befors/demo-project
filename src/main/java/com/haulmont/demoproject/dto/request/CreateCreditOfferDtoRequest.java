package com.haulmont.demoproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditOfferDtoRequest {

    @NotNull
    private UUID credit;

    @NotNull
    private UUID bank;

    @Positive
    private double total;

    @Future
    private LocalDate endDate;
}
