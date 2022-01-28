package com.haulmont.den26.loanoffer.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class PaymentScheduler {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate paymentDay;
    private Double amountPay;
    private Double amountBody;
    private Double amountPercent;

    @ManyToOne
    private LoanOffer loanOffer;
}
//    o График платежей
//        ▪ Дата платежа
//        ▪ Сумма платежа
//        ▪ Сумма гашения тела кредита
//        ▪ Сумма гашения процентов