package com.example.demo.services;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Date;

import java.time.LocalDate;
import java.util.List;

public interface DateService {
    void createRate(Long currencyId, DateDto dateDto);

    List <Date> findById(LocalDate rateDate, Long currencyId);

    List <Date> findExchangeRate(Long currencyId, LocalDate startDate, LocalDate endDate);


    List<Date> findAllDates(Long currencyId);

    DateDto findByDateId(Long dateId);

    void updateRate(DateDto dateDto);

    void delete(Long dateId);
}
