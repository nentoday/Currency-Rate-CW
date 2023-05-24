package com.example.demo.services;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;

import java.util.List;

public interface CurrencyService {
    List <CurrencyDto> findAllCurrencies();
    Currency saveCurrency(CurrencyDto currencyDto);

    CurrencyDto findByClubId(long currencyId);

    void updateCurrency(CurrencyDto currency);
}
