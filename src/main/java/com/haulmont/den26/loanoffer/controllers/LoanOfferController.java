package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Client;
import com.haulmont.den26.loanoffer.entities.Credit;
import com.haulmont.den26.loanoffer.entities.LoanOffer;
import com.haulmont.den26.loanoffer.entities.PaymentScheduler;
import com.haulmont.den26.loanoffer.repositories.ClientRepository;
import com.haulmont.den26.loanoffer.repositories.CreditRepository;
import com.haulmont.den26.loanoffer.repositories.LoanOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("loan-offer")
public class LoanOfferController {
    private Client client = new Client();

    private final LoanOfferRepository loanOfferRepository;
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;

    public LoanOfferController(LoanOfferRepository loanOfferRepository, ClientRepository clientRepository, CreditRepository creditRepository) {
        this.loanOfferRepository = loanOfferRepository;
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "clientId", required = false) UUID id, Model model) {
        client = clientRepository.findById(id).get();
        List<LoanOffer> loanOffers = loanOfferRepository.findAllByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("loanOffers", loanOffers);
        return "/loanoffer/loanoffer-list";
    }

    @PostMapping()
    public String filter(@RequestParam(name = "filter") String filter,
                         Model model) {
        List<LoanOffer> loanOffers;
        if (filter != null && !filter.isEmpty()) {
            loanOffers = loanOfferRepository.findAllByAmountCredit(Long.parseLong(filter));
        } else {
            loanOffers = loanOfferRepository.findAll();
        }
        model.addAttribute("client", client);
        model.addAttribute("loanOffers", loanOffers);
        model.addAttribute("filter", filter);
        return "/loanoffer/loanoffer-list";
    }


//    @GetMapping("/new")
//    public String createLoanOffer(@RequestParam(value = "clientId", required = false) UUID id,
//                               @ModelAttribute("loanOffer") LoanOffer loanOffer, Model model) {
//        model.addAttribute("path", "new" );
//        model.addAttribute("client", client);
//        return "/loanoffer/new-loanoffer";
//    }
//
//    @PostMapping("/new")
//    public String addLoanOffer(@RequestParam(value = "clientId", required = false) UUID id,
//                            @ModelAttribute("loanOffer") @Valid LoanOffer loanOffer,
//                            BindingResult bindingResult,
//                            Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("path", "new" );
//            model.addAttribute("client", client);
//            return "/loanoffer/new-loanoffer";
//        }
//        loanOffer.setClient(clientRepository.findById(id).get());
//        LoanOffer saveLoanoffer =loanOfferRepository.save(loanOffer);
//        return "redirect:/loan-offer/" + loanOffer.getId();
//    }
//
//    @GetMapping("/{id}")
//    public String showLoanOffer(
//            @PathVariable("id") UUID id,
//            Model model) {
//        LoanOffer loanOffer = loanOfferRepository.findById(id).orElse(null);
//        model.addAttribute("client", loanOffer);
//        model.addAttribute("loanOffer", new LoanOffer());
//        model.addAttribute("credits", creditRepository.findAll());
//        model.addAttribute("CLIENT", client);
//        return "/loanoffer/show-loanoffer";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editLoanoffer(@PathVariable("id") UUID id, Model model) {
//        Client client = clientRepository.findById(id).get();
//        model.addAttribute("client", client);
//        model.addAttribute("path", "edit");
//        model.addAttribute("client", client);
//        return "/loanoffer/edit-loanoffer";
//    }
//
//    @PostMapping("/edit")
//    public String updateLoanoffer(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
//                                  Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("path", "edit" );
//            model.addAttribute("client", client);
//            return "/loanoffer/edit-loanoffer";
//        }
//
//        Client fromDb = clientRepository.findById(client.getId()).get();
//        fromDb.setFullName(client.getFullName())
//                .setEmail(client.getEmail())
//                .setPhone(client.getPhone())
//                .setPassportNumber(client.getPassportNumber())
//                .setBank(client.getBank());
//        clientRepository.save(fromDb);
//        return "redirect:/loan-offer/" + client.getId();
//    }
//
//    @PostMapping("/{id}/delete")
//    public String deleteLoanoffer(@PathVariable("id") UUID id) {
//        clientRepository.deleteById(id);
//        return "redirect:/loan-offer?clientId=" + client.getId();
//    }
}
