package com.example.demo.services.impl;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Date;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.services.CurrencyService;
import com.example.demo.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.mapper.CurrencyMapper.mapToCurrency;
import static com.example.demo.mapper.CurrencyMapper.mapToCurrencyDto;

@Service
public class Currencyserviceimpl implements CurrencyService {
    private CurrencyRepository currencyRepository;
    @Autowired
    public Currencyserviceimpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDto> findAllCurrencies() {
        List <Currency> currencies = currencyRepository.findAll();
        return currencies.stream().map((currency) -> mapToCurrencyDto(currency)).collect(Collectors.toList());
    }

    @Override
    public Currency saveCurrency(CurrencyDto currencyDto) {
        Currency currency=mapToCurrency(currencyDto);
        return currencyRepository.save(currency);
    }

    @Override
    public CurrencyDto findByCurrencyId(long currencyId) {
        Currency currency=currencyRepository.findById(currencyId).get();
        return mapToCurrencyDto(currency);
    }

    @Override
    public void updateCurrency(CurrencyDto currencyDto) {
        Currency currency= mapToCurrency(currencyDto);
        currencyRepository.save(currency);
    }

    @Override
    public void delete(Long currencyId) {
        currencyRepository.deleteById(currencyId);
    }

    @Override
    public List<CurrencyDto> searchCurrency(String query) {
        List<Currency> currencies = currencyRepository.searchCurrency(query);
        if (currencies.isEmpty()) {
            throw new DataNotFoundException("Please enter right currency");
        }
        return currencies.stream()
                .map(currency -> mapToCurrencyDto(currency))
                .collect(Collectors.toList());
    }

    @Override
    public List<Currency> sortByTitle(String title) {
        List <Currency> currencies = currencyRepository.findAll();
        List<Currency> sortedList = currencies.stream()
                .sorted(Comparator.comparing(Currency::getTitle))
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Currency> sortByCountry(String title) {
        List <Currency> currencies = currencyRepository.findAll();
        List<Currency> sortedList = currencies.stream()
                .sorted(Comparator.comparing(Currency::getCountry))
                .collect(Collectors.toList());
        return sortedList;
    }

}