package com.haulmont.demoproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDtoResponse {

    private UUID id;
    private LocalDate date;
    private double total;
    private double bodySum;
    private double interestSum;
}
