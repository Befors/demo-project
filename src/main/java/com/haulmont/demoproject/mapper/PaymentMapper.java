package com.haulmont.demoproject.mapper;

import org.mapstruct.Mapper;
import com.haulmont.demoproject.dto.response.PaymentDtoResponse;
import com.haulmont.demoproject.model.Payment;

@Mapper
public interface PaymentMapper {

    PaymentDtoResponse toDto(Payment payment);
}
