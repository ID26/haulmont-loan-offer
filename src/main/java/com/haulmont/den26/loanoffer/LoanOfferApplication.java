package com.haulmont.den26.loanoffer;

import com.haulmont.den26.loanoffer.entities.Bank;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;

@SpringBootApplication
@AllArgsConstructor
public class LoanOfferApplication implements CommandLineRunner {
    private final BankRepository bankRepository;
    private final JdbcTemplate jdbcTemplate;



    public static void main(String[] args) {
        SpringApplication.run(LoanOfferApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Bank haulmontBank = new Bank();
//        haulmontBank.setBankName("Haulmont Bank");
//        bankRepository.save(haulmontBank);
//        Thread.sleep(10000);
//
//        jdbcTemplate.query("insert into bank (bank_name, id) values ('Haulmont Bank', " +
//                "'A0EEBC99-9C0B-4EF8-BB6D-6BB9BD380A11')", new BeanPropertyRowMapper<>(Bank.class));
    }
}
