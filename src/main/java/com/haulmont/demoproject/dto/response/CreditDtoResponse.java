package com.haulmont.demoproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDtoResponse {

    private UUID id;
    private double limit;
    private float interestRate;
}
