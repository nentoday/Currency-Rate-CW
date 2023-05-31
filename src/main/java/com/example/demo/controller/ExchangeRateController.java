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
    private CurrencyService currencyService;
    private DateService dateService;

    public ExchangeRateController(CurrencyService currencyService, DateService dateService) {
        this.currencyService = currencyService;
        this.dateService = dateService;
    }
//    @GetMapping("/currency-rate")
//
//    public List<Date> getCurrencyRates(@RequestParam Long currencyId,
//                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return dateService.getCurrencyRatesByDateRange(currencyId, startDate, endDate);}

//ОЦЕ ЩАСТЯ ПРОСТО ВИВОДТЬ ФОРМУ
    @GetMapping("/currency/exchange-rate")
    public String findExchangeRateForCurrentDay(Model model) {

        List<CurrencyDto> currencies=currencyService.findAllCurrencies();
        model.addAttribute("currencies",currencies);
        return "user-choose";}
    //ОЦЕ ЩАСТЯ В ЦІЙ ФОРМІ ЩОСЬ МАЄ ШУКАТИ І ПЕРЕНАПРАВЛЯТИ
//    @PostMapping("/currency/exchange-rate/search/currencyId")
//    public String findExchangeRateForCurrentDay(LocalDate startDate, Model model){
//        LocalDate startDate1=LocalDate.of(2023,05,20);
//        LocalDate startDate2=LocalDate.now();
//        List<CurrencyDto> currencies=currencyService.findAllCurrencies();
//        model.addAttribute("currencies",currencies);
////        List <Date> exchangeRates = dateService.findExchangeRate(currencyId,startDate1,startDate2);
////        model.addAttribute("exchangeRates", exchangeRates);
//        return "redirect:/currency/{currencyId}/exchange-rate/period";}


  //ОТУТ МАЄ БУТИ ВЕВЕДЕНИЙ ЗА ПЕРІОД
    @GetMapping("/currency/{currencyId}/exchange-rate/month")
    public String viewExchangeRateForPeriod(@PathVariable("currencyId") Long currencyId,
                                            Model model) {
        LocalDate startDate2=LocalDate.now();
        LocalDate startDate1=startDate2.minusMonths(1);
        List <Date> exchangeRates = dateService.findExchangeRate(currencyId,startDate1,startDate2);
        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";}

    @GetMapping("/currency/{currencyId}/exchange-rate/week")
    public String viewExchangeRateForWeek(@PathVariable("currencyId") Long currencyId,
                                            Model model) {
        LocalDate startDate2=LocalDate.now();
        LocalDate startDate1=startDate2.minusWeeks(1);
        List <Date> exchangeRates = dateService.findExchangeRate(currencyId,startDate1,startDate2);
        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";}
    @GetMapping("/currency/{currencyId}/exchange-rate/year")
    public String viewExchangeRateForYear(@PathVariable("currencyId") Long currencyId,
                                            Model model) {
        LocalDate startDate2=LocalDate.now();
        LocalDate startDate1=startDate2.minusYears(1);
        List <Date> exchangeRates = dateService.findExchangeRate(currencyId,startDate1,startDate2);
        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";}

//TODAY ПРАЦЮЄ НЕ ЧІПАТИ
    @GetMapping("/currency/{currencyId}/exchange-rate/today")
    public String viewExchangeRateForCurrentDay(@PathVariable("currencyId") Long currencyId, Model model) {
        LocalDate rateDate = LocalDate.now();
        Date exchangeRates= dateService.findById(rateDate,currencyId);
        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";

    }

}
