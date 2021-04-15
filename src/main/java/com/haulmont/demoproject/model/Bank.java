package com.haulmont.demoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank implements Serializable, Activated {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToMany
    @JoinTable(name = "client_bank",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> clients;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bank_credit",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "credit_id"))
    private Set<Credit> credits;

    private boolean active;

    public void addClient(Client client) {
        clients.add(client);
    }

    public boolean containsCredit(Credit credit) {
        return credits.contains(credit);
    }

    @PostLoad
    public void postLoad() {
        credits = credits.stream()
                .filter(Credit::isActive)
                .collect(Collectors.toSet());
    }
}
