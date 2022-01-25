package com.haulmont.den26.loanoffer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Credit {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Long creditLimit;

    private Double percent;

//    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
////    @JoinColumn(name = "credit_id")
//    private LoanOffer loanOffer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bank_id")
    @ToString.Exclude
    private Bank bank;
}
