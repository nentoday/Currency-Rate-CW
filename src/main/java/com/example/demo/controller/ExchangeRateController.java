package com.example.demo.controller;

import com.example.demo.model.Date;
import org.springframework.ui.Model;
import com.example.demo.services.DateService;
import org.springframework.stereotype.Controller;
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

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate;
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
                case "today":
                    startDate = endDate;
                    model.addAttribute("startDate",startDate);
                    break;
            }
        }
        else startDate=endDate;

        List<Date> exchangeRates = dateService.findExchangeRate(currencyId, startDate, endDate);
        model.addAttribute("exchangeRates", exchangeRates);
        return "userview/exchange-rate";
    }
    @GetMapping("/currency/{currencyId}/exchange-rate/today")
    public String viewExchangeRateForCurrentDay(@PathVariable("currencyId") Long currencyId, Model model) {
        LocalDate rateDate = LocalDate.now();
        List<Date> exchangeRates= dateService.findById(rateDate,currencyId);
        model.addAttribute("exchangeRates", exchangeRates);
        return "userview/exchange-rate";
    }
}
