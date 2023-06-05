package com.example.demo.controller;

import com.example.demo.model.ExchangeRate;
import com.example.demo.model.PeriodDates;
import com.example.demo.services.PeriodDatesService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import com.example.demo.services.ExchangeRateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class PeriodDatesController {
    private ExchangeRateService exchangeRateService;
    private PeriodDatesService periodDatesService;


    @GetMapping("/currency/{currencyId}/exchange-rate")
    public String viewExchangeRate(@PathVariable("currencyId") Long currencyId,
                                   @RequestParam(value = "period", required = false) String period,
                                   ModelMap model) {

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
                    model.addAttribute("startDate", startDate);
                    break;
            }
        } else {
            startDate = endDate;
        }
        PeriodDates periodDates = PeriodDates.builder()
                .startDate(startDate)
                .endDate(endDate)
                .period(period)
                .build();
        periodDatesService.savePeriodDate(periodDates);

        List<ExchangeRate> exchangeRates = exchangeRateService.findExchangeRate(currencyId, startDate, endDate);
        model.addAttribute("exchangeRates", exchangeRates);
        return "userview/exchange-rate";
    }
    @GetMapping("/currency/{currencyId}/exchange-rate/today")
    public String viewExchangeRateForCurrentDay(@PathVariable("currencyId") Long currencyId, Model model) {
        LocalDate rateDate = LocalDate.now();
        List<ExchangeRate> exchangeRates= exchangeRateService.findById(rateDate,currencyId);
        model.addAttribute("exchangeRates", exchangeRates);
        return "userview/exchange-rate";
    }
}
