package com.example.demo.mapper;

import com.example.demo.dto.CurrencyDto;
import com.example.demo.model.Currency;

public class CurrencyMapper {

    public static Currency mapToCurrency(CurrencyDto currency){
        Currency currencyDto= Currency.builder()
                .id(currency.getId())
                .title(currency.getTitle())
                .url(currency.getUrl())
                .country(currency.getCountry())
                .updatedOn(currency.getUpdatedOn())
                .symbol(currency.getSymbol())
                .build();
        return currencyDto;

    }


    public static CurrencyDto mapToCurrencyDto(Currency currency){
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(currency.getId())
                .title(currency.getTitle())
                .url(currency.getUrl())
                .country(currency.getCountry())
                .updatedOn(currency.getUpdatedOn())
                .symbol(currency.getSymbol())
                .build();
        return currencyDto;
    }
}
