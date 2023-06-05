package com.example.demo.services.impl;

import com.example.demo.dto.ExchangeRateDto;
import com.example.demo.model.Currency;
import com.example.demo.model.ExchangeRate;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.ExchangeRateRepository;
import com.example.demo.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.mapper.ExchangeRateMapper.mapToDate;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private CurrencyRepository currencyRepository;
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    public ExchangeRateServiceImpl(CurrencyRepository currencyRepository, ExchangeRateRepository exchangeRateRepository) {
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void createRate(Long currencyId, ExchangeRateDto dateDto) {
        Currency currency = currencyRepository.findById(currencyId).get();
        ExchangeRate date = mapToDate(dateDto);
        date.setCurrency(currency);
        exchangeRateRepository.save(date);
    }
    public List<ExchangeRate> findById(LocalDate today, Long currencyId) {
        List <ExchangeRate>dates= exchangeRateRepository.findByRateDateAndCurrency_Id(today,currencyId);
        return dates;
    }
    @Override
    public List<ExchangeRate> findExchangeRate(Long currencyId, LocalDate startDate, LocalDate endDate) {
        List<ExchangeRate> thisRate = exchangeRateRepository.findAllByCurrency_Id(currencyId);
        List<ExchangeRate> exchangeRate = thisRate.stream()
                .filter(date -> date.getRateDate().isAfter(startDate.minusDays(1)) && date.getRateDate().isBefore(endDate.plusDays(1)))
                .sorted(Comparator.comparing(ExchangeRate::getRateDate).reversed())
                .collect(Collectors.toList());
        return exchangeRate;
    }

    @Override
    public List<ExchangeRate> findAllDates(Long currencyId) {
        List<ExchangeRate> exchangeRate= exchangeRateRepository.findAllByCurrency_Id(currencyId);
        exchangeRate.sort(Comparator.comparing(ExchangeRate::getRateDate));
        return exchangeRate;

    }

    @Override
    public ExchangeRateDto findByDateId(Long dateId) {
        ExchangeRateDto date= exchangeRateRepository.findByDateId(dateId);
        return date;
    }

    @Override
    public void updateRate(ExchangeRateDto dateDto) {
        ExchangeRate date=mapToDate(dateDto);
        exchangeRateRepository.save(date);
    }

    @Override
    public void delete(Long dateId) {
        exchangeRateRepository.deleteById(dateId);
    }
}


