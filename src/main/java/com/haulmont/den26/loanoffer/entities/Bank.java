package com.haulmont.den26.loanoffer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Bank name can't be empty")
    @Size(min = 2, max = 255, message = "Bank name mast have from 2 to 255 characters!")
    private String bankName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_id")
    private Map<UUID, Credit> creditsScheduler = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_id")
    private Map<UUID, Client> clientsScheduler = new HashMap<>();
}
