package com.example.demo.services;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;

import java.time.LocalDate;
import java.util.List;

public interface DateService {
    void createRate(Long currencyId, DateDto dateDto);

    Date findById(LocalDate rateDate, Long currencyId);

//    List<Date> findByRateDate(Long currencyId, LocalDate startDate, LocalDate endDate);
//    List<Date> getCurrencyRatesByDateRange(Long currencyId, LocalDate startDate, LocalDate endDate) ;

    List <Date> findExchangeRate(Long currencyId, LocalDate startDate, LocalDate endDate);

//    Date date = getCurrencyRatesByDateRange.orElseThrow(() -> new NoSuchElementException("No value present for dateId: " + currencyId));


}
