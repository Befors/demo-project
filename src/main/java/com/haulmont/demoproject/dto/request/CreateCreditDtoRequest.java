package com.haulmont.demoproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditDtoRequest {

    @Positive
    private double limit;

    @Positive
    private float interestRate;
}
