package com.haulmont.den26.loanoffer;

import com.haulmont.den26.loanoffer.entities.Bank;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class LoanOfferApplication implements CommandLineRunner {
    private final BankRepository bankRepository;



    public static void main(String[] args) {
        SpringApplication.run(LoanOfferApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Bank haulmontBank = new Bank();
        haulmontBank.setBankName("Haulmont Bank");
        bankRepository.save(haulmontBank);
    }
}
