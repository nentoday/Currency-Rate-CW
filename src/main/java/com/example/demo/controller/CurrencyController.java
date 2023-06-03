package com.example.demo.controller;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;
import com.example.demo.model.Date;
import com.example.demo.services.CurrencyService;
import com.example.demo.services.DateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurrencyController {
    private CurrencyService currencyService;
    private DateService dateService;
    @Autowired

    public CurrencyController(CurrencyService currencyService, DateService dateService) {
        this.currencyService = currencyService;
        this.dateService =dateService;
    }
  //User
    @GetMapping("/currency")
    public String listCurrencies(Model model){
        List<CurrencyDto> currencies=currencyService.findAllCurrencies();
        model.addAttribute("currencies",currencies);
        return "userview/currencies-list";
    }

    @GetMapping("/currency/{currencyId}")
    public String currencyDetail(@PathVariable("currencyId") Long currencyId, Model model){
        CurrencyDto currencyDto = currencyService.findByCurrencyId(currencyId);
        List<Date> exchangeRate = dateService.findAllDates(currencyId);
        model.addAttribute("exchangeRate",exchangeRate);
        model.addAttribute("currency", currencyDto);
        return "userview/currency-detail";
    }

    @GetMapping("/currency/search")
    public String searchCurrency(@RequestParam(value = "query") String query, Model model) {
        try {
            List<CurrencyDto> currencies = currencyService.searchCurrency(query);
            model.addAttribute("currencies", currencies);

        } catch (DataNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "userview/currencies-list";
    }
    @GetMapping("/currency/sort")
    public String getCurrencies(@RequestParam(value = "title", required = false) String title, Model model) {
        List<Currency> currencies = null;
        switch (title) {
            case "name":
                currencies = currencyService.sortByTitle(title);
                break;
            case "country":
                currencies = currencyService.sortByCountry(title);
                break;
        }
        model.addAttribute("currencies", currencies);
        return "userview/currencies-list";
    }

//Admin

    @GetMapping("/currency/new")
    public String createCurrencyForm(Model model){
        Currency currency= new Currency();
        model.addAttribute("currency",currency);
        return "admin/currency-create";

    }
    @PostMapping("/currency/new")
    public String saveCurrency(@Valid @ModelAttribute("currency") CurrencyDto currencyDto,
                               BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("currency", currencyDto);
            return "admin/currency-create";
        }
        currencyService.saveCurrency(currencyDto);
        return "redirect:/currency";
    }
    @GetMapping("/currency/{currencyId}/edit")
    public String editCurrencyForm(@PathVariable("currencyId") Long currencyId,Model model){
        CurrencyDto currency=currencyService.findByCurrencyId(currencyId);
        model.addAttribute("currency", currency);
        return "admin/currency-edit";

    }
    @PostMapping("/currency/{currencyId}/edit")
    public String updateCurrency(@PathVariable("currencyId") Long currencyId,
                                 @Valid @ModelAttribute("currency") CurrencyDto currency,
                                 BindingResult result){
        if (result.hasErrors()){
            return "admin/currency-edit";
        }
        currency.setId(currencyId);
        currencyService.updateCurrency(currency);
        return"redirect:/currency";
    }

    @GetMapping("/currency/{currencyId}/delete")
    public String showDeleteConfirmation(@PathVariable("currencyId") Long currencyId, Model model) {
        CurrencyDto currency = currencyService.findByCurrencyId(currencyId);
        model.addAttribute("currency", currency);
        return "admin/currency-delete";
    }

    @PostMapping("/currency/{currencyId}/delete")
    public String deleteCurrency(@PathVariable("currencyId") Long currencyId) {
        currencyService.delete(currencyId);
        return "redirect:/currency";
    }


}
