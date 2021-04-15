package com.haulmont.demoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditOffer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    private double total;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Payment> payments;

    @PrePersist
    public void prePersist() {
        bank.addClient(client);
    }
}
