package com.haulmont.demoproject.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.haulmont.demoproject.dto.request.CreateCreditOfferDtoRequest;
import com.haulmont.demoproject.model.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CreditOfferDecorator implements CreditOfferMapper {

    @Autowired
    private CreditOfferMapper delegate;

    @Override
    public CreditOffer toEntity(CreateCreditOfferDtoRequest dtoRequest, Client client,
                                Bank bank, Credit credit) {
        CreditOffer creditOffer = delegate.toEntity(dtoRequest, client, bank, credit);
        LocalDate startDate = creditOffer.getStartDate();
        LocalDate endDate = creditOffer.getEndDate();
        double total = creditOffer.getTotal();
        int numberOfMonths = (int) ChronoUnit.MONTHS.between(startDate, endDate);
        double interestRate = creditOffer.getCredit().getInterestRate() / 100 / numberOfMonths;
        double bodySum = total / numberOfMonths;

        Queue<Double> interestSums = Stream.iterate(total, currTotal -> currTotal - bodySum)
                .limit(numberOfMonths)
                .map(currTotal -> currTotal * interestRate)
                .collect(Collectors.toCollection(LinkedList::new));

        List<Payment> payments = Stream.iterate(startDate.plusMonths(1),
                date -> date.compareTo(endDate) <= 0,
                date -> date.plusMonths(1))
                .map(date -> Payment.builder()
                        .creditOffer(creditOffer)
                        .date(date)
                        .total(interestSums.element() + bodySum)
                        .bodySum(bodySum)
                        .interestSum(interestSums.remove())
                        .build())
                .collect(Collectors.toList());

        creditOffer.setPayments(payments);

        return creditOffer;
    }
}
