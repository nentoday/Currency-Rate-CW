package com.example.demo.controller;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CurrencyController {
    private CurrencyService currencyService;
    @Autowired

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public String listCurrencies(Model model){
        List<CurrencyDto> currencies=currencyService.findAllCurrencies();
        model.addAttribute("currencies",currencies);
        return "currencies-list";
    }
}
//import com.example.demo.repository.CurrencyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.ui.Model;
//import java.util.List;
//
//import java.util.Currency;
//
//
//@Controller
//public class CurrencyController {
//    @Autowired
//    private CurrencyRepository currencyRepository;
//    @GetMapping("/")
//    public String showcurrency(Model model){
//        List <Currency> currencies = currencyRepository.findDistinctCurrency();
//        return "home";
//    }
//    @PostMapping("/")
//    public String GetCurrencyData(
//        @RequestParam String currency,
//        @RequestParam String dayFrom,
//        @RequestParam String dayTo,
//        Model model){
//        // Обробка запиту та передача даних на сторінку
//        model.addAttribute("currency", currency);
//        model.addAttribute("dayFrom", dayFrom);
//        model.addAttribute("dayTo", dayTo);
//        return "home";
//    }
//}
