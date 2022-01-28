package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Bank;
import com.haulmont.den26.loanoffer.entities.Credit;
import com.haulmont.den26.loanoffer.entities.LoanOffer;
import com.haulmont.den26.loanoffer.repositories.BankRepository;
import com.haulmont.den26.loanoffer.repositories.CreditRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private Bank bank = new Bank();
    private final CreditRepository creditRepository;
    private final BankRepository bankRepository;

    public CreditController(CreditRepository creditRepository, BankRepository bankRepository) {
        this.creditRepository = creditRepository;
        this.bankRepository = bankRepository;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "bankId", required = false) UUID id, Model model) {
        bank = bankRepository.findById(id).get();
        List<Credit> credits = creditRepository.findAllByBank(bank);
        model.addAttribute("credits", credits);
        model.addAttribute("bank", bank);
        return "/credit/credits-list";
    }

    @PostMapping()
    public String filter(@RequestParam(name = "filter") String filter,
                         Model model) {
        List<Credit> credits;
        if (filter != null && !filter.isEmpty()) {
            credits = creditRepository.findAllByName(filter);
        } else {
            credits = creditRepository.findAll();
        }
        model.addAttribute("bankName", bank.getBankName());
        model.addAttribute("bank", bank);
        model.addAttribute("credits", credits);
        model.addAttribute("filter", filter);
        return "/credit/credits-list";
    }


    @GetMapping("/new")
    public String createCredit(@RequestParam(value = "bankId", required = false) UUID id,
                               @ModelAttribute("credit") Credit credit, Model model) {
        model.addAttribute("path", "new" );
        model.addAttribute("bank", bank);
        return "/credit/new-credit";
    }

    @PostMapping("/new")
    public String addCredit(/*@RequestParam(value = "bankId", required = false) UUID id,*/
                            @ModelAttribute("credit") @Valid Credit credit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "new" );
            model.addAttribute("bank", bank);
            return "/credit/new-credit";
        }
        credit.setBank(bankRepository.findAll().get(0));
        Credit saveCredit = creditRepository.save(credit);
        return "redirect:/credits/" + saveCredit.getId();
    }

    @GetMapping("/{id}")
    public String showCredit(
            @PathVariable("id") UUID id,
            Model model) {
        Credit credit = creditRepository.findById(id).orElse(null);
        model.addAttribute("credit", credit);
        model.addAttribute("loanOffer", new LoanOffer());
        model.addAttribute("credits", creditRepository.findAll());
        model.addAttribute("bank", bank);
        return "/credit/show-credit";
    }

    @GetMapping("/{id}/edit")
    public String editCredit(@PathVariable("id") UUID id, Model model) {
        Credit credit = creditRepository.findById(id).get();
        model.addAttribute("credit", credit);
        model.addAttribute("path", "edit");
        model.addAttribute("bank", bank);
        return "/credit/edit-credit";
    }

    @PostMapping("/edit")
    public String updateCredit(@ModelAttribute("credit") @Valid Credit credit, BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("path", "edit" );
            model.addAttribute("bank", bank);
            return "/credit/edit-credit";
        }

        Credit fromDb = creditRepository.findById(credit.getId()).get();
        fromDb.setName(credit.getName())
                .setPercent(credit.getPercent())
                .setCreditLimit(credit.getCreditLimit())
                .setBank(credit.getBank());
        creditRepository.save(fromDb);
        return "redirect:/credits/" + credit.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteCredit(@PathVariable("id") UUID id) {
        creditRepository.deleteById(id);
        return "redirect:/credits?bankId=" + bank.getId();
    }
}
