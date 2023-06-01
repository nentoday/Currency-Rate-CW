package com.example.demo.services.impl;

import com.example.demo.dto.DateDto;
import com.example.demo.model.Currency;
import com.example.demo.model.Date;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.DateRepository;
import com.example.demo.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
    public List<Date> findById(LocalDate today, Long currencyId) {
        List <Date>dates=dateRepository.findByRateDateAndCurrency_Id(today,currencyId);
        return dates;
    }
    @Override
    public List<Date> findExchangeRate(Long currencyId, LocalDate startDate, LocalDate endDate) {
        List<Date> thisRate = dateRepository.findAllByCurrency_Id(currencyId);
        List<Date> exchangeRate = new ArrayList<>();
        for (int i = 0; i < thisRate.size(); i++) {
            Date date = thisRate.get(i);
            if (date.getRateDate().isAfter(startDate) && date.getRateDate().isBefore(endDate)||date.getRateDate().equals(startDate)||date.getRateDate().equals(endDate)) {
                exchangeRate.add(date);
            }
        }
        exchangeRate.sort(Comparator.comparing(Date::getRateDate));
        return exchangeRate;
    }
}


