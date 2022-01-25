package com.haulmont.den26.loanoffer.repositories;

import com.haulmont.den26.loanoffer.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
    List<Credit> findAllByName(String filter);
}
