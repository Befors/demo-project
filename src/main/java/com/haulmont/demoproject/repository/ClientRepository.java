package com.haulmont.demoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.haulmont.demoproject.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByEmail(String email);
}
