package com.haulmont.den26.loanoffer.controllers;

import com.haulmont.den26.loanoffer.entities.Client;
import com.haulmont.den26.loanoffer.entities.Credit;
import com.haulmont.den26.loanoffer.entities.LoanOffer;
import com.haulmont.den26.loanoffer.entities.PaymentScheduler;
import com.haulmont.den26.loanoffer.repositories.CreditRepository;
import com.haulmont.den26.loanoffer.repositories.LoanOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("loan-offer")
@AllArgsConstructor
public class LoanOfferController {
    private final LoanOfferRepository loanOfferRepository;
    private final CreditRepository creditRepository;

//    @GetMapping
//    public String getAll(Model model) {
//        List<Client> clients = clientRepository.findAll();
//        model.addAttribute("clients", clients);
//        return "/clients-list";
//    }

//    @PostMapping
//    public String filter(@RequestParam String filter, Model model) {
//        List<Client> clients;
//        if (filter != null && !filter.isEmpty()) {
//            clients = clientRepository.findAllByFullName(filter);
//        } else {
//            clients = clientRepository.findAll();
//        }
//        model.addAttribute("clients", clients);
//        model.addAttribute("filter", filter);
//        return "/clients-list";
//    }


//    @GetMapping("/new")
//    public String createClient(@ModelAttribute("client") Client client, Model model) {
//        model.addAttribute("path", "new" );
//        return "/new-client";
//    }

    @PostMapping("/new")
    public String addClient(@ModelAttribute("client")  Client client,
                            @ModelAttribute("credit") Credit credit,
                            @ModelAttribute("loanOffer") @Valid LoanOffer loanOffer,
                            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/show-client";
        }
        Credit creditFromDb = creditRepository.findById(credit.getId()).get();
        loanOffer.setCredit(credit);
        LoanOffer savedOffer = loanOfferRepository.save(loanOffer);
        if (loanOffer.getAmountCredit() <= creditFromDb.getCreditLimit()) {
            Long amountCredit = loanOffer.getAmountCredit();
            Integer quantityMonth = loanOffer.getQuantityMonth();
            Double percent = creditFromDb.getPercent();
            List<PaymentScheduler> paymentSchedulerList = returnPaymentScheduler(amountCredit, quantityMonth, percent, savedOffer);
            savedOffer.setPaymentScheduler(paymentSchedulerList);
            loanOfferRepository.save(savedOffer);
        }

        return "/show-client";
    }

    private List<PaymentScheduler> returnPaymentScheduler(Long amountCredit, Integer quantityMonth, Double percent,
                                                          LoanOffer loanOffer) {
        List<PaymentScheduler> paymentSchedulerList = new LinkedList<>();
        LocalDate now = LocalDate.now();
        double draft = amountCredit;
        for (int i = 0; i < quantityMonth; i++) {
            PaymentScheduler paymentScheduler = new PaymentScheduler();
            double body = draft/(quantityMonth - i);
            double amountPercent = (draft * (percent / 100)) / 12;
            paymentScheduler.setAmountPercent(amountPercent);
            paymentScheduler.setAmountBody(body);
            paymentScheduler.setAmountPay(body + amountPercent);
            paymentScheduler.setPaymentDay(now.plusMonths(i));
            paymentScheduler.setLoanOffer(loanOffer);
            draft -= body;
        }
        return paymentSchedulerList;
    }
//
//    @GetMapping("/{id}")
//    public String showClient(@PathVariable("id") UUID id, Model model) {
//        Client client = clientRepository.findById(id).orElse(null);
//        model.addAttribute("client", client);
//        model.addAttribute("loanOffer", new LoanOffer());
//        return "show-client";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editClient(@PathVariable("id") UUID id, Model model) {
//        Client client = clientRepository.findById(id).get();
//        model.addAttribute("client", client);
//        model.addAttribute("path", "edit");
//        return "edit-client";
//    }
//
//    @PostMapping("/edit")
//    public String updateClient(@ModelAttribute("client") Client client) {
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
//        Client fromDb = clientRepository.findById(client.getId()).get();
//        fromDb.setFullName(client.getFullName())
//                .setEmail(client.getEmail())
//                .setPhone(client.getPhone())
//                .setPassportNumber(client.getPassportNumber())
//                .setBank(client.getBank());
//        clientRepository.save(fromDb);
//        return "redirect:/clients/" + client.getId();
//    }
//
//    @PostMapping("/{id}/delete")
//    public String deleteClient(@PathVariable("id") UUID id) {
//        clientRepository.deleteById(id);
//        return "redirect:/clients/";
//    }

}
