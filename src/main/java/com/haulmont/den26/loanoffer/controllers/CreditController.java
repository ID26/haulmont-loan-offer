package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Credit;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import com.haulmont.den26.loanoffer.repositories.CreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
@AllArgsConstructor
public class CreditController {
    private final CreditRepository creditRepository;
    private final BankRepository bankRepository;

    @GetMapping
    public String getAll(Model model) {
        List<Credit> credits = creditRepository.findAll();
        model.addAttribute("credits", credits);
        model.addAttribute("credit", new Credit());
        return "credit/credits-list";
    }

    @PostMapping()
    public String filter(@RequestParam String filter, Model model) {
        List<Credit> credits;
        if (filter != null && !filter.isEmpty()) {
            credits = creditRepository.findAllByName(filter);
        } else {
            credits = creditRepository.findAll();
        }
        model.addAttribute("credits", credits);
        model.addAttribute("credit", new Credit());
        model.addAttribute("filter", filter);

            return "credit/credits-list";
    }

    @PostMapping("/new")
    public String createCredit(@ModelAttribute ("credit") Credit credit) {
        credit.setBank(bankRepository.findAll().get(0));
        creditRepository.save(credit);
        return "redirect:/credits";
    }

    @GetMapping("/{id}")
    public String showCredit(@PathVariable("id") UUID id, Model model) {
        Credit credit = creditRepository.findById(id).orElse(null);
        model.addAttribute("credit", credit);
        return "credit/show-credit";
    }

    @GetMapping("/{id}/edit")
    public String editCredit(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("credit", creditRepository.findById(id).get());
        return "credit/edit-credit";
    }

    @PostMapping("/edit")
    public String updateCredit(@ModelAttribute("credit") Credit credit) {
        Credit fromDb = creditRepository.findById(credit.getId()).get();
        fromDb.setName(credit.getName())
                .setCreditLimit(credit.getCreditLimit())
                .setPercent(credit.getPercent());
        creditRepository.save(fromDb);
        return "redirect:/credits/" + credit.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") UUID id) {
        creditRepository.deleteById(id);
        return "redirect:/credits/";
    }
}
