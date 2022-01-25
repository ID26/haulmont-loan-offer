package com.haulmont.den26.loanoffer.repositories;

import com.haulmont.den26.loanoffer.entities.PaymentScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymantSchedulerRepository extends JpaRepository<PaymentScheduler, UUID> {
}
