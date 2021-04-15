package com.haulmont.demoproject.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.haulmont.demoproject.model.Credit;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {

    @Query("select c from Credit c where c.id in :ids and c.active = true")
    Set<Credit> findAllActiveByIds(Iterable<UUID> ids);

    List<Credit> findAllByActiveIsTrue(Sort sort);
}
