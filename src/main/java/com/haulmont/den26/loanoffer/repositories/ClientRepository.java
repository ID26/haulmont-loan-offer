package com.haulmont.den26.loanoffer.repositories;

import com.haulmont.den26.loanoffer.entities.Bank;
import com.haulmont.den26.loanoffer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllByFullName(String filter);

    List<Client> findAllByBank(Bank bank);
}
