package com.haulmont.den26.loanoffer.repositories;

import com.haulmont.den26.loanoffer.entities.Client;
import com.haulmont.den26.loanoffer.entities.LoanOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanOfferRepository extends JpaRepository<LoanOffer, UUID> {
    List<LoanOffer> findAllByClient(Client client);

    List<LoanOffer> findAllByAmountCredit(Long filter);
}
