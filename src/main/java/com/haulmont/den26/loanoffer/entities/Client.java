package com.haulmont.den26.loanoffer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Client {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Client name can't be empty")
    @Size(min = 2, max = 255, message = "Full name mast have from 2 to 255 characters!")
    private String fullName;

    @NotBlank(message = "Phone can't be empty")
    @Size(min = 5, max = 20, message = "Phone mast have from 5 to 20 characters!")
    private String phone;

    @Email(message = "Email isn't correct")
    @NotBlank(message = "Email can't be empty")
    @Size(min = 5, max = 55, message = "Email mast have from 5 to 55 characters!")
    private String email;

    @NotBlank(message = "Passport number can't be empty")
    @Size(min = 6, max = 20, message = "Passport number mast have from 6 to 20 characters!")
    private String passportNumber;

//    @OneToMany
//    private List<LoanOffer> loanOffers;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bank_id")
    @ToString.Exclude
    private Bank bank;

    public Client(String fullName, String phone, String email, String passportNumber) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.passportNumber = passportNumber;
    }
}
