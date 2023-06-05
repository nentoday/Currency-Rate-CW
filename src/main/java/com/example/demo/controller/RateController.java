package com.example.demo.controller;

import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.model.ExchangeRate;
import com.example.demo.services.ExchangeRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RateController {
    private ExchangeRateService exchangeRateService;
    @Autowired
    public RateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/rate/{currencyId}/new")
    public String createRateForm(@PathVariable("currencyId") Long currencyId, Model model){
        ExchangeRate date = new ExchangeRate();
        model.addAttribute("currencyId", currencyId);
        model.addAttribute("date", date);
        return "admin/rate-create";
    }

    @PostMapping("/rate/{currencyId}")
    public String createRate(@PathVariable("currencyId") Long currencyId, @Valid @ModelAttribute("date") ExchangeRateDto dateDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("currencyId", currencyId);
            return "admin/rate-create";
        }
        exchangeRateService.createRate(currencyId, dateDto);
        return "redirect:/currency/"+currencyId;
    }
    @GetMapping("/rate/{dateId}/edit")
    public String editCurrencyForm(@PathVariable("dateId") Long dateId,Model model){
        ExchangeRateDto date = exchangeRateService.findByDateId(dateId);
        model.addAttribute("date", date);
        return "admin/rate-edit";

    }
    @PostMapping("/rate/{dateId}/edit")
    public String updateCurrency(@PathVariable("dateId") Long dateId,
                                 @Valid @ModelAttribute("date") ExchangeRateDto date,
                                 BindingResult result){
        if (result.hasErrors()){
            return "admin/rate-edit";
        }
        ExchangeRateDto dateDto = exchangeRateService.findByDateId(dateId);
        date.setDateId(dateId);
        date.setCurrency(dateDto.getCurrency());
        exchangeRateService.updateRate(date);
        return "redirect:/currency";
    }

    @GetMapping("/rate/{dateId}/delete")
    public String showDeleteConfirmation(@PathVariable("dateId") Long currencyId, Model model) {
        ExchangeRateDto date = exchangeRateService.findByDateId(currencyId);
        model.addAttribute("date", date);
        return "admin/rate-delete";
    }
    @PostMapping("/rate/{dateId}/delete")
    public String deleteCurrency(@PathVariable("dateId") Long currencyId) {
        exchangeRateService.delete(currencyId);
        return "redirect:/currency";
    }
}
