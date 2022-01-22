package com.haulmont.den26.loanoffer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_id")
    private List<Credit> creditsScheduler;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_id")
    private List<Client> clientsScheduler;
}
