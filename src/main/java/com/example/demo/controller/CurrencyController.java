package com.example.demo.controller;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;
import com.example.demo.services.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/currency/{currencyId}")
    public String currencyDetail(@PathVariable("currencyId") Long currencyId, Model model){
        CurrencyDto currencyDto = currencyService.findByClubId(currencyId);
        model.addAttribute("currency", currencyDto);
        return "currency-detail";
    }

    @PostMapping("/currency/new")
    public String saveCurrency(@Valid @ModelAttribute("currency") CurrencyDto currencyDto,
                               BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("currency", currencyDto);
            return "currency-create";
        }
        currencyService.saveCurrency(currencyDto);
        return "redirect:/currency";
    }
    @GetMapping("/currency/{currencyId}/edit")
    public String editCurrencyForm(@PathVariable("currencyId") Long currencyId,Model model){
        CurrencyDto currency=currencyService.findByClubId(currencyId);
        model.addAttribute("currency", currency);
        return "currency-edit";

    }
    @PostMapping("/currency/{currencyId}/edit")
    public String updateCurrency(@PathVariable("currencyId") Long currencyId,
                                 @Valid @ModelAttribute("currency") CurrencyDto currency,
                                 BindingResult result){
        if (result.hasErrors()){
            return "currency-edit";
        }
        currency.setId(currencyId);
        currencyService.updateCurrency(currency);
        return"redirect:/currency";
    }
    @GetMapping("currency/{currencyId}/delete")
    public String deleteCurrency(@PathVariable("currencyId") Long currencyId){
        currencyService.delete(currencyId);
        return "redirect:/currency";

    }
    @GetMapping("/currency/search")
    public String searchCurrency(@RequestParam(value = "query") String query, Model model){
        List<CurrencyDto> currencies = currencyService.searchCurrency(query);
        model.addAttribute("currencies", currencies);
        return "currencies-list";


    }

}
