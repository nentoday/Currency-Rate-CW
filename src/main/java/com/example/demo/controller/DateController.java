package com.example.demo.controller;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;
import com.example.demo.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "rate-create";
    }

    @PostMapping("/rate/{currencyId}")
    public String createRate(@PathVariable("currencyId") Long currencyId, @ModelAttribute("date")DateDto dateDto,Model model){
        dateService.createRate(currencyId, dateDto);
        return "redirect:/currency/"+currencyId;
    }
}
