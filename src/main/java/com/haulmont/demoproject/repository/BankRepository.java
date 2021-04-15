package com.haulmont.demoproject.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.haulmont.demoproject.model.Bank;

import java.util.List;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {

    List<Bank> findAllByActiveIsTrue(Sort sort);
}
