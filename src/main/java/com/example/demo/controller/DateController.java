package com.example.demo.controller;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;
import com.example.demo.services.DateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.demo.mapper.DateMapper.mapToDate;

@Controller
public class DateController {
    private DateService dateService;
    @Autowired
    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping("/rate/{currencyId}/new")
    public String createRateForm(@PathVariable("currencyId") Long currencyId, Model model){
        Date date = new Date();
        model.addAttribute("currencyId", currencyId);
        model.addAttribute("date", date);
        return "admin/rate-create";
    }

    @PostMapping("/rate/{currencyId}")
    public String createRate(@PathVariable("currencyId") Long currencyId, @Valid @ModelAttribute("date")DateDto dateDto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("currencyId", currencyId);
            return "admin/rate-create";
        }
        dateService.createRate(currencyId, dateDto);
        return "redirect:/currency/"+currencyId;
    }
    @GetMapping("/rate/{dateId}/edit")
    public String editCurrencyForm(@PathVariable("dateId") Long dateId,Model model){
        DateDto date = dateService.findByDateId(dateId);
        model.addAttribute("date", date);
        return "admin/rate-edit";

    }
    @PostMapping("/rate/{dateId}/edit")
    public String updateCurrency(@PathVariable("dateId") Long dateId,
                                 @Valid @ModelAttribute("date") DateDto date,
                                 BindingResult result){
        if (result.hasErrors()){
            return "admin/rate-edit";
        }
        DateDto dateDto = dateService.findByDateId(dateId);
        date.setDateId(dateId);
        date.setCurrency(dateDto.getCurrency());
        dateService.updateRate(date);
        return "redirect:/currency";
    }

    @GetMapping("/rate/{dateId}/delete")
    public String showDeleteConfirmation(@PathVariable("dateId") Long currencyId, Model model) {
        DateDto date = dateService.findByDateId(currencyId);
        model.addAttribute("date", date);
        return "admin/rate-delete";
    }
    @PostMapping("/rate/{dateId}/delete")
    public String deleteCurrency(@PathVariable("dateId") Long currencyId) {
        dateService.delete(currencyId);
        return "redirect:/currency";
    }
}
