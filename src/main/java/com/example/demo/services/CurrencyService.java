package com.example.demo.services;

import com.example.demo.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List <CurrencyDto> findAllCurrencies();
}
