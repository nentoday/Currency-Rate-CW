package com.example.demo.controller;

import com.example.demo.model.Date;
import org.springframework.ui.Model;
import com.example.demo.dto.CurrencyDto;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
public class ExchangeRateController {
    private CurrencyService currencyService;
    private DateService dateService;

    public ExchangeRateController(CurrencyService currencyService, DateService dateService) {
        this.currencyService = currencyService;
        this.dateService = dateService;
    }

    @GetMapping("/currency/exchange-rate/{currencyId}")
    public String viewExchangeRateForCurrentDay(@PathVariable("currencyId") Long currencyId, Model model) {
        LocalDate rateDate = LocalDate.now();
        Date exchangeRates= dateService.findById(rateDate,currencyId);

        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";

    }




}
