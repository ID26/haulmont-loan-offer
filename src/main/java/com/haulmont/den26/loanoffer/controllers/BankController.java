package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Bank;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/banks")
@AllArgsConstructor
public class BankController {
    private final BankRepository bankRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("banks", bankRepository.findAll());
        model.addAttribute("filter", "");
        return "bank/bank-list";
    }

    @PostMapping
    public String filter(@RequestParam String filter, Model model){
        List<Bank> banks;
        if (filter != null && !filter.isEmpty()) {
            banks = bankRepository.findAllByBankName(filter);
        } else {
            banks = bankRepository.findAll();
        }
        model.addAttribute("banks", banks);
        model.addAttribute("filter", filter);
        return "bank/bank-list";
    }

    @GetMapping("/new")
    public String createBank(Model model) {
        model.addAttribute("bank", new Bank());
        model.addAttribute("path", "new");
        return "/bank/new-bank";
    }

    @PostMapping("/new")
    public String saveBank(@ModelAttribute("bank") @Valid Bank bank, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "new");
            return "/bank/new-bank";
        }
        Bank saveBank = bankRepository.save(bank);
        return "redirect:/banks/" + saveBank.getId();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("bank", bankRepository.findById(id).get());
        return "/bank/show-bank";
    }

    @GetMapping("/{id}/edit")
    public String editBank(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("bank", bankRepository.findById(id).get());
        model.addAttribute("path", "edit");
        return "/bank/edit-bank";
    }

    @PostMapping("/edit")
    public String updateBank(@ModelAttribute("bank") @Valid Bank bank, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "edit");
            return "/bank/new-bank";
        }
        Bank bankFromDb = bankRepository.findById(bank.getId()).get();
        if (!bank.getBankName().equals(bankFromDb.getBankName())) {
            bankFromDb.setBankName(bank.getBankName());
            bankRepository.save(bankFromDb);
        }
        return "redirect:/banks/" + bank.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        bankRepository.deleteById(id);
        return "redirect:/banks/";
    }
}
