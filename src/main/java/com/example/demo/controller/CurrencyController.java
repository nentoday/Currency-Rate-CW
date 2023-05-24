package com.example.demo.controller;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;
import com.example.demo.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/currency/new")
    public String createCurrencyForm(Model model){
        Currency currency= new Currency();
        model.addAttribute("currency",currency);
        return "currency-create.html";

    }
    @PostMapping("/currency/new")
    public String saveCurrency(@ModelAttribute("currency") Currency currency){
        currencyService.saveCurrency(currency);
        return "redirect:/currency";
    }
    @GetMapping("/currency/{currencyId}/edit")
    public String editCurrencyForm(@PathVariable("currencyId") Long currencyId,Model model){
        CurrencyDto currency=currencyService.findByClubId(currencyId);
        model.addAttribute("currency", currency);
        return "currency-edit";

    }
    @PostMapping("/currency/{currencyId}/edit")
    public String updateCurrency(@PathVariable("currencyId") Long currencyId, @ModelAttribute("currency") CurrencyDto currency){
        currency.setId(currencyId);
        currencyService.updateCurrency(currency);
        return"redirect:/currency";
    }


}
