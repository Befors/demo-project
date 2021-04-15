package com.haulmont.demoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements Serializable, Activated {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double limit;
    private float interestRate;
    private boolean active;
}
