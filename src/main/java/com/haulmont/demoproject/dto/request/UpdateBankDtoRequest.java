package com.haulmont.demoproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBankDtoRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private Set<UUID> credits;
}
