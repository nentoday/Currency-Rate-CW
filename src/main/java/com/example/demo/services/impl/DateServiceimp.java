package com.example.demo.services.impl;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.dto.DateDto;
import com.example.demo.model.Currency;
import com.example.demo.model.Date;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.DateRepository;
import com.example.demo.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.mapper.DateMapper.mapToDate;

@Service
public class DateServiceimp implements DateService {
    private CurrencyRepository currencyRepository;
    private DateRepository dateRepository;
    @Autowired
    public DateServiceimp(CurrencyRepository currencyRepository, DateRepository dateRepository) {
        this.currencyRepository = currencyRepository;
        this.dateRepository = dateRepository;
    }

    @Override
    public void createRate(Long currencyId, DateDto dateDto) {
        Currency currency = currencyRepository.findById(currencyId).get();
        Date date = mapToDate(dateDto);
        date.setCurrency(currency);
        dateRepository.save(date);
    }


    public Date findById(LocalDate today, Long currencyId) {
        Date dates=dateRepository.findByRateDateAndCurrency_Id(today,currencyId).get();
        return dates;
    }


}
