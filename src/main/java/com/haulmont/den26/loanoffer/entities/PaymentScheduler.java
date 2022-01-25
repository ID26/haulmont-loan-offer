package com.haulmont.den26.loanoffer.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class PaymentScheduler {
    @Id
    @GeneratedValue
    private UUID id;
    private Long amountPay;
    private Long amountBody;
    private Long amountPercent;
}
