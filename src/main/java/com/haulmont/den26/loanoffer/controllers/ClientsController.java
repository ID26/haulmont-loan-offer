package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Bank;
import com.haulmont.den26.loanoffer.entities.Client;
import com.haulmont.den26.loanoffer.entities.LoanOffer;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import com.haulmont.den26.loanoffer.repositories.ClientRepository;
import com.haulmont.den26.loanoffer.repositories.CreditRepository;
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
@RequestMapping("/clients")
public class ClientsController {

    private Bank bank = new Bank();

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;
    private final CreditRepository creditRepository;

    public ClientsController(ClientRepository clientRepository, BankRepository bankRepository, CreditRepository creditRepository) {
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
        this.creditRepository = creditRepository;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "bankId", required = false) UUID id, Model model) {
        bank = bankRepository.findById(id).get();
        List<Client> clients = clientRepository.findAllByBank(bank);
        model.addAttribute("clients", clients);
        model.addAttribute("bank", bank);
        return "/client/clients-list";
    }

    @PostMapping()
    public String filter(@RequestParam(name = "filter") String filter,
                         Model model) {
        List<Client> clients;
        if (filter != null && !filter.isEmpty()) {
            clients = clientRepository.findAllByFullName(filter);
        } else {
            clients = clientRepository.findAll();
        }
        model.addAttribute("bankName", bank.getBankName());
        model.addAttribute("bank", bank);
        model.addAttribute("clients", clients);
        model.addAttribute("filter", filter);
        return "/client/clients-list";
    }


    @GetMapping("/new")
    public String createClient(@RequestParam(value = "bankId", required = false) UUID id,
                               @ModelAttribute("client") Client client, Model model) {
        model.addAttribute("path", "new" );
        model.addAttribute("bank", bank);
        return "/client/new-client";
    }

    @PostMapping("/new")
    public String addClient(@RequestParam(value = "bankId", required = false) UUID id,
                            @ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "new" );
            model.addAttribute("bank", bank);
            return "/client/new-client";
        }
//        model.addAttribute("bank", bank);
        client.setBank(bankRepository.findAll().get(0));
        Client saveClient = clientRepository.save(client);
        return "redirect:/clients/" + saveClient.getId();
    }

    @GetMapping("/{id}")
    public String showClient(
                             @PathVariable("id") UUID id,
                             Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        model.addAttribute("client", client);
        model.addAttribute("loanOffer", new LoanOffer());
        model.addAttribute("credits", creditRepository.findAll());
        model.addAttribute("bank", bank);
        return "/client/show-client";
    }

    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable("id") UUID id, Model model) {
        Client client = clientRepository.findById(id).get();
        model.addAttribute("client", client);
        model.addAttribute("path", "edit");
        model.addAttribute("bank", bank);
        return "/client/edit-client";
    }

    @PostMapping("/edit")
    public String updateClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "edit" );
            model.addAttribute("bank", bank);
            return "/client/edit-client";
        }

        Client fromDb = clientRepository.findById(client.getId()).get();
        fromDb.setFullName(client.getFullName())
                .setEmail(client.getEmail())
                .setPhone(client.getPhone())
                .setPassportNumber(client.getPassportNumber())
                .setBank(client.getBank());
        clientRepository.save(fromDb);
        return "redirect:/clients/" + client.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") UUID id) {
        clientRepository.deleteById(id);
        return "redirect:/clients?bankId=" + bank.getId();
    }
}
