package com.haulmont.den26.loanoffer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue
    private UUID id;

    private Long creditLimit;

    private Double percent;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bank_id")
    @ToString.Exclude
    private Bank bank;
}
