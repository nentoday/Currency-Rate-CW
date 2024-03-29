package com.example.demo.services;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List <CurrencyDto> findAllCurrencies();
    Currency saveCurrency(CurrencyDto currencyDto);

    CurrencyDto findByCurrencyId(long currencyId);

    void updateCurrency(CurrencyDto currency);

    void delete(Long currencyId);
    List<CurrencyDto> searchCurrency(String query);

    List<Currency> sortByTitle(String title);

    List<Currency> sortByCountry(String title);
}
