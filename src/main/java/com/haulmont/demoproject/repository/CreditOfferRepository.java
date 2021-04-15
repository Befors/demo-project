package com.haulmont.demoproject.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.haulmont.demoproject.model.Client;
import com.haulmont.demoproject.model.CreditOffer;

import java.util.List;
import java.util.UUID;

public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {

    List<CreditOffer> findAllByClient(Client client, Sort sort);
}
