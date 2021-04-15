package com.haulmont.demoproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.haulmont.demoproject.model.Credit;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDtoResponse {

    private UUID id;
    private String name;
    private Set<Credit> credits;
}
