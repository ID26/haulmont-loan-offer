package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Client;
import com.haulmont.den26.loanoffer.entities.LoanOffer;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import com.haulmont.den26.loanoffer.repositories.ClientRepository;
import com.haulmont.den26.loanoffer.repositories.LoanOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientsController {

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;
    private final LoanOfferRepository loanOfferRepository;

    @GetMapping
    public String getAll(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "/clients-list";
    }

    @PostMapping
    public String filter(@RequestParam String filter, Model model) {
        List<Client> clients = null;
        if (filter != null && !filter.isEmpty()) {
            clients = clientRepository.findAllByFullName(filter);
        } else {
            clients = clientRepository.findAll();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("filter", filter);
        return "/clients-list";
    }


    @GetMapping("/new")
    public String createClient(@ModelAttribute("client") Client client) {
        return "/new-client";
    }

    @PostMapping("/new")
    public String addClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new-client";
        }
        client.setBank(bankRepository.findAll().get(0));
        Client saveClient = clientRepository.save(client);
        return "redirect:/clients/" + saveClient.getId();
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable("id") UUID id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        model.addAttribute("client", client);
        model.addAttribute("loanOffer", new LoanOffer());
        return "show-client";
    }

    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable("id") UUID id, Model model) {
        Client client = clientRepository.findById(id).get();
        model.addAttribute("client", client);
        return "edit-client";
    }

    @PostMapping("/edit")
    public String updateClient(@ModelAttribute("client") Client client) {
//        UUID id = client.getId();
//        String fullName = client.getFullName();
//        String phone = client.getPhone();
//        String email = client.getEmail();
//        String passportNumber = client.getPassportNumber();

//        boolean isEmailChanged = ((email != null && !email.equals(userEmail))
//                || (userEmail != null && !userEmail.equals(email)));
//
//        if (isEmailChanged) {
//            user.setEmail(email);
//            if (!StringUtils.isEmpty(email)) {
//                user.setActivationCode(UUID.randomUUID().toString());
//            }
//        }
//
//        if (!StringUtils.isEmpty(password)) {
//            user.setPassword(password);
//        }
//
//        userRepository.save(user);
//
//        if (isEmailChanged) {
//            sendEmail(user);
//        }
        Client fromDb = clientRepository.findById(client.getId()).get();
        fromDb.setFullName(client.getFullName()).setEmail(client.getEmail()).setPhone(client.getPhone()).setPassportNumber(client.getPassportNumber());
        clientRepository.save(fromDb);
        return "redirect:/clients/" + client.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") UUID id) {
        clientRepository.deleteById(id);
        return "redirect:/clients/";
    }


}
