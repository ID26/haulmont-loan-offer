package com.haulmont.den26.loanoffer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanOffer {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
//    @JoinColumn(name = "credit_id")
    private Credit credit;

    @OneToOne
    private Client client;

    private Long amountCredit;

    private Integer quantityMonth;

    @OneToMany
    private List<PaymentScheduler> paymentScheduler;
}