package com.haulmont.demoproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditOfferDtoResponse {

    private UUID id;
    private CreditDtoResponse credit;
    private String bankName;
    private double total;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<PaymentDtoResponse> payments;
}
