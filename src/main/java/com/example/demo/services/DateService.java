package com.example.demo.services;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;

import java.time.LocalDate;
import java.util.List;

public interface DateService {
    void createRate(Long currencyId, DateDto dateDto);

    Date findById(LocalDate rateDate, Long currencyId);
}
