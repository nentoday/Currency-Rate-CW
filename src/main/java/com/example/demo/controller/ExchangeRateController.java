package com.example.demo.controller;

import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.model.Currency;
import com.example.demo.model.Date;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import com.example.demo.dto.CurrencyDto;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DateService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExchangeRateController {
    private DateService dateService;

    public ExchangeRateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping("/currency/{currencyId}/exchange-rate")
    public String viewExchangeRate(@PathVariable("currencyId") Long currencyId,
                                   @RequestParam(value = "period", required = false) String period,
                                   Model model) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();
        if (period != null) {
            switch (period) {
                case "week":
                    startDate = endDate.minusWeeks(1);
                    break;
                case "month":
                    startDate = endDate.minusMonths(1);
                    break;
                case "year":
                    startDate = endDate.minusYears(1);
                    break;
                default:
                    startDate = endDate;
                    break;
            }
        } else {
            startDate = endDate;
        }

        List<Date> exchangeRates = dateService.findExchangeRate(currencyId, startDate, endDate);
        model.addAttribute("exchangeRates", exchangeRates);

        return "exchange-rate";
    }
    @GetMapping("/currency/{currencyId}/exchange-rate/today")
    public String viewExchangeRateForCurrentDay(@PathVariable("currencyId") Long currencyId, Model model) {
        LocalDate rateDate = LocalDate.now();
        Date exchangeRates= dateService.findById(rateDate,currencyId);
        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";

    }
}
