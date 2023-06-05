package com.example.demo.services;

import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.model.ExchangeRate;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateService {
    void createRate(Long currencyId, ExchangeRateDto dateDto);

    List <ExchangeRate> findById(LocalDate rateDate, Long currencyId);

    List <ExchangeRate> findExchangeRate(Long currencyId, LocalDate startDate, LocalDate endDate);


    List<ExchangeRate> findAllDates(Long currencyId);

    ExchangeRateDto findByDateId(Long dateId);

    void updateRate(ExchangeRateDto dateDto);

    void delete(Long dateId);
}
